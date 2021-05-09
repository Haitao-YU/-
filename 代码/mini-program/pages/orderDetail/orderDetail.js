Page({
  data: {
    foodTotal: null, // 食物的总价格
    otherTotal: null, // 其他费用总价格
    order: null,
    // 订单内容
    content: null,
    nOfDiners: null,
    tableId: null,
    sum: null,
    remark: null
  },

  onLoad: function () {
    const order = wx.getStorageSync('order');
    wx.removeStorageSync('order');
    let foodTotal = 0;
    let otherTotal = 0;
    for (let food of order.content) {
      foodTotal += food.price * food.count;
    }
    this.setData({
      order: order,
      foodTotal: foodTotal,
      otherTotal: otherTotal,
    })
  }
})