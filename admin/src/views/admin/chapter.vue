<template>
    <div class="page-content">
        <p>
            <button v-on:click="add()" v-bind:list="list" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-edit blue"></i>
                新增
            </button>
            &nbsp;
            <button v-on:click="list(1)" v-bind:list="list" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh green"></i>
                刷新
            </button>
        </p>

        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>名称</th>
                <th>课程ID</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="chapter in chapters">
                <td>{{chapter.id}}</td>
                <td>{{chapter.name}}</td>
                <td>{{chapter.courseId}}</td>

                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <!--把循环中的每一个edit传入模态框上-->
                        <button v-on:click="edit(chapter)" class="btn btn-xs btn-info">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button v-on:click="del(chapter.id)" class="btn btn-xs btn-danger">
                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                        </button>

                    </div>

                </td>
            </tr>

            </tbody>
        </table>

        <!--模态框-->
        <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">表单</h4>
                    </div>
                    <div class="modal-body">
                        <!--水平表单-->
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-10">
                                    <input v-model="chapter.name" type="text" class="form-control" placeholder="名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">课程ID</label>
                                <div class="col-sm-10">
                                    <input v-model="chapter.courseId" type="text" class="form-control"
                                           placeholder="课程ID">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button v-on:click="save()" type="button" class="btn btn-primary">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div><!-- /.span -->
</template>

<script>
    import Pagination from "../../components/pagination";

    export default {
        components: {Pagination},
        name: "chapter",
        data: function () {
            return {
                chapter: {},
                chapters: []
            }
        },
        mounted: function () {
            //激活侧边栏状态写法1
            // this.$parent.activeSidebar("business-chapter-sidebar");
            let _this = this;
            _this.$refs.pagination.size = 5;
            _this.list(1);
        },
        methods: {
            add() {
                let _this = this;
                /*新增的时候表单不用数据*/
                _this.chapter = {};
                $("#form-modal").modal("show");
            },
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/list', {
                    page: page,
                    /*根据pagination名字获取组件*/
                    size: _this.$refs.pagination.size,
                })
                    .then((response) => {
                        Loading.hide();
                        console.log("查询大章列表结果:", response);
                        /*接口返回的data是ChapterDto*/
                        let resp = response.data;
                        _this.chapters = resp.content.list;
                        _this.$refs.pagination.render(page, resp.content.total);
                    })
            },
            save(page) {
                let _this = this;

                /*校验表单值是否合法*/
                if (    !Validator.require(_this.chapter.name, "名称")
                    ||
                        !Validator.require(_this.chapter.courseId, "课程ID")
                    ||
                        !Validator.length(_this.chapter.courseId, "课程ID", 1, 8)
                    ) {
                    return;
                }

                Loading.show();
                _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/save', _this.chapter)
                    .then((response) => {
                        Loading.hide();
                        console.log("保存大章结果: ", response);
                        let resp = response.data;
                        if (resp.success) {
                            $("#form-modal").modal("hide");
                            Toast.success("保存成功");
                            _this.list(1);
                        }else{
                            Toast.warning(resp.message);
                        }
                    })
            },
            edit(chapter) {
                let _this = this;
                /*_this.chapter = chapter; 这种写法有双向数据绑定的问题*/
                _this.chapter = $.extend({}, chapter);
                /**/
                $("#form-modal").modal("show");
            },
            del(id) {
                let _this = this;
                Confirm.show("删除大章后不可恢复111，确认删除？", function () {
                    Loading.show();
                    _this.$ajax.delete('http://127.0.0.1:9000/business/admin/chapter/delete/' + id, _this.chapter).then((response) => {
                        Loading.hide();
                        console.log("删除大章列表结果: ", response);
                        let resp = response.data;
                        if (resp.success) {
                            Toast.success("删除成功");
                            _this.list(1);
                        }
                    });
                })
            },
        }
    }
</script>

<style>

</style>