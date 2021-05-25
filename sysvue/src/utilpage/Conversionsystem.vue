<!-- 进制转换 -->
<template>
  <div class="" style="width: 100%">
    <p>进制转换</p>
    <div>
      <el-radio-group @change="onChange1" class="box" v-model="value1">
        <el-radio
          v-for="item in plainOptions"
          :key="item.value"
          :label="item.value"
          >{{ item.label }}</el-radio
        >
      </el-radio-group>
      <el-select v-model="value1" style="width: 100px" @change="handleChange">
        <el-option
          v-for="item in list36"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
    </div>
    <el-input
      style="width: 300px"
      v-model="num1"
      placeholder="待转数字"
      @change="abtn"
    />
    <div>
      <el-radio-group @change="onChange2" class="box" v-model="value2">
        <el-radio
          v-for="item in plainOptions1"
          :key="item.value"
          :label="item.value"
          >{{ item.label }}</el-radio
        >
      </el-radio-group>
      <el-select v-model="value2" style="width: 100px" @change="handleChange2">
        <el-option
          v-for="item in list36"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
    </div>
    <el-input style="width: 300px" v-model="num2" placeholder="待转结果" />
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
const plainOptions = [
  { label: "2进制", value: 2 },
  { label: "8进制", value: 8 },
  { label: "10进制", value: 10 },
  { label: "16进制", value: 16 },
];
const plainOptions1 = [
  { label: "2进制", value: 2 },
  { label: "8进制", value: 8 },
  { label: "10进制", value: 10 },
  { label: "16进制", value: 16 },
];
export default {
  name: "Conversionsystem",
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: [],
  data() {
    //这里存放数据
    return {
      plainOptions,
      plainOptions1,
      value1: 16,
      value2: 10,
      num1: "",
      num2: "",
      c2: 0,
      c8: 0,
      c10: 0,
      c16: 0,
      newvalue: 0, //待转换值
      list36: [],
    };
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    onChange1(e) {
      this.value1 = +e;
      this.num1 = "";
      this.num2 = "";
      console.log("onChange1", e);
    },
    num16() {
      let intp = +this.num1;
      if (this.value1 == 16) {
        var re = new RegExp(/^\s*(\+|-)?((\d+(\.\d+)?)|(\.\d+))\s*$/);
        let num16 = +("0x" + this.num1);

        if (re.test(num16)) {
          this.newvalue = num16;
          intp = num16;
        } else {
          this.$message.error("不是16进制！");
        }
      }

      let cc = parseInt(intp, Number(this.value1)).toString(
        Number(this.value2)
      );
      this.num2 = cc;
      console.log("gg", this.value1, this.value2);
    },
    onChange2(e) {
      this.value2 = +e;
      this.btn();
      console.log("ee", this.value2);
    },
    abtn() {
      this.btn();
    },
    handleChange(e) {
      this.value1 = e;
      // this.num16()
      console.log(e);
    },
    handleChange2(e) {
      this.isnum();
      this.value2 = e;
      this.num16();

      // this.btn();
    },
    /**
     * 二进制：里面只有0和1
     *八进制: 以0开头的数,如075,023,012等.每单个数在0-7之间(含).
     *十六进制: 以0x开头的数,如0x12,x12ff,0x86等.
     *十进制: 第一位数不是0,不以0x开头.每单个数在0-9之间(含).
     */
    /**
     *
     *parseInt(num).toString(8)  //十进制转八进制
      *parseInt(num).toString(16)   //十进制转十六进制
      * parseInt(num,2).toString(8)   //二进制转八进制
      * parseInt(num,2).toString(16)  //二进制转十六进制
      * parseInt(num,8);   //八进制转十进制
      *parseInt(num,8).toString(2)   //八进制转二进制
      *parseInt(num,8).toString(16)  //八进制转十六进制
      *parseInt(num,16).toString(2)  //十六进制转二进制
      *parseInt(num,16).toString(8)  //十六进制转八进制
      *parseInt(num,16);   //十六进制转十进制

     */
    /**
     * 除了16进制需要加前缀 0x 之外
     * parseInt(num,value1).toString(value2)
     * num 待转数字 +num
     * value1进制转value2进制
     */
    isnum() {
      if (!this.num1) {
        this.$message.error("请输入待转换数字！");
        return;
      }
    },
    btn() {
      this.isnum();
      // 16》10 8 2 ?
      if (this.value1 == 16) {
        var re = new RegExp(/^\s*(\+|-)?((\d+(\.\d+)?)|(\.\d+))\s*$/);
        let num16 = +("0x" + this.num1);
        if (re.test(num16)) {
          let num10 = num16.toString(Number(this.value2));
          this.num2 = num10;
        } else {
          this.$message.error("不是16进制！");
        }
      }
      //10> 16 8 2  ok
      if (this.value1 == 10) {
        let re10 = new RegExp(/^-{0,1}\d*\.{0,1}\d+$/);
        let num10 = +this.num1;
        if (re10.test(num10)) {
          let num = num10.toString(Number(this.value2));
          this.num2 = num;
        } else {
          this.$message.error("不是10进制！");
        }
      }
      //8> 16 10 2  ok
      if (this.value1 == 8) {
        let re8 = new RegExp(/^[0-7]+$/);
        let num8 = +this.num1;
        if (re8.test(num8)) {
          let num = parseInt(num8, 8).toString(Number(this.value2));
          this.num2 = num;
        } else {
          this.$message.error("不是8进制！");
        }
      }
      //2> 16 10 8   ok
      if (this.value1 == 2) {
        let re8 = new RegExp(/^[0-1]+$/);
        let num8 = +this.num1;
        if (re8.test(num8)) {
          let num = parseInt(num8, 2).toString(Number(this.value2));
          // let num = parseInt(num8,Number(this.value2));
          this.num2 = num;
        } else {
          this.$message.error("不是2进制！");
        }
      }
      //其他进制 转换
      let cc = parseInt(this.num1, Number(this.value1)).toString(
        Number(this.value2)
      );
      this.num2 = cc;
      console.log("ee", this.value1, this.value2);
    },
    listarry() {
      let list = [];
      let listobj = {};
      for (let i = 2; i < 37; i++) {
        listobj = {
          label: i + "进制",
          value: i,
        };
        list.push(listobj);
      }
      this.list36 = list;
      // console.log("ee", list);
    },
  },
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {
    this.listarry();
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
.box {
  padding: 24px 0;
}
</style>
