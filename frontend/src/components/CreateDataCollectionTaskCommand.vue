<template>
    <v-card outlined>
        <v-card-title>
            CreateDataCollectionTask
        </v-card-title>

        <v-card-text>
            <String label="SourceSystem" v-model="value.sourceSystem" :editMode="editMode"/>
            <String label="CollectionSchedule" v-model="value.collectionSchedule" :editMode="editMode"/>
            <CollectionStatus offline label="Status" v-model="value.status" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="createDataCollectionTask"
            >
                CreateDataCollectionTask
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
        name: 'CreateDataCollectionTaskCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.sourceSystem = '';
            this.value.collectionSchedule = '';
            this.value.status = {};
        },
        watch: {
        },
        methods: {
            createDataCollectionTask() {
                this.$emit('createDataCollectionTask', this.value);
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

