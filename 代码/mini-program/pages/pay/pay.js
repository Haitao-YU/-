const tool = require("../../utils/tools.js");

Page({
  data: {
    showPay: false,
    showCover: false,
    foodTotal: null, // 食物的总价格
    otherTotal: null, // 其他费用总价格
    // 订单内容
    content: null,
    nOfDiners: null,
    mealFee: null,
    tableId: null,
    sum: null,
    remark: ''
  },

  // 点击立即支付
  handlePay: function () {
    const that = this;
    wx.showLoading({
      title: '处理中',
    })
    setTimeout(function () {
      wx.hideLoading();
      that.setData({
        showPay: true,
        showCover: true
      })
    }, 300)
  },

  // 关闭支付
  closePay: function () {
    this.setData({
      showPay: false,
      showCover: false
    })
  },

  // 支付并提交订单
  pay: function () {
    const that = this;
    this.closePay();
    wx.request({
      url: wx.getStorageSync('ip_address') + "/order/add",
      method: 'post',
      data: {
        phone: wx.getStorageSync('userPhone'),
        nOfDiners: that.data.nOfDiners,
        mealFee: that.data.mealFee,
        tableId: that.data.tableId,
        sum: that.data.sum,
        remark: that.data.remark,
        content: JSON.stringify(that.data.content)
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      success(res) {
        if (res.data.code == 0) {
          tool.msg("支付成功", "success");
          setTimeout(function () {
            wx.reLaunch({
              url: '../index/index',
            })
          }, 1000)
        } else {
          tool.busy()
        }
      },
      fail() {
        tool.busy();
      }
    })
  },

  // 输入备注
  inputRemark: function (e) {
    this.setData({
      remark: e.detail.value
    })
  },

  // 页面加载
  onLoad: function (options) {
    const mealFee = Number(options.mealfee);
    const nOfDiners = Number(options.nofdiners);
    const tableId = String(options.tableid);
    const content = wx.getStorageSync('content');
    wx.removeStorageSync('content');
    let foodTotal = 0;
    let otherTotal = mealFee * nOfDiners;
    for (const food of content) {
      foodTotal += food.price * food.count;
    }
    this.setData({
      nOfDiners: nOfDiners,
      tableId: tableId,
      mealFee: mealFee,
      content: content,
      foodTotal: foodTotal.toFixed(2),
      otherTotal: otherTotal.toFixed(2),
      sum: (foodTotal + otherTotal).toFixed(2)
    })
  }

})