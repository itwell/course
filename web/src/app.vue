<template>
  <div>
    <the-header></the-header>

    <router-view></router-view>

    <the-footer></the-footer>
  </div>
</template>

<script>

import TheHeader from "./components/the-header";
import TheFooter from "./components/the-footer";

export default {
    name: 'App',
    components: {TheFooter, TheHeader},
}
</script>

<style>
  .bd-placeholder-img {
    font-size: 1.125rem;
    text-anchor: middle;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
  }

  @media (min-width: 768px) {
    .bd-placeholder-img-lg {
      font-size: 3.5rem;
    }
  }
</style>
