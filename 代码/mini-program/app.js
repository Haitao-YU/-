App({
  onLaunch: function () {
    wx.setStorageSync('ip_address', 'http://www.linqiu.ltd');
    // wx.setStorageSync('ip_address','http://localhost');
  }
})