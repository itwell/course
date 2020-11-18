Validator = {
  require: function (value, text) {
    //如果输入的没有值就提示值不能为空
    if (Tool.isEmpty(value)) {
      Toast.warning(text + "不能为空");
      return false;
    } else {
      return true
    }
  },

  length: function (value, text, min, max) {
    //如果输入的没有值 也是可以提交表单的
    if (Tool.isEmpty(value)) {
      return true;
    }
    //如果输入的有值则指的长度必须在这个范围内
    if (!Tool.isLength(value, min, max)) {
      Toast.warning(text + "长度" + min + "~" + max + "位");
      return false;
    } else {
      return true
    }
  }
};