<template>
    <v-card outlined>
        <v-card-title>
            SubmitApplication
        </v-card-title>

        <v-card-text>
            <String label="ApplicationNumber" v-model="value.applicationNumber" :editMode="editMode"/>
            <List&lt;Document&gt; offline label="Documents" v-model="value.documents" :editMode="editMode" @change="change"/>
            <DocumentList offline label="Documents" v-model="value.documents" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="submitApplication"
            >
                SubmitApplication
            </v-btn>
            
            <v-btn
                    color="primary"
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
        name: 'SubmitApplicationCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.applicationNumber = '';
            this.value.documents = [];
        },
        watch: {
        },
        methods: {
            submitApplication() {
                this.$emit('submitApplication', this.value);
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

