package civilapplicationqwenb.domain;

import civilapplicationqwenb.DocumentIssuanceApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PdfDocument_table")
@Data
//<<< DDD / Aggregate Root
public class PdfDocument {

    @Id
    private String documentId;

    private String content;

    private Date generationDate;

    @Enumerated(EnumType.STRING)
    private DocumentStatus status;

    @Embedded
    private UserData userData;

    @Embedded
    private Document documents;

    public static PdfDocumentRepository repository() {
        PdfDocumentRepository pdfDocumentRepository = DocumentIssuanceApplication.applicationContext.getBean(
            PdfDocumentRepository.class
        );
        return pdfDocumentRepository;
    }

    //<<< Clean Arch / Port Method
    public void generatePdf(GeneratePdfCommand generatePdfCommand) {
        //implement business logic here:

        PdfGenerated pdfGenerated = new PdfGenerated(this);
        pdfGenerated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void confirmPdf(ConfirmPdfCommand confirmPdfCommand) {
        //implement business logic here:

        PdfConfirmed pdfConfirmed = new PdfConfirmed(this);
        pdfConfirmed.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void addElectronicSignature(
        AddElectronicSignatureCommand addElectronicSignatureCommand
    ) {
        //implement business logic here:

        SignatureAdded signatureAdded = new SignatureAdded(this);
        signatureAdded.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void finalizePdf(FinalizePdfCommand finalizePdfCommand) {
        //implement business logic here:

        PdfFinalized pdfFinalized = new PdfFinalized(this);
        pdfFinalized.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
