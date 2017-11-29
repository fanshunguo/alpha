<template>
  <div id="account-detail">
    <fieldset style="size: 1px; font-size: 14px">
      <legend class="detail_title">账号详情</legend>
      <slot></slot>
    </fieldset>
  </div>
</template>

<style scoped>
  .detail_border {
    border: 1px dashed gray;
    border-bottom-style: hidden;
  }

  .detail_title {
    margin-left: 10px;
    margin-top: -10px;
    font-style: oblique;
    font-size: 14px;
    color: #666666;
  }
</style>
