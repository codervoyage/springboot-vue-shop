<template>
  <div id="bigbox">

    <el-card shadow="hover">
      <div class="smbox1">
        <i class="el-icon-user-solid" style="width:48px;height:48px;color:#40D2C6;font-size:48px;"></i>
        <p style="color:#8C8C8C; ">用户数量</p>
        <count-to class="card-panel-num" :startVal="0" :endVal="55" :duration="1500"></count-to>
      </div>
    </el-card>

    <el-card shadow="hover">
      <div class="smbox1">
        <i class="el-icon-shopping-cart-full" style="width:48px;height:48px;color:#36A3F7;font-size:48px;"></i>
        <p style="color:#8C8C8C; ">商品数量</p>
        <count-to class="card-panel-num" :startVal="0" :endVal="97" :duration="1500"></count-to>
      </div>
    </el-card>

    <el-card shadow="hover">
      <div class="smbox1">
        <i class="el-icon-box" style="width:48px;height:48px;color:#F4516C;font-size:48px;"></i>
        <p style="color:#8C8C8C; ">货品数量</p>
        <count-to class="card-panel-num" :startVal="0" :endVal="156" :duration="1500"></count-to>
      </div>
    </el-card>

    <el-card shadow="hover">
      <div class="smbox1">
        <i class="el-icon-shopping-bag-1" style="width:48px;height:48px;color:#34BFA3;font-size:48px;"></i>
        <p style="color:#8C8C8C; ">订单数量</p><br>
        <count-to class="card-panel-num" :startVal="0" :endVal="899" :duration="1500"></count-to>
      </div>
    </el-card>

  </div>
</template>

<script>
import CountTo from 'vue-count-to'

export default {
  components: {
    CountTo
  },
  name: 'Welcome'
}
</script>

<style scoped>
#bigbox {
  background-color: #f0f2f5;
  height: 200px;
  display: flex;
  justify-content: space-around;
  align-items: center;
  min-width: 700px;
}

/*  #smi1{
    border:1px solid red;
    color: #40D2C6;
    font-size: 48px;
  }*/
.smbox1 {
  display: flex;
  justify-content: center;
  align-items: center;
}

/*  .el-card{
    display: flex;
    justify-content: center;
    align-items: center;
  }*/
span {
  color: black;
  font-weight: bold;
  width: 30px;
  text-align: center;
}
</style>