<template>
  <div id="main-app">
    <div class="action-container">
      <el-button type="warning" @click="undo()">undo上一步</el-button>
      <el-button type="danger" @click="rewrite()">rewrite重写</el-button>
      <el-button type="success" @click="submit()">submit确定</el-button>
    </div>
    <div class="signature-pad-container">
      <canvas></canvas>
    </div>

    <div class="tips">
<!--      <strong>快签 - quick sign</strong>-->
<!--      <strong>1570194845@qq.com</strong>-->
      <strong><a href="https://github.com/haust-lyb/signature">open source on github</a></strong>
    </div>

    <div v-if="!hengping" class="warning">
      请在横屏模式下使用
      <br>
      Please use it in landscape mode
    </div>
  </div>
</template>


<script>
import SignaturePad from "signature_pad";

export default {
  name: "SignaturePad",
  data() {
    return {
      // 原始画布
      canvas: null,
      // 签字板本板
      signaturePad: null,
      // 签字板原始笔迹数据
      signaturePadData: [],
      // 是否是横屏，根据signature-pad-container的长和款来判断，width>height则判断为横屏模式
      hengping: false
    }
  },
  mounted() {
    // 初始化
    this.init();
    this.resize();
    window.onresize = this.resize;
  },
  methods: {
    init: function () {
      const canvas = document.querySelector("canvas");
      this.canvas = canvas;
      const signaturePad = new SignaturePad(canvas);
      signaturePad.minWidth = 1;
      signaturePad.maxWidth = 6;
      // signaturePad.penColor = "rgb(0,0,0)";
      this.signaturePad = signaturePad;
    },
    resize() {
      const canvas = this.canvas;
      // 将canvas调整到和其父容器一样的宽和高
      const cantainer_width = Number.parseInt(window.getComputedStyle(document.querySelector(".signature-pad-container")).getPropertyValue('width'));
      const cantainer_height = Number.parseInt(window.getComputedStyle(document.querySelector(".signature-pad-container")).getPropertyValue('height'));
      if (cantainer_width > cantainer_height) {
        this.hengping = true;
      } else {
        this.hengping = false;
      }
      canvas.width = cantainer_width;
      canvas.height = cantainer_height;
    },
    undo: function () {
      var data = this.signaturePad.toData();
      if (data) {
        data.pop(); // remove the last dot or line
        this.signaturePad.fromData(data);
      }
    },
    submit: function () {
      if (this.signaturePad.isEmpty()) {
        this.$alert('请先完成签名再提交', '提示', {
          confirmButtonText: '好的'
        });
      } else {
        this.signaturePadData = this.signaturePad.toData();
        const dataURL = this.canvas.toDataURL("image/png", 1); // 可选取多种模式
        this.$emit("onSubmit",dataURL)
      }
    },
    rewrite: function () {
      this.signaturePad.clear();
      this.signaturePadData = [];
    }
  }
}

</script>

<style lang="css" scoped>


.tips {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  background: #000;
  color: white;
  width: 100vw;
  box-shadow: 1px 0px 3px #3a3a3a;
}

.signature-pad-container {
  /*background: dodgerblue;*/
  background: url("../assets/paperbg.png");
  height: calc(100vh - 100px);
  width: 80vw;
}

.action-container {
  background: #000;
  width: 100vw;
  box-shadow: -1px 0px 3px #3a3a3a;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 50px;
}

.warning {
  z-index: 999;
  color: black;
  font-weight: bold;
  font-size: 20px;
  position: fixed;
  width: 100vw;
  height: 100vh;
  background: white;
  display: flex;
  justify-content: center;
  align-items: center;
}

#main-app {
  flex-direction: column;
  display: flex;
  justify-content: center;
  align-items: center;
}

</style>
