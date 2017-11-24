<template>
  <div>
    <button :class="buttonClass" type="button" @click="open=!open">{{ open ? closeTitle : openTitle }}</button>
    <div v-show="open">
      <slot></slot>
    </div>
  </div>
</template>

<script>
  export default {
    props: {
      closeTitle: String,
      openTitle: String,
      buttonClass: {
        type: String,
        default: 'collapse-button'
      }},
    data () {
      return {
        open: false
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
