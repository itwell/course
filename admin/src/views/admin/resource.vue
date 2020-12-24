<template>
    <div class="page-content">
        <p>
            <button v-on:click="list(1)" v-bind:list="list" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh green"></i>
                刷新
            </button>
        </p>

        <div class="row">
            <div class="col-md-6">
                <textarea id="resource-textarea" class="form-control" v-model="resourceStr" name="resource"
                          rows="10"></textarea>

                <br>
                <button id="save-btn" type="button" class="btn btn-primary" v-on:click="save()">
                    保存
                </button>
            </div>

            <div class="col-md-6">
                <ul id="tree" class="ztree"></ul>
            </div>
        </div>

        <hr>

    </div><!-- /.span -->
</template>

<script>
    export default {
        name: "system-resource",
        data: function () {
            return {
                resource: {},
                resources: [],
                resourceStr: "",
                tree: {},
            }
        },
        mounted: function () {
            //激活侧边栏状态写法1
            // this.$parent.activeSidebar("system-resource-sidebar");
            let _this = this;
            _this.list();
        },
        methods: {

            /**
             * 查询
             */
            list() {
                let _this = this;
                Loading.show();
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/resource/load-tree').then((res) => {
                        Loading.hide();
                        /*接口返回的data是ChapterDto*/
                        let response = res.data;
                        _this.resources = response.content;
                        //初始化树
                        _this.initTree();
                    })
            },

            /**
             * 保存
             */
            save() {
                let _this = this;

                /*校验表单值是否合法*/
                if (Tool.isEmpty(_this.resourceStr)) {
                    Toast.warning("资源不能为空");
                    return;
                }
                let json = JSON.parse(_this.resourceStr);

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/resource/save',json).then((response) => {
                        Loading.hide();
                        let resp = response.data;
                        if (resp.success) {
                            $("#form-modal").modal("hide");
                            Toast.success("保存成功");
                            _this.list(1);
                        } else {
                            Toast.warning(resp.message);
                        }
                    })
            },

            /**
             * 删除数据
             * @param id
             */
            del(id) {
                let _this = this;
                Confirm.show("删除资源后不可恢复，确认删除？", function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/system/admin/resource/delete/' + id, _this.resource).then((response) => {
                        Loading.hide();
                        let resp = response.data;
                        if (resp.success) {
                            Toast.success("删除成功");
                            _this.list(1);
                        }
                    });
                })
            },

            /**
             * 初始资源树
             */
            initTree() {
                let _this = this;
                let setting = {
                    data: {
                        simpleData: {
                            idKey: "id",
                            pIdKey: "parent",
                            rootPId: "",
                            // enable: true
                        }
                    }
                };

                _this.zTree = $.fn.zTree.init($("#tree"), setting, _this.resources);
                _this.zTree.expandAll(true);
            },
        }
    }
</script>

<style>

</style>