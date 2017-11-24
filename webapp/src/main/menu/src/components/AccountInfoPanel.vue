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
        open: false,
        accounts: {
          broadband: [
            new Field('global_id', 'ID', false),
            new Field('prod_name', '产品名称', true),
            new Field('acc_num', '业务号码', true)
          ],
          ct_mobile_phone: [
            new Field('global_id', 'ID', false),
            new Field('prod_name', '产品名称', true),
            new Field('acc_num', '手机号码', true)
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
