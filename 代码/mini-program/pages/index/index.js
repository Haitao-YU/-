const tool = require("../../utils/tools.js");

Page({
  data: {
    // 服务器数据
    carouselFigures: null, // 轮播图
    userPhone: null, // 用户手机号
  },

  // 工具函数,检查是否登录
  checkLogIf() {
    if (this.data.userPhone == null) {
      tool.msg("请先授权登录");
      return false;
    }
    return true;
  },

  // 获取用户授权
  getPhoneNumber(e) {
    const that = this;
    if (this.data.userPhone != null) {
      wx.showModal({
        title: "退出账号",
        content: "是否登出你的账号 18844114670",
        cancelText: "取消",
        confirmText: "确定",
        success(res) {
          if (res.confirm) {
            wx.removeStorageSync('userPhone');
            that.setData({
              userPhone: null
            })
          }
        }
      })
    } else {
      wx.login({
        success: res => {
          wx.request({
            url: wx.getStorageSync('ip_address') + '/user/oauth',
            data: {
              'encryptedData': e.detail.encryptedData,
              'iv': e.detail.iv,
              'codes': res.code
            },
            withCredentials: true,
            method: 'GET',
            header: {
              'content-type': 'application/json'
            },
            success: function (res) {
              let phone = res.data.phoneNumber;
              if (phone == undefined) {
                tool.msg("请重试");
              } else {
                wx.setStorageSync('userPhone', phone);
                that.setData({
                  userPhone: phone
                })
              }
            },
            fail: function () {
              tool.msg("请重试");
            }
          })
        }
      })
    }
  },

  // 扫码点餐
  jumpToMenu() {
    if (!this.checkLogIf()) {
      return;
    }
    wx.scanCode({
      onlyFromCamera: true,
      success(res) {
        const tableId = res.result;
        wx.request({
          url: wx.getStorageSync('ip_address') + '/table/' + tableId,
          method: "GET",
          success(res) {
            if (res.data.code == 0) {
              const table = res.data.data;
              if (table.state == 0) {
                wx.navigateTo({
                  url: '../menu/menu?tableid=' + tableId,
                })
              } else if (table.state == 1) {
                wx.request({
                  url: wx.getStorageSync('ip_address') + '/order/id/' + table.orderId,
                  method: "GET",
                  success: (res) => {
                    const order = res.data.data;
                    if (order.phone == wx.getStorageSync('userPhone')) {
                      order.content = JSON.parse(order.content);
                      wx.setStorageSync('order', order);
                      wx.navigateTo({
                        url: '../orderDetail/orderDetail',
                      })
                    } else {
                      wx.navigateTo({
                        url: '../sorry/sorry?info=' + table.id + '号桌存在未完成的订单',
                      })
                    }
                  }
                })
              } else if (table.state == 2) {
                wx.navigateTo({
                  url: '../sorry/sorry?info=' + table.id + '号桌已经停止使用',
                })
              }
            } else {
              tool.msg("餐桌二维码错误");
            }
          }
        })
      },
    })

  },

  // 模拟获取用户授权
  fake_getPhoneNumber() {
    const that = this;
    if (this.data.userPhone != null) {
      wx.showModal({
        title: "退出账号",
        content: "是否登出你的账号 18844114670",
        cancelText: "取消",
        confirmText: "确定",
        success(res) {
          if (res.confirm) {
            wx.removeStorageSync('userPhone');
            that.setData({
              userPhone: null
            })
          }
        }
      })
    } else {
      wx.showModal({
        title: "微信授权",
        content: "申请获得你微信绑定的手机号",
        cancelText: "拒绝",
        confirmText: "允许",
        success(res) {
          if (res.confirm) {
            wx.setStorageSync('userPhone', "18844114670");
            that.setData({
              userPhone: "18844114670"
            })
          }
        }
      })
    }
  },

  // 我的订单
  jumpToOrder() {
    if (!this.checkLogIf()) {
      return;
    }
    wx.navigateTo({
      url: '../orders/orders',
    })
  },

  // 排队预约
  jumpToLine() {
    if (!this.checkLogIf()) {
      return;
    }
    wx.navigateTo({
      url: '../line/line',
    })
  },

  // 餐厅评价
  jumpToRemarks() {
    if (!this.checkLogIf()) {
      return;
    }
    wx.navigateTo({
      url: '../remarks/remarks',
    })
  },

  onLoad() {
    let userPhone = wx.getStorageSync('userPhone');
    if (userPhone != undefined && userPhone != null && userPhone != '') {
      this.setData({
        userPhone: userPhone
      })
    }
    const that = this;
    // 获取轮播图
    wx.request({
      url: wx.getStorageSync('ip_address') + '/carouselFigure/all',
      method: 'GET',
      success: function (res) {
        if (res.data.code == 0) {
          that.setData({
            carouselFigures: res.data.data
          })
        } else {
          tool.busy();
        }
      },
      fail: function () {
        tool.busy();
      }
    })
    // wx.login({
    //   success: res => {
    //     console.log(res);
    //   }
    // })
  }
})