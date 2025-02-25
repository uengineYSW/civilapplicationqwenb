<template>
    <v-card outlined>
        <v-card-title>
            StartDataCollection
        </v-card-title>

        <v-card-text>
            <Date label="RunTime" v-model="value.runTime" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="startDataCollection"
            >
                StartDataCollection
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
        name: 'StartDataCollectionCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.runTime = '2025-02-25';
        },
        watch: {
        },
        methods: {
            startDataCollection() {
                this.$emit('startDataCollection', this.value);
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

