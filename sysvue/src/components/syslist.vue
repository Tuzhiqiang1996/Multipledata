<!--  -->
<template>
  <div class="" style="width: 100%">
    <div style="">
      <div class="top">
        <el-form
          :inline="true"
          :model="formInline"
          ref="formInline"
          class="demo-form-inline"
        >
          <el-form-item label="订单id" prop="orderId">
            <el-input
              v-model="formInline.orderId"
              placeholder="订单id"
            ></el-input>
          </el-form-item>
          <el-form-item label="设备id" prop="deviceid">
            <el-input
              v-model="formInline.deviceid"
              placeholder="设备id"
            ></el-input>
          </el-form-item>

          <el-form-item label="时间范围" prop="valuetime">
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
            >
            </el-date-picker
          ></el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">查询</el-button>
            <el-button type="primary" @click="resetForm">重置</el-button>
          </el-form-item>
        </el-form>
        <!-- <el-tag type="info">默认显示最近24小时</el-tag> -->
      </div>
      <div class="tablebox">
        <el-table
          ref="filterTable"
          style="width: 100%"
          :data="tableData"
          height="629"
          v-loading="loading"
          @filter-change="handleFilterChange"
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
          {{this.listid=="X" ||this.listid=="T"||this.listid=="D"?
          <PackageDatetime />
          :
          <checkResult />
          }}

          <el-table-column
            prop="checkDatetime"
            show-overflow-tooltip
            label="检查时间"
            width="160"
          >
          </el-table-column>
          {{this.listid=="X" ||this.listid=="T"||this.listid=="D"?

          <el-table-column
            prop="packageDatetime"
            show-overflow-tooltip
            label="包装时间"
            width="160"
          >
          </el-table-column>
          :
          <div style="display: none"></div>
          }}

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
    </div>
    <div>
      <div class="fonter">
        <!-- layout="prev, pager, next" -->
        <div class="fchild">
          0次测试 <el-tag>{{ num0 }} </el-tag>
        </div>
        <div class="fchild">
          一次测试 <el-tag>{{ num1 }} </el-tag>
        </div>
        <div class="fchild">
          二次测试 <el-tag>{{ num2 }} </el-tag>
        </div>
        <div class="fchild">
          三+次测试 <el-tag>{{ num3 }} </el-tag>
        </div>
        <el-pagination
          layout="total, prev, pager, next, jumper"
          :total="total"
          :current-page="currentpage"
          :page-size="pagesize"
          @current-change="page"
          background
        >
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import PackageDatetime from "./PackageDatetime";
import checkResult from "./checkResult";
export default {
  name: "devList",
  //import引入的组件需要注入到对象中才能使用
  components: { PackageDatetime, checkResult },
  props: ["listid"],
  data() {
    //这里存放数据
    return {
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
      formData: {},
      formInline: {
        deviceid: "",
        orderId: "",
        valuetime: "",
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
      url1: "devList",
      url2: "searchlist",
      url3: "statistical",
    };
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    page(num) {
      let time1 = this.formInline.valuetime && this.formInline.valuetime[0];
      let time2 = this.formInline.valuetime && this.formInline.valuetime[1];
      let url = `/${this.url1}?currentPage=${num}`;
      let url1 = `/${this.url2}?deviceid=${
        this.formInline.deviceid
      }&currentPage=${num}&orderId=${this.formInline.orderId}&starttime=${
        time1 || ""
      }&endtime=${time2 || ""}`;
      let urls =
        (this.formInline.deviceid == "" &&
          this.formInline.orderId == "" &&
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
            this.tableData = data.records;
            this.total = data.total;
            this.currentpage = data.current;
            this.pagesize = data.size;

            this.num();

            this.$nextTick(() => {
              this.$refs.filterTable.bodyWrapper.scrollTop = 0;
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
    numCount(num, count) {
      let time1 = this.formInline.valuetime && this.formInline.valuetime[0];
      let time2 = this.formInline.valuetime && this.formInline.valuetime[1];
      let url = `/${this.url3}?currentPage=${num}&starttime=${
        time1 || ""
      }&endtime=${time2 || ""}&num=${count}&orderId=${this.formInline.orderId}`;
      this.$axios
        .get(url)
        .then((res) => {
          const { code, data } = res.data;
          if (code == 200) {
            if (count == 0) {
              this.num0 = data.total;
            } else if (count == 1) {
              this.num1 = data.total;
            } else if (count == 2) {
              this.num2 = data.total;
            } else {
              this.num3 = data.total;
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
          console.error(err);
        });
    },
    onSubmit() {
      this.page(1);
    },
    resetForm() {
      this.page(1);
      this.$refs.formInline.resetFields();
    },

    btntime() {
      if (this.formInline.valuetime) {
        this.page(1);
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
  },
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {
    this.page(1);
    console.log("ds", this.listid);
    // console.log('d',sessionStorage.getItem("active"));

    /**
     *
     */
    //酷宅
    let datak = {
      id: "",
      sn: "",
      orderId: "",
      deviceid: "",
      addr1: "",
      addr2: "",
      createtime: "",
      testResult: "",
      testDatetime: "",
      checkResult: "",
      checkDatetime: "",
      checkCount: "",
    };
    //小匠 涂鸦 大华？
    let dataxt = {
      Id: "",
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
    };

    switch (this.listid) {
      case "K":
        this.formData = datak;
        this.url1 = "devList";
        this.url2 = "searchlist";
        this.url3 = "statistical";
        break;
      case "X":
        this.formData = dataxt;
        this.url1 = "xiaojlist";
        this.url2 = "searchXjlist";
        this.url3 = "xjtatistical";
        break;
      case "D":
        this.formData = dataxt;
        this.url1 = "xiaojlist";
        this.url2 = "searchXjlist";
        this.url3 = "xjtatistical";
        break;
      case "T":
        this.formData = dataxt;
        this.url1 = "TYlist";
        this.url2 = "searchTYlist";
        this.url3 = "tytatistical";
        break;

      default:
        break;
    }
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
.top {
  display: flex;
  align-items: center;
  margin: 0 0 10px 0;
  background: #fff;

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
}
.tablebox {
  padding-left: 24px;
  background: #fff;
}
</style>