// 价格输入 oninput="value=moneyInput(value)"
function moneyInput(value) {
    //修复第一个字符是小数点 的情况.
    let fa = '';
    if (value !== '' && value.substr(0, 1) === '.') {
        value = "";
    }
    value = value.replace(/^0*(0\.|[1-9])/, '$1'); //解决 粘贴不生效
    value = value.replace(/[^\d.]/g, ""); //清除“数字”和“.”以外的字符
    value = value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的
    value = value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
    value = value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'); //只能输入两个小数
    if (value.indexOf(".") < 0 && value !== "") { //以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
        if (value.substr(0, 1) === '0' && value.length === 2) {
            value = value.substr(1, value.length);
        }
    }
    value = fa + value;
    return value;
}