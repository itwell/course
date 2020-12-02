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
        name: "big-file",
        props: {
            text: {
                default: "上传大文件"
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
            uploadFile() {
                let _this = this;
                //使用表单的方式提交图片
                let formData = new window.FormData();
                let file = _this.$refs.file.files[0];

                console.log(file);

                let key = hex_md5(file);
                let key10 = parseInt(key, 16);
                //26个大写字母 + 26个小写字母 + 10个阿拉伯数字 共62个字符
                let key62 = Tool._10to62(key10);
                console.log(key, key10, key62);


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

                //文件分片
                let shardSize = 20 * 1024 * 1024;    //以20MB为一个分片
                // let shardSize = 50 * 1024;    //以50KB为一个分片
                let shardIndex = 1;		//分片索引 1表示第一个分片



                let size = file.size;
                let shardTotal = Math.ceil(size / shardSize); //总片数

                let inputId = document.querySelector("#" + _this.inputId + "-input");
                console.log(formData);
                console.log(inputId.files[0]);

                let param = {
                    'shardIndex': shardIndex,
                    'shardSize': shardSize,
                    'shardTotal': shardTotal,
                    'use': _this.use,
                    'name': file.name,
                    'suffix': suffix,
                    'size': file.size,
                    'key': key62
                };

                _this.upload(param);

            },

            upload: function (param) {
                let _this = this;
                let shardIndex = param.shardIndex;
                let shardTotal = param.shardTotal;
                let shardSize = param.shardSize;
                let fileShard = _this.getFileShard(shardIndex,shardSize);
                //将图片转为base64进行存储
                let fileReader = new FileReader();
                fileReader.onload = function (e) {
                    let base64 = e.target.result;
                    // console.log("base64:",base64);
                    param.shard = base64;

                    Loading.show();
                    _this.$ajax.post(process.env.VUE_APP_SERVER + '/file/admin/upload', param).then((response) => {
                        Loading.hide();
                        let resp = response.data;
                        console.log("上传文件成功：", resp);
                        if (shardIndex < shardTotal) {
                            //上传下一个分片
                            param.shardIndex = param.shardIndex + 1;
                            _this.upload(param);
                        } else {
                            _this.afterUpload(resp);
                        }
                        _this.afterUpload(resp);
                        let image = resp.content;
                        $("#" + _this.inputId + "-input").val("");
                    });
                };
                fileReader.readAsDataURL(fileShard)
            },

            getFileShard: function (shardIndex, shardSize) {
                let _this = this;
                let file = _this.$refs.file.files[0];
                let start = (shardIndex - 1) * shardSize; // 当前分片起始位置
                let end = Math.min(file.size, start + shardSize);   //当前分片结束的位置
                let fileShard = file.slice(start, end); //从文件中截取当前的分片数据
                return fileShard;
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