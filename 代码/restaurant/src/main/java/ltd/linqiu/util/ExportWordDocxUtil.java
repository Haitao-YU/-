package ltd.linqiu.util;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExportWordDocxUtil {

    /**
     * 根据模板生成新word文档
     * 判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
     * @param inputUrl  模板存放地址
     * @param outputUrl 新文档存放地址
     * @param textMap   需要替换的信息集合
     * @return 成功返回true, 失败返回false
     */
    public static boolean changWord(String inputUrl, String outputUrl, Map<String, Object> textMap) {

        //模板转换默认成功
        boolean changeFlag = true;
        try {
            //获取docx解析对象
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));

            //解析替换文本段落对象
            Iterator<XWPFParagraph> iterator = document.getParagraphsIterator();
            XWPFParagraph para;
            while (iterator.hasNext()) {
                para = iterator.next();
                replaceInPara(para, textMap);
            }

            //解析替换表格对象
            replaceInTable(document, textMap);

            //生成新的word
            File file = new File(outputUrl);
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                if (!fileParent.mkdirs()) {
                    throw new RuntimeException("创建目录失败!");
                }
                if (!file.createNewFile()) {
                    throw new RuntimeException("生成文件失败!");
                }
            }
            FileOutputStream stream = new FileOutputStream(file);
            document.write(stream);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
            changeFlag = false;
        }

        return changeFlag;

    }


    /**
     * 替换段落里面的变量
     * @param para   要替换的段落
     * @param params 参数
     */
    private static void replaceInPara(XWPFParagraph para, Map<String, Object> params) {
        List<XWPFRun> runs;
        Matcher matcher;
        if (matcher(para.getParagraphText()).find()) {
            runs = para.getRuns();

            // XWPFRun是XWPFDocument中的一段文本对象（就是一段文字）
            // 变量合并逻辑(有的分段会出问题 会把${XXX} 分成3段  ${   XXX   }  这样就会导致变量匹配不到的问题  下面循环把分段错的内容重新合并 再设置到原来的文本对象中)
            //合并逻辑
            for (int i = 0; i < runs.size(); i++) {
                String text0 = runs.get(i).getText(runs.get(i).getTextPosition());
                // 有时候还会把${ 分成 $ 和 {  或者空格$
                if (text0 != null && (text0.startsWith("$") || text0.startsWith(" $"))) {
                    //记录分隔符中间跨越的runs数量，用于字符串拼接和替换
                    int num = 0;
                    int j = i + 1;
                    for (; j < runs.size(); j++) {
                        String text1 = runs.get(j).getText(runs.get(j).getTextPosition());
                        // 这里  }后面可能会带字符  所以要多判断一层 以}结尾 或者以}开头
                        if (text1 != null && (text1.endsWith("}") || text1.startsWith("}") || text1.startsWith(" }"))) {
                            num = j - i;
                            break;
                        }
                    }
                    if (num != 0) {
                        //num!=0说明找到了${}配对，需要替换
                        StringBuilder newText = new StringBuilder();
                        for (int s = i; s <= i + num; s++) {
                            String text2 = runs.get(s).getText(runs.get(s).getTextPosition());
                            newText.append(text2);
                            runs.get(s).setText(null, 0);
                        }
                        runs.get(i).setText(newText.toString(), 0);
                        //重新定义遍历位置，跳过设置为null的位置
                        i = j + 1;
                    }
                }
            }


            // 变量替换逻辑
            for (XWPFRun run : runs) {
                String runText = run.toString();
                matcher = matcher(runText);
//                System.out.println("==========" + runText);
                if (matcher.find()) {
//                    System.out.println("匹配成功");
                    while ((matcher = matcher(runText)).find()) {
                        // 有时候 ${XXX} 可能被分割成 $  {  空格 XXX 空格 }  所以这时候通过XXX去map获取值时可能会获取到null
                        // 这里做个trim() 操作 去除前后空格
                        runText = matcher.replaceFirst(String.valueOf(params.get(matcher.group(1).trim())));
                    }
                    //设置替换值
                    if (null != runText) {
                        run.setText(runText, 0);
                    } else {
                        run.setText("", 0);
                    }
                }
            }
        }
    }

    /**
     * 正则匹配字符串
     * @param str 匹配的字符串
     */
    private static Matcher matcher(String str) {
        Pattern pattern = Pattern.compile("\\$\\{(.+?)}", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(str);
    }


    /**
     * 替换表格里面的变量
     * @param doc    要替换的文档
     * @param params 参数
     */
    private static void replaceInTable(XWPFDocument doc, Map<String, Object> params) {
        Iterator<XWPFTable> iterator = doc.getTablesIterator();
        XWPFTable table;
        List<XWPFTableRow> rows;
        List<XWPFTableCell> cells;
        List<XWPFParagraph> paras;
        while (iterator.hasNext()) {
            table = iterator.next();
            rows = table.getRows();
            for (XWPFTableRow row : rows) {
                cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    paras = cell.getParagraphs();
                    for (XWPFParagraph para : paras) {
                        replaceInPara(para, params);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        //模板文件地址
        String inputUrl = "C:\\Users\\Tyler\\Desktop\\1.docx";
        //新生成的word文件
        String outputUrl = "C:\\Users\\Tyler\\Desktop\\2.docx";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "余海涛");
        map.put("grade", "17");
        if (!ExportWordDocxUtil.changWord(inputUrl, outputUrl, map)) {
            throw new RuntimeException("转换文档失败!");
        }
    }
}
