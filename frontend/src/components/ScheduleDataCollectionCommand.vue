<template>
    <v-card outlined>
        <v-card-title>
            ScheduleDataCollection
        </v-card-title>

        <v-card-text>
            <String label="NewSchedule" v-model="value.newSchedule" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="scheduleDataCollection"
            >
                ScheduleDataCollection
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
        name: 'ScheduleDataCollectionCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.newSchedule = '';
        },
        watch: {
        },
        methods: {
            scheduleDataCollection() {
                this.$emit('scheduleDataCollection', this.value);
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

