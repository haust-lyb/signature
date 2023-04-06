<template>
  <signature-pad @onSubmit="submitSignature"></signature-pad>
</template>

<script>
import SignaturePad from "./SignaturePad.vue";
import api from "../api/api.js";

export default {
  name: "Signature",
  components: {SignaturePad},
  methods: {
    submitSignature(dataURL) {
      let params = {signKey: location.search.split("signKey=")[1], base64: dataURL};
      api.submit(params).then(res=>{
        if (res.data.status === '-1'){
          this.$message({center: true,type:"error",message:"提交失败，未检测到合法的signKey"})
        }else{
          console.log(res)
          this.$message({center: true,type:"success",message:"签名已提交"})
          // alert("签名已提交");
        }

      })
    }

  }
}
</script>

<style scoped>
  *{
    padding: 0px;margin: 0px;
  }
</style>
