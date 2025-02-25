package civilapplicationqwenb.domain;

import civilapplicationqwenb.domain.*;
import civilapplicationqwenb.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ApplicationSubmitted extends AbstractEvent {

    private String applicationNumber;
    private ApplicationStatus status;
    private Date submissionDate;

    public ApplicationSubmitted(Application aggregate) {
        super(aggregate);
    }

    public ApplicationSubmitted() {
        super();
    }
}
//>>> DDD / Domain Event
