<template>
    <div style="max-height:80vh;">
        <div class="hierarchy">
            <div>민원신청 프로세스 &nbsp; ></div>
            <div>&nbsp; 신청서</div>
        </div>
        <div class="gs-bundle-of-buttons" style="max-height:10vh;">
            <v-btn @click="addNewRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Guest')">
                <v-icon small>mdi-plus-circle-outline</v-icon>등록
            </v-btn>
            <v-btn  @click="editSelectedRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Guest')">
                <v-icon small>mdi-pencil</v-icon>수정
            </v-btn>
            <v-btn @click="createApplicationDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Guest')">
                <v-icon small>mdi-minus-circle-outline</v-icon>신청 생성
            </v-btn>
            <v-dialog v-model="createApplicationDialog" width="500">
                <CreateApplicationCommand
                    @closeDialog="createApplicationDialog = false"
                    @createApplication="createApplication"
                ></CreateApplicationCommand>
            </v-dialog>
            <v-btn @click="submitApplicationDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Guest')">
                <v-icon small>mdi-minus-circle-outline</v-icon>신청 제출
            </v-btn>
            <v-dialog v-model="submitApplicationDialog" width="500">
                <SubmitApplicationCommand
                    @closeDialog="submitApplicationDialog = false"
                    @submitApplication="submitApplication"
                ></SubmitApplicationCommand>
            </v-dialog>
            <v-btn @click="cancelApplicationDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Guest')">
                <v-icon small>mdi-minus-circle-outline</v-icon>신청 취소
            </v-btn>
            <v-dialog v-model="cancelApplicationDialog" width="500">
                <CancelApplicationCommand
                    @closeDialog="cancelApplicationDialog = false"
                    @cancelApplication="cancelApplication"
                ></CancelApplicationCommand>
            </v-dialog>
            <v-btn @click="deleteSelectedRows" class="contrast-primary-text" small color="primary" :disabled="!hasRole('')">
                <v-icon small>mdi-minus-circle-outline</v-icon>삭제
            </v-btn>
            <excel-export-button class="contrast-primary-text" :exportService="this.exportService" :getFlex="getFlex" />
        </div>
        <ApplicationSummary @search="search"></ApplicationSummary>
        <ApplicationDetails @search="search"></ApplicationDetails>


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
            <wj-flex-grid-filter :filterColumns="['RowHeader','serviceType','applicationDate','status','documents','userData','dataCollectorId',]" />
            <wj-flex-grid-cell-template cellType="RowHeader" v-slot="cell">{{cell.row.index + 1}}</wj-flex-grid-cell-template>
            <wj-flex-grid-column binding="serviceType" header="ServiceType" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="applicationDate" header="ApplicationDate" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="status" header="Status" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="documents" header="문서" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <Document v-model="cell.item.documents" :editMode="editMode"></Document>
                </wj-flex-grid-cell-template>
            </wj-flex-grid-column>
            <wj-flex-grid-column binding="userData" header="사용자 정보" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <UserData v-model="cell.item.userData" :editMode="editMode"></UserData>
                </wj-flex-grid-cell-template>
            </wj-flex-grid-column>
            <wj-flex-grid-column binding="dataCollectorId" header="dataCollectorId" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <DataCollectorId v-model="cell.item.dataCollectorId" :editMode="editMode"></DataCollectorId>
                </wj-flex-grid-cell-template>
            </wj-flex-grid-column>
            <wj-flex-grid-column binding="dataCollectorId" header="데이터 수집기" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <DataCollectorId v-model="cell.item.dataCollectorId" :editMode="editMode"></DataCollectorId>
                    {{ cell.item.dataCollectorId }}
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
                            <div style="color:white; font-size:17px; font-weight:700;">신청서 등록</div>
                            <v-spacer></v-spacer>
                            <v-icon
                                color="white"
                                small
                                @click="closeDialog()"
                            >mdi-close</v-icon>
                        </v-toolbar>
                        <v-card-text>
                            <Application :offline="offline"
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
    name: 'applicationGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'applications',
        createApplicationDialog: false,
        submitApplicationDialog: false,
        cancelApplicationDialog: false,
    }),
    watch: {
    },
    methods:{
        createApplication(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "/createapplication", params)
                this.$EventBus.$emit('show-success','CreateApplication 성공적으로 처리되었습니다.')
                this.createApplicationDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
        submitApplication(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "submitapplication", params)
                this.$EventBus.$emit('show-success','SubmitApplication 성공적으로 처리되었습니다.')
                this.submitApplicationDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
        cancelApplication(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "cancelapplication", params)
                this.$EventBus.$emit('show-success','CancelApplication 성공적으로 처리되었습니다.')
                this.cancelApplicationDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
    }
}
</script>