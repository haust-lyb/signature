<template>
  <signature-pad v-if="!hasSubmited" @onSubmit="submitSignature"></signature-pad>
  <div v-if="hasSubmited">
    <h3>签名已提交</h3>
    <p style="color: #909399">若要重新签名，请再次扫码</p>
  </div>
</template>

<script>
import SignaturePad from "./SignaturePad.vue";
import api from "../api/api.js";

export default {
  name: "Signature",
  components: {SignaturePad},
  data() {
    return {
      hasSubmited: false
    }
  },
  methods: {
    submitSignature(dataURL) {
      let params = {signKey: location.search.split("signKey=")[1], base64: dataURL};
      api.submit(params).then(res => {
        if (res.data.status === '-1') {
          this.$message({center: true, type: "error", message: "提交失败，未检测到合法的signKey"})
        } else {
          console.log(res)
          this.$message({center: true, type: "success", message: "提交成功"})
          this.hasSubmited = true
          // alert("签名已提交");
        }
      })
    }

  }
}
</script>

<style scoped>
* {
  padding: 0px;
  margin: 0px;
}
</style>
