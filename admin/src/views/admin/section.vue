<template>
    <div class="page-content">
        <h4 class="lighter">
            <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
            <router-link to="/business/course" class="pink"> {{course.name}} </router-link>

            <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
            <router-link to="/business/chapter" class="pink"> {{chapter.name}} </router-link>
        </h4>
        <hr>
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
                    <th>标题</th>
                    <th>VOD</th>
                    <th>时长</th>
                    <th>收费</th>
                    <th>顺序</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
                <tr v-for="section in sections">
                            <td>{{section.id}}</td>
                            <td>{{section.title}}</td>
                            <td>{{section.vod}}</td>
                            <td>{{section.time | formatSecond}}</td>
                            <td>{{SECTION_CHARGE | optionKV(section.charge)}}</td>
                            <td>{{section.sort}}</td>

                    <td>
                        <div class="hidden-sm hidden-xs btn-group">
                            <button v-on:click="play(section)" class="btn btn-xs btn-info">
                                <i class="ace-icon fa fa-video-camera bigger-120"></i>
                            </button>

                            <!--把循环中的每一个edit传入模态框上-->
                            <button v-on:click="edit(section)" class="btn btn-xs btn-info">
                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                            </button>

                            <button v-on:click="del(section.id)" class="btn btn-xs btn-danger">
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
                                        <label class="col-sm-2 control-label">标题</label>
                                        <div class="col-sm-10">
                                            <input v-model="section.title" type="text" class="form-control"
                                                   placeholder="标题">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">课程</label>
                                        <div class="col-sm-10">
                                            <p class="form-control-static">{{course.name}}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">大章</label>
                                        <div class="col-sm-10">
                                            <p class="form-control-static">{{chapter.name}}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">视频</label>
                                        <div class="col-sm-10">
                                            <vod v-bind:input-id="'video-upload'"
                                                  v-bind:text="'上传视频'"
                                                  v-bind:suffixs="['mp4']"
                                                  v-bind:use="FILE_USE.COURSE.key"
                                                  v-bind:after-upload="afterUpload"></vod>
                                            <div class="row" v-show="section.video">
                                                <div class="col-md-9">
                                                    <player ref="player" v-bind:player-id="'form-player-div'"></player>
                                                    <video v-bind:src="section.video" id="video" controls="controls" class="hidden"></video>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">时长</label>
                                        <div class="col-sm-10">
                                            <input v-model="section.time" type="text" class="form-control"
                                                   placeholder="时长">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">视频</label>
                                        <div class="col-sm-10">
                                            <input v-model="section.video" class="form-control" disabled>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">VOD</label>
                                        <div class="col-sm-10">
                                            <input v-model="section.vod" class="form-control" disabled>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">收费</label>
                                        <div class="col-sm-10">
                                            <select v-model="section.charge" class="form-control">
                                                <option v-for="o in SECTION_CHARGE" v-bind:value="o.key">{{o.value}}</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">顺序</label>
                                        <div class="col-sm-10">
                                            <input v-model="section.sort" type="text" class="form-control"
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

        <modal-player ref="modalPlayer"></modal-player>
        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div><!-- /.span -->
</template>

<script>
    import Pagination from "../../components/pagination";
    import Vod from "../../components/vod";
    import Player from "../../components/player";
    import ModalPlayer from "../../components/modal-player";

    export default {
        components: {ModalPlayer,Player, Vod,Pagination},
        name: "business-section",
        data: function () {
            return {
                section: {},
                sections: [],
                SECTION_CHARGE: SECTION_CHARGE ,
                FILE_USE: FILE_USE,
                course: {},
                chapter: {},
        }
        },
        mounted: function () {
            //激活侧边栏状态写法1
            // this.$parent.activeSidebar("business-section-sidebar");
            let _this = this;
            _this.$refs.pagination.size = 5;
            let course = SessionStorage.get(SESSION_KEY_COURSE) || {};
            let chapter = SessionStorage.get(SESSION_KEY_CHAPTER) || {};
            if (Tool.isEmpty(course) || Tool.isEmpty(chapter)) {
                _this.$router.push("/welcome");
            }
            _this.course = course;
            _this.chapter = chapter;
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
                _this.section = {};
                $("#form-modal").modal("show");
            },

            /**
             * 查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/section/list', {
                    page: page,
                    /*根据pagination名字获取组件*/
                    size: _this.$refs.pagination.size,
                    courseId: _this.course.id,
                    chapterId: _this.chapter.id
                })
                        .then((response) => {
                            Loading.hide();
                            /*接口返回的data是ChapterDto*/
                            let resp = response.data;
                            _this.sections = resp.content.list;
                            _this.$refs.pagination.render(page, resp.content.total);
                        })
            },

            /**
             * 保存
             */
            save(page) {
                let _this = this;

                _this.section.video = "";
                /*校验表单值是否合法*/
                if (1 != 1
                      || !Validator.require(_this.section.title, "标题")
                      || !Validator.length(_this.section.title, "标题", 1, 50)
                      || !Validator.length(_this.section.video, "视频", 1, 200)
                ) {
                    return;
                }
                _this.section.courseId = _this.course.id;
                _this.section.chapterId = _this.chapter.id;


                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/section/save', _this.section)
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
            edit(section) {
                let _this = this;
                /*_this.chapter = chapter; 这种写法有双向数据绑定的问题*/
                _this.section = $.extend({}, section);

                $("#form-modal").modal("show");
            }
            ,

            /**
             * 删除数据
             * @param id
             */
            del(id) {
                let _this = this;
                Confirm.show("删除小节后不可恢复，确认删除？", function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/section/delete/' + id, _this.section).then((response) => {
                        Loading.hide();
                        let resp = response.data;
                        if (resp.success) {
                            Toast.success("删除成功");
                            _this.list(1);
                        }
                    });
                })
            },
            afterUpload(resp) {
                let _this = this;
                let video = resp.content.path;
                let vod =   resp.content.vod;
                console.log("************************");
                console.log("video" + video);
                console.log("************************");
                _this.section.video = video;
                console.log("************************");
                console.log("vod" + vod);
                console.log("************************");
                _this.section.vod = vod;
                _this.getTime();
                _this.$refs.player.playUrl(video);
            },

            /**
             * 获取时长
             */
            getTime(){
                let _this = this;
                setTimeout(function () {
                    let ele = document.getElementById("video");
                    console.log(ele);
                    //把时长换算成10进制的整数
                    _this.section.time = parseInt(ele.duration,10);
                    console.log(_this.section.time);
                    console.log(_this.section.video);
                },1000);
            },

            /**
             * 播放视频
             *
             * @param section
             */
            play(section) {
                let _this = this;
                _this.$refs.modalPlayer.playVod(section.vod);
            }

        }
    }
</script>

<style>
    video{
        width: 100%;
        height: auto;
        margin-top: 10px;
    }
</style>