<template>
  <div  style="float:right;">
    login page
    <button @click="do_send()">提交</button>
    <p>{{ this.received_messages }}</p>
  </div>
</template>
<script>
  import SockJS from 'sockjs-client'
//  const Stomp = require('stompjs')
  import Stomp from 'webstomp-client'

  export default{
    data () {
      return {
        stompClient: Object,
        received_messages: [],
        user: {
          id: '123',
          name: 'guest',
          password: 'guest',
          token: 'guest'
        }
      }
    },
    mounted () {
      this.get_sub()
    },
    methods: {
      get_sub () {
        const _self = this
        const socket = new SockJS('http://localhost:8999/messages')
        socket.onopen = function () {
          console.log('open')
        }
        socket.onmessage = function (e) {
          console.log('message', e.data)
        }
        socket.onclose = function () {
          console.log('close')
        }
        _self.stompClient = Stomp.over(socket)
        _self.stompClient.connect(this.user, function (frame) {
          console.log('Connected: ' + frame)
          _self.stompClient.subscribe('/topic/greetings', (greeting) => {
            console.log(greeting)
            _self.received_messages.push(greeting)
          })
//
//          client.subscribe('/user/' + userid + '/message', function (greeting) {
//            alert(JSON.parse(greeting.body).content);
//            showGreeting(JSON.parse(greeting.body).content);
//          });
        })
      },

      do_send () {
        this.stompClient.send('/app/foo/handle', {}, JSON.stringify({name: 'name'}))
      }
    }
  }
</script>
<style>
</style>
