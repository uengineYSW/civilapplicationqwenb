package civilapplicationqwenb.domain;

import civilapplicationqwenb.domain.*;
import civilapplicationqwenb.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PdfConfirmed extends AbstractEvent {

    private String documentId;
    private String confirmedBy;
    private Date confirmationDate;

    public PdfConfirmed(PdfDocument aggregate) {
        super(aggregate);
    }

    public PdfConfirmed() {
        super();
    }
}
//>>> DDD / Domain Event
