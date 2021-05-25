<template>
  <div class="box">
    <div class="content">
      <div style="">
        可选参数：
        <el-input
          style="width: 300px"
          v-model="orderid"
          placeholder="手动输入orderid"
        ></el-input>
      </div>
      <div style="padding-top: 25px">
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
          <el-form-item label="订单号：">
            <el-input
              v-model="formInline.ordernumber"
              placeholder="例：B02-F_WTW-SNL-02_170"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">查询</el-button>
            <el-button
              type="primary"
              @click="savelistbtn"
              :disabled="isdisabled"
              >插入</el-button
            >
          </el-form-item>
        </el-form>
      </div>
      <el-radio-group v-model="radio" @change="changeradio">
        <div style="padding-top: 25px">
          <el-tooltip
            class="item"
            effect="dark"
            content="tb_device_list_re705"
            placement="top"
          >
            <el-radio v-model="radio" label="1">酷宅云</el-radio>
          </el-tooltip>
          <el-tooltip
            class="item"
            effect="dark"
            content="tb_device_list_re755"
            placement="top"
          >
            <el-radio v-model="radio" label="2">涂鸦云</el-radio>
          </el-tooltip>
          <el-tooltip
            class="item"
            effect="dark"
            content="tb_device_list_xiaojiang"
            placement="top"
          >
            <el-radio v-model="radio" label="3">小匠</el-radio>
          </el-tooltip>
        </div>
      </el-radio-group>
      <el-tabs
        v-model="activeName"
        tab-position="left"
        style="padding: 10px"
        @tab-click="handleClick"
      >
        <el-tab-pane label="单列数据" name="1">
          <el-upload
            class="upload-demo"
            drag
            ref="upload"
            action="https://jsonplaceholder.typicode.com/posts/"
            multiple
            :auto-upload="false"
            :file-list="fileList"
            :on-change="handleChange"
            :on-success="success"
            :http-request="uploadFile"
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或<em>点击上传</em>
            </div>
            <div class="el-upload__tip" slot="tip">支持单列数据格式.CSV</div>
          </el-upload>
        </el-tab-pane>
        <el-tab-pane disabled label="多列数据" name="3">
          <el-upload
            class="upload-demo"
            drag
            ref="upload"
            action="https://jsonplaceholder.typicode.com/posts/"
            multiple
            :auto-upload="false"
            :file-list="fileList"
            :on-change="handleChange"
            :on-success="success"
            :http-request="uploadFile"
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或<em>点击上传</em>
            </div>
            <div class="el-upload__tip" slot="tip">支持多列数据格式.CSV</div>
          </el-upload>
        </el-tab-pane>
        <el-tab-pane label="数字导入" name="2">
          <div>
            <el-form
              ref="formInlinelist"
              :rules="regiRules"
              :model="formInlinelist"
              label-width="80px"
              status-icon
              class="formbox"
            >
              <el-form-item label="前缀">
                <el-input
                  v-model="formInlinelist.label"
                  placeholder="前缀名"
                  style="width: 300px"
                ></el-input>
              </el-form-item>
              <el-form-item label="开始值" prop="user">
                <el-input
                  v-model="formInlinelist.user"
                  placeholder="十进制数字"
                  style="width: 300px"
                ></el-input>
              </el-form-item>
              <el-form-item label="结束值" prop="password">
                <el-input
                  v-model="formInlinelist.password"
                  placeholder="十进制数字"
                  style="width: 300px"
                ></el-input>
              </el-form-item>
              <div>
                <el-button size="medium" @click="onSubmitnum('formInlinelist')"
                  >生成数字数组</el-button
                >
              </div>
            </el-form>
          </div>
        </el-tab-pane>
      </el-tabs>
      <div class="updata"></div>
      <div>
        <el-button size="medium" type="primary" @click="btn"
          >获取orderID</el-button
        >
        <el-button size="medium" type="primary" @click="btnorder"
          >插入order表</el-button
        >
        <el-button size="medium" @click="btns">插入devlist表</el-button>
      </div>
    </div>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';

export default {
  name: "",
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: [],
  data() {
    var validatePass1 = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请输入"));
      } else {
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请再次输入"));
      } else if (value <= this.formInlinelist.user) {
        callback(new Error("不得小于等于开始值"));
      } else {
        callback();
      }
    };
    //这里存放数据
    return {
      activeName: "1",
      radio: "1",
      fileList: [],
      ee: "",
      name: "",
      info: [],
      filename: "",
      num: "",
      orderid: "",
      datadevlist: [],
      formInline: {
        ordernumber: "",
      },
      isdisabled: true, // 是否查询到
      formInlinelist: {
        label: "",
        user: "",
        password: "",
      },
      regiRules: {
        user: [{ validator: validatePass1, trigger: "blur" }],
        password: [{ validator: validatePass2, trigger: "blur" }],
      },
    };
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    onSubmit() {
      if (!this.formInline.ordernumber) {
        this.$message({
          message: "文件名为空或者上传文件！",
          showClose: true,
          type: "error",
        });
        return;
      }
      let name = this.formInline.ordernumber;
      let file1 = name.split(".");
      let file2 = file1[0].split("_");
      let num = file2[2];
      let file3;
      if (file2[1]) {
        file3 = file2[0] + "_" + file2[1];
        if (file2[2]) {
          file3 = file2[0] + "_" + file2[1] + "_" + file2[2];
        }
      }
      console.log("e", file3, num);

      this.getorder(file2[0]);
      this.num = num;
      this.filename = file3;
    },
    savelistbtn() {
      if (this.info.orderNumber) {
        this.info.orderNumber = this.filename;
        this.orderlist();
      } else {
        this.$message({
          message: "请先上传文件！",
          showClose: true,
          type: "error",
        });
      }
    },
    handleChange(file, fileList) {
      //   this.fileList = file.raw;
      this.fileList = fileList.slice(-1);

      this.$message({
        message: "文件已上传！",
        showClose: true,
        type: "success",
      });
      this.name = file.name;
      console.log(file);
    },
    //解析、查询文件名
    btn() {
      let flie = this.name;
      if (!flie) {
        this.$message({
          message: "请先上传文件！",
          showClose: true,
          type: "error",
        });
        return;
      }
      let file1 = flie.split(".");
      let file2 = file1[0].split("_");
      let num = file2[2];
      let file3 = file2[0] + "_" + file2[1] || "" + "_" + file2[2] || "";

      this.getorder(file2[0]);

      console.log(file2);
      this.filename = file3;
      this.num = num;
    },
    //插入表中事件 order
    btnorder() {
      let flie = this.name;

      if (!flie) {
        this.$message({
          message: "请先上传文件！",
          showClose: true,
          type: "error",
        });
        return;
      }
      this.info.orderNumber = this.filename;
      this.orderlist();
    },
    //获取相同类型额 id order
    getorder(order) {
      let url = "/getlistnumber?orderNumber=";
      this.$axios
        .get(url + order)
        .then((res) => {
          if (res.data.code == 200) {
            this.info = res.data.data[0];
            // this.info.orderNumber = this.filename;
            this.info.info = this.num;
            this.info.id = "";
            this.isdisabled = false;
            console.log("dd", res.data.data[0]);

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
        });
    },

    //插入表中 order
    orderlist() {
      let url = "/saveorderfile";

      this.$axios
        .post(url, this.info)
        .then((res) => {
          if (res.data.code == 200) {
            this.orderid = res.data.data.id;
            console.log("dd", this.orderid);
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
        });
    },
    success(response, file, fileList) {
      console.log("success", response, file, fileList);
      // let reader = new FileReader();
      // reader.readAsText(file.raw);
      // reader.onload = (e) => {
      //   this.ee = e.target.result;
      //   console.log(e.target.result);
      //   this.datadevlist = e.target.result;
      //   // this.refile(fileList);
      //   this.arraylist(this.datadevlist);
      // };
    },
    //选择 单列
    arraylist(e) {
      if (this.activeName == 3) {
        this.morearrylists();
      } else {
        this.onlyarrylist();
      }
    },
    //格式化数字 单列
    onlyarrylist() {
      let datalist = this.datadevlist;
      let list = datalist.replace(/([.\n\r]+)/g, ",");
      let lista = list.split(",");
      this.Selectcloud(lista);
    },
    //格式化数字 多列
    morearrylists() {
      let datalist = this.datadevlist;
      let list = datalist.replace(/([.\n\r]+)/g, "#*#");
      let arr = new Array();
      arr = list.split(",");
      if (arr.length == 1) {
        this.$message({
          message: "请上传多列数据文件！",
          showClose: true,
          type: "error",
        });

        return false;
      }
      let listsplit = list.split("#*#");
      console.log("list", listsplit);
      let listpush = [];
      for (let index = 0; index < listsplit.length; index++) {
        let listarry = listsplit[index].split(",");
        listpush.push(listarry);
      }

      if (this.radio == 1) {
        this.morearrylistku(listpush);
      } else {
        this.morearrylist(listpush);
      }
    },
    /**
     * 导入须知
     * 确保文件格式为.csv
     * 内容不存在中文
     * 导入对应的属性均为字符串格式
     * 其他待优化中
     * tuya
     */
    morearrylist(data) {
      let datalist = {};
      let datalist2 = [];
      for (let index = 0; index < data.length; index++) {
        for (let indexs = 0; indexs < data[index].length; indexs++) {
          datalist = {
            deviceid: data[index][3],
            sn: data[index][0],
            addr1: data[index][1],
            addr2: data[index][2],
            testResult: "",
            testDatetime: "",
            checkDatetime: "",
            createTime: "",
            checkCount: "",
            orderId: this.orderid,
            packages: "",
            packageDatetime: "",
          };
        }
        datalist2.push(datalist);
      }
      console.log("morearry", datalist2);

      this.$message({
        message: "多列数据准备就绪！",
        showClose: true,
        type: "success",
      });
      this.refile(datalist2);
    },
    /**
     * ku
     * 插入过程中对于字段太长的  关键在于表中的注释（COMMENT）COMMENT的区别
     */
    morearrylistku(data) {
      console.log("ku");

      let datalist = {};
      let datalist2 = [];
      for (let index = 0; index < data.length; index++) {
        for (let indexs = 0; indexs < data[index].length; indexs++) {
          datalist = {
            deviceid: data[index][1],
            sn: data[index][0],
            addr1: data[index][2],
            addr2: data[index][3],
            testResult: "",
            testDatetime: "",
            checkResult: "",
            checkDatetime: "",
            checkCount: "",
            orderId: this.orderid,
          };
        }
        datalist2.push(datalist);
      }
      console.log("morearry", datalist2);

      this.$message({
        message: "多列数据准备就绪！",
        showClose: true,
        type: "success",
      });
      this.refile(datalist2);
    },
    //选择云端
    Selectcloud(lista) {
      if (this.radio == 1) {
        this.datalists(lista, this.orderid);
      } else {
        this.datatylist(lista, this.orderid);
      }
    },
    //涂鸦 2 小匠
    datatylist(lista, id) {
      let datalist = {};
      let datalist4 = {};
      let datalist2 = [];
      let datalist3 = [];
      for (let i = 0; i < lista.length; i++) {
        datalist = {
          deviceid: "",
          sn: lista[i],
          addr1: "",
          addr2: "",
          testResult: "",
          testDatetime: "",
          checkDatetime: "",
          createTime: "",
          checkCount: "",
          orderId: this.orderid,
          packages: "",
          packageDatetime: "",
        };
        datalist4 = lista[i];
        datalist2.push(datalist);
        datalist3.push(datalist4);
        // console.log("12", datalist2);  得到的数组
      }
      //1
      this.refile(datalist2);

      //2
      // this.filehas(datalist3);
    },
    //酷宅 1
    datalists(lista, id) {
      let datalist = {};
      let datalist4 = {};
      let datalist2 = [];
      let datalist3 = [];
      for (let i = 0; i < lista.length; i++) {
        datalist = {
          deviceid: lista[i],
          sn: "",
          addr1: " ",
          addr2: " ",
          testResult: "",
          testDatetime: "",
          checkResult: "",
          checkDatetime: "",
          checkCount: "",
          orderId: this.orderid,
        };
        datalist4 = lista[i];
        datalist2.push(datalist);
        datalist3.push(datalist4);
        // console.log("12", datalist2);  得到的数组
      }
      //1
      this.refile(datalist2);
      //2
      // this.filehas(datalist3);
    },
    // 第一方法 执行成功
    refile(e) {
      this.$message({
        message: "数据插入准备，数量为" + e.length,
        showClose: true,
        type: "success",
      });

      let time = new Date();
      let timeold = time.getTime();
      let url =
        this.radio == 1
          ? "/receivefile"
          : this.radio == 2
          ? "/tyreceivefile"
          : "/xjlist";

      this.$axios
        .post(url, e, {
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          console.log(res);
          if (res.data.code == 200) {
            let timenew = new Date();
            let timenew1 = timenew.getTime();
            let timenew2 = timenew1 - timeold;

            this.$message({
              message: res.data.msg + "耗时：" + timenew2 + "ms",
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
    //第二套方法 可行
    filehas(e) {
      let url =
        this.radio == 1
          ? "/receivefilehas"
          : this.radio == 2
          ? "/tyreceivefilehas"
          : "/xjreceivefilehas";
      let params = {
        savefiles: e,
        id: this.orderid,
      };

      this.$axios
        .post(url, params, {
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          if (res.data.code == 200) {
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
    btns() {
      let flie = this.name;
      let orderid = this.orderid;
      if (!flie || !orderid) {
        this.$message({
          message: "请先上传文件！或者orderid为空",
          showClose: true,
          type: "error",
        });
        return false;
      }
      if (this.formInlinelist.user) {
        this.Selectcloud(this.datadevlist);
      } else {
        this.$refs.upload.submit();
      }
    },
    //覆盖上传 不走success函数
    uploadFile(params) {
      let reader = new FileReader();
      reader.readAsText(params.file);
      reader.onload = (e) => {
        this.ee = e.target.result;
        console.log(e.target.result);
        this.datadevlist = e.target.result;
        this.arraylist(this.datadevlist);
      };
    },
    handleClick(tab, event) {
      if (tab.name == 3) {
        this.name = "666";
      }
      // console.log('ee',this.activeName);
      //切换清楚数据
      this.datadevlist = [];
      this.fileList = [];
      this.formInlinelist = {};
      // console.log("ee", tab.name);
    },
    //数字生成
    onSubmitnum(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.loopfun();
          this.name = "666";
          this.$message({
            message: "数组已生成！",
            showClose: true,
            type: "success",
          });
        } else {
          this.$message({
            message: "error",
            showClose: true,
            type: "error",
          });
          console.log("error submit!!");
          return false;
        }
      });
    },
    loopfun() {
      let v1 = +this.formInlinelist.user;
      let v2 = +this.formInlinelist.password;
      let v3 = this.formInlinelist.label;
      let list = [];
      console.log("ee", v1, v2);

      for (let i = v1; i <= v2; i++) {
        let v4 = v3 + i.toString(16).toLocaleUpperCase();
        // console.log(v4);
        list.push(v4);
      }
      this.datadevlist = list;
    },
    //多列数据

    //单选
    changeradio() {
      // console.log('',this.radio); deviceid
      if (this.activeName != 3) {
        if (this.radio == 1) {
          this.$message({
            message: "已选择云端插入位置：deviceid",
            showClose: true,
            type: "success",
          });
        } else {
          this.$message({
            message: "已选择云端插入位置：sn",
            showClose: true,
            type: "success",
          });
        }
      }
    },
  },
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {},
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
.box {
  display: flex;
  justify-content: center;
  height: 100%;
}
.content {
  width: 100%;
  justify-content: center;
  align-items: center;
  text-align: center;
  display: flex;
  flex-flow: column;
  >>> .el-form-item {
    // margin: 0;
  }
}
.upload-demo {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;

  min-height: 300px;
  min-width: 450px;
}
.updata {
  display: flex;
  width: 100%;
  justify-content: space-evenly;
}
.formbox {
  display: flex;
  justify-content: center;
  flex-direction: column;
  min-height: 300px;
  min-width: 450px;
}
</style>
