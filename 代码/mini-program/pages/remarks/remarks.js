const tool = require("../../utils/tools.js");
Page({

  data: {
    scrollTop: 0,
    filterItems: ['全部评价', '我的评价'],
    filterIndex: 0,
    remarkList: null,
    remarkMine: null
  },

  filter(e) {
    const filterIndex = e.currentTarget.dataset.index;
    if (filterIndex == this.data.filterIndex) {
      return;
    }
    if (filterIndex == 1 && this.data.remarkMine == null) {
      const remarkList = this.data.remarkList;
      const phone = wx.getStorageSync('userPhone');
      let remarkMine = [];
      for (let remark of remarkList) {
        if (remark.phone == phone) {
          remarkMine.push(remark);
        }
      }
      this.setData({
        remarkMine: remarkMine
      })
    }
    this.setData({
      filterIndex: filterIndex,
      scrollTop: 0
    })
  },

  goTop() {
    this.setData({
      scrollTop: 0
    })
  },

  tapRemark(e) {
    const images = e.currentTarget.dataset.images;
    if (images) {
      wx.previewImage({
        urls: images
      })
    }
  },

  onLoad() {
    // 请求服务器获取所有评价
    wx.request({
      url: wx.getStorageSync('ip_address') + '/remark/all',
      success: (res) => {
        if (res.data.code == 0) {
          let remarkList = res.data.data;
          for (let remark of remarkList) {
            remark.date = tool.dateFormat("YYYY-mm-dd HH:MM", new Date(remark.date))
            remark.images = JSON.parse(remark.images);
          }
          this.setData({
            remarkList: remarkList,
          })
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