<template>
    <div style="max-height:80vh;">
        <div class="hierarchy">
            <div>추가 정보 수집 &nbsp; ></div>
            <div>&nbsp; 데이터 수집기</div>
        </div>
        <div class="gs-bundle-of-buttons" style="max-height:10vh;">
            <v-btn @click="addNewRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Guest')">
                <v-icon small>mdi-plus-circle-outline</v-icon>등록
            </v-btn>
            <v-btn  @click="editSelectedRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('System')">
                <v-icon small>mdi-pencil</v-icon>수정
            </v-btn>
            <v-btn @click="createDataCollectionTaskDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Guest')">
                <v-icon small>mdi-minus-circle-outline</v-icon>데이터 수집 작업 생성
            </v-btn>
            <v-dialog v-model="createDataCollectionTaskDialog" width="500">
                <CreateDataCollectionTaskCommand
                    @closeDialog="createDataCollectionTaskDialog = false"
                    @createDataCollectionTask="createDataCollectionTask"
                ></CreateDataCollectionTaskCommand>
            </v-dialog>
            <v-btn @click="startDataCollectionDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('System')">
                <v-icon small>mdi-minus-circle-outline</v-icon>데이터 수집 시작
            </v-btn>
            <v-dialog v-model="startDataCollectionDialog" width="500">
                <StartDataCollectionCommand
                    @closeDialog="startDataCollectionDialog = false"
                    @startDataCollection="startDataCollection"
                ></StartDataCollectionCommand>
            </v-dialog>
            <v-btn @click="completeDataCollectionDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('System')">
                <v-icon small>mdi-minus-circle-outline</v-icon>데이터 수집 완료
            </v-btn>
            <v-dialog v-model="completeDataCollectionDialog" width="500">
                <CompleteDataCollectionCommand
                    @closeDialog="completeDataCollectionDialog = false"
                    @completeDataCollection="completeDataCollection"
                ></CompleteDataCollectionCommand>
            </v-dialog>
            <v-btn @click="failDataCollectionDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('System')">
                <v-icon small>mdi-minus-circle-outline</v-icon>데이터 수집 실패
            </v-btn>
            <v-dialog v-model="failDataCollectionDialog" width="500">
                <FailDataCollectionCommand
                    @closeDialog="failDataCollectionDialog = false"
                    @failDataCollection="failDataCollection"
                ></FailDataCollectionCommand>
            </v-dialog>
            <v-btn @click="scheduleDataCollectionDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Guest')">
                <v-icon small>mdi-minus-circle-outline</v-icon>데이터 수집 일정 설정
            </v-btn>
            <v-dialog v-model="scheduleDataCollectionDialog" width="500">
                <ScheduleDataCollectionCommand
                    @closeDialog="scheduleDataCollectionDialog = false"
                    @scheduleDataCollection="scheduleDataCollection"
                ></ScheduleDataCollectionCommand>
            </v-dialog>
            <v-btn @click="deleteSelectedRows" class="contrast-primary-text" small color="primary" :disabled="!hasRole('')">
                <v-icon small>mdi-minus-circle-outline</v-icon>삭제
            </v-btn>
            <excel-export-button class="contrast-primary-text" :exportService="this.exportService" :getFlex="getFlex" />
        </div>
        <DataCollectionTaskSummary @search="search"></DataCollectionTaskSummary>
        <DataCollectionTaskDetails @search="search"></DataCollectionTaskDetails>
        <DataCollectionLog @search="search"></DataCollectionLog>


        <!-- the grid -->
        <wj-flex-grid
            ref="flexGrid"
            :key="value.length"
            :autoGenerateColumns="false"
            :allowAddNew="false"
            :allowDelete="true"
            :allowPinning="'SingleColumn'"
            :newRowAtTop="false"
            :showMarquee="true"
            :selectionMode="'MultiRange'"
            :validateEdits="false"
            :itemsSource="value"
            :initialized="flexInitialized"
            :selectionChanged="onSelectionChanged"
            style="margin-top:10px; max-height:65vh;"
            class="wj-felx-grid"
        >
            <wj-flex-grid-filter :filterColumns="['RowHeader','status','schedule','nextRunTime','userData','document','applicationId',]" />
            <wj-flex-grid-cell-template cellType="RowHeader" v-slot="cell">{{cell.row.index + 1}}</wj-flex-grid-cell-template>
            <wj-flex-grid-column binding="status" header="Status" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="schedule" header="Schedule" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="nextRunTime" header="NextRunTime" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="userData" header="사용자 정보" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <UserData v-model="cell.item.userData" :editMode="editMode"></UserData>
                </wj-flex-grid-cell-template>
            </wj-flex-grid-column>
            <wj-flex-grid-column binding="document" header="문서" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <Document v-model="cell.item.document" :editMode="editMode"></Document>
                </wj-flex-grid-cell-template>
            </wj-flex-grid-column>
            <wj-flex-grid-column binding="applicationId" header="applicationId" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <ApplicationId v-model="cell.item.applicationId" :editMode="editMode"></ApplicationId>
                </wj-flex-grid-cell-template>
            </wj-flex-grid-column>
            <wj-flex-grid-column binding="applicationId" header="신청서" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <ApplicationId v-model="cell.item.applicationId" :editMode="editMode"></ApplicationId>
                    {{ cell.item.applicationId }}
                </wj-flex-grid-cell-template>
            </wj-flex-grid-column>
        </wj-flex-grid>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <template>
                    <v-card>
                        <v-toolbar
                            color="primary"
                            class="elevation-0"
                            height="50px"
                        >
                            <div style="color:white; font-size:17px; font-weight:700;">데이터 수집기 등록</div>
                            <v-spacer></v-spacer>
                            <v-icon
                                color="white"
                                small
                                @click="closeDialog()"
                            >mdi-close</v-icon>
                        </v-toolbar>
                        <v-card-text>
                            <DataCollector :offline="offline"
                                :isNew="!itemToEdit"
                                :editMode="true"
                                v-model="newValue"
                                @add="append"
                                @edit="edit"
                            />
                        </v-card-text>
                    </v-card>
                </template>
            </v-dialog>
        </v-col>
        <v-col style="margin-bottom:40px;">
            <div class="text-center">
                <v-dialog
                    width="332.5"
                    fullscreen
                    hide-overlay
                    transition="dialog-bottom-transition"
                >
                    <v-btn
                        style="position:absolute; top:2%; right:2%"
                        @click="closeDialog()"
                        depressed
                        icon 
                        absolute
                    >
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-dialog>
            </div>
        </v-col>
    </div>
</template>

<script>
import BaseGrid from '../base-ui/BaseGrid'

export default {
    name: 'dataCollectorGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'dataCollectors',
        createDataCollectionTaskDialog: false,
        startDataCollectionDialog: false,
        completeDataCollectionDialog: false,
        failDataCollectionDialog: false,
        scheduleDataCollectionDialog: false,
    }),
    watch: {
    },
    methods:{
        createDataCollectionTask(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "/createdatacollectiontask", params)
                this.$EventBus.$emit('show-success','CreateDataCollectionTask 성공적으로 처리되었습니다.')
                this.createDataCollectionTaskDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
        startDataCollection(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "/startdatacollection", params)
                this.$EventBus.$emit('show-success','StartDataCollection 성공적으로 처리되었습니다.')
                this.startDataCollectionDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
        completeDataCollection(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "completedatacollection", params)
                this.$EventBus.$emit('show-success','CompleteDataCollection 성공적으로 처리되었습니다.')
                this.completeDataCollectionDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
        failDataCollection(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "faildatacollection", params)
                this.$EventBus.$emit('show-success','FailDataCollection 성공적으로 처리되었습니다.')
                this.failDataCollectionDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
        scheduleDataCollection(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "scheduledatacollection", params)
                this.$EventBus.$emit('show-success','ScheduleDataCollection 성공적으로 처리되었습니다.')
                this.scheduleDataCollectionDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
    }
}
</script>