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
                                <th>id</th>
                    <th>手机号</th>
                    <th>验证码</th>
                    <th>用途</th>
                    <th>生成时间</th>
                    <th>用途</th>
                    <!--<th>操作</th>-->
            </tr>
            </thead>

            <tbody>
                <tr v-for="sms in smss">
                                <td>{{sms.id}}</td>
                                <td>{{sms.mobile}}</td>
                                <td>{{sms.code}}</td>
                                 <td>{{SMS_USE | optionKV(sms.use)}}</td>
                                <td>{{sms.at}}</td>
                                 <td>{{SMS_STATUS | optionKV(sms.status)}}</td>

                    <td>
                        <div class="hidden-sm hidden-xs btn-group">
                            <!--把循环中的每一个edit传入模态框上-->
                            <!--<button v-on:click="edit(sms)" class="btn btn-xs btn-info">
                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                            </button>

                            <button v-on:click="del(sms.id)" class="btn btn-xs btn-danger">
                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                            </button>-->

                        </div>

                    </td>
                </tr>

            </tbody>
        </table>

        <!--模态框-->
        <!--<div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">表单</h4>
                    </div>
                    <div class="modal-body">
                        &lt;!&ndash;水平表单&ndash;&gt;
                        <form class="form-horizontal">
                                      <div class="form-group">
                                          <label class="col-sm-2 control-label">手机号</label>
                                          <div class="col-sm-10">
                                              <input v-model="sms.mobile" class="form-control">
                                          </div>
                                      </div>
                                      <div class="form-group">
                                          <label class="col-sm-2 control-label">验证码</label>
                                          <div class="col-sm-10">
                                              <input v-model="sms.code" class="form-control">
                                          </div>
                                      </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">用途</label>
                                            <div class="col-sm-10">
                                                <select v-model="sms.use" class="form-control">
                                                    <option v-for="o in SMS_USE" v-bind:value="o.key">{{o.value}}</option>
                                                </select>
                                            </div>
                                        </div>
                                      <div class="form-group">
                                          <label class="col-sm-2 control-label">生成时间</label>
                                          <div class="col-sm-10">
                                              <input v-model="sms.at" class="form-control">
                                          </div>
                                      </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">用途</label>
                                            <div class="col-sm-10">
                                                <select v-model="sms.status" class="form-control">
                                                    <option v-for="o in SMS_STATUS" v-bind:value="o.key">{{o.value}}</option>
                                                </select>
                                            </div>
                                        </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button v-on:click="save()" type="button" class="btn btn-primary">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>&lt;!&ndash; /.modal-content &ndash;&gt;
            </div>&lt;!&ndash; /.modal-dialog &ndash;&gt;
        </div>--><!-- /.modal -->

        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div><!-- /.span -->
</template>

<script>
    import Pagination from "../../components/pagination";

    export default {
        components: {Pagination},
        name: "business-sms",
        data: function () {
            return {
                sms:{},
                smss: [],
                        SMS_USE: SMS_USE,
                        SMS_STATUS: SMS_STATUS,
            }
        },
        mounted: function () {
            //激活侧边栏状态写法1
            // this.$parent.activeSidebar("business-sms-sidebar");
            let _this = this;
            _this.$refs.pagination.size = 5;
            _this.list(1);
        },
        methods: {
            /**
             * 新增
             */
            /*add() {
                let _this = this;
                /!*新增的时候表单不用数据*!/
                _this.sms = {};
                $("#form-modal").modal("show");
            },*/

            /**
             * 查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/sms/list', {
                    page: page,
                    /*根据pagination名字获取组件*/
                    size: _this.$refs.pagination.size,
                })
                        .then((response) => {
                            Loading.hide();
                            /*接口返回的data是ChapterDto*/
                            let resp = response.data;
                            _this.smss = resp.content.list;
                            _this.$refs.pagination.render(page, resp.content.total);
                        })
            },

            /**
             * 保存
             */
            /*save() {
                let _this = this;

                /!*校验表单值是否合法*!/
                if (1 != 1
                      || !Validator.require(_this.sms.mobile, "手机号")
                      || !Validator.length(_this.sms.mobile, "手机号", 1, 50)
                      || !Validator.require(_this.sms.code, "验证码")
                      || !Validator.require(_this.sms.use, "用途")
                      || !Validator.require(_this.sms.at, "生成时间")
                      || !Validator.require(_this.sms.status, "用途")
                ) {
                    return;
                }

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/sms/save', _this.sms)
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
            },*/

            /**
             * 回显数据
             */
            /*edit(sms) {
                let _this = this;
                /!*_this.chapter = chapter; 这种写法有双向数据绑定的问题*!/
                _this.sms = $.extend({}, sms);

                $("#form-modal").modal("show");
            }
            ,*/

            /**
             * 删除数据
             * @param id
             */
            /*del(id) {
                let _this = this;
                Confirm.show("删除短信验证码后不可恢复111，确认删除？", function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/sms/delete/' + id, _this.sms).then((response) => {
                        Loading.hide();
                        let resp = response.data;
                        if (resp.success) {
                            Toast.success("删除成功");
                            _this.list(1);
                        }
                    });
                })
            }
            ,*/
        }
    }
</script>

<style>

</style>