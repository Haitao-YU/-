const tool = require("../../utils/tools.js");

Page({
  data: {
    // 服务器获得的数据
    menu: null, // [{分类1,内容:[food1,food2,food3]}, {分类2,内容:[food4,food5,food6,...]}, ...]
    storeInfoList: null,
    // hidden指示变量
    showCoverLayer: true, // 是否显示遮盖层
    showNOfDiners: true, // 是否显示选择就餐人数页面
    showCartPage: false, // 是否显示购物车页面
    // 就餐信息
    tableId: null,
    nOfDiners: null, // 确定的用餐人数
    nOfDiners_: null, // 选择用餐人数时的临时变量
    // 菜单相关数据
    currentLeftIndex: 0, // 被点击的左侧菜单项
    pressingMinusBtnIndex: -1, // 被点击的右侧菜单项的减按钮索引，用于模拟hover动画
    pressingPlusBtnIndex: -1, // 被点击的右侧菜单项的加按钮索引
    foodCounterList: undefined, // 各餐品的计数,为大小与menu相同的二维数组,页面加载时初始化为0
    badgeCountList: undefined, // 左侧徽章计数数组
    hover: false, // 用于‘选好了’按钮的动画标志位,表示正在触摸该按钮
    topNum: 0, // 用于标志右侧滚动视图的滚动进度
    // 购物车相关数据
    numOfSelectedFood: 0, // 购物车中食物的总件数
    sum: 0, // 总计金额
    cartList: [], // 购物车内容格式 [[index1,index2,num],[index1,index2,num],...]
  },

  // 选择就餐人数
  chooseNOfDiners: function (e) {
    this.setData({
      nOfDiners_: e.currentTarget.dataset.nofdiners
    })
  },

  // 确定就餐人数
  submitNOfDiners: function () {
    if (this.data.nOfDiners_ != null) {
      this.setData({
        nOfDiners: this.data.nOfDiners_,
        showCoverLayer: false,
        showNOfDiners: false
      })
    }
  },

  // 选择就餐人数时点击取消
  cancel: function () {
    wx.navigateBack({
      delta: 1,
    })
  },

  // 工具函数: 计算获得购物车内容
  updateCartList: function () {
    const foodCounterList = this.data.foodCounterList;
    var cartList = new Array();
    for (let i = 0; i < foodCounterList.length; i++) {
      for (let j = 0; j < foodCounterList[i].length; j++) {
        let num = foodCounterList[i][j];
        if (num > 0) {
          cartList.push([i, j, num])
        }
      }
    }
    this.setData({
      cartList: cartList
    })
  },

  // 工具函数: 生成订单内容
  generateOrderContent() {
    var ret = [];
    const foodCounterList = this.data.foodCounterList;
    const menu = this.data.menu;
    for (let i = 0; i < foodCounterList.length; i++) {
      for (let j = 0; j < foodCounterList[i].length; j++) {
        let num = foodCounterList[i][j];
        if (num > 0) {
          ret.push([menu[i].foodList[j].id, num]);
        }
      }
    }
    return ret;
  },

  // 跳转至支付
  jumpToOrder() {
    if (this.data.numOfSelectedFood > 0) {
      this.updateCartList();
      const cartList = this.data.cartList;
      const menu = this.data.menu;
      const nOfDiners = this.data.nOfDiners;
      const tableId = this.data.tableId;
      // 生成content
      let content = []
      for (let item of cartList) {
        let food = menu[item[0]].foodList[item[1]];
        // 添加count属性
        food.count = item[2];
        // 删除无用的属性
        food.monthlySales = undefined;
        food.typeId = undefined;
        food.typeName = undefined;
        food.state = undefined;
        content.push(food);
      }
      wx.setStorageSync('content', content);
      wx.navigateTo({
        url: "../pay/pay?nofdiners=" + nOfDiners + "&tableid=" + tableId + "&mealfee=" + this.data.storeInfoList[7].value,
      })
    }
  },

  // 左侧分类菜单点击事件
  handleLeftMenuTap: function (e) {
    const index = e.currentTarget.dataset.index;
    this.setData({
      currentLeftIndex: index,
      topNum: 0
    })
  },

  // 加减按钮点击事件,用于计数餐品,更新页面内容,更新购物车等
  handleBtnTap: function (e) {
    const index = e.currentTarget.dataset.para.index;
    const tag = e.currentTarget.dataset.para.tag;
    const currentLeftIndex = this.data.currentLeftIndex;
    const price = this.data.menu[currentLeftIndex].foodList[index].price;
    let numOfSelectedFood = this.data.numOfSelectedFood;
    let sum = this.data.sum;
    let foodCounterList = this.data.foodCounterList;
    let badgeCountList = this.data.badgeCountList;
    if (tag === 'minus') {
      if (foodCounterList[currentLeftIndex][index] > 0) {
        foodCounterList[currentLeftIndex][index] -= 1;
        badgeCountList[currentLeftIndex] -= 1;
        numOfSelectedFood -= 1;
        sum -= price;
      }
    } else if (tag == 'plus') {
      foodCounterList[currentLeftIndex][index] += 1;
      badgeCountList[currentLeftIndex] += 1;
      numOfSelectedFood += 1;
      sum += price;
    }
    this.setData({
      foodCounterList: foodCounterList,
      badgeCountList: badgeCountList,
      numOfSelectedFood: numOfSelectedFood,
      sum: sum
    })
  },

  // 购物车内加减按钮点击事件
  handleCartBtnTap: function (e) {
    const index = e.currentTarget.dataset.para.index;
    const tag = e.currentTarget.dataset.para.tag;
    let foodCounterList = this.data.foodCounterList;
    let cartList = this.data.cartList;
    let badgeCountList = this.data.badgeCountList;
    let numOfSelectedFood = this.data.numOfSelectedFood;
    let sum = this.data.sum;
    if (tag === 'minus') {
      cartList[index][2] -= 1;
      foodCounterList[cartList[index][0]][cartList[index][1]] -= 1;
      badgeCountList[cartList[index][0]] -= 1;
      numOfSelectedFood -= 1;
      sum -= this.data.menu[cartList[index][0]].foodList[cartList[index][1]].price;
    } else if (tag === 'plus') {
      cartList[index][2] += 1;
      foodCounterList[cartList[index][0]][cartList[index][1]] += 1;
      badgeCountList[cartList[index][0]] += 1;
      numOfSelectedFood += 1;
      sum += this.data.menu[cartList[index][0]].foodList[cartList[index][1]].price;
    }
    if (cartList[index][2] === 0) {
      let newCartList = new Array();
      for (let item of cartList) {
        if (item[2] === 0)
          continue;
        newCartList.push(item);
      }
      this.setData({
        cartList: newCartList,
      })
    } else {
      this.setData({
        cartList: cartList,
      })
    }
    this.setData({
      foodCounterList: foodCounterList,
      badgeCountList: badgeCountList,
      numOfSelectedFood: numOfSelectedFood,
      sum: sum
    })
  },

  // 清空购物车
  clearCart: function () {
    let cartList = [];
    let sum = 0;
    let numOfSelectedFood = 0;
    let foodCounterList = this.data.foodCounterList;
    let badgeCountList = this.data.badgeCountList;
    for (let i = 0; i < foodCounterList.length; i++) {
      foodCounterList[i].fill(0);
    }
    badgeCountList.fill(0);
    this.setData({
      cartList: cartList,
      sum: sum,
      numOfSelectedFood: numOfSelectedFood,
      foodCounterList: foodCounterList,
      badgeCountList: badgeCountList
    })
  },

  // 点击购物车图标
  handleTapCartImage: function () {
    const showCartPage = this.data.showCartPage;
    if (showCartPage) {
      this.setData({
        showCartPage: false,
        showCoverLayer: false
      })
    } else if (this.data.numOfSelectedFood > 0) {
      this.updateCartList();
      this.setData({
        showCartPage: true,
        showCoverLayer: true
      });
    }
  },

  // 点击阴影遮盖层,关闭购物车页面
  handleCloseCartPage: function () {
    if (this.data.showNOfDiners) return;
    this.setData({
      showCartPage: false,
      showCoverLayer: false
    });
  },

  // 加减按钮点击动画,触摸时
  handlePressBtn: function (e) {
    const para = e.currentTarget.dataset.para;
    if (para.tag === 'minus') {
      this.setData({
        pressingMinusBtnIndex: para.index
      });
    } else if (para.tag == 'plus') {
      this.setData({
        pressingPlusBtnIndex: para.index
      });
    }
  },
  // 加减按钮点击动画,抬起时
  handleBtnTouchEnd: function () {
    this.setData({
      pressingMinusBtnIndex: -1,
      pressingPlusBtnIndex: -1,
    });
  },
  // '选好了'按钮点击动画,触摸时
  handleHoverStart: function () {
    const numOfSelectedFood = this.data.numOfSelectedFood;
    if (numOfSelectedFood > 0) {
      this.setData({
        hover: true
      })
    }
  },
  // '选好了'按钮点击动画,抬起时
  handleHoverEnd: function () {
    this.setData({
      hover: false
    })
  },

  // 页面加载
  onLoad: function (options) {
    const tableId = options.tableid;
    if (tableId == undefined || tableId == null || tableId == '') {
      tool.msg("桌号选择异常");
    }
    this.setData({
      tableId: tableId
    })
    var that = this;
    wx.request({
      url: wx.getStorageSync('ip_address') + '/storeInfo/all',
      method: 'GET',
      success: function (res) {
        if (res.data.code == 0) {
          that.setData({
            storeInfoList: res.data.data
          })
        } else {
          tool.busy();
        }
      },
      fail: function () {
        tool.busy();
      }
    })
    wx.request({
      url: wx.getStorageSync('ip_address') + '/food/front/menu',
      method: 'GET',
      success: function (res) {
        if (res.data.code == 0) {
          var menu = res.data.data;
          var foodCounterList = new Array();
          var badgeCountList = new Array();
          for (var i = 0; i < menu.length; i++) {
            var foodListCounterList = new Array();
            for (var j = 0; j < menu[i].foodList.length; j++) {
              foodListCounterList.push(0);
            }
            foodCounterList.push(foodListCounterList);
            badgeCountList.push(0);
          }
          that.setData({
            menu: menu,
            foodCounterList: foodCounterList,
            badgeCountList: badgeCountList
          })
        } else {
          tool.busy();
        }
      },
      fail: function () {
        tool.busy();
      }
    })
  }

})