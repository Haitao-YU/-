package ltd.linqiu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class String2Date {

    public static Date parse(String s) {
        Date ret = null;
        try {
            ret = new SimpleDateFormat("yyyy-MM-dd").parse(s);
        } catch (Exception e1) {
            try {
                ret = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
            } catch (Exception e2) {
                try {
                    ret = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(s);
                } catch (Exception e3) {
                    try {
                        ret = new SimpleDateFormat("yyyy-MM-dd HH").parse(s);
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
            }
        }
        if (ret == null) {
            throw new RuntimeException("字符初转Date失败！");
        }
        return ret;
    }
}
