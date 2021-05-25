<!-- 另一个数据设备list -->
<template>
  <div class="">
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
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="onSave">确 定</el-button>
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
    //这里存放数据
    return {
      formData: {
        orderId: "1",
        sn: "1",
        mac: "1",
        AddTime: "1",
        UpdateTime: "1",
        flag24g: "1",
        flag5g: "1",
        packages: "1",
        TestResult: "1",
      },
    };
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    onSave(){
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
    }
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
</style>
