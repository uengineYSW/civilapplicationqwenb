package civilapplicationqwenb.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class AddElectronicSignatureCommand {

    private String documentId;
    private String signatureData;
    private Date signatureDate;
}
