package civilapplicationqwenb.infra;

import civilapplicationqwenb.config.kafka.KafkaProcessor;
import civilapplicationqwenb.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    PdfDocumentRepository pdfDocumentRepository;

    @Autowired
    ElectronicSignatureRepository electronicSignatureRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DataCollectionCompleted'"
    )
    public void wheneverDataCollectionCompleted_PdfGenerationPolicy(
        @Payload DataCollectionCompleted dataCollectionCompleted
    ) {
        DataCollectionCompleted event = dataCollectionCompleted;
        System.out.println(
            "\n\n##### listener PdfGenerationPolicy : " +
            dataCollectionCompleted +
            "\n\n"
        );

        // Comments //
        //데이터 수집이 완료되면 PDF문서를 생성합니다.

        // Sample Logic //

        GeneratePdfCommand command = new GeneratePdfCommand();
        //command.setContent("???");
        //command.setUserData("???");
        //command.setDocuments("???");
        PdfDocument.generatePdf(command);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SignatureVerified'"
    )
    public void wheneverSignatureVerified_PdfFinalizationPolicy(
        @Payload SignatureVerified signatureVerified
    ) {
        SignatureVerified event = signatureVerified;
        System.out.println(
            "\n\n##### listener PdfFinalizationPolicy : " +
            signatureVerified +
            "\n\n"
        );

        // Comments //
        //전자서명이 검증되면 PDF문서를 최종화합니다

        // Sample Logic //

        FinalizePdfCommand command = new FinalizePdfCommand();
        //command.setDocumentId("???");
        //command.setStatus("???");
        PdfDocument.finalizePdf(command);
    }
}
//>>> Clean Arch / Inbound Adaptor
