const tool = require("../../utils/tools.js");
Page({
  data: {
    remark: null
  },

  tapRemark(e) {
    const images = e.currentTarget.dataset.images;
    if (images) {
      wx.previewImage({
        urls: images
      })
    }
  },
  
  onLoad(options) {
    let remark = JSON.parse(options.remark);
    remark.date = tool.dateFormat("YYYY-mm-dd HH:MM", new Date(remark.date))
    remark.images = JSON.parse(remark.images);
    this.setData({
      remark: remark
    })

  },
})