<template>
  <div>
    <button :class="buttonClass" type="button" @click="parseData"><u>{{ open ? closeTitle : openTitle }}</u></button>
    <div v-show="open">
      <div v-if="loading">
        <loading :loading="loading"></loading>
      </div>
      <div v-if="!loading">
        <table>
          <thead>
          <tr>
            <th v-for="head in accHeads" v-show="head.show">{{ head.desc }}</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="acc in response.hits.hits">
            <td v-for="head in accHeads" v-show="head.show">{{ acc._source[head.code] }}</td>
          </tr>
          </tbody>
        </table>
        <slot></slot>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'

  function Field (code, desc, show) {
    this.code = code
    this.desc = desc
    this.show = show
  }

  export default {
    props: {
      open: false,
      address: {
        type: String,
        default: 'http://localhost:8999/business/identity/account'
      },
      request_body: {
        type: Object,
        default: function () {
          return {'global_id': 'ce1e9000c0ff44d6a6bc5372f5ae8f58'}
        }
      },
      accountName: {
        type: String,
        default: 'ct_mobile_phone'
      },
      closeTitle: String,
      openTitle: String,
      buttonClass: {
        type: String,
        default: 'collapse-button'
      }},
    data () {
      return {
        loading: Boolean,
        accounts: {
          broadband: [
            new Field('global_id', 'ID', false),
            new Field('prod_name', '产品名称', true),
            new Field('acc_num', '业务号码', true),
            new Field('status_name', '使用状态', true),
            new Field('begin_rent_date', '起租时间', false),
            new Field('act_date', '激活时间', true),
            new Field('use_cust_id', '客户编号', false),
            new Field('payment_mode_name', '付费方式', false),
            new Field('stop_rent_date', '到期时间', true)
          ],
          ct_mobile_phone: [
            new Field('global_id', 'ID', false),
            new Field('prod_name', '产品名称', true),
            new Field('acc_num', '手机号码', true),
            new Field('status_name', '使用状态', true),
            new Field('begin_rent_date', '起租时间', false),
            new Field('act_date', '激活时间', true),
            new Field('cust_id', '客户编号', false),
            new Field('payment_mode_name', '付费方式', false),
            new Field('strop_rent_date', '到期时间', true)
          ],
          device_info: [
            new Field('global_id', 'ID', false),
            new Field('intelligent_system', '操作系统', true),
            new Field('prod_software', 'UI', false),
            new Field('pro_brand', '手机品牌', true),
            new Field('term_model', '手机型号', true),
            new Field('market_price', '市场价', true),
            new Field('phone_no', '手机号', true),
            new Field('cust_id', '客户编号', false)
          ],
          telephone: [
            new Field('global_id', 'ID', false),
            new Field('use_cust_id', '客户编号', false),
            new Field('prod_name', '产品名称', true),
            new Field('telephone_num', '固话号码', true),
            new Field('status_name', '状态', true),
            new Field('prov_id', '省份编码', false),
            new Field('act_date', '激活时间', true),
            new Field('stop_rent_date', '到期时间', false),
            new Field('begin_rent_date', '起租时间', false),
            new Field('status_date', '状态时间', false),
            new Field('prod_inst_id', '产品编号', false),
            new Field('payment_mode_name', '付费方式', false)
          ],
          yi_pay: [
            new Field('global_id', 'ID', false),
            new Field('pay_login', '翼支付账号', true),
            new Field('operator_name', '用户姓名', true),
            new Field('customer_type_name', '客户类型', true),
            new Field('contract_no', '合同号', false),
            new Field('operator_lvl', '操作等级', false),
            new Field('customer_no', '客户编码', false),
            new Field('operator_lvl_name', '用户等级', true),
            new Field('etl_time', 'ETL时间', false),
            new Field('is_realname_flag', '是否实名标记', false),
            new Field('customer_type', 'customer_type', false),
            new Field('created_at', '创建时间', true),
            new Field('is_realname_flag_name', '是否实名', true),
            new Field('updated_at', '更新日期', false),
            new Field('auth_time', '验证时间', false),
            new Field('operator_no', '操作号', false)
          ],
          yi_wang: [
            new Field('global_id', 'ID', false),
            new Field('prod_name', '产品名称', true),
            new Field('acc_num', '手机号码', true),
            new Field('status_name', '使用状态', true),
            new Field('begin_rent_date', '起租时间', false),
            new Field('act_date', '激活时间', true),
            new Field('cust_id', '客户编号', false),
            new Field('payment_mode_name', '付费方式', false),
            new Field('strop_rent_date', '到期时间', true)
          ]
        },
        accHeads: [],
        response: {
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
//      console.log(this.getAccount('broadband'))
//      this.remoteAccount()
    },
    methods: {
      getAccount (name) {
        return this.accounts[name]
      },

      remoteAccount () {
        console.log(this.address)
        axios({
          method: 'POST',
          url: this.address,
          headers: {'content-type': 'application/json;charset=UTF-8'},
          params: {'accName': this.accountName},
          data: this.request_body
        }).then(result => {
//          console.log(result.data)
          this.response = result.data
        }, error => {
          console.error(error)
        })
      },

      parseData () {
//        console.log(this.request_body)
        if ('global_id' in this.request_body) {
          this.open = !this.open
          this.loading = true
          this.accHeads = this.getAccount(this.accountName)
          this.remoteAccount()
          this.loading = false
        }
      }
    }
  }
</script>

<style>
  .collapse-button {
    margin-left: 140px;
    background-color: black;
    border: none;
    color: whitesmoke;
    width: auto;
    height: 15px;
    /*padding: 15px 32px;*/
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 10px;
    font-style: italic;
    border-radius: 4px;
    transition-duration: 0.4s;
    cursor: pointer;
  }

  .collapse-button:hover {
    background-color: gray;
    color: white;
  }
</style>
