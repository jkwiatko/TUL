<template>
  <div>
    <router-link to="/another"> Another </router-link>
    <router-link to="/silnia"> Silnia </router-link>
    <HelloWorld />
  </div>
</template>

<script>
  import HelloWorld from '../components/HelloWorld'

  export default {
    components: {
      HelloWorld
    }
  }
</script>
