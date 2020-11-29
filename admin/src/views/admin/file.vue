<template>
    <div class="page-content">
        <p>
            <button v-on:click="list(1)" v-bind:list="list" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh green"></i>
                刷新
            </button>
        </p>

        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th>id</th>
                <th>相对路径</th>
                <th>文件名</th>
                <th>后缀</th>
                <th>大小</th>
                <th>用途</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="file in files">
                <td>{{file.id}}</td>
                <td>{{file.path}}</td>
                <td>{{file.name}}</td>
                <td>{{file.suffix}}</td>
                <td>{{file.size}}</td>
                <td>{{FILE_USE | optionKV(file.use)}}</td>
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
        name: "file-file",
        data: function () {
            return {
                file: {},
                files: [],
                FILE_USE: FILE_USE,
            }
        },
        mounted: function () {
            //激活侧边栏状态写法1
            // this.$parent.activeSidebar("file-file-sidebar");
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
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/file/admin/file/list', {
                    page: page,
                    /*根据pagination名字获取组件*/
                    size: _this.$refs.pagination.size,
                })
                    .then((response) => {
                        Loading.hide();
                        /*接口返回的data是ChapterDto*/
                        let resp = response.data;
                        _this.files = resp.content.list;
                        _this.$refs.pagination.render(page, resp.content.total);
                    })
            },
        }
    }
</script>

<style>

</style>