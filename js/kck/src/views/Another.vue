<template>
    <div>
        <router-link to="/"> back </router-link>
        <HelloWorld :parentData="myData"/>
    </div>
</template>

<script>
    import HelloWorld from "../components/HelloWorld";
    export default {
        name: "Another",
        components: {HelloWorld},

        data () {
            return {
                myData: "from hej hej view"
            }

        }
    }
</script>

<style scoped>

</style>