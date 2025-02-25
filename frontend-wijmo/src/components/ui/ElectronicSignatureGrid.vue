<template>
    <div style="max-height:80vh;">
        <div class="hierarchy">
            <div>문서 발급 &nbsp; ></div>
            <div>&nbsp; 전자서명</div>
        </div>
        <div class="gs-bundle-of-buttons" style="max-height:10vh;">
            <v-btn @click="addNewRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Guest')">
                <v-icon small>mdi-plus-circle-outline</v-icon>등록
            </v-btn>
            <v-btn  @click="editSelectedRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('')">
                <v-icon small>mdi-pencil</v-icon>수정
            </v-btn>
            <v-btn @click="verifyElectronicSignatureDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Guest')">
                <v-icon small>mdi-minus-circle-outline</v-icon>전자 서명 검증
            </v-btn>
            <v-dialog v-model="verifyElectronicSignatureDialog" width="500">
                <VerifyElectronicSignatureCommand
                    @closeDialog="verifyElectronicSignatureDialog = false"
                    @verifyElectronicSignature="verifyElectronicSignature"
                ></VerifyElectronicSignatureCommand>
            </v-dialog>
            <v-btn @click="deleteSelectedRows" class="contrast-primary-text" small color="primary" :disabled="!hasRole('')">
                <v-icon small>mdi-minus-circle-outline</v-icon>삭제
            </v-btn>
            <excel-export-button class="contrast-primary-text" :exportService="this.exportService" :getFlex="getFlex" />
        </div>


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
            <wj-flex-grid-filter :filterColumns="['RowHeader','documentId','userId','signatureDate','status','userData','document',]" />
            <wj-flex-grid-cell-template cellType="RowHeader" v-slot="cell">{{cell.row.index + 1}}</wj-flex-grid-cell-template>
            <wj-flex-grid-column binding="documentId" header="DocumentId" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="userId" header="UserId" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="signatureDate" header="SignatureDate" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="status" header="Status" width="2*" :isReadOnly="true" align="center" />
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
                            <div style="color:white; font-size:17px; font-weight:700;">전자서명 등록</div>
                            <v-spacer></v-spacer>
                            <v-icon
                                color="white"
                                small
                                @click="closeDialog()"
                            >mdi-close</v-icon>
                        </v-toolbar>
                        <v-card-text>
                            <ElectronicSignature :offline="offline"
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
    name: 'electronicSignatureGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'electronicSignatures',
        verifyElectronicSignatureDialog: false,
    }),
    watch: {
    },
    methods:{
        verifyElectronicSignature(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "/verifyelectronicsignature", params)
                this.$EventBus.$emit('show-success','VerifyElectronicSignature 성공적으로 처리되었습니다.')
                this.verifyElectronicSignatureDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
    }
}
</script>