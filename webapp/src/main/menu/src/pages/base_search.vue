<template>
  <div style="float:left;">
    <!--<keep-alive>-->
      <!--<route-view v-if="$route.meta.keepAlive">-->
      <!--<main-search>-->
        <form class="main-form">
          <input class="main-input" type="text" v-model="advance.map.ct_msisdn[0]" placeholder="输入手机号"/>
          <button class="main-button" style="margin-top: 2px;" @click="sendData()">搜索</button>
        </form>
        <br/>
        <span class="input-title">用户名称：</span>
        <input class="sub-input" type="text" v-model="advance.map.cust_name[0]"/>
        <span class="input-title" style="margin-left: 15px">证件号码：</span>
        <input class="sub-input" type="text" v-model="advance.map.cert_num[0]"/>
        <!--<label><input type="radio" v-model="advance.field" value="cust_name" checked="checked"/>用户名称</label>-->
        <!--<label><input type="radio" v-model="advance.field" value="cert_num"/>证件号码</label>-->
      <!--</main-search>-->
    <!--</keep-alive>-->
        <br/>
        <br/>
    <!--<keep-alive>-->
        <collapse :closeTitle="'收起搜索条件'" :openTitle="'更多搜索条件'">
          <span class="input-title">性别：</span>
          <input class="sub-input" type="text" v-model="advance.map.gender[0]"/>
          <span class="input-title">证件地址：</span>
          <input class="sub-input" type="text" v-model="advance.map.cert_addr_txt[0]"/>
          <span class="input-title">通信地址：</span>
          <input class="sub-input" type="text" v-model="advance.map.contact_addr_txt[0]"/>
        </collapse>
    <!--</keep-alive>-->
        <br/>
        <div v-if="loading">
          <loading :loading="loading"></loading>
        </div>
        <div v-if="!loading">
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
                <a href="#account-detail" v-on:click="details(person._source)">详细信息</a>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
        <br/>
        <br/>
        <p id="account-detail"></p><h4>账号详情</h4>
    <account-panel :buttonClass="'account-info-button'" :openTitle="'固网账号详情'" :closeTitle="'固网账号详情'">
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
    </account-panel>
    <br/>
    <account-panel :buttonClass="'account-info-button'"
                   :openTitle="'展开本网手机号详情'"
                   :closeTitle="'收起本网手机号详情'"
                   :address="'http://' + this.ip + ':' + this.port + '/business/identity/account'"
                   :accountName="'ct_mobile_phone'"
                   :request_body="baseInfo"
                   :allOpen="allPanelOpen">
    </account-panel>
      <!--</route-view>-->
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
        loading: false,
        advance: {
          map: {
            cust_name: [],
            ct_msisdn: [],
            cert_num: [],
            gender: [],
            cert_addr_txt: [],
            contact_addr_txt: []
          }
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
        },
        baseInfo: {},
        allPanelOpen: false
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
        this.loading = true
        axios({
          method: 'POST',
          url: 'http://' + this.ip + ':' + this.port + '/business/identity/baseInfo',
          headers: {'content-type': 'application/json;charset=UTF-8'},
          data: this.advance
        }).then(result => {
          this.response = result.data
          this.loading = false
        }, error => {
          console.error(error)
        })
      },

      details (source) {
        this.baseInfo = source
        this.allPanelOpen = false
        console.log(this.baseInfo)
      }
    }
  }
</script>

<style>
  textarea {
    width: 600px;
    height: 200px;
  }

  form.main-form {
    width: 600px;
    height: 30px;
  }

  .main-input {
    width: 350px;
    height: 20px;
  }

  .main-button {
    background-color: black;
    border: none;
    color: white;
    width: 90px;
    height: 26px;
    /*padding: 15px 32px;*/
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 14px;
    border-radius: 4px;
    transition-duration: 0.4s;
    cursor: pointer;
  }

  .main-button:hover {
    background-color: gray;
    color: white;
  }

  .sub-input {
    width: 100px;
    height: 15px;
  }

  .input-title {
    font-size: 12px;
  }

  .account-info {
    margin-left: 140px;
    width: 600px;
    height: 600px;
    color: gray;
  }

  .account-info-button {
    margin-left: 0px;
    background-color: black;
    border: none;
    color: blanchedalmond;
    width: 500px;
    height: 20px;
    /*padding: 15px 32px;*/
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    font-style: oblique;
    border-radius: 4px;
    transition-duration: 0.4s;
    cursor: pointer;
  }

  .account-info-button:hover {
    background-color: gray;
    color: white;
  }
</style>
