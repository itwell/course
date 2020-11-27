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

        <div class="row">
            <div v-for="teacher in teachers" class="col-md-3">
                <div>
          <span class="profile-picture">
            <img v-show="!teacher.image" class="editable img-responsive editable-click editable-empty"
                 src="/static/image/teacher/avator/avator1.jpg" v-bind:title="teacher.intro"/>
            <img v-show="teacher.image" class="editable img-responsive editable-click editable-empty"
                 v-bind:src="teacher.image" v-bind:title="teacher.intro"/>
          </span>

                    <div class="space-4"></div>

                    <div class="width-85 label label-info label-xlg arrowed-in arrowed-in-right">
                        <div class="inline position-relative">
                            <a href="javascript:;" class="user-title-label dropdown-toggle" data-toggle="dropdown">
                                <i class="ace-icon fa fa-circle light-green"></i>
                                &nbsp;
                                <span class="white">{{teacher.position}}</span>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="space-6"></div>

                <div class="text-center">
                    <a href="javascript:;" class="text-info bigger-110" v-bind:title="teacher.motto">
                        <i class="ace-icon fa fa-user"></i>
                        {{teacher.name}}【{{teacher.nickname}}】
                    </a>
                </div>

                <div class="space-6"></div>

                <div class="profile-social-links align-center">
                    <button v-on:click="edit(teacher)" class="btn btn-xs btn-info">
                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </button>
                    &nbsp;
                    <button v-on:click="del(teacher.id)" class="btn btn-xs btn-danger">
                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                    </button>
                </div>

                <div class="hr hr16 dotted"></div>

            </div>
        </div>

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
                                <label class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-10">
                                    <input v-model="teacher.name" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">昵称</label>
                                <div class="col-sm-10">
                                    <input v-model="teacher.nickname" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">头像</label>
                                <div class="col-sm-10">
                                    <input v-model="teacher.image" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">职位</label>
                                <div class="col-sm-10">
                                    <input v-model="teacher.position" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">座右铭</label>
                                <div class="col-sm-10">
                                    <input v-model="teacher.motto" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">简介</label>
                                <div class="col-sm-10">
                                    <textarea v-model="teacher.intro" class="form-control" rows="5"></textarea>
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
        name: "business-teacher",
        data: function () {
            return {
                teacher: {},
                teachers: [],
            }
        },
        mounted: function () {
            //激活侧边栏状态写法1
            // this.$parent.activeSidebar("business-teacher-sidebar");
            let _this = this;
            _this.$refs.pagination.size = 5;
            _this.list(1);
        },
        methods: {
            /**
             * 新增
             */
            add() {
                let _this = this;
                /*新增的时候表单不用数据*/
                _this.teacher = {};
                $("#form-modal").modal("show");
            },

            /**
             * 查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/teacher/list', {
                    page: page,
                    /*根据pagination名字获取组件*/
                    size: _this.$refs.pagination.size,
                }).then((response) => {
                    Loading.hide();
                    /*接口返回的data是ChapterDto*/
                    let resp = response.data;
                    _this.teachers = resp.content.list;
                    _this.$refs.pagination.render(page, resp.content.total);
                })
            },

            /**
             * 保存
             */
            save() {
                let _this = this;

                /*校验表单值是否合法*/
                if (1 != 1
                    || !Validator.require(_this.teacher.name, "姓名")
                    || !Validator.length(_this.teacher.name, "姓名", 1, 50)
                    || !Validator.length(_this.teacher.nickname, "昵称", 1, 50)
                    || !Validator.length(_this.teacher.image, "头像", 1, 100)
                    || !Validator.length(_this.teacher.position, "职位", 1, 50)
                    || !Validator.length(_this.teacher.motto, "座右铭", 1, 50)
                    || !Validator.length(_this.teacher.intro, "简介", 1, 500)
                ) {
                    return;
                }

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/teacher/save', _this.teacher)
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
             * 回显数据
             */
            edit(teacher) {
                let _this = this;
                /*_this.chapter = chapter; 这种写法有双向数据绑定的问题*/
                _this.teacher = $.extend({}, teacher);

                $("#form-modal").modal("show");
            }
            ,

            /**
             * 删除数据
             * @param id
             */
            del(id) {
                let _this = this;
                Confirm.show("删除讲师后不可恢复111，确认删除？", function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/teacher/delete/' + id, _this.teacher).then((response) => {
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