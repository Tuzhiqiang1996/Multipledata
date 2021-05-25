<!--  -->
<template>
  <div class="">
    <div  >
      <div class="top">
        <el-form   :model="formInline"     ref="formInline" class="demo-form-inline">
          <el-form-item label="设备id" prop="deviceid" label-width="100px">
            <el-input
              v-model="formInline.deviceid"
              placeholder="设备id"
              clearable
            ></el-input>
          </el-form-item>
         <el-form-item label="时间范围" prop="datatime" label-width="100px">
            <el-date-picker
              v-model="formInline.datatime"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              align="right"
              value-format="yyyy-MM-dd HH:mm:ss"
              @change="btntime"
              :picker-options="pickerOptions"
              style="width:100%"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label-width="100px">
            <el-button type="primary" @click="onSubmit">查询</el-button>
            <el-button type="primary" @click="resetForm">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div  class="tablebox">

      <el-table
        ref="filterTable"
        style="width: 100%"
        :data="tableData"
        height="624"
        v-loading="loading"
      >
        <el-table-column prop="id" label="Id"  > </el-table-column>
        <el-table-column
          prop="deviceid"
          show-overflow-tooltip
          fixed
          label="设备id"

        >
        </el-table-column>
        <el-table-column
          prop="pcinfo"
          show-overflow-tooltip
          label="项目"

        >
        </el-table-column>
        <el-table-column
          prop="addtime"
          show-overflow-tooltip
          label="时间"

        >
        </el-table-column>
        <el-table-column
          prop="testData"
          show-overflow-tooltip
          label="信息"
        >
        </el-table-column>
        <el-table-column
          prop="testResult"
          label="结果"
          fixed="right"
        >
          <template slot-scope="scope">
            <!-- <el-tag
              :type="
                scope.row.testResult == 1
                  ? 'success'
                  : scope.row.testResult == 0
                  ? 'info'
                  : 'danger'
              "
              disable-transitions
              >{{
                scope.row.testResult == 1
                  ? "成功"
                  : scope.row.testResult == 0
                  ? "异常"
                  : "失败"
              }}</el-tag
            > -->
            {{ scope.row.testResult }}
          </template>
        </el-table-column>
      </el-table>
      </div>
    </div>
    <div>
      <div class="fonter">
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
export default {
  name: "devlogList",
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
      formData: {
        id: "",
        deviceid: "",
        pcinfo: "",
        addtime: "",
        testData: "",
        testResult: "",
      },
      formInline: {
        deviceid: "",
        datatime: "",
      },
      pickerOptions: {
        shortcuts: [
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
        ],
      },
    };
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    page(num) {
      let time1 = this.formInline.datatime && this.formInline.datatime[0];
      let time2 = this.formInline.datatime && this.formInline.datatime[1];
      let url1 = `/devlogList?currentPage=${num}`;
      let url2 = `/searchlog?deviceid=${
        this.formInline.deviceid
      }&currentPage=${num}&starttime=${time1 || ""}&endtime=${time2 || ""}`;
      let url = this.formInline.deviceid == "" && time1 == ""||time1 ==  undefined ? url1 : url2;

      this.$axios
        .get(url)
        .then((res) => {
          const { code, data } = res.data;
          if (code == 200) {
            console.log(data);
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
    onSubmit() {
      this.page(1);
      // console.log(this.formInline.deviceid);
    },
    btntime() {
      if (this.formInline.datatime) {
        this.page(1);
      }
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
.top {
  display: flex;
  align-items: center;
  margin: 0 0 10px 0;
  background: #fff;

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
.tablebox{
  padding-left: 24px;
   background: #fff;
}
.fonter {
  text-align: center;
  padding: 20px 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #fff;

}
.fonter {
  text-align: center;
  padding: 20px 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #fff;

}
</style>
