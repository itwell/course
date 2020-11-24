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
            <div v-for="course in courses" class="col-md-3">
                <div class="thumbnail search-thumbnail">
                    <img v-show="!course.image" class="media-object" src="/static/image/demo-course.jpg" />
                    <img v-show="course.image" class="media-object" v-bind:src="course.image" />
                    <div class="caption">
                    <div class="clearfix">
                          <span class="pull-right label label-primary info-label">
                            {{COURSE_LEVEL | optionKV(course.level)}}
                          </span>
                          <span class="pull-right label label-primary info-label">
                            {{COURSE_CHARGE | optionKV(course.charge)}}
                          </span>
                           <span class="pull-right label label-primary info-label">
                            {{COURSE_STATUS | optionKV(course.status)}}
                          </span>
                    </div>

                    <h3 class="search-title">
                        <a href="#" class="blue">{{course.name}}</a>
                    </h3>

                    <p>
                        <span class="blue bolder bigger-150">{{course.price}}&nbsp;<i class="fa fa-rmb"></i></span>&nbsp;
                    </p>

                    <p>{{course.summary}}</p>

                    <p>
                        <span class="badge badge-info">{{course.id}}</span>
                        <span class="badge badge-info">排序：{{course.sort}}</span>
                        <span class="badge badge-info">时长：{{course.time | formatSecond}}</span>
                    </p>

                    <p>
                        <button v-on:click="toChapter(course)" class="btn btn-white btn-xs btn-info btn-round">
                            大章
                        </button>&nbsp;
                        <button v-on:click="edit(course)" class="btn btn-white btn-xs btn-info btn-round">
                            编辑
                        </button>&nbsp;
                        <button v-on:click="del(course.id)" class="btn btn-white btn-xs btn-warning btn-round">
                            删除
                        </button>
                    </p>
                </div>
            </div>
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
                                    <div class="form-group ">
                                        <label class="col-sm-2 control-label">分类</label>
                                        <div class="col-sm-10">
                                            <ul id="tree" class="ztree"></ul>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">名称</label>
                                        <div class="col-sm-10">
                                            <input v-model="course.name" type="text" class="form-control"
                                                   placeholder="名称">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">概述</label>
                                        <div class="col-sm-10">
                                            <input v-model="course.summary" type="text" class="form-control"
                                                   placeholder="概述">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">时长</label>
                                        <div class="col-sm-10">
                                            <input v-model="course.time" type="text" class="form-control"
                                                   placeholder="时长">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">价格（元）</label>
                                        <div class="col-sm-10">
                                            <input v-model="course.price" type="text" class="form-control"
                                                   placeholder="价格（元）">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">封面</label>
                                        <div class="col-sm-10">
                                            <input v-model="course.image" type="text" class="form-control"
                                                   placeholder="封面">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">级别</label>
                                        <div class="col-sm-10">
                                            <select v-model="course.level" class="form-control">
                                                <option v-for="o in COURSE_LEVEL" v-bind:value="o.key">{{o.value}}</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">收费</label>
                                        <div class="col-sm-10">
                                            <select v-model="course.charge" class="form-control">
                                                <option v-for="o in COURSE_CHARGE" v-bind:value="o.key">{{o.value}}</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">状态</label>
                                        <div class="col-sm-10">
                                            <select v-model="course.status" class="form-control">
                                                <option v-for="o in COURSE_STATUS" v-bind:value="o.key">{{o.value}}</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">报名数</label>
                                        <div class="col-sm-10">
                                            <input v-model="course.enroll" type="text" class="form-control"
                                                   placeholder="报名数">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">顺序</label>
                                        <div class="col-sm-10">
                                            <input v-model="course.sort" type="text" class="form-control"
                                                   placeholder="顺序">
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
        name: "business-course",
        data: function () {
            return {
                course:{},
                courses: [],
                COURSE_LEVEL: COURSE_LEVEL,
                COURSE_CHARGE: COURSE_CHARGE,
                COURSE_STATUS: COURSE_STATUS,
                Categorys: [],
                tree:{},
            }
        },
        mounted: function () {
            //激活侧边栏状态写法1
            // this.$parent.activeSidebar("business-course-sidebar");
            let _this = this;
            _this.$refs.pagination.size = 5;
            _this.allCategory();
            _this.initTree();
            _this.list(1);
        },
        methods: {
            /**
             * 新增
             */
            add() {
                let _this = this;
                /*新增的时候表单不用数据*/
                _this.course = {};
                //新增的时候不应该有选项
                _this.tree.checkAllNodes(false);
                $("#form-modal").modal("show");
            },

            /**
             * 查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/list', {
                    page: page,
                    /*根据pagination名字获取组件*/
                    size: _this.$refs.pagination.size,
                })
                        .then((response) => {
                            Loading.hide();
                            /*接口返回的data是ChapterDto*/
                            let resp = response.data;
                            _this.courses = resp.content.list;
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
                      || !Validator.require(_this.course.name, "名称")
                      || !Validator.length(_this.course.name, "名称", 1, 50)
                      || !Validator.length(_this.course.summary, "概述", 1, 2000)
                      || !Validator.require(_this.course.price, "价格（元）")
                      || !Validator.length(_this.course.image, "封面", 1, 100)
                ) {
                    return;
                }

                let categorys = _this.tree.getCheckedNodes();
                if (Tool.isEmpty(categorys)) {
                    Toast.warning("请选择分类! ");
                    return;
                }
                _this.course.categorys = categorys;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/save', _this.course)
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
            edit(course) {
                let _this = this;
                /*_this.chapter = chapter; 这种写法有双向数据绑定的问题*/
                _this.course = $.extend({}, course);
                _this.listCategory(course.id);
                $("#form-modal").modal("show");
            },

            /**
             * 删除数据
             * @param id
             */
            del(id) {
                let _this = this;
                Confirm.show("删除课程后不可恢复，确认删除？", function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/course/delete/' + id, _this.course).then((response) => {
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
             * 点击大章
             */
            toChapter(course) {
                let _this = this;
                SessionStorage.set("course",course);
                _this.$router.push("/business/chapter")
            },

            /**
             * 查询
             */
            allCategory() {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/all').then((response) => {
                    Loading.hide();
                    /*接口返回的data是ChapterDto*/
                    let resp = response.data;
                    _this.categorys = resp.content;
                    //查到所有分类以后
                    _this.initTree();
                })
            },

            initTree(){
                let _this = this;
                var setting = {
                    check: {
                        enable: true
                    },
                    data: {
                        simpleData: {
                            idkey: "id",
                            pIdKey: "parent",
                            rootPId: "00000000",
                            enable: true
                        }
                    }
                };

                var zNodes = _this.categorys;

                _this.tree = $.fn.zTree.init($("#tree"), setting, zNodes);
                //展开所有节点
                _this.tree.expandAll(true);
            },

            /**
             * 查找课程下所有分类
             * @param courseId
             */
            listCategory(courseId) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/list-category/' + courseId).then((res)=>{
                    Loading.hide();
                    console.log("查找课程下所有分类结果：", res);
                    let response = res.data;
                    let categorys = response.content;

                    // 勾选查询到的分类
                    _this.tree.checkAllNodes(false);
                    for (let i = 0; i < categorys.length; i++) {
                        let node = _this.tree.getNodeByParam("id", categorys[i].categoryId);
                        _this.tree.checkNode(node, true);
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .caption h3 {
        font-size: 20px;
    }
</style>