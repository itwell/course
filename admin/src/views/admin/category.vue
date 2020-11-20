<template>
    <div class="page-content">
        <div class="row">
            <div class="col-md-6">
                <p>
                    <button v-on:click="add()" class="btn btn-white btn-default btn-round">
                        <i class="ace-icon fa fa-edit blue"></i>
                        新增
                    </button>
                    &nbsp;
                    <button v-on:click="all()" class="btn btn-white btn-default btn-round">
                        <i class="ace-icon fa fa-refresh green"></i>
                        刷新
                    </button>
                </p>

                <table id="level1-table" class="table  table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>名称</th>
                        <th>顺序</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="category in level1" v-on:click="onClickLevel1(category)" v-bind:class="{'active' : category.id === active.id}">
                        <td>{{category.id}}</td>
                        <td>{{category.name}}</td>
                        <td>{{category.sort}}</td>

                        <td>
                            <div class="hidden-sm hidden-xs btn-group">
                                <!--把循环中的每一个edit传入模态框上-->
                                <button v-on:click="edit(category)" class="btn btn-xs btn-info">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>

                                <button v-on:click="del(category.id)" class="btn btn-xs btn-danger">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>

                            </div>

                        </td>
                    </tr>

                    </tbody>
                </table>
            </div>

            <div class="col-md-6">
                <p>
                    <button v-on:click="add()" class="btn btn-white btn-default btn-round">
                        <i class="ace-icon fa fa-edit blue"></i>
                        新增
                    </button>
                    &nbsp;
                </p>

                <table id="level2-table" class="table  table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>名称</th>
                        <th>顺序</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="category in level2">
                        <td>{{category.id}}</td>
                        <td>{{category.name}}</td>
                        <td>{{category.sort}}</td>

                        <td>
                            <div class="hidden-sm hidden-xs btn-group">
                                <!--把循环中的每一个edit传入模态框上-->
                                <button v-on:click="edit(category)" class="btn btn-xs btn-info">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>

                                <button v-on:click="del(category.id)" class="btn btn-xs btn-danger">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>

                            </div>

                        </td>
                    </tr>

                    </tbody>
                </table>
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
                                          <label class="col-sm-2 control-label">父id</label>
                                          <div class="col-sm-10">
                                              <input v-model="category.parent" class="form-control">
                                          </div>
                                      </div>
                                      <div class="form-group">
                                          <label class="col-sm-2 control-label">名称</label>
                                          <div class="col-sm-10">
                                              <input v-model="category.name" class="form-control">
                                          </div>
                                      </div>
                                      <div class="form-group">
                                          <label class="col-sm-2 control-label">顺序</label>
                                          <div class="col-sm-10">
                                              <input v-model="category.sort" class="form-control">
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

    </div><!-- /.span -->
</template>

<script>

    export default {
        name: "business-category",
        data: function () {
            return {
                category:{},
                categorys: [],
                level1: [],
                level2: [],
                active: {},
            }
        },
        mounted: function () {
            //激活侧边栏状态写法1
            // this.$parent.activeSidebar("business-category-sidebar");
            let _this = this;
            _this.all();
        },
        methods: {
            /**
             * 新增
             */
            add() {
                let _this = this;
                /*新增的时候表单不用数据*/
                _this.category = {};
                $("#form-modal").modal("show");
            },

            /**
             * 查询
             */
            all() {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/all').then((response) => {
                            Loading.hide();
                            /*接口返回的data是ChapterDto*/
                            let resp = response.data;
                            _this.categorys = resp.content;

                    _this.level1 = [];
                    //将所有数据格式化成树形结构
                    for (let i = 0; i < this.categorys.length ; i++) {
                        let c = _this.categorys[i];
                        if(c.parent === '00000000'){
                            _this.level1.push(c);
                            for (let j = 0; j < this.categorys.length ; j++) {
                                let child = _this.categorys[j];
                                if(child.parent === c.id){
                                    if (Tool.isEmpty(c.children)){
                                        c.children = [];
                                    }
                                    c.children.push(child);
                                }
                            }
                        }
                    }
                })
            },

            /**
             * 保存
             */
            save() {
                let _this = this;

                /*校验表单值是否合法*/
                if (1 != 1
                      || !Validator.require(_this.category.parent, "父id")
                      || !Validator.require(_this.category.name, "名称")
                      || !Validator.length(_this.category.name, "名称", 1, 50)
                ) {
                    return;
                }

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/save', _this.category)
                        .then((response) => {
                            Loading.hide();
                            let resp = response.data;
                            if (resp.success) {
                                $("#form-modal").modal("hide");
                                Toast.success("保存成功");
                                _this.all();
                            } else {
                                Toast.warning(resp.message);
                            }
                        })
            },

            /**
             * 回显数据
             */
            edit(category) {
                let _this = this;
                /*_this.chapter = chapter; 这种写法有双向数据绑定的问题*/
                _this.category = $.extend({}, category);

                $("#form-modal").modal("show");
            }
            ,

            /**
             * 删除数据
             * @param id
             */
            del(id) {
                let _this = this;
                Confirm.show("删除分类后不可恢复111，确认删除？", function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/category/delete/' + id, _this.category).then((response) => {
                        Loading.hide();
                        let resp = response.data;
                        if (resp.success) {
                            Toast.success("删除成功");
                            _this.all();
                        }
                    });
                })
            },
            onClickLevel1(category){
                let _this = this;
                _this.active = category;
                _this.level2 = category.children;
            }
        }
    }
</script>

<style>
    .active td{
        background-color: #d6e9c6 !important;
    }
</style>