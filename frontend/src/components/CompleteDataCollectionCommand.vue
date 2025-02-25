<template>
    <v-card outlined>
        <v-card-title>
            CompleteDataCollection
        </v-card-title>

        <v-card-text>
            <List&lt;Document&gt; offline label="CollectedData" v-model="value.collectedData" :editMode="editMode" @change="change"/>
            <DocumentList offline label="CollectedData" v-model="value.collectedData" :editMode="editMode" @change="change"/>
            <CollectionStatus offline label="Status" v-model="value.status" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="completeDataCollection"
            >
                CompleteDataCollection
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
        name: 'CompleteDataCollectionCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.collectedData = [];
            this.value.status = {};
        },
        watch: {
        },
        methods: {
            completeDataCollection() {
                this.$emit('completeDataCollection', this.value);
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

