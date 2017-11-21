<template>
  <div style="float:right;">
    <input type="text" v-model="id" placeholder="点击搜索按钮筛选"/>
    <button @click="sendData()">搜索</button>
    <br/>
    <label><input type="radio" v-model="advance.field" value="cust_name" checked="checked"/>用户名称</label>
    <label><input type="radio" v-model="advance.field" value="cert_num"/>证件号码</label>
    <br/>
    <br/>
    <textarea>{{ response.hits }}</textarea>
    <table>
      <thead>
      <tr>
        <th style='width:3%; text-align: left' v-show="false">ID</th>
        <th style='width:10%; text-align: left'>客户名称</th>
        <th style='width:10%; text-align: left'>证件号</th>
        <th style='width:10%; text-align: left'>联系地址</th>
        <th style='width:10%; text-align: left'>证件地址</th>
        <th style='width:10%; text-align: left'>详细信息</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="person in response.hits.hits">
        <td v-show="false">{{person._source.cust_id}}</td>
        <td>{{person._source.cust_name}}</td>
        <td>{{person._source.cert_num}}</td>
        <td>{{person._source.contact_addr_txt}}</td>
        <td>{{person._source.cert_addr_txt}}</td>
        <td>
          <a href="#" v-on:click="details(person._source)">详细信息</a>
        </td>
      </tr>
      </tbody>
    </table>
    <br/>
    <br/>
    <h4>固网账号</h4>
    <table>
      <thead>
      <tr>
        <th style='width:3%; text-align: left' v-show="false">ID</th>
        <th style='width:10%; text-align: left'>产品名称</th>
        <th style='width:10%; text-align: left'>业务号码</th>
        <th style='width:10%; text-align: left'>账号</th>
        <th style='width:10%; text-align: left'>付费模式</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="acc in account.hits.hits">
        <td v-show="false">{{acc._source.use_cust_id}}</td>
        <td>{{acc._source.prod_name}}</td>
        <td>{{acc._source.acc_num}}</td>
        <td>{{acc._source.account}}</td>
        <td>{{acc._source.payment_mode_cd}}</td>
      </tr>
      </tbody>
    </table>
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
        response: {
          took: '',
          time_out: '',
          _shards: {},
          hits: {
            total: '',
            max_score: '',
            hits: []
          }
        },
        account: {
          took: '',
          time_out: '',
          _shards: {},
          hits: {
            total: '',
            max_score: '',
            hits: []
          }
        }
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
        axios({
          method: 'POST',
          url: 'http://' + this.ip + ':' + this.port + '/business/identity/baseInfo',
          headers: {'content-type': 'application/json;charset=UTF-8'},
          params: {'id': this.id},
          data: this.advance
        }).then(result => {
          this.response = result.data
        }, error => {
          console.error(error)
        })
      },

      details () {}
    }
  }
</script>

<style>
  textarea {
    width: 600px;
    height: 200px;
  }
</style>
