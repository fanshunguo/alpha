<template>
  <div  style="float:right;">
    <input type="text" v-model="id" placeholder="点击搜索按钮筛选" />
    <button @click="sendData()">搜索</button>
    <br />
    <label><input type="radio" v-model="advance.field" value="cust_name" checked="checked"/>用户名称</label>
    <label><input type="radio" v-model="advance.field" value="cert_num"/>证件号码</label>
    <br />
    <br />
    <textarea>{{ response }}</textarea>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: 'BaseSearch',
    data () {
      return {
        ip: 'localhost',
        port: '8999',
        input: {
          fn: '',
          ln: ''
        },
        id: '',
        advance: {
          field: 'cust_name',
          map: {}
        },
        response: ''
      }
    },
    mounted () {
//      axios({ method: 'GET', 'url': 'https://httpbin.org/ip' }).then(result => {
//        this.ip = result.data.origin
//        console.info(this.ip)
//      }, error => {
//        console.error(error)
//      })
    },
    methods: {
      sendData () {
        axios({ method: 'POST',
          url: 'http://' + this.ip + ':' + this.port + '/business/identity/baseInfo',
          headers: { 'content-type': 'application/json;;charset=UTF-8' },
          params: { 'id': this.id },
          data: this.advance}).then(result => {
            this.response = result.data
          }, error => {
            console.error(error)
          })
      }
    }
  }
</script>

<style>
  textarea {
    width: 600px;
    height: 200px;
  }
</style>
