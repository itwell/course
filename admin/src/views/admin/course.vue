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

                    <div v-for="teacher in teachers.filter(t=>{return t.id===course.teacherId})" class="profile-activity clearfix">
                        <div>
                            <img v-show="!teacher.image" class="pull-left" src="/ace/assets/images/avatars/avatar5.png">
                            <img v-show="teacher.image" class="pull-left" v-bind:src="teacher.image">
                            <a class="user" href="#"> {{teacher.name}} </a>
                            <br>
                            {{teacher.position}}
                        </div>
                    </div>

                    <p>
                        <span class="blue bolder bigger-150">{{course.price}}&nbsp;<i class="fa fa-rmb"></i></span>&nbsp;
                    </p>

                    <p>{{course.summary}}</p>

                    <p>
                        <span class="badge badge-info">{{course.id}}</span>
                        <span class="badge badge-info">排序：{{course.sort}}</span>
                    </p>

                    <p>
                        <button v-on:click="toChapter(course)" class="btn btn-white btn-xs btn-info btn-round">
                            大章
                        </button>&nbsp;
                        <button v-on:click="toContent(course)" class="btn btn-white btn-xs btn-info btn-round">
                            内容
                        </button>&nbsp;
                        <button v-on:click="openSortModal(course)" class="btn btn-white btn-xs btn-info btn-round">
                            排序
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

        <!--编辑模态框-->
        <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">编辑课程</h4>
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
                                        <label class="col-sm-2 control-label">讲师</label>
                                        <div class="col-sm-10">
                                            <select v-model="course.teacherId" class="form-control">
                                                <option v-for="o in teachers" v-bind:value="o.id">{{o.name}}</option>
                                            </select>
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
                                            <big-file v-bind:input-id="'image-upload'"
                                                  v-bind:suffixs="['jpg','jpeg','png']"
                                                  v-bind:text="'上传封面'"
                                                  v-bind:use="FILE_USE.COURSE.key"
                                                  v-bind:after-upload="afterUpload"></big-file>
                                            <div v-show="course.image" class="row">
                                                <div class="col-md-6">
                                                    <img v-bind:src="course.image" class="img-responsive">
                                                </div>
                                            </div>
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
                                            <input v-model="course.sort" type="text" class="form-control" placeholder="顺序" disabled>
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


        <!--排序模态框-->
        <div id="course-sort-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">排序</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-lg-3">
                                    当前排序
                                </label>
                                <div class="col-lg-9">
                                    <input class="form-control" v-model="sort.oldSort" name="oldSort" disabled>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-lg-3">
                                    新排序
                                </label>
                                <div class="col-lg-9">
                                    <input class="form-control" v-model="sort.newSort" name="newSort">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-white btn-default btn-round" data-dismiss="modal">
                            <i class="ace-icon fa fa-times"></i>
                            取消
                        </button>
                        <button type="button" class="btn btn-white btn-info btn-round" v-on:click="updateSort()">
                            <i class="ace-icon fa fa-plus blue"></i>
                            更新排序
                        </button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div>

        <!--分页插件-->
        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>


    </div><!-- /.span -->
</template>

<script>
    import Pagination from "../../components/pagination";
    // import File from "../../components/file";
    import BigFile from "../../components/big-file";

    export default {
        components: {BigFile,Pagination},
        name: "business-course",
        data: function () {
            return {
                course:{},
                courses: [],
                COURSE_LEVEL: COURSE_LEVEL,
                COURSE_CHARGE: COURSE_CHARGE,
                COURSE_STATUS: COURSE_STATUS,
                FILE_USE: FILE_USE,
                categorys: [],
                tree:{},
                saveContentLabel: "",
                saveContentInterval: {},
                sort: {
                    id:"",
                    oldSort: 0,
                    newSort: 0
                },
                teachers: [],
            }
        },
        mounted: function () {
            //激活侧边栏状态写法1
            // this.$parent.activeSidebar("business-course-sidebar");
            let _this = this;
            _this.$refs.pagination.size = 5;
            _this.allCategory();
            _this.allTeacher();

            _this.list(1);
        },
        methods: {
            /**
             * 新增
             */
            add() {
                let _this = this;
                /*新增的时候表单不用数据*/
                _this.course = {
                    sort: _this.$refs.pagination.total + 1
                };
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
                SessionStorage.set(SESSION_KEY_COURSE, course);
                _this.$router.push("/business/chapter");
            },

            /**
             * 点击内容
             */
            toContent(course) {
                let _this = this;
                SessionStorage.set(SESSION_KEY_COURSE,course);
                _this.$router.push("/business/content")
            },

            /**
             * 查询所有分类
             */
            allCategory() {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/all').then((response) => {
                    Loading.hide();
                    /*接口返回的data是ChapterDto*/
                    let resp = response.data;
                    _this.categorys = resp.content;
                  console.log( _this.categorys)
                    //查到所有分类以后,把树展开
                    _this.initTree();
                })
            },
            /**
             * 查询所有老师
             */
            allTeacher() {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/teacher/all').then((response) => {
                    Loading.hide();
                    /*接口返回的data是ChapterDto*/
                    let resp = response.data;
                    _this.teachers = resp.content;
                })
            },

            /**
             * 初始资源树
             */
            initTree() {
                let _this = this;
                let setting = {
                  check: {
                    enable: true
                  },
                    data: {
                        simpleData: {
                          idKey: "id",
                          pIdKey: "parent",
                          rootPId: "00000000",
                          enable: true
                        }
                    }
                };
                //对节点赋值
                let zNodes = _this.categorys;
                _this.tree = $.fn.zTree.init($("#tree"), setting, zNodes);
                // _this.tree.expandAll(true);
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
            },
            /**
             * 保存内容
             */
            saveContent () {
                let _this = this;
                let content = $("#content").summernote("code");
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/save-content', {
                    id: _this.course.id,
                    content: content
                }).then((response)=>{
                    Loading.hide();
                    let resp = response.data;
                    if (resp.success) {
                        // Toast.success("内容保存成功");
                        // let now = Tool.dateFormat("yyyy-MM-dd hh:mm:ss");
                        let now = Tool.dateFormat("mm:ss");
                        _this.saveContentLabel = "最后保存时间：" + now;
                    } else {
                        Toast.warning(resp.message);
                    }
                });
            },

            /**
             * 打开排序模态框
             */
            openSortModal(course){
                let _this = this;
                _this.sort = {
                    id: course.id,
                    oldSort: course.sort,
                    newSort: course.sort
                };
                $("#course-sort-modal").modal("show");
            },

            /**
             * 排序
             */
            updateSort() {
                let _this = this;
                if (_this.sort.newSort === _this.sort.oldSort) {
                    Toast.warning("排序没有变化");
                    return;
                }
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + "/business/admin/course/sort", _this.sort).then((res) => {
                    let response = res.data;

                    if (response.success) {
                        Toast.success("更新排序成功");
                        $("#course-sort-modal").modal("hide");
                        _this.list(1);
                    } else {
                        Toast.error("更新排序失败");
                    }
                });
            },
            afterUpload(resp) {
                let _this = this;
                let image = resp.content.path;
                _this.course.image = image;
            },

            /**
             * 加载内容文件列表
             */
            listContentFiles() {
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/admin/course-content-file/list/' + _this.course.id).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.files = resp.content;
                    }
                });
            },

            /**
             * 上传内容文件后，保存内容文件记录
             */
            afterUploadContentFile(response) {
                let _this = this;
                console.log("开始保存文件记录");
                let file = response.content;
                file.courseId = _this.course.id;
                file.url = file.path;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course-content-file/save', file).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        Toast.success("上传文件成功");
                        _this.files.push(resp.content);
                    }
                });
            },

            /**
             * 删除内容文件
             */
            delFile(f) {
                let _this = this;
                Confirm.show("删除课程后不可恢复，确认删除？", function () {
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/course-content-file/delete/' + f.id).then((response)=>{
                        let resp = response.data;
                        if (resp.success) {
                            Toast.success("删除文件成功");
                            Tool.removeObj(_this.files, f);
                        }
                    });
                });
            },
        }
    }
</script>

<style scoped>
    .caption h3 {
        font-size: 20px;
    }

    @media (max-width: 1199px) {
        .caption h3 {
            font-size: 16px;
        }
    }
</style>