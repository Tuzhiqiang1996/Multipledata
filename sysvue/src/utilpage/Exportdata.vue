<!-- 数据导出 -->
<template>
  <div class="formbox">
    <p>数据导出</p>
    <el-form
      ref="formInline"
      :rules="regiRules"
      :model="formInline"
      label-width="80px"
      status-icon
    >
      <el-form-item label="前缀">
        <el-input
          v-model="formInline.label"
          placeholder="前缀名"
          style="width: 300px"
        ></el-input>
      </el-form-item>
      <el-form-item label="开始值" prop="user">
        <el-input
          v-model="formInline.user"
          placeholder="十进制数字"
          style="width: 300px"
        ></el-input>
      </el-form-item>
      <el-form-item label="结束值" prop="password">
        <el-input
          v-model="formInline.password"
          placeholder="十进制数字"
          style="width: 300px"
        ></el-input>
      </el-form-item>

      <el-form-item label="文件名">
        <el-input v-model="formInline.filename" style="width: 300px">
          <el-select
            slot="append"
            v-model="formInline.sufname"
            style="width: 75px"
          >
            <el-option value=".csv"> .csv </el-option>
            <el-option value=".xls"> .xls </el-option>
            <el-option value=".xlsx"> .xlsx </el-option>
          </el-select>
        </el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit('formInline')"
          >生成文件</el-button
        >
        <el-button @click="resetForm">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';

export default {
  name: "Exportdata",
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
      } else if (value <= this.formInline.user) {
        callback(new Error("不得小于等于开始值"));
      } else {
        callback();
      }
    };
    //这里存放数据
    return {
      formInline: {
        label: "",
        user: "",
        password: "",
        filename: "数据表",
        sufname: ".csv",
      },
      list: [],
      strdata: "",
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
    resetForm() {
      this.formInline.label = "";
      // this.formInline.user = "";
      // this.formInline.password = "";
      this.$refs.formInline.resetFields();
    },
    onSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.loopfun();
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    loopfun() {
      let v1 = +this.formInline.user;
      let v2 = +this.formInline.password;
      let v3 = this.formInline.label;
      let list = [];

      for (let i = v1; i <= v2; i++) {
        let v4 = v3 + i.toString(16).toLocaleUpperCase();
        // console.log(v4);
        list.push(v4);
      }
      this.list = list;
      this.Arraylist();
    },
    //导出到
    Arraylist() {
      let list = this.list;
      let str = "";
      for (const key in list) {
        str += `${list[key] + "\n"}`;
      }
      console.log("e", str);
      this.strdata = str;
      this.files(str);
    },
    //下载到本地
    files(str) {
      const uri =
        "data:text/csv;charset=utf-8,\ufeff" + encodeURIComponent(this.strdata);
      // 通过创建a标签实现
      const link = document.createElement("a");
      link.href = uri;
      // 对下载的文件命名
      link.download = `${this.formInline.filename}${this.formInline.sufname}`;
      link.click();
      this.$message.success("操作成功！");
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
.formbox {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}
</style>
