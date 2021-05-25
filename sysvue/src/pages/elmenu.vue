<!-- 侧边菜单 -->
<template>
  <div>
    <template v-for="(item, index) in tag" :index="item.key">
      <template v-if="item.list">
        <el-submenu :index="item.key" :key="index">
          <template slot="title">
            <i :class="item.icon"></i>
            <span slot="title" v-show="!collapse">{{ item.title }}</span>
          </template>
          <el-menu-item-group>
            <el-menu-item
              v-for="items in item.list"
              :index="items.key"
              :key="items.key"
              >{{ items.title }}</el-menu-item
            >
          </el-menu-item-group>
        </el-submenu>
      </template>
      <template v-else>
        <el-menu-item :key="index" :index="item.key">
          <i :class="item.icon"></i>
          <span slot="title">{{ item.title }}</span>
        </el-menu-item>
      </template>
    </template>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';

export default {
  name: "",
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: {
     collapse: {
        type: Boolean,
        required: true
      }
},
  data() {
    //这里存放数据
    return {
      tag: [
        { title: "设备日志", icon: "el-icon-menu", key: "1" },
        {
          title: "设备列表",
          icon: "el-icon-s-grid",
          key: "2",
          list: [
            { title: "小匠", key: "2-1" },
            { title: "酷宅", key: "2-2" },
            { title: "涂鸦", key: "2-4" },
          ],
        },
        // { title: "设备信息", icon: "el-icon-tickets", key: "3" },
        { title: "设备订单", icon: "el-icon-document", key: "4" },
        { title: "用户管理", icon: "el-icon-user", key: "5" },
        { title: "数据录入", icon: "el-icon-finished", key: "7" },
        { title: "工具辅助", icon: "el-icon-setting", key: "6" },
        { title: "设备订单", icon: "el-icon-s-order", key: "8" },
      ],
    };
  },
  //监听属性 类似于data概念
  computed: {
  },
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
</style>
