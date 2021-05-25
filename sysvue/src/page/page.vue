<!--  -->
<template>
  <div class="contentbox">
    <div v-if="defaultactive == '1'"><DevlogList /></div>
    <!-- <div v-else-if="defaultactive == '2-1'"><Syslist :listid="listid" /></div> -->
    <!--小匠 X-->
    <!-- <div v-else-if="defaultactive == '2-2'"><Syslist :listid="listid" /></div> -->
    <!--酷宅 K-->
    <!-- <div v-else-if="defaultactive == '2-3'"><Syslist :listid="listid" /></div> -->
    <!--大华 D-->
    <!-- <div v-else-if="defaultactive == '2-4'"><Syslist :listid="listid" /></div> -->
    <!--涂鸦 T-->
    <div v-else-if="defaultactive == '2-1'"><XiaoJlist /></div>
    <div v-else-if="defaultactive == '2-2'"><DevList /></div>
    <div v-else-if="defaultactive == '2-3'"><DahuaList listid="D" /></div>
    <div v-else-if="defaultactive == '2-4'"><TyList /></div>
    <div v-else-if="defaultactive == '3'"><SystemList /></div>
    <div v-else-if="defaultactive == '4'"><OrderList /></div>
    <div v-else-if="defaultactive == '5'"><UseList /></div>
    <div v-else-if="defaultactive == '6'"><Utilpage /></div>
    <div v-else-if="defaultactive == '7'"><Dataentry /></div>
    <div v-else-if="defaultactive == '8'"><Orderlisttwo /></div>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import UseList from "./userlist";
import OrderList from "./orderList";
import SystemList from "./systemList";
import DevList from "./devList";
import DevlogList from "./devlogList";
import XiaoJlist from "./XiaoJlist";
import TyList from "./TyList";
import DahuaList from "./dahuaList";
import Utilpage from "../utilpage";
import Dataentry from "./dataentry";
import Orderlisttwo from "./orderlisttwo";
export default {
  name: "page",
  //import引入的组件需要注入到对象中才能使用
  //子组件接收来自父组件的值
  props: ["defaultactive"],
  components: {
    UseList,
    OrderList,
    SystemList,
    DevList,
    DevlogList,
    XiaoJlist,
    TyList,
    DahuaList,
    Utilpage,
    Dataentry,
    Orderlisttwo,
  },
  data() {
    //这里存放数据
    return {};
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {},
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
.contentbox {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  div {
    width: 100%;
  }
}
</style>
