<template>
    <div class="page-content">
        <div>
            <button type="button" v-on:click="selectFile()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-upload"></i>
                {{text}}
            </button>
            <input class="hidden" type="file" ref="file" v-on:change="uploadFile()" v-bind:id="inputId+'-input'">
        </div>
    </div><!-- /.span -->
</template>

<script>
    export default {
        name: "file",
        props: {
            text: {
                default: "上传文件"
            },
            inputId: {
                default: "file-upload"
            },
            suffixs: {
                default: []
            },
            use: {
                default: ""
            },
            afterUpload: {
                type: Function,
                default: null
            },
        },
        data: function () {
            return {
                file: {},
                files: [],
                FILE_USE: FILE_USE,
            }
        },
        methods: {
            /**
             * 查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/file/admin/file/list', {
                    page: page,
                    /*根据pagination名字获取组件*/
                    size: _this.$refs.pagination.size,
                })
                    .then((response) => {
                        Loading.hide();
                        /*接口返回的data是ChapterDto*/
                        let resp = response.data;
                        _this.files = resp.content.list;
                        _this.$refs.pagination.render(page, resp.content.total);
                    })
            },
            uploadFile(){
                let _this = this;
                //使用表单的方式提交图片
                let formData = new window.FormData();
                let file = _this.$refs.file.files[0];

                // 判断文件格式
                let suffixs = _this.suffixs;
                let fileName = file.name;
                let suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length).toLowerCase();
                let validateSuffix = false;
                for (let i = 0; i < suffixs.length; i++) {
                    if (suffixs[i].toLowerCase() === suffix) {
                        validateSuffix = true;
                        break;
                    }
                }
                if (!validateSuffix) {
                    Toast.warning("文件格式不正确！只支持上传：" + suffixs.join(","));
                    $("#" + _this.inputId + "-input").val("");
                    return;
                }

                let inputId = document.querySelector("#" + _this.inputId + "-input");
                console.log(formData);
                console.log(inputId.files[0]);
                //key file必须和后端controller参数名一致
                formData.append('file',file);
                formData.append('use', _this.use);
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/file/admin/oss-simple',formData).then((response)=>{
                    Loading.hide();
                    let resp = response.data;
                    console.log("上传文件成功：", resp);
                    _this.afterUpload(resp);
                    let image = resp.content;
                    $("#" + _this.inputId + "-input").val("");
                });
            },
            selectFile() {
                let _this = this;
                $("#" + _this.inputId + "-input").trigger("click");
            },
        }
    }
</script>

<style>

</style>