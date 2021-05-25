<!--  -->
<template>
  <div class="">
    <div class="adduser" v-show="this.userInfo.status == 0">
      <el-button @click="addclick()" style="margin: 0 0 0 24px">
        新增用户
      </el-button>
    </div>
    <div class="tablebox">
      <el-table
        v-loading="loading"
        ref="filterTable"
        :data="tableData"
        style="width: 100%"
        height="616"
      >
        <el-table-column label="头像">
          <template slot-scope="scope">
            <el-avatar size="large" :src="scope.row.avatar"></el-avatar>
          </template>
        </el-table-column>
        <el-table-column label="姓名">
          <template slot-scope="scope">
            <el-tag size="medium">{{ scope.row.username }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="email" label="邮箱"> </el-table-column>
        <el-table-column prop="created" label="日期">
          <template slot-scope="scope">
            {{ timestamptime(scope.row.created) }}
            <!-- {{ scope.row.created.replace("T", " ") }} -->
          </template>
        </el-table-column>
        <el-table-column prop="status" label="权限">
          <template slot-scope="scope">
            <el-tag size="medium">{{
              scope.row.status == 0
                ? "超级管理员"
                : scope.row.status == 1
                ? "VIP用户"
                : "普通用户"
            }}</el-tag>
          </template>
        </el-table-column>

        <el-table-column label="操作" width="180" fixed="right">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="fonter">
      <el-pagination
        layout="prev, pager, next"
        :total="total"
        :current-page="currentpage"
        :page-size="pagesize"
        @current-change="pages"
        background
      >
      </el-pagination>
    </div>
    <el-dialog
      title="用户详情"
      :visible.sync="dialogFormVisible"
      width="50%"
      center
      destroy-on-close
    >
      <el-form ref="formData" :model="formData" :rules="rules" status-icon>
        <div
          style="display: flex; justify-content: center; padding-bottom: 20px"
        >
          <el-avatar :size="50" :src="formData.avatar"></el-avatar>
        </div>
        <el-form-item label="用户名：" label-width="100px" prop="username">
          <el-input
            v-model="formData.username"
            type="text"
            class="input"
          ></el-input>
        </el-form-item>
        <el-form-item label="邮箱：" label-width="100px" prop="email">
          <el-input
            v-model="formData.email"
            type="text"
            class="input"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="权限："
          label-width="100px"
          v-show="formData.status != 0"
        >
          <el-radio-group v-model="formData.status">
            <el-radio label="1">VIP用户</el-radio>
            <el-radio label="2">普通用户</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <el-link
        @click="textclick"
        :underline="false"
        style="display: flex; justify-content: flex-end"
        v-show="this.userInfo.status == 0"
        >更换密码？</el-link
      >
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSave">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog
      width="50%"
      title="密码更换"
      :visible.sync="innerVisible"
      append-to-body
      center
      destroy-on-close
    >
      <el-form
        :model="ruleFormpass"
        status-icon
        :rules="rulesFormpass"
        ref="ruleFormpass"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="原密码:" prop="age">
          <el-input v-model.number="ruleFormpass.age"></el-input>
        </el-form-item>
        <el-form-item label="密码:" prop="pass">
          <el-input
            type="password"
            v-model="ruleFormpass.pass"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="确认密码:" prop="checkPass">
          <el-input
            type="password"
            v-model="ruleFormpass.checkPass"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item style="text-align: center">
          <el-button type="primary" @click="submitFormpass('ruleFormpass')"
            >提交</el-button
          >
          <el-button @click="resetFormpass('ruleFormpass')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog
      title="用户注册"
      :visible.sync="dialogFormregina"
      width="50%"
      center
      destroy-on-close
    >
      <div class="imgbox">
        <div v-for="(item, index) in Image" :key="index">
          <el-radio v-model="radioimg" :label="index" border @change="changeimg"
            ><img :src="item.img" alt="" srcset="" class="imgs" />
          </el-radio>
        </div>
      </div>
      <el-form :model="reaginuser" :rules="rules" ref="reaginuser" status-icon>
        <el-form-item
          prop="username"
          class="itemwidth"
          label="用户名："
          label-width="100px"
        >
          <el-input
            autocomplete="off"
            type="text"
            class="input"
            v-model="reaginuser.username"
            placeholder="请输入用户名"
        /></el-form-item>
        <el-form-item
          prop="password"
          class="itemwidth"
          label="用户密码："
          label-width="100px"
        >
          <el-input
            autocomplete="off"
            type="password"
            class="input"
            v-model="reaginuser.password"
            maxlength="20"
            placeholder="请输入密码"
        /></el-form-item>
        <el-form-item
          prop="email"
          class="itemwidth"
          label="用户邮箱："
          label-width="100px"
        >
          <el-input
            autocomplete="off"
            type="email"
            class="input"
            v-model="reaginuser.email"
            maxlength="20"
            placeholder="请输入邮箱"
          />
        </el-form-item>
        <el-form-item label="权限：" label-width="100px">
          <el-radio-group v-model="reaginuser.status">
            <el-radio label="1">VIP用户</el-radio>
            <el-radio label="2">普通用户</el-radio>
          </el-radio-group>
        </el-form-item>
        <!-- <el-form-item label-width="100px">
          <el-button type="primary" @click="submitregina('reaginuser')">
            提交
          </el-button>
          <el-button @click="resetregina('reaginuser')">重置 </el-button>
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitregina('reaginuser')">
          提交
        </el-button>
        <el-button @click="resetregina('reaginuser')">重置 </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import Image from "../utils/image.json";
import { timestampToTime } from "../utils/timeDate";
import { mapState, mapMutations } from "vuex";
import syslistVue from "../components/syslist.vue";
export default {
  name: "userlist",
  //import引入的组件需要注入到对象中才能使用
  components: {},
  data() {
    var validatoremail = (rule, value, callback) => {
      // var MobileRegex = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
      var MobileRegex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
      if (value === "") {
        callback(new Error("请输入正确的邮箱"));
      } else if (!MobileRegex.test(value)) {
        callback(new Error("请输入正确的邮箱格式"));
      } else {
        callback();
      }
    };
    //
    var checkAge = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入密码"));
      }
      setTimeout(() => {
        callback();
      }, 1000);
    };
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleFormpass.age !== "") {
          this.$refs.ruleFormpass.validateField("age");
        }
        if (this.ruleFormpass.checkPass !== "") {
          this.$refs.ruleFormpass.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleFormpass.pass) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };

    //这里存放数据
    return {
      radioimg: 1,
      loading: true,
      Image: Image,
      tableData: [],
      total: 0, //总条目数
      currentpage: 1, //当前页数
      pagesize: 5, //每页显示条目个数
      data: {}, //数据
      dialogFormVisible: false,
      innerVisible: false,
      dialogFormregina: false,
      formData: {
        avatar: "",
        username: "",
        email: "",
        status: "",
        id: "",
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        email: [
          {
            validator: validatoremail,
            trigger: "blur",
          },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
          { required: true, message: "请输入正确的邮箱格式", trigger: "blur" },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
      rulespass: {
        oldpass: [{ validator: checkAge, trigger: "blur" }],
        pass: [{ validator: validatePass, trigger: "blur" }],
        checkPass: [{ validator: validatePass2, trigger: "blur" }],
      },
      reaginuser: {
        username: "",
        password: "",
        avatar: null,
        email: "",
        status: "1",
      },
      ruleFormpass: {
        pass: "",
        checkPass: "",
        age: "",
      },
      rulesFormpass: {
        pass: [{ validator: validatePass, trigger: "blur" }],
        checkPass: [{ validator: validatePass2, trigger: "blur" }],
        age: [{ validator: checkAge, trigger: "blur" }],
      },
    };
  },
  //监听属性 类似于data概念
  computed: {
    ...mapState(["userInfo"]),
  },
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    ...mapMutations({
      SET_USERINFO: "SET_USERINFO",
    }),
    pages(page) {
      let url = "/userList?currentPage=";
      this.$axios
        .get(url + page)
        .then((res) => {
          if (res.data.code == 200) {
            this.loading = false;
            this.tableData = res.data.data.records;
            this.total = res.data.data.total;
            this.currentpage = res.data.data.current;
            this.pagesize = res.data.data.size;
            this.$message.success(res.data.msg);
          } else {
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "error",
            });
          }
          // console.log(res);
        })
        .catch((err) => {
          // console.error(err);
          this.$message({
            message: res.data.msg,
            showClose: true,
            type: "error",
          });
        });
    },
    handleEdit(index, row) {
      // console.log(index, row);
      this.dialogFormVisible = true;
      this.formData.username = row.username;
      this.formData.email = row.email;
      this.formData.status = row.status + "";
      this.formData.avatar = row.avatar;
      this.formData.id = row.id;
    },
    //修改基本信息
    onSave() {
      if (this.userInfo.status == 0) {
        this.$axios
          .post("/user/edit", JSON.stringify(this.formData), {
            headers: {
              // Authorization: localStorage.getItem("token"),
              "Content-Type": "application/json;charset=utf-8",
            },
          })
          .then((res) => {
            console.log("返回结果", res);

            if (res.data.code == 200) {
              if (res.data.data.status == this.userInfo.status) {
                this.SET_USERINFO(res.data.data);
              }
              this.$message({
                message: "修改成功！",
                showClose: true,
                type: "success",
              });
              this.pages(1);
              this.dialogFormVisible = false;
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
      } else {
        this.$message({
          message: "没有权限！",
          showClose: true,
          type: "error",
        });
        this.dialogFormVisible = false;
      }
    },
    handleDelete(index, row) {
      this.$confirm("此操作将永久删除该用户？, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        if (row.status !== 0) {
          this.$axios
            .get(`/deleteUser?id=${row.id}&status=${this.userInfo.status}`)
            .then((res) => {
              if (res.data.code == 200) {
                this.$message({
                  message: res.data.msg,
                  showClose: true,
                  type: "success",
                });
                this.pages(1);
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
        } else {
          this.$message({
            message: "该账号不可删除",
            showClose: true,
            type: "error",
          });
        }
      });
      // console.log(row.id, row.status);
    },
    onSavepass() {},
    //修改密码
    resetFormpass(formName) {
      this.$refs[formName].resetFields();
    },
    submitFormpass(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid && this.userInfo.status == 0) {
          let url = `/user/pass?id=${+this.formData.id}&newpassword=${
            this.ruleFormpass.checkPass
          }&password=${this.ruleFormpass.age}`;

          this.$axios
            .post(url, {
              headers: {
                // Authorization: localStorage.getItem("token"),
                "Content-Type": "application/x-www-form-urlencoded",
              },
            })
            .then((res) => {
              if (res.data.code == 200) {
                if (res.data.data.id == this.userInfo.id) {
                  this.$store.commit("REMOVE_INFO");
                  this.$router.push("/");
                }
                this.$message({
                  message: res.data.msg,
                  center: true,
                  type: "success",
                });
                this.innerVisible = false;
              } else {
                this.$message({
                  message: res.data.msg,
                  center: true,
                  type: "error",
                });
              }
              console.log(res);
            })
            .catch((err) => {
              this.$message({
                message: err,
                showClose: true,
                type: "error",
              });
              console.error(err);
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    textclick() {
      this.innerVisible = true;
      this.passwordForm = {};
    },
    //新增用户
    addclick() {
      this.dialogFormregina = true;
      this.reaginuser.avatar = Image[this.radioimg].img;
    },
    //
    changeimg(img) {
      // console.log(Image[img].img)
      this.reaginuser.avatar = Image[img].img;
    },
    //新增提交
    submitregina() {
      if (this.userInfo.status == 0) {
        this.$axios
          .post("/regina", this.reaginuser)
          .then((res) => {
            if (res.data.code == 200) {
              this.$message({
                message: res.data.msg,
                showClose: true,
                type: "success",
              });
              this.pages(1);
              this.dialogFormregina = false;
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
    //重置
    resetregina(formName) {
      this.$refs[formName].resetFields();
    },
    //时间格式化
    timestamptime(e) {
      return timestampToTime(e);
    },
  },
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {
    this.pages(1);
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
<style lang="scss" scoped>
//@import url(); 引入公共css类
.img {
  width: 80px;
  border-radius: 50%;
}
.fonter {
  text-align: center;
  padding: 20px 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #fff;
}
.tablebox {
  padding-left: 24px;
  background: #fff;
}
.adduser {
  background: #fff;
  display: flex;
  width: 100%;
  height: 70px;
  align-items: center;
}
.imgbox {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  height: 70px;
  >>> .el-radio__input {
    display: none;
  }
  >>> .el-radio.is-bordered {
    padding: 0;
    height: 40px;
    width: 40px;
  }
  >>> .el-radio__label {
    padding: 0;
  }
  .imgs {
    width: 38px;
    height: 38px;
    border-radius: 4px;
  }
}
</style>
