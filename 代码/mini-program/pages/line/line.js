const tool = require("../../utils/tools.js");
Page({

  data: {
    timer_data: null, // 数据刷新定时器
    timer_wait: null, // 等待时间定时器
    waitTime: null, // 页面渲染等待的时间

    // 服务器获得的数据
    inline: false, // 该账号是否已经在队伍中
    optionList: null,
    optionIndex: null,
    lineInfos: null
  },

  // 点击选择餐桌类型按钮
  chooseMealsNumber(e) {
    const newIndex = e.currentTarget.dataset.index;
    if (this.data.inline || newIndex == this.data.optionIndex) {
      return;
    }
    this.requestLineInfo(this.data.optionList[newIndex]);
    this.setData({
      optionIndex: newIndex
    })
  },

  // 工具函数: 数据更新,向服务器请求排队信息
  requestLineInfo(mealsNumber) {
    const that = this;
    wx.request({
      url: wx.getStorageSync('ip_address') + '/line/infos/' + mealsNumber + '/' + wx.getStorageSync('userPhone'),
      method: "GET",
      success(res) {
        if (res.data.code == 0) {
          that.setData({
            lineInfos: res.data.data
          })
        } else {
          tool.busy();
        }
      },
      fail() {
        tool.busy();
      }
    })
  },

  // 工具函数:计算时间差
  tiemDispose: function (faultDate, completeTime) {
    let priorTime = Date.parse(new Date(faultDate));
    let nowTime = Date.parse(new Date(completeTime));
    let usedTime = nowTime - priorTime; //两个时间戳差
    let days = Math.floor(usedTime / (24 * 3600 * 1000)); // 计算天数
    let leave1 = usedTime % (24 * 3600 * 1000); //计算小时
    let hours = Math.floor(leave1 / (3600 * 1000));
    let leave2 = leave1 % (3600 * 1000); //计算分钟
    let minutes = Math.floor(leave2 / (60 * 1000));
    let leave3 = leave2 % (60 * 1000) // 计算秒数
    let second = Math.floor(leave3 / 1000);
    let time = (days > 0 ? (days + "天") : '') + (hours > 0 ? (hours + "小时") : '') + (minutes > 0 ? (minutes + "分") : '') + second + "秒"; // 拼接
    return time;
  },

  // 排队
  enqueue() {
    const that = this;
    const mealsNumber = that.data.optionList[that.data.optionIndex];
    that.startWaitTimer(new Date());
    wx.request({
      url: wx.getStorageSync('ip_address') + "/line/enqueue",
      method: 'POST',
      data: {
        phone: wx.getStorageSync('userPhone'),
        mealsNumber: mealsNumber,
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      success(res) {
        if (res.data.code == 0) {
          tool.msg("预约成功", "success");
          that.setData({
            inline: true
          })
          that.requestLineInfo(mealsNumber);
        } else {
          tool.msg("您已在队列中", "error");
        }
      },
      fail() {
        tool.busy();
      }
    })
  },

  // 取消排队
  cancel() {
    const that = this;
    wx.request({
      url: wx.getStorageSync('ip_address') + "/line/enqueue/cancel",
      method: 'POST',
      data: {
        phone: wx.getStorageSync('userPhone')
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      success(res) {
        if (res.data.code == 0) {
          tool.msg("取消成功", "success");
          clearInterval(that.data.timer_wait);
          that.setData({
            inline: false,
            waitTime: null
          })
          that.requestLineInfo(that.data.optionList[that.data.optionIndex]);
        } else {
          tool.busy();
        }
      },
      fail() {
        tool.busy();
      }
    })
  },

  startDataTimer() {
    const that = this;
    clearInterval(that.data.timer_data);
    that.data.timer_data = setInterval(function () {
      that.requestLineInfo(that.data.optionList[that.data.optionIndex]);
    }, 3000)
  },

  startWaitTimer(date) {
    const that = this;
    clearInterval(that.data.timer_wait);
    that.data.timer_wait = setInterval(function () {
      that.setData({
        waitTime: that.tiemDispose(date, new Date())
      })
    }, 1000)
  },

  onLoad: function (options) {
    const that = this;
    wx.showLoading({
      title: '加载中',
    })
    // 获取可选的排队类型
    wx.request({
      url: wx.getStorageSync('ip_address') + '/line/getMealsNumberOption',
      method: 'GET',
      success(res) {
        // 判断是否存在选项,若不存在则报服务器异常
        if (res.data.code == 0 && res.data.data && res.data.data.length > 0) {
          const optionList = res.data.data;
          that.setData({
            optionList: optionList
          })
          // 判断该用户是否已经在排队队伍中
          wx.request({
            url: wx.getStorageSync('ip_address') + '/line/' + wx.getStorageSync('userPhone'),
            method: "GET",
            success(rs) {
              // 开启数据更新计时器
              that.startDataTimer();
              if (rs.data.code == 0) {
                // 用户已在排队队列中
                const mealsNumber = rs.data.data.mealsNumber;
                let optionIndex = optionList.indexOf(mealsNumber);
                that.setData({
                  inline: true,
                  optionIndex: optionIndex
                });
                // 获取已在的队列的排队信息
                that.requestLineInfo(mealsNumber);
                // 开启计等待时长计时器
                that.startWaitTimer(rs.data.data.date);
              } else if (rs.data.code == 400) {
                // 用户还没排队
                that.setData({
                  inline: false,
                  optionIndex: 0
                });
                // 获取默认第一个选项的排队信息
                that.requestLineInfo(that.data.optionList[0]);
              } else {
                tool.busy();
              }
            },
            fail() {
              tool.busy();
            }
          })
        } else {
          tool.busy();
        }
      },
      fail() {
        tool.busy();
      }
    })
    wx.hideLoading();
  },

  onUnload() {
    //清除定时器
    clearInterval(this.data.timer_wait);
    clearInterval(this.data.timer_data);
  }
})