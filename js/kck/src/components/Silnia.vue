<template>
    <div class="appContainer">
        <div>
            <router-link to="/"> back </router-link>
        </div>
        <div class="appEvenSubContainer">
            <button v-on:click="computeSilnia" class="silniaButton silniaLeftPanel">
                1
            </button>
            <div class="appEvenSubContainer silniaRightPanel">
                <div class="appEvenSubContainer silniaButtonRow">
                    <button v-on:click="computeSilnia" class="silniaButton">2</button>
                    <button v-on:click="computeSilnia" class="silniaButton">3</button>
                </div>
                <div class="appEvenSubContainer silniaButtonRow">
                   <button v-on:click="computeSilnia" class="silniaButton">4</button>
                   <button v-on:click="computeSilnia" class="silniaButton">5</button>
               </div>
                <div class="appEvenSubContainer silniaButtonRow">
                    <button v-on:click="computeSilnia" class="silniaButton">6</button>
                    <button v-on:click="computeSilnia" class="silniaButton">7</button>
                </div>

            </div>
        </div>

    </div>
</template>

<script>
    export default {
        name: "Silnia",

        methods: {
            computeSilnia(event) {
                let number = event.target.innerText;
                console.log(this.silnia(number));
            },

            silnia(numb) {
                if(numb  < 2) {
                    return 1;
                } else {
                    return numb  * this.silnia(numb - 1);
                }
            }
        }
    }
</script>

<style scoped>

</style>