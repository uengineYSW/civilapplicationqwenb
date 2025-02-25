<template>

    <v-card outlined>
        <v-card-title>
            CreateApplication
        </v-card-title>

        <v-card-text>
            <ServiceType offline label="ServiceType" v-model="value.serviceType" :editMode="editMode" @change="change"/>
            <UserData offline label="UserData" v-model="value.userData" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="createApplication"
            >
                CreateApplication
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'CreateApplicationCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.serviceType = {};
            this.value.userData = {};
        },
        watch: {
        },
        methods: {
            createApplication() {
                this.$emit('createApplication', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

