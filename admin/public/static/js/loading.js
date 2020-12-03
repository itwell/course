Loading = {
    //显示等待框
    show: function () {
        $.blockUI({
            message: '<img src="/static/image/loading.gif"/>',
            css: {
                zIndex: "10011",
                padding: "10px",
                left: "50%",
                width: "80px",
                marginLeft: "-40px"
            }
        })
    },
    //关闭等待框
    hide: function () {
        setTimeout(function () {
            $.unblockUI();
        },500)
    }
};