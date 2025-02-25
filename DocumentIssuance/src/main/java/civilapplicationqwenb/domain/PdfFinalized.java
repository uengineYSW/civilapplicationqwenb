package civilapplicationqwenb.domain;

import civilapplicationqwenb.domain.*;
import civilapplicationqwenb.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PdfFinalized extends AbstractEvent {

    private String documentId;
    private DocumentStatus status;
    private Date finalizationDate;

    public PdfFinalized(PdfDocument aggregate) {
        super(aggregate);
    }

    public PdfFinalized() {
        super();
    }
}
//>>> DDD / Domain Event
