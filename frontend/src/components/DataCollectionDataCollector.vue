<template>
    <v-card outlined>
        <template slot="progress">
            <v-progress-linear
                    color="primary-darker-1"
                    height="10"
                    indeterminate
            ></v-progress-linear>
        </template>

        <v-card-title v-if="value._links">
            데이터 수집기 # {{decode(value._links.self.href.split("/")[value._links.self.href.split("/").length - 1])}}
        </v-card-title >
        <v-card-title v-else>
            데이터 수집기
        </v-card-title >        

        <v-card-text style="background-color: white;">
            <String v-if="editMode" label="DataCollectorId" v-model="value.dataCollectorId" :editMode="editMode" :inputUI="''"/>
            <CollectionStatus offline label="Status" v-model="value.status" :editMode="editMode" @change="change"/>
            <String label="Schedule" v-model="value.schedule" :editMode="editMode" :inputUI="''"/>
            <Date label="NextRunTime" v-model="value.nextRunTime" :editMode="editMode" :inputUI="''"/>
            <UserData offline label="사용자 정보" v-model="value.userData" :editMode="editMode" @change="change"/>
            <Document offline label="문서" v-model="value.document" :editMode="editMode" @change="change"/>
            <ApplicationId offline label="applicationId" v-model="value.applicationId" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions style="background-color: white;">
            <v-spacer></v-spacer>
            <div v-if="!editMode">
                <v-btn
                    color="primary"
                    text
                    @click="edit"
                >
                    수정
                </v-btn>
                <v-btn
                    color="primary"
                    text
                    @click="remove"
                >
                    삭제
                </v-btn>
            </div>
            <div v-else>
                <v-btn
                    color="primary"
                    text
                    @click="save"
                >
                저장
                </v-btn>
                <v-btn
                    color="primary"
                    text
                    @click="editMode = false"
                    v-if="editMode && !isNew"
                >
                    취소
                </v-btn>
            </div>
        </v-card-actions>
        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openCreateDataCollectionTask"
            >
                CreateDataCollectionTask
            </v-btn>
            <v-dialog v-model="createDataCollectionTaskDiagram" width="500">
                <CreateDataCollectionTaskCommand
                    @closeDialog="closeCreateDataCollectionTask"
                    @createDataCollectionTask="createDataCollectionTask"
                ></CreateDataCollectionTaskCommand>
            </v-dialog>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openStartDataCollection"
            >
                StartDataCollection
            </v-btn>
            <v-dialog v-model="startDataCollectionDiagram" width="500">
                <StartDataCollectionCommand
                    @closeDialog="closeStartDataCollection"
                    @startDataCollection="startDataCollection"
                ></StartDataCollectionCommand>
            </v-dialog>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openCompleteDataCollection"
            >
                CompleteDataCollection
            </v-btn>
            <v-dialog v-model="completeDataCollectionDiagram" width="500">
                <CompleteDataCollectionCommand
                    @closeDialog="closeCompleteDataCollection"
                    @completeDataCollection="completeDataCollection"
                ></CompleteDataCollectionCommand>
            </v-dialog>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openFailDataCollection"
            >
                FailDataCollection
            </v-btn>
            <v-dialog v-model="failDataCollectionDiagram" width="500">
                <FailDataCollectionCommand
                    @closeDialog="closeFailDataCollection"
                    @failDataCollection="failDataCollection"
                ></FailDataCollectionCommand>
            </v-dialog>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openScheduleDataCollection"
            >
                ScheduleDataCollection
            </v-btn>
            <v-dialog v-model="scheduleDataCollectionDiagram" width="500">
                <ScheduleDataCollectionCommand
                    @closeDialog="closeScheduleDataCollection"
                    @scheduleDataCollection="scheduleDataCollection"
                ></ScheduleDataCollectionCommand>
            </v-dialog>
        </v-card-actions>

        <v-snackbar
            v-model="snackbar.status"
            :top="true"
            :timeout="snackbar.timeout"
            color="error"
        >
            {{ snackbar.text }}
            <v-btn dark text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
    </v-card>

</template>

<script>
    const axios = require('axios').default;


    export default {
        name: 'DataCollectionDataCollector',
        components:{
        },
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
        },
        data: () => ({
            snackbar: {
                status: false,
                timeout: 5000,
                text: '',
            },
            createDataCollectionTaskDiagram: false,
            startDataCollectionDiagram: false,
            completeDataCollectionDiagram: false,
            failDataCollectionDiagram: false,
            scheduleDataCollectionDiagram: false,
        }),
	async created() {
        },
        methods: {
            decode(value) {
                return decodeURIComponent(value);
            },
            selectFile(){
                if(this.editMode == false) {
                    return false;
                }
                var me = this
                var input = document.createElement("input");
                input.type = "file";
                input.accept = "image/*";
                input.id = "uploadInput";
                
                input.click();
                input.onchange = function (event) {
                    var file = event.target.files[0]
                    var reader = new FileReader();

                    reader.onload = function () {
                        var result = reader.result;
                        me.imageUrl = result;
                        me.value.photo = result;
                        
                    };
                    reader.readAsDataURL( file );
                };
            },
            edit() {
                this.editMode = true;
            },
            async save(){
                try {
                    var temp = null;

                    if(!this.offline) {
                        if(this.isNew) {
                            temp = await axios.post(axios.fixUrl('/dataCollectors'), this.value)
                        } else {
                            temp = await axios.put(axios.fixUrl(this.value._links.self.href), this.value)
                        }
                    }

                    if(this.value!=null) {
                        for(var k in temp.data) this.value[k]=temp.data[k];
                    } else {
                        this.value = temp.data;
                    }

                    this.editMode = false;
                    this.$emit('input', this.value);

                    if (this.isNew) {
                        this.$emit('add', this.value);
                    } else {
                        this.$emit('edit', this.value);
                    }

                    location.reload()

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
                
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.value._links.self.href))
                    }

                    this.editMode = false;
                    this.isDeleted = true;

                    this.$emit('input', this.value);
                    this.$emit('delete', this.value);

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            change(){
                this.$emit('input', this.value);
            },
            async createDataCollectionTask() {
                try {
                    if(!this.offline){
                        var temp = await axios.post(axios.fixUrl(this.value._links['/createdatacollectiontask'].href))
                        for(var k in temp.data) this.value[k]=temp.data[k];
                    }

                    this.editMode = false;
                    
                    this.$emit('input', this.value);
                    this.$emit('delete', this.value);
                
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            async startDataCollection() {
                try {
                    if(!this.offline){
                        var temp = await axios.post(axios.fixUrl(this.value._links['/startdatacollection'].href))
                        for(var k in temp.data) this.value[k]=temp.data[k];
                    }

                    this.editMode = false;
                    
                    this.$emit('input', this.value);
                    this.$emit('delete', this.value);
                
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            async completeDataCollection(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['completedatacollection'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeCompleteDataCollection();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openCompleteDataCollection() {
                this.completeDataCollectionDiagram = true;
            },
            closeCompleteDataCollection() {
                this.completeDataCollectionDiagram = false;
            },
            async failDataCollection(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['faildatacollection'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeFailDataCollection();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openFailDataCollection() {
                this.failDataCollectionDiagram = true;
            },
            closeFailDataCollection() {
                this.failDataCollectionDiagram = false;
            },
            async scheduleDataCollection(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['scheduledatacollection'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeScheduleDataCollection();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openScheduleDataCollection() {
                this.scheduleDataCollectionDiagram = true;
            },
            closeScheduleDataCollection() {
                this.scheduleDataCollectionDiagram = false;
            },
        },
    }
</script>

