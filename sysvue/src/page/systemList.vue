<!--设备列表  -->
<template>
  <div class="">
    <div>
      <div class="add">
        <el-button @click="handleClick" style="margin: 0 0 0 24px"
          >新增</el-button
        >
      </div>
      <div class="tablebox">
        <el-table
          ref="filterTable"
          :data="tableData"
          style="width: 100%"
          height="506"
          v-loading="loading"
        >
          <!-- <el-table-column prop="id" label="id" width="100"> </el-table-column> -->
          <el-table-column
            prop="acttimer"
            show-overflow-tooltip
            fixed
            label="acttimer"
          >
          </el-table-column>
          <el-table-column prop="cmd" show-overflow-tooltip label="cmd">
          </el-table-column>
          <el-table-column prop="display" show-overflow-tooltip label="display">
          </el-table-column>
          <el-table-column prop="end" show-overflow-tooltip label="end">
          </el-table-column>
          <el-table-column prop="failed" show-overflow-tooltip label="failed">
          </el-table-column>
          <el-table-column prop="filter" show-overflow-tooltip label="filter">
          </el-table-column>
          <el-table-column prop="flag" show-overflow-tooltip label="flag">
          </el-table-column>
          <el-table-column
            prop="function"
            show-overflow-tooltip
            label="function"
          >
          </el-table-column>
          <el-table-column prop="pass" show-overflow-tooltip label="pass">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            width="120"
            class-name="operation"
          >
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="editClick(scope.row)"
                >编辑</el-button
              >
              <el-popconfirm
                title="这是一段内容确定删除吗？"
                @confirm="deleteClick(scope.row)"
              >
                <el-button type="text" size="small" slot="reference"
                  >删除
                </el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div>
      <div class="fonter">
        <el-pagination
          layout="prev, pager, next"
          :total="total"
          :current-page="currentpage"
          :page-size="pagesize"
          @current-change="page"
          background
        >
        </el-pagination>
      </div>
    </div>
    <el-dialog
      title="详情"
      :visible.sync="dialogFormVisible"
      width="50%"
      center
      destroy-on-close
    >
        <el-form ref="formData" :model="formData" class="formbox">
          <!-- <el-form-item label="id" label-width="100px">
          <el-input v-model="formData.id" type="text" class="input" autocomplete="off" placeholder="请输入" ></el-input>
        </el-form-item> -->
            <el-form-item label="acttimer" label-width="100px">
              <el-input
                v-model="formData.acttimer"
                type="text"
                class="input"
                autocomplete="off"
                placeholder="请输入"
              ></el-input>
            </el-form-item>
          <el-form-item label="cmd" label-width="100px">
            <el-input
              v-model="formData.cmd"
              type="text"
              class="input"
              autocomplete="off"
              placeholder="请输入"
            ></el-input>
          </el-form-item>
          <el-form-item label="display" label-width="100px">
            <el-input
              v-model="formData.display"
              type="text"
              class="input"
              autocomplete="off"
              placeholder="请输入"
            ></el-input>
          </el-form-item>
          <el-form-item label="end" label-width="100px">
            <el-input
              v-model="formData.end"
              type="text"
              class="input"
              autocomplete="off"
              placeholder="请输入"
            ></el-input>
          </el-form-item>
          <el-form-item label="failed" label-width="100px">
            <el-input
              v-model="formData.failed"
              type="text"
              class="input"
              autocomplete="off"
              placeholder="请输入"
            ></el-input>
          </el-form-item>
          <el-form-item label="filter" label-width="100px">
            <el-input
              v-model="formData.filter"
              type="text"
              class="input"
              autocomplete="off"
              placeholder="请输入"
            ></el-input>
          </el-form-item>
          <el-form-item label="flag" label-width="100px">
            <el-input
              v-model="formData.flag"
              type="text"
              class="input"
              autocomplete="off"
              placeholder="请输入"
            ></el-input>
          </el-form-item>
          <el-form-item label="function" label-width="100px">
            <el-input
              v-model="formData.function"
              type="text"
              class="input"
              autocomplete="off"
              placeholder="请输入"
            ></el-input>
          </el-form-item>
          <el-form-item label="pass" label-width="100px">
            <el-input
              v-model="formData.pass"
              type="text"
              class="input"
              autocomplete="off"
              placeholder="请输入"
            ></el-input>
          </el-form-item> </el-form
      >
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSave">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import { mapState } from "vuex";
export default {
  name: "systemList",
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: [],
  data() {
    //这里存放数据
    return {
      loading: true,
      tableData: [],
      total: 0, //总条目数
      currentpage: 1, //当前页数
      pagesize: 5, //每页显示条目个数
      data: {}, //数据
      loading: true,
      dialogFormVisible: false,
      formData: {
        acttimer: "",
        cmd: "",
        display: "",
        end: "",
        filter: "",
        flag: "",
        function: "",
        failed: "",
        id: "",
        pass: "",
      },
      selectid: "",
    };
  },
  //监听属性 类似于data概念
  computed: {
    ...mapState(["userInfo"]),
  },
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    page(num) {
      let url = "/message?currentPage=";
      this.$axios
        .get(url + num)
        .then((res) => {
          const { code, data } = res.data;
          if (code == 200) {
            console.log(data);
            this.loading = false;
            this.tableData = data.records;
            this.total = data.total;
            this.currentpage = data.current;
            this.pagesize = data.size;
          } else {
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "error",
            });
          }
        })
        .catch((err) => {
          console.error(err);
        });
    },
    //新增 1
    handleClick() {
      this.selectid = 1;
      this.dialogFormVisible = true;
      this.formData = {};
    },
    //编辑 2
    editClick(row) {
      this.dialogFormVisible = true;
      this.formData.acttimer = row.acttimer;
      this.formData.cmd = row.cmd;
      this.formData.display = row.display;
      this.formData.end = row.end;
      this.formData.filter = row.filter;
      this.formData.flag = row.flag;
      this.formData.function = row.function;
      this.formData.failed = row.failed;
      this.formData.id = row.id;
      this.formData.pass = row.pass;
      this.selectid = 2;
    },
    //删除
    deleteClick(row) {
      this.$axios
        .get(`/deleteCmd?id=${row.id}&status=${this.userInfo.status}`)
        .then((res) => {
          console.log(res);
          if (res.data.code == 200) {
            this.loading = false;
            this.page(1);
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "success",
            });
          } else {
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "error",
            });
          }
        })
        .catch((err) => {
          console.error(err);
        });
    },

    onSave() {
      this.dialogFormVisible = false;
      console.log(this.formData);
      switch (this.selectid) {
        case 1:
          this.add();
          break;
        case 2:
          this.edit();
          break;

        default:
          break;
      }
    },
    //新增
    add() {
      this.formData.id = "";
      let url = "/cmdadd";
      let params = JSON.stringify(this.formData);
      this.$axios
        .post(url, params, {
          headers: {
            Authorization: localStorage.getItem("token"),
            "Content-Type": "application/json;charset=utf-8",
          },
        })
        .then((res) => {
          if (res.data.code == 200) {
            this.page(this.currentpage);
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "success",
            });
          } else {
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "error",
            });
          }
        })
        .catch((err) => {
          console.error(err);
        });
    },
    //编辑
    edit() {
      let url = "/editlist";
      let params = JSON.stringify(this.formData);
      this.$axios
        .post(url, params, {
          headers: {
            // Authorization: localStorage.getItem("token"),
            "Content-Type": "application/json;charset=utf-8",
          },
        })
        .then((res) => {
          if (res.data.code == 200) {
            this.page(this.currentpage);
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "success",
            });
          } else {
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "error",
            });
          }
        })
        .catch((err) => {
          this.$message({
            message: err,
            showClose: true,
            type: "error",
          });
          console.error(err);
        });
    },
  },
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {
    this.page(1);
  },
  //生命周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style lang='scss' scoped>
//@import url(); 引入公共css类
.fonter {
  text-align: center;
  padding: 20px 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #fff;
}
.tablebox {
  padding-left: 24px;
  background: #fff;
}
.add {
  background: #fff;
  display: flex;
  width: 100%;
  height: 70px;
  align-items: center;
}
.formbox  {
    display: grid;
    grid-gap: 10px;
    grid-template-columns: repeat(auto-fill, minmax(258px, 1fr));
}
.input {
  width: 200px;
}
</style>
