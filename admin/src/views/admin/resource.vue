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
            </div>
        </div>

        <hr>

        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th>id</th>
                <th>名称</th>
                <th>页面</th>
                <th>请求</th>
                <th>父id</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="resource in resources">
                <td>{{resource.id}}</td>
                <td>{{resource.name}}</td>
                <td>{{resource.page}}</td>
                <td>{{resource.request}}</td>
                <td>{{resource.parent}}</td>

                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <!--把循环中的每一个edit传入模态框上-->
                        <button v-on:click="edit(resource)" class="btn btn-xs btn-info">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button v-on:click="del(resource.id)" class="btn btn-xs btn-danger">
                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                        </button>

                    </div>

                </td>
            </tr>

            </tbody>
        </table>

        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div><!-- /.span -->
</template>

<script>
    import Pagination from "../../components/pagination";

    export default {
        components: {Pagination},
        name: "system-resource",
        data: function () {
            return {
                resource: {},
                resources: [],
                resourceStr: "",
            }
        },
        mounted: function () {
            //激活侧边栏状态写法1
            // this.$parent.activeSidebar("system-resource-sidebar");
            let _this = this;
            _this.$refs.pagination.size = 5;
            _this.list(1);
        },
        methods: {

            /**
             * 查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/resource/list', {
                    page: page,
                    /*根据pagination名字获取组件*/
                    size: _this.$refs.pagination.size,
                })
                    .then((response) => {
                        Loading.hide();
                        /*接口返回的data是ChapterDto*/
                        let resp = response.data;
                        _this.resources = resp.content.list;
                        _this.$refs.pagination.render(page, resp.content.total);
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
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/resource/save', json)
                    .then((response) => {
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
                Confirm.show("删除资源后不可恢复111，确认删除？", function () {
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
            }
            ,
        }
    }
</script>

<style>

</style>