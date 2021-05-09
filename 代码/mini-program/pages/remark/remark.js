const tool = require("../../utils/tools.js");
Page({
  data: {
    maxImageCount: 5, // 最大可上传图片数目

    order: null,
    score: 5,
    text: '',
    images: [],
  },

  // 评分
  getScore(e) {
    this.setData({
      score: e.currentTarget.dataset.score
    })
  },

  // 文字评价
  getText(e) {
    this.setData({
      text: e.detail.value
    })
  },

  getImage() {
    let images = this.data.images;
    const maxImageCount = this.data.maxImageCount;
    const count = maxImageCount - images.length;
    if (count <= 0) {
      return;
    }
    wx.chooseImage({
      count: count,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success: (res) => {
        let images = this.data.images;
        for (let item of res.tempFilePaths) {
          images.push(item);
        }
        this.setData({
          images: images
        })
      }
    })
  },

  removeImage(e) {
    wx.showModal({
      title: '提示',
      content: '确认删除当前图片？',
      success: (res) => {
        if (res.confirm) {
          const index = e.currentTarget.dataset.index;
          let images = this.data.images;
          images.splice(index, 1);
          this.setData({
            images: images
          })
        }
      }
    })
  },

  // 提交清空当前值
  submit() {
    wx.request({
      url: wx.getStorageSync('ip_address') + "/remark/add",
      method: 'POST',
      data: {
        phone: wx.getStorageSync('userPhone'),
        orderId: this.data.order.id,
        score: this.data.score,
        text: this.data.text
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      success: (res) => {
        console.log(res);
        if (res.data.code == 0) {
          const images = this.data.images;
          if (images.length > 0) {
            const id = res.data.data;
            for (let image of images) {
              this.uploadImage(id, image);
            }
          }
          tool.msg("发布成功", "success");
          setTimeout(() => {
            let pages = getCurrentPages();
            let prevPage = pages[pages.length - 2];
            prevPage.onLoad();
            wx.navigateBack({
              delta: 1
            })
          }, 1000)
        } else {
          tool.busy();
        }
      },
      fail() {
        tool.busy();
      }
    })


  },

  uploadImage(id, filePath) {
    wx.uploadFile({
      url: wx.getStorageSync('ip_address') + '/remark/uploadImage',
      filePath: filePath,
      name: "file",
      formData: {
        id: id
      },
      header: {
        "Content-Type": "multipart/form-data"
      },
      fail() {
        util.busy();
      }
    });
  },

  onLoad() {
    const order = wx.getStorageSync('order');
    wx.removeStorageSync('order');
    this.setData({
      order: order
    })
  }
})