Page({

  data: {
    info: "该餐桌存在未完成的订单"
  },

  onLoad: function (options) {
    this.setData({
      info: options.info ? options.info : null
    })
  }
})