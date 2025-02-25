<template>
    <v-card outlined>
        <v-card-title>
            FailDataCollection
        </v-card-title>

        <v-card-text>
            <String label="ErrorDetails" v-model="value.errorDetails" :editMode="editMode"/>
            <CollectionStatus offline label="Status" v-model="value.status" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="failDataCollection"
            >
                FailDataCollection
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
        name: 'FailDataCollectionCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.errorDetails = '';
            this.value.status = {};
        },
        watch: {
        },
        methods: {
            failDataCollection() {
                this.$emit('failDataCollection', this.value);
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

