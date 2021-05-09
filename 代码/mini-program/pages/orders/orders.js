const tool = require("../../utils/tools.js")
Page({

  data: {
    filterIndex: 0,
    orderToShow: null,
    topNum: 0, // 滚动视图的进度
    // 服务器数据
    orderList: null,
  },

  filter(e) {
    const filterIndex = e.currentTarget.dataset.index;
    if (filterIndex == this.data.filterIndex) {
      return;
    }
    this.doFilter(filterIndex);
  },

  doFilter(filterIndex) {
    const orderList = this.data.orderList;
    if (orderList) {
      let orderToShow = [];
      if (filterIndex == 0) {
        orderToShow = orderList;
      } else if (filterIndex == 1) {
        for (let order of orderList) {
          if (order.state == 0) {
            orderToShow.push(order);
          }
        }
      } else if (filterIndex == 2) {
        for (let order of orderList) {
          if (order.state == 1) {
            orderToShow.push(order);
          }
        }
      } else if (filterIndex == 3) {
        for (let order of orderList) {
          if (order.state == 2) {
            orderToShow.push(order);
          }
        }
      }
      if (orderToShow.length == 0) {
        orderToShow = null;
      }
      this.setData({
        orderToShow: orderToShow,
      })
    }
    this.setData({
      filterIndex: filterIndex,
      topNum: 0
    })
  },

  jumpToOrderDetail(e) {
    wx.setStorageSync('order', this.data.orderToShow[e.currentTarget.dataset.index]);
    wx.navigateTo({
      url: '../orderDetail/orderDetail',
    })
  },

  jumpToRemarkDetail(e) {
    wx.request({
      url: wx.getStorageSync('ip_address') + '/remark/orderId/' + this.data.orderToShow[e.currentTarget.dataset.index].id,
      success: (res) => {
        if (res.data.code == 0) {
          let remark = res.data.data;
          if (remark) {
            wx.navigateTo({
              url: '../remarkDetail/remarkDetail?remark=' + JSON.stringify(remark)
            })
          } else {
            wx.navigateTo({
              url: '../sorry/sorry?info=未找到评价',
            })
          }
        } else {
          tool.busy();
        }
      },
      fail: function () {
        tool.busy();
      }
    })
  },


  jumpToRemark(e) {
    const index = e.currentTarget.dataset.index;
    wx.setStorageSync('order', this.data.orderToShow[index]);
    wx.navigateTo({
      url: '../remark/remark',
    })
  },

  onLoad() {
    const that = this;
    // 请求服务器获取订单
    wx.request({
      url: wx.getStorageSync('ip_address') + '/order/' + wx.getStorageSync('userPhone'),
      success: function (res) {
        if (res.data.code == 0) {
          let orderList = res.data.data;
          for (let order of orderList) {
            order.date = tool.dateFormat("YYYY-mm-dd HH:MM", new Date(order.date))
            order.content = JSON.parse(order.content);
            order.foodCount = order.content.length;
          }
          that.setData({
            orderList: orderList,
            orderToShow: orderList.length > 0 ? orderList : null
          })
          that.doFilter(that.data.filterIndex);
        } else {
          tool.busy();
        }
      },
      fail: function () {
        tool.busy();
      }
    })
  },

})