<template>

    <v-card outlined>
        <v-card-title>
            CancelApplication
        </v-card-title>

        <v-card-text>
            <String label="ApplicationNumber" v-model="value.applicationNumber" :editMode="editMode"/>
            <String label="CancellationReason" v-model="value.cancellationReason" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="cancelApplication"
            >
                CancelApplication
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
        name: 'CancelApplicationCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.applicationNumber = '';
            this.value.cancellationReason = '';
        },
        watch: {
        },
        methods: {
            cancelApplication() {
                this.$emit('cancelApplication', this.value);
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

