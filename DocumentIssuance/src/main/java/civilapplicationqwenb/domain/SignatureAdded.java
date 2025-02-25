package civilapplicationqwenb.domain;

import civilapplicationqwenb.domain.*;
import civilapplicationqwenb.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class SignatureAdded extends AbstractEvent {

    private String documentId;
    private String signatureId;
    private Date signatureDate;

    public SignatureAdded(PdfDocument aggregate) {
        super(aggregate);
    }

    public SignatureAdded() {
        super();
    }
}
//>>> DDD / Domain Event
