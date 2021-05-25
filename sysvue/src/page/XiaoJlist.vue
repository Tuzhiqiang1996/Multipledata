<!--  -->
<template>
  <div class="contentbox">
      <div class="top">
        <el-form :model="formInline" ref="formInline" class="demo-form-inline">
          <el-form-item label="序列号" prop="sn" label-width="100px">
            <el-input
              class="input"
              v-model="formInline.sn"
              placeholder="sn序列号"
            ></el-input>
          </el-form-item>
          <el-form-item label="订单id" prop="orderId" label-width="100px">
            <el-input
              v-model="formInline.orderId"
              placeholder="订单id"
              class="input"
            ></el-input>
          </el-form-item>
          <el-form-item label="设备id" prop="deviceid" label-width="100px">
            <el-input
              v-model="formInline.deviceid"
              placeholder="设备id"
              class="input"
            ></el-input>
          </el-form-item>
          <el-form-item label="时间范围" prop="valuetime" label-width="100px">
            <el-date-picker
              v-model="formInline.valuetime"
              type="datetimerange"
              :picker-options="pickerOptions"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              align="right"
              @change="btntime"
              value-format="yyyy-MM-dd HH:mm:ss"
              style="width:100%"
            >
            </el-date-picker
          ></el-form-item>
          <el-form-item label-width="100px">
            <el-button type="primary" @click="onSubmit">查询</el-button>
            <el-button type="primary" @click="resetForm">重置</el-button>
          </el-form-item>
        </el-form>
        <!-- <el-tag type="info">默认显示最近24小时</el-tag> -->
      </div>
    <div style="width: 100%">
      <div class="tablebox">
        <el-table
          ref="filterTable"
          style="width: 100%"
          :data="tableData"
          height="629"
          v-loading="loading"
          @filter-change="handleFilterChange"
          @row-click="showdialog"
        >
          <el-table-column prop="sn" show-overflow-tooltip label="序列号">
          </el-table-column>
          <el-table-column prop="id" label="Id" width="80"> </el-table-column>
          <el-table-column prop="orderId" show-overflow-tooltip label="订单id">
          </el-table-column>
          <el-table-column prop="deviceid" show-overflow-tooltip label="设备id">
          </el-table-column>
          <el-table-column prop="addr1" show-overflow-tooltip label="秘钥A">
          </el-table-column>
          <el-table-column prop="addr2" show-overflow-tooltip label="秘钥B">
          </el-table-column>
          <el-table-column
            prop="createtime"
            show-overflow-tooltip
            label="创建时间"
          >
          </el-table-column>
          <!-- prop="testResult" -->
          <el-table-column label="测试结果" width="80" fixed="right">
            <template slot-scope="scope">
              <el-tag
                :type="
                  scope.row.testResult == 1
                    ? 'success'
                    : scope.row.testResult == 0
                    ? 'info'
                    : scope.row.testResult == -1
                    ? 'danger'
                    : 'warning'
                "
                disable-transitions
                >{{
                  scope.row.testResult == 1
                    ? "成功"
                    : scope.row.testResult == 0
                    ? "未测试"
                    : scope.row.testResult == -1
                    ? "失败"
                    : "NULL"
                }}</el-tag
              >
            </template>
          </el-table-column>
          <el-table-column
            prop="testDatetime"
            show-overflow-tooltip
            label="测试时间"
          >
          </el-table-column>
          <el-table-column
            prop="packages"
            show-overflow-tooltip
            label="检查结果"
            width="80"
            fixed="right"
          >
            <template slot-scope="scope">
              <el-tag
                :type="
                  scope.row.packages == 1
                    ? 'success'
                    : scope.row.packages == 0
                    ? 'info'
                    : scope.row.packages == -1
                    ? 'danger'
                    : 'warning'
                "
                disable-transitions
                >{{
                  scope.row.packages == 1
                    ? "成功"
                    : scope.row.packages == 0
                    ? "未测试"
                    : scope.row.packages == -1
                    ? "失败"
                    : "NULL"
                }}</el-tag
              >
            </template>
          </el-table-column>
          <el-table-column
            prop="checkDatetime"
            show-overflow-tooltip
            label="检查时间"
            width="160"
          >
          </el-table-column>
          <el-table-column
            prop="packageDatetime"
            show-overflow-tooltip
            label="包装时间"
            width="160"
          >
          </el-table-column>
          <el-table-column
            prop="checkCount"
            show-overflow-tooltip
            label="检查次数"
            width="90"
            column-key="tag"
            :filters="[
              { text: '0', value: '0' },
              { text: '1', value: '1' },
              { text: '2', value: '2' },
              { text: '3', value: '3' },
              { text: '4', value: '4' },
              { text: '5', value: '5' },
            ]"
            :filter-method="filterTag"
            :filter-multiple="false"
            filter-placement="bottom-end"
          >
            <template slot-scope="scope">
              {{ scope.row.checkCount }}
            </template>
          </el-table-column>
        </el-table>
      </div>
    <div style="width: 100%">
      <div class="fonter">
        <el-row :gutter="10" style="width: 100%">
          <el-col :sm="12" :md="6" :lg="3">
            <div class="fchild">
              0次测试 <el-tag>{{ num0 }} </el-tag>
            </div>
          </el-col>
          <el-col :sm="12" :md="6" :lg="3">
            <div class="fchild">
             一次测试 <el-tag>{{ num1 }} </el-tag>
            </div>
          </el-col>
          <el-col :sm="12" :md="6" :lg="3">
            <div class="fchild">
              二次测试 <el-tag>{{ num2 }} </el-tag>
            </div>
          </el-col>
          <el-col :sm="12" :md="6" :lg="3">
            <div class="fchild">
               三次测试 <el-tag>{{ num3 }} </el-tag>
            </div>
          </el-col>
          <el-col :sm="24" :md="24" :lg="12">
            <el-pagination
          layout="total, prev, pager, next, jumper"
          :total="total"
          :current-page="currentpage"
          :page-size="pagesize"
          @current-change="page"
          background
        >
        </el-pagination>
          </el-col>
        </el-row>
      </div>
    </div>
    </div>
    <el-dialog
      title="修改"
      :visible.sync="dialogFormVisible"
      width="50%"
      center
    >
      <el-form ref="formData" :model="formData" class="formbox">
        <el-form-item label="ID:" label-width="100px">
          <span>{{ formData.id }}</span>
        </el-form-item>
        <el-form-item label="序列号:" label-width="100px">
          <span>{{ formData.sn }}</span>
        </el-form-item>
        <el-form-item label="订单id:" label-width="100px">
          <span>{{ formData.orderId }}</span>
        </el-form-item>
        <el-form-item label="设备id:" label-width="100px">
          <el-input
            v-model="formData.deviceid"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="请输入"
          ></el-input>
        </el-form-item>
        <el-form-item label="秘钥A:" label-width="100px">
          <el-input
            v-model="formData.addr1"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="请输入"
          ></el-input>
        </el-form-item>
        <el-form-item label="秘钥B:" label-width="100px">
          <el-input
            v-model="formData.addr2"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="请输入"
          ></el-input>
        </el-form-item>
        <el-form-item label="创建时间:" label-width="100px">
          <el-input
            v-model="formData.createtime"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="请输入"
          ></el-input>
        </el-form-item>
        <el-form-item label="测试结果:" label-width="100px">
          <el-input
            v-model="formData.testResult"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="请输入"
          ></el-input>
        </el-form-item>
        <el-form-item label="测试时间:" label-width="100px">
          <el-input
            v-model="formData.testDatetime"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="请输入"
          ></el-input>
        </el-form-item>
        <el-form-item label="包装时间:" label-width="100px">
          <el-input
            v-model="formData.packageDatetime"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="请输入"
          ></el-input>
        </el-form-item>
        <el-form-item label="检查结果:" label-width="100px">
          <el-input
            v-model="formData.packages"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="请输入"
          ></el-input>
        </el-form-item>
        <el-form-item label="检查时间:" label-width="100px">
          <el-input
            v-model="formData.checkDatetime"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="请输入"
          ></el-input>
        </el-form-item>
        <el-form-item label="检查次数:" label-width="100px">
          <el-input
            v-model="formData.checkCount"
            type="text"
            class="input"
            autocomplete="off"
            placeholder="请输入"
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
import { mapGetters } from "vuex";
export default {
  name: "devList",
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: [],
  data() {
    //这里存放数据
    return {
      dialogFormVisible: false,
      num0: 0,
      num1: 0,
      num2: 0,
      num3: 0,
      tag: "",
      tableData: [],
      total: 0, //总条目数
      currentpage: 1, //当前页数
      pagesize: 5, //每页显示条目个数
      data: {}, //数据
      loading: true,
      formData: {
        id: "",
        sn: "",
        orderId: "",
        deviceid: "",
        addr1: "",
        addr2: "",
        createtime: "",
        testResult: "",
        testDatetime: "",
        packageDatetime: "",
        packages: "",
        checkDatetime: "",
        checkCount: "",
      },
      formInline: {
        deviceid: "",
        orderId: "",
        valuetime: "",
        sn: "",
      },
      pickerOptions: {
        shortcuts: [
          {
            text: "最近2小时",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 2);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近六个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 180);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },
    };
  },
  //监听属性 类似于data概念
  computed: {
    ...mapGetters(["getUser"]),
  },
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    page(num, tag) {
      /**
       * 统计？？
       *
       */
      let time1 = this.formInline.valuetime && this.formInline.valuetime[0];
      let time2 = this.formInline.valuetime && this.formInline.valuetime[1];
      let url = `/xiaojlist?currentPage=${num}`;
      let url1 = `/searchXjlist?deviceid=${
        this.formInline.deviceid
      }&currentPage=${num}&orderId=${this.formInline.orderId}&starttime=${
        time1 || ""
      }&endtime=${time2 || ""}&sn=${this.formInline.sn}`;
      let urls =
        (this.formInline.deviceid == "" &&
          this.formInline.orderId == "" &&
          this.formInline.sn == "" &&
          time1 == "") ||
        time1 == undefined
          ? url
          : url1;

      this.$axios
        .get(urls)
        .then((res) => {
          const { code, data } = res.data;
          if (code == 200) {
            this.loading = false;
            // this.tableData = data.list;
            // this.total = data.totalRows;
            // this.currentpage = data.page;
            // this.pagesize = data.pagesize;
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
    numCount(num, count) {
      let time1 = this.formInline.valuetime && this.formInline.valuetime[0];
      let time2 = this.formInline.valuetime && this.formInline.valuetime[1];
      let url = `/xjtatistical?currentPage=${num}&starttime=${
        time1 || ""
      }&endtime=${time2 || ""}&num=${count}&orderId=${this.formInline.orderId}&deviceid=${
        this.formInline.deviceid
      }`;
      this.$axios
        .get(url)
        .then((res) => {
          const { code, data } = res.data;
          if (code == 200) {
            if (count == 0) {
              this.num0 = data;
            } else if (count == 1) {
              this.num1 = data;
            } else if (count == 2) {
              this.num2 = data;
            } else {
              this.num3 = data;
            }
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
        });
    },
    onSubmit() {
      this.page(1);
      this.num();
    },
    resetForm() {
      this.$refs.formInline.resetFields();
      this.page(1);
    },

    btntime() {
      if (this.formInline.valuetime) {
        this.onSubmit();
      }
    },
    filterTag(value, row) {
      //  console.log(value, row)
      return row.checkCount == value;
    },
    handleFilterChange(filters) {
      console.log(filters.tag[0]);
      // this.page(1,filters.tag[0])
    },
    num() {
      this.numCount(1, 0);
      this.numCount(1, 1);
      this.numCount(1, 2);
      this.numCount(1, 3);
    },
    onSave() {
      if (this.getUser.status != 2) {
        this.modification();
      }
    },
    modification() {
      let url = "/listFix";
      this.$axios
        .post(url, this.formData)
        .then((res) => {
          if (res.data.code == 200) {
            this.dialogFormVisible = false;
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
          this.$message({
            message: err,
            showClose: true,
            type: "error",
          });
        });
    },

    showdialog(e, n, t) {
      // console.log("dialog", e, n, t);
      if (this.getUser.status != 2) {
        this.dialogFormVisible = true;
      }
      this.formData.id = e.id;
      this.formData.sn = e.sn;
      this.formData.orderId = e.orderId;
      this.formData.deviceid = e.deviceid;
      this.formData.addr1 = e.addr1;
      this.formData.addr2 = e.addr2;
      this.formData.createtime = e.createtime;
      this.formData.testResult = e.testResult;
      this.formData.testDatetime = e.testDatetime;
      this.formData.packageDatetime = e.packageDatetime;
      this.formData.packages = e.packages;
      this.formData.checkDatetime = e.checkDatetime;
      this.formData.checkCount = e.checkCount;
    },
  },
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {
    this.page(1);
    this.num();
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
.contentbox {
  width: 100%;
  height: 100%;
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
.fonter {
  text-align: center;
  padding: 20px 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #fff;
  .fchild {
    padding: 0 10px 0 10px;
  }
}
.demo-form-inline {
  padding: 24px;
  display: grid;
  grid-gap: 10px;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  width: 100%;
}
.tablebox {
  padding-left: 24px;
  background: #fff;
}
.formbox {
  // display: grid;
  // grid-template-columns: auto auto auto;
  // grid-template-rows: auto auto auto;
  display: grid;
  grid-gap: 10px;
  grid-template-columns: repeat(auto-fill, minmax(258px, 1fr));
}
.input {
  // width: 200px;
}
</style>
