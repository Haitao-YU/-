const dateFormat = (fmt, date) => {
  let ret;
  const opt = {
    "Y+": date.getFullYear().toString(), // 年
    "m+": (date.getMonth() + 1).toString(), // 月
    "d+": date.getDate().toString(), // 日
    "H+": date.getHours().toString(), // 时
    "M+": date.getMinutes().toString(), // 分
    "S+": date.getSeconds().toString() // 秒
    // 有其他格式化字符需求可以继续添加，必须转化成字符串
  };
  for (let k in opt) {
    ret = new RegExp("(" + k + ")").exec(fmt);
    if (ret) {
      fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
    };
  };
  return fmt;
}

const busy = () => {
  wx.showToast({
    title: '服务器繁忙',
    icon: 'loading',
    duration: 1500
  })
}

const msg = (content, icon, success) => {
  wx.showToast({
    title: content,
    icon: icon == null ? 'none' : icon,
    duration: 1000,
    success: success
  })
}

module.exports = {
  busy: busy,
  msg: msg,
  dateFormat: dateFormat
}