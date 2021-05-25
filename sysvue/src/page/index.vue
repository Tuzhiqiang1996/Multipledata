<!--  -->
<template>
  <div class="box">
    <el-container style="height: 100%">
      <el-aside width="" style="background: #304156">
        <div v-if="isCollapse" class="title">
          <img src="../assets/image/E6L.png" alt="" class="img" />
        </div>
        <div v-else class="title">
          <h1>E6事业部</h1>
        </div>
        <!-- background-color="#00152a" -->
        <el-menu
          :default-active="defaultactive"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          :collapse="isCollapse"
          background-color="#1f2d3d"
          text-color="#fff"
          @select="select"
        >
          <Elmenu :collapse="isCollapse" />
          <!-- 循环解决-->

          <!-- <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <span slot="title">设备日志</span>
          </el-menu-item>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-s-grid"></i>
              <span slot="title">设备列表</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="2-1">小匠</el-menu-item>
              <el-menu-item index="2-2">酷宅</el-menu-item>
              <el-menu-item index="2-3" style="display: none"
                >大华</el-menu-item
              >
              <el-menu-item index="2-4">涂鸦</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-menu-item index="3" style="display: none">
            <i class="el-icon-tickets"></i>
            <span slot="title">设备信息</span>
          </el-menu-item>
          <el-menu-item index="4" v-show="getUser.status != 2">
            <i class="el-icon-document"></i>
            <span slot="title">设备订单</span>
          </el-menu-item>
          <el-menu-item index="5" v-show="getUser.status != 2">
            <i class="el-icon-user"></i>
            <span slot="title">用户管理</span>
          </el-menu-item>
          <el-menu-item index="7" v-show="getUser.status != 2">
            <i class="el-icon-finished"></i>
            <span slot="title">数据录入</span>
          </el-menu-item>
          <el-menu-item index="6">
            <i class="el-icon-setting"></i>
            <span slot="title">工具辅助</span>
          </el-menu-item> -->
        </el-menu>
        <div class="botinfo">123</div>
      </el-aside>
      <el-container>
        <el-header>
          <div style="" class="headers">
            <el-button
              :icon="isCollapse ? isbtn2 : isbtn1"
              @click="tarrget"
              class="btns"
            ></el-button>

            <div class="rightimg">
              <el-button
                icon="el-icon-rank"
                @click="click"
                class="btns"
                size="25"
              >
              </el-button>
              <P
                >欢迎：<i
                  class="el-icon-user-solid"
                  :style="{
                    color:
                      getUser.status == 0
                        ? '#FF0000'
                        : getUser.status == 1
                        ? '#FFFF00'
                        : '#00FFFF',
                  }"
                ></i
                >-{{ getUser.username }}</P
              >

              <el-dropdown @command="handleCommand" class="eldown">
                <el-avatar
                  :size="50"
                  :src="getUser.avatar"
                  @error="errorHandler"
                >
                  <img
                    src="https://img2.woyaogexing.com/2020/04/10/2911e52acd544055ab7f434108037632!400x400.webp"
                  />
                </el-avatar>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>退出</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
          </div>
        </el-header>
        <el-scrollbar style="height: 100%">
          <el-main>
            <div class="pagecontent">
              <!-- <transition name="fade-transform" mode="out-in"> -->
              <Pagehome :defaultactive="defaultactive" />
              <!-- </transition> -->
            </div>
          </el-main>
          <!-- <el-footer>Footer</el-footer> -->
        </el-scrollbar>
      </el-container>
    </el-container>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import Pagehome from "./page";
import Elmenu from "../pages/elmenu";
import { mapGetters } from "vuex";
import screenfull from "screenfull";
export default {
  name: "index",
  //import引入的组件需要注入到对象中才能使用
  components: {
    Pagehome,
    Elmenu,
  },
  data() {
    //这里存放数据
    return {
      isCollapse: false,
      isbtn1: "el-icon-s-fold",
      isbtn2: "el-icon-s-unfold",
      defaultactive: "2-1",
      onLine: navigator.onLine,
      isFullscreen: false,
    };
  },
  //监听属性 类似于data概念
  computed: {
    ...mapGetters(["getUser"]),
  },
  //监控data中的数据变化
  watch: {
    $route() {
      this.getDatanum(); //换成你的方法
    },
  },
  //方法集合
  methods: {
    getDatanum() {
      this.defaultactive = "2-1";
    },
    handleOpen(key, keyPath) {
      // console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      // console.log(key, keyPath);
    },
    tarrget() {
      this.isCollapse = !this.isCollapse;
    },
    select(key, keyPath) {
      console.log(key, keyPath);
      this.defaultactive = key;
      // sessionStorage.setItem("active",key)
    },
    errorHandler() {
      return true;
    },
    handleCommand(command) {
      this.$confirm("退出, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$axios
            .get("/logout", {
              headers: {
                // Authorization: localStorage.getItem("token"),
              },
            })
            .then((res) => {
              if (res.data.code == 200) {
                this.$store.commit("REMOVE_INFO");
                this.$router.push("/");
                // console.log("d", res);
                this.$message({
                  type: "success",
                  message: "退出成功!",
                });
              } else {
                this.$message({
                  type: "error",
                  message: "退出失败!",
                });
              }
            });
        })
        .catch((err) => {
          this.$message({
            type: "error",
            message: err,
          });
        });
    },
    updateOnlineStatus(e) {
      const { type } = e;
      this.onLine = type === "online";
      this.$message({
        type: "error",
        message: "网络由异常",
        showClose: true,
      });
      console.log(e);
    },
    //全屏
    click() {
      if (!screenfull.isEnabled) {
        this.$message({ message: "你的浏览器不支持全屏", type: "warning" });
        return false;
      }
      screenfull.toggle();
    },
    change() {
      this.isFullscreen = screenfull.isFullscreen;
    },
    init() {
      if (screenfull.isEnabled) {
        screenfull.on("change", this.change);
      }
    },
    destroy() {
      if (screenfull.isEnabled) {
        screenfull.off("change", this.change);
      }
    },
  },
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {},
  //生命周期 - 挂载完成（可以访问DOM元素）
  mounted() {
    window.addEventListener("online", this.updateOnlineStatus); // 网络由异常到正常时触发

    window.addEventListener("offline", this.updateOnlineStatus); // 网络由正常常到异常时触发
    this.init();
  },
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {
    window.removeEventListener("online", this.updateOnlineStatus);
    window.removeEventListener("offline", this.updateOnlineStatus);
    this.destroy();
  }, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style lang='scss' scoped>
//@import url(); 引入公共css类
.box {
  position: absolute;
  width: 100%;
  height: 100%;
  .el-scrollbar__wrap {
    overflow-x: hidden;
  }
  .el-scrollbar__view {
    height: 100%;
  }
}
.btns {
  font-size: 30px;
  background: transparent;
  border: 0;
  color: #333;
}
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
  height: 60px;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
  // min-width: 65px;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  // transition: width .2s ;
  width: 200px;
  // min-height: 400px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  //   line-height: 160px;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 100%;
  position: absolute;
}
.el-menu-vertical-demo {
  // height: 100%;
  border: 0;
  //  width: 100%;
  .el-menu-item.is-active {
    // background: rgba(212, 211, 224, 0.2) !important;
    margin: 0;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .el-menu-item {
    padding: 0 !important;
    &:hover {
      background: rgb(0, 21, 40) !important;
    }
  }
}
.headers {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100%;
}
.rightimg {
  display: flex;
  align-items: center;
  height: 100%;
  justify-content: space-evenly;
}
.eldown {
  height: 100%;
  padding-left: 10px;
  align-items: center;
  justify-content: center;
  display: flex;
}
.title {
  height: 60px;
  background-color: #fff;
  color: #333;
  display: flex;
  justify-content: center;
  align-items: center;
  // width: 300px;
  // height: 200px;
  // position: absolute;
  // left: 50%;
  // margin-left: -150px;
  background-image: -webkit-linear-gradient(
    left,
    blue,
    #66ffff 10%,
    #cc00ff 20%,
    #cc00cc 30%,
    #ccccff 40%,
    #00ffff 50%,
    #ccccff 60%,
    #cc00cc 70%,
    #cc00ff 80%,
    #66ffff 90%,
    blue 100%
  );
  -webkit-text-fill-color: transparent; /* 将字体设置成透明色 */
  -webkit-background-clip: text; /* 裁剪背景图，使文字作为裁剪区域向外裁剪 */
  -webkit-background-size: 200% 100%;
  -webkit-animation: masked-animation 4s linear infinite;
  // font-size: 35px;
}

@keyframes masked-animation {
  0% {
    background-position: 0 0;
  }
  100% {
    background-position: -100% 0;
  }
}
.pagecontent {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.botinfo {
  color: #fff;
  position: fixed;
  bottom: 0;
  left: 0;
  height: 50px;
  line-height: 50px;
  display: flex;
  justify-content: center;
  width: 200px;
  display: none;
}
/* fade-transform */
.fade-transform-leave-active,
.fade-transform-enter-active {
  transition: all 0.5s;
}

.fade-transform-enter {
  opacity: 0;
  transform: translateX(-30px);
}

.fade-transform-leave-to {
  opacity: 0;
  transform: translateX(30px);
}
.img {
  width: 40px;
  height: 32px;
}
</style>
