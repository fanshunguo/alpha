<template>
  <div id="baseSearch" style="float:left; width: 100%">
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
          <!--<textarea>{{ response.hits }}</textarea>-->
          <base-info v-on:detail-info="details" :tableData="response.hits.hits"></base-info>
        </div>
        <br/>
        <br/>
    <account-detail v-show="showDetail" :id="'accountDetail'">
      <account-panel :buttonClass="'account-info-button'"
                     :openTitle="'固网账号详情'"
                     :closeTitle="'固网账号详情'"
                     :address="'http://' + this.ip + ':' + this.port + '/business/identity/account'"
                     :accountName="'broadband'"
                     :request_body="baseInfo._source"
                     :open="!allPanelClose">
      </account-panel>
      <br/>
      <account-panel :buttonClass="'account-info-button'"
                     :openTitle="'本网手机号详情'"
                     :closeTitle="'本网手机号详情'"
                     :address="'http://' + this.ip + ':' + this.port + '/business/identity/account'"
                     :accountName="'ct_mobile_phone'"
                     :request_body="baseInfo._source"
                     :open="!allPanelClose">
      </account-panel>
      <br/>
      <account-panel :buttonClass="'account-info-button'"
                     :openTitle="'设备信息'"
                     :closeTitle="'设备信息'"
                     :address="'http://' + this.ip + ':' + this.port + '/business/identity/account'"
                     :accountName="'device_info'"
                     :request_body="baseInfo._source"
                     :open="!allPanelClose">
      </account-panel>
      <br/>
      <account-panel :buttonClass="'account-info-button'"
                     :openTitle="'固话信息'"
                     :closeTitle="'固话信息'"
                     :address="'http://' + this.ip + ':' + this.port + '/business/identity/account'"
                     :accountName="'telephone'"
                     :request_body="baseInfo._source"
                     :open="!allPanelClose">
      </account-panel>
      <br/>
      <account-panel :buttonClass="'account-info-button'"
                     :openTitle="'翼支付信息'"
                     :closeTitle="'翼支付信息'"
                     :address="'http://' + this.ip + ':' + this.port + '/business/identity/account'"
                     :accountName="'yi_pay'"
                     :request_body="baseInfo._source"
                     :open="!allPanelClose">
      </account-panel>
    </account-detail>
        <!--<div id="accountDetail"></div><h4>账号详情</h4>-->
      <!--</route-view>-->
  </div>
</template>

<script>
  import axios from 'axios'
//  import $ from 'jquery'

  export default {
    name: 'BaseSearch',
    data () {
      return {
        ip: process.env.SERVICE_IP,
        port: '8999',
        loading: false,
        showDetail: false,
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
        allPanelClose: true
      }
    },
    mounted () {
//      this.ip = process.env.SERVICE_IP
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
        this.showDetail = false
        axios({
          method: 'POST',
          url: 'http://' + this.ip + ':' + this.port + '/business/identity/baseInfo',
          headers: {'content-type': 'application/json;charset=UTF-8'},
          data: this.advance
        }).then(result => {
          this.response = result.data
          console.log(this.response)
          this.loading = false
        }, error => {
          console.error(error)
        })
      },

      details (source) {
        this.baseInfo = source
        this.allPanelClose = true
        console.log(this.allPanelClose)
//        console.log(this.baseInfo)
        this.showDetail = true
//        const anchor = this.$refs.accountDetail
//        $('#baseSearch').animate({scrollTop: 400})
//        this.scrollHeight = 400
//        console.log(document.getElementById('accountDetail').scrollHeight)
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
    width: 100%;
    margin-left: 2px;
    margin-right: 2px;
    background-color: black;
    border: none;
    color: blanchedalmond;
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
