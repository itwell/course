<template>
    <div class="page-content">
        <h4 class="lighter">
            <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
            <router-link to="/business/course" class="pink"> {{course.name}} </router-link>
        </h4>
        <hr>
        <p>
            <router-link to="/business/course" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-arrow-left"></i>
                返回课程
            </router-link>
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
                <th>id</th>
                <th>名称</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="chapter in chapters">
                <td>{{chapter.id}}</td>
                <td>{{chapter.name}}</td>

                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button v-on:click="toSection(chapter)" class="btn btn-white btn-xs btn-info btn-round">
                            小节
                        </button>&nbsp;

                        <!--把循环中的每一个edit传入模态框上-->
                        <button v-on:click="edit(chapter)" class="btn btn-white btn-xs btn-info btn-round">
                            编辑
                        </button>&nbsp;

                        <button v-on:click="del(chapter.id)" class="btn btn-white btn-xs btn-warning btn-round">
                            删除
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
                                    <input v-model="chapter.name" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">课程</label>
                                <div class="col-sm-10">
                                    <p class="form-control-static">{{course.name}}</p>
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
        name: "business-chapter",
        data: function () {
            return {
                chapter: {},
                chapters: [],
                course: {},
            }
        },
        mounted: function () {
            //激活侧边栏状态写法1
            // this.$parent.activeSidebar("business-chapter-sidebar");
            let _this = this;
            _this.$refs.pagination.size = 5;
            let course = SessionStorage.get(SESSION_KEY_COURSE) || {};
            if (Tool.isEmpty(course)) {
                _this.$router.push("/welcome");
            }
            _this.course = course;
            _this.list(1);
            //进入大章管理的时候,还是课程管理还是激活的状态
            this.$parent.activeSidebar("business-course-sidebar");
        },
        methods: {
            /**
             * 新增
             */
            add() {
                let _this = this;
                /*新增的时候表单不用数据*/
                _this.chapter = {};
                $("#form-modal").modal("show");
            },

            /**
             * 查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/list', {
                    page: page,
                    /*根据pagination名字获取组件*/
                    size: _this.$refs.pagination.size,
                    courseId: _this.course.id
                })
                    .then((response) => {
                        Loading.hide();
                        /*接口返回的data是ChapterDto*/
                        let resp = response.data;
                        _this.chapters = resp.content.list;
                        _this.$refs.pagination.render(page, resp.content.total);
                    })
            },

            /**
             * 保存
             */
            save(page) {
                let _this = this;


                /*校验表单值是否合法*/
                if (1 != 1
                    || !Validator.require(_this.chapter.name, "名称")
                    || !Validator.length(_this.chapter.courseId, "课程ID", 1, 8)
                ) {
                    return;
                }
                _this.chapter.courseId = _this.course.id;

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/save', _this.chapter)
                    .then((response) => {
                        Loading.hide();
                        let resp = response.data;
                        if (resp.success) {
                            $("#form-modal").modal("hide");
                            _this.list(1);
                            Toast.success("保存成功");
                        } else {
                            Toast.warning(resp.message);
                        }
                    })
            },

            /**
             * 回显数据
             */
            edit(chapter) {
                let _this = this;
                /*_this.chapter = chapter; 这种写法有双向数据绑定的问题*/
                _this.chapter = $.extend({}, chapter);

                $("#form-modal").modal("show");
            }
            ,

            /**
             * 删除数据
             * @param id
             */
            del(id) {
                let _this = this;
                Confirm.show("删除大章后不可恢复，确认删除？", function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/chapter/delete/' + id, _this.chapter).then((response) => {
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
             * 点击【小节】
             */
            toSection(chapter) {
                let _this = this;
                SessionStorage.set(SESSION_KEY_CHAPTER, chapter);
                _this.$router.push("/business/section");
            }
        }
    }
</script>

<style>

</style>