package civilapplicationqwenb.domain;

import civilapplicationqwenb.domain.*;
import civilapplicationqwenb.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PdfGenerated extends AbstractEvent {

    private String documentId;
    private String content;
    private Date generationDate;
    private DocumentStatus status;
    private UserData userData;

    public PdfGenerated(PdfDocument aggregate) {
        super(aggregate);
    }

    public PdfGenerated() {
        super();
    }
}
//>>> DDD / Domain Event
