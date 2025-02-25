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
@Table(name = "ElectronicSignature_table")
@Data
//<<< DDD / Aggregate Root
public class ElectronicSignature {

    @Id
    private String signatureId;

    private String documentId;

    private String userId;

    private Date signatureDate;

    @Enumerated(EnumType.STRING)
    private SignatureStatus status;

    @Embedded
    private UserData userData;

    @Embedded
    private Document document;

    public static ElectronicSignatureRepository repository() {
        ElectronicSignatureRepository electronicSignatureRepository = DocumentIssuanceApplication.applicationContext.getBean(
            ElectronicSignatureRepository.class
        );
        return electronicSignatureRepository;
    }

    //<<< Clean Arch / Port Method
    public void verifyElectronicSignature(
        VerifyElectronicSignatureCommand verifyElectronicSignatureCommand
    ) {
        //implement business logic here:

        SignatureVerified signatureVerified = new SignatureVerified(this);
        signatureVerified.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
