<!-- 设备订单 -->
<template >
  <div class="">
    <div class="top">
      <el-form
        :inline="true"
        :model="formInline"
        ref="formInline"
        class="demo-form-inline"
      >
        <el-form-item label="订单号" prop="orderNumber" label-width="100px">
          <el-input
            v-model="formInline.orderNumber"
            placeholder="订单号"
            class="input"
          ></el-input>
        </el-form-item>
        <el-form-item label="项目" prop="projectName" label-width="100px">
          <el-input
            v-model="formInline.projectName"
            placeholder="项目"
            class="input"
          ></el-input>
        </el-form-item>
        <el-form-item label-width="100px">
          <el-button type="primary" @click="querylist">查询</el-button>
          <el-button type="primary" @click="resetForm">重置</el-button>
          <el-button type="primary" @click="handleClick">新增</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-- <div class="add">
      <el-button @click="handleClick()" style="margin: 0 0 0 24px"
        >新增</el-button
      >
    </div> -->
    <div class="ordertable">
      <el-table
        ref="filterTable"
        style="width: 100%"
        :data="tableData"
        height="618"
        v-loading="loading"
      >
        <el-table-column
          prop="orderNumber"
          show-overflow-tooltip
          fixed
          label="订单号"
        >
        </el-table-column>
        <el-table-column
          prop="projectName"
          show-overflow-tooltip
          label="项目"
          width="80"
        >
        </el-table-column>
        <el-table-column
          prop="productName"
          show-overflow-tooltip
          label="产品名称"
        >
        </el-table-column>
        <el-table-column
          prop="swVersion"
          show-overflow-tooltip
          label="软件版本号"
        >
        </el-table-column>
        <el-table-column
          prop="hwVersion"
          show-overflow-tooltip
          label="硬件版本号"
        >
        </el-table-column>
        <el-table-column
          prop="userSwVersion"
          show-overflow-tooltip
          label="APP版本"
        >
        </el-table-column>
        <el-table-column
          prop="orderQuantity"
          show-overflow-tooltip
          label="数量"
        >
        </el-table-column>
        <el-table-column
          prop="cloudPlatform"
          show-overflow-tooltip
          label="云端"
        >
        </el-table-column>
        <el-table-column prop="info" show-overflow-tooltip label="信息">
        </el-table-column>
        <el-table-column prop="bak" show-overflow-tooltip label="文件">
        </el-table-column>
        <el-table-column prop="atTable" show-overflow-tooltip label="表">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          class-name="operation"
          width="100"
        >
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="editClick(scope.row)"
              >编辑</el-button
            >
            <!-- <el-popconfirm
              title="这是一段内容确定删除吗？"
              @confirm="deleteClick(scope.row)"
            >
              <el-button  style="" type="text" size="small" slot="reference"
                >删除
              </el-button>
            </el-popconfirm> -->
            <el-button type="text" size="small" @click="addclick(scope.row)"
              >新增</el-button
            ></template
          >
        </el-table-column>
      </el-table>
    </div>
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

    <el-dialog
      title="详情"
      :visible.sync="dialogFormVisible"
      width="50%"
      center
    >
      <el-form ref="formData" :model="formData" class="formbox">
        <el-form-item label="订单号" label-width="100px">
          <el-input
            v-model="formData.orderNumber"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="orderNumber"
          ></el-input>
        </el-form-item>
        <el-form-item label="文件" label-width="100px">
          <el-input
            v-model="formData.bak"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="bak"
          ></el-input>
        </el-form-item>
        <el-form-item label="信息" label-width="100px">
          <el-input
            v-model="formData.info"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="info"
          ></el-input>
        </el-form-item>
        <el-form-item label="表" label-width="100px">
          <el-input
            v-model="formData.atTable"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="atTable"
          ></el-input>
        </el-form-item>
        <el-form-item label="云端" label-width="100px">
          <el-input
            v-model="formData.cloudPlatform"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="cloudPlatform"
          ></el-input>
        </el-form-item>
        <el-form-item label="数量" label-width="100px">
          <el-input
            v-model="formData.orderQuantity"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="orderQuantity"
          ></el-input>
        </el-form-item>
        <el-form-item label="APP版本" label-width="100px">
          <el-input
            v-model="formData.userSwVersion"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="userSwVersion"
          ></el-input>
        </el-form-item>
        <el-form-item label="硬件版本号" label-width="100px">
          <el-input
            v-model="formData.hwVersion"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="hwVersion"
          ></el-input>
        </el-form-item>
        <el-form-item label="软件版本号" label-width="100px">
          <el-input
            v-model="formData.swVersion"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="swVersion"
          ></el-input>
        </el-form-item>
        <el-form-item label="产品名称" label-width="100px">
          <el-input
            v-model="formData.productName"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="productName"
          ></el-input>
        </el-form-item>
        <el-form-item label="项目" label-width="100px">
          <el-input
            v-model="formData.projectName"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="projectName"
          ></el-input>
        </el-form-item>
      </el-form>
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
  name: "orderList",
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
        atTable: "",
        bak: "",
        cloudPlatform: "",
        hwVersion: "",
        // id: "",
        info: "",
        orderNumber: "",
        orderQuantity: "",
        productName: "",
        projectName: "",
        swVersion: "",
        userSwVersion: "",
      },
      selectid: "",
      formInline: {
        orderNumber: "",
        projectName: "",
      },
    };
  },
  //监听属性 类似于data概念
  computed: { ...mapState(["userInfo"]) },
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    page(num) {
      let url = `/order?currentPage=${num}`;
      let url1 = `/queryorderlist?currentPage=${num}&projectName=${this.formInline.projectName}&orderNumber=${this.formInline.orderNumber}`;
      let urls =
        this.formInline.projectName == "" && this.formInline.orderNumber == ""
          ? url
          : url1;
      this.$axios
        .get(urls)
        .then((res) => {
          const { code, data } = res.data;
          if (code == 200) {
            console.log(data);
            this.loading = false;
            //  this.tableData = data.list;
            //   this.total = data.totalRows;
            //   this.currentpage = data.page;
            //   this.pagesize = data.pagesize;
            this.tableData = res.data.data.records;
            this.total = res.data.data.total;
            this.currentpage = res.data.data.current;
            this.pagesize = res.data.data.size;
            this.$nextTick(() => {
              this.$refs.filterTable.bodyWrapper.scrollTop = 0;
            });

            this.$message.success(res.data.msg);
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
    //新增 1
    handleClick() {
      this.selectid = 1;
      this.dialogFormVisible = true;
      this.formData = {};
      // this.$refs.formData.resetFields();
    },
    //新增 2
    addclick(row) {
      this.selectid = 1;
      this.dialogFormVisible = true;
      this.formData = row;

      // this.formData.atTable = row.atTable;
      // this.formData.bak = row.bak;
      // this.formData.cloudPlatform = row.cloudPlatform;
      // this.formData.hwVersion = row.hwVersion;
      // this.formData.info = row.info;
      // this.formData.orderNumber = row.orderNumber;
      // this.formData.orderQuantity = row.orderQuantity;
      // this.formData.productName = row.productName;
      // this.formData.projectName = row.projectName;
      // this.formData.swVersion = row.swVersion;
      // this.formData.userSwVersion = row.userSwVersion;
    },
    //编辑 2
    editClick(row) {
      this.dialogFormVisible = true;
      this.formData = row;
      // this.formData.atTable = row.atTable;
      // this.formData.bak = row.bak;
      // this.formData.cloudPlatform = row.cloudPlatform;
      // this.formData.hwVersion = row.hwVersion;
      // this.formData.id = row.id;
      // this.formData.info = row.info;
      // this.formData.orderNumber = row.orderNumber;
      // this.formData.orderQuantity = row.orderQuantity;
      // this.formData.productName = row.productName;
      // this.formData.projectName = row.projectName;
      // this.formData.swVersion = row.swVersion;
      // this.formData.userSwVersion = row.userSwVersion;
      this.selectid = 2;
      // console.log(row);
    },
    onSave() {
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
      let url = "/orderadd";
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
            this.dialogFormVisible = false;
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
    /**
 * , {
          headers: {
            Authorization: localStorage.getItem("token"),
            "Content-Type": "application/json;charset=utf-8",
          },
        }
 */
    //编辑
    edit() {
      let url = "/editorder";
      let params = JSON.stringify(this.formData);
      this.$axios
        .post(url, params, {
          headers: {
            "Content-Type": "application/json;charset=utf-8",
          },
        })
        .then((res) => {
          if (res.data.code == 200) {
            this.dialogFormVisible = false;
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
    //删除
    deleteClick(row) {
      this.$axios
        .get(`/deleteorder?id=${row.id}&status=${this.userInfo.status}`)
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
          this.$message({
            message: err,
            showClose: true,
            type: "error",
          });
          console.error(err);
        });
    },
    querylist() {
      this.page(1);
    },
    resetForm() {
      this.$refs.formInline.resetFields();
      this.page(1);
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

.ordertable {
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
.fonter {
  text-align: center;
  padding: 20px 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #fff;
}
.formbox {
  display: grid;
  grid-gap: 10px;
  grid-template-columns: repeat(auto-fill, minmax(258px, 1fr));
  align-items: end;
}
.input {
  width: 200px;
}
.top {
  display: flex;
  align-items: center;
  margin: 0 0 10px 0;
  background: #fff;
  width: 100%;
  >>> .el-form-item {
    margin-bottom: 0;
  }
}
.demo-form-inline {
  padding: 24px;
  display: grid;
  grid-gap: 10px;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  width: 100%;
}
</style>
