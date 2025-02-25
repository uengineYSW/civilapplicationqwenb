package civilapplicationqwenb.domain;

import civilapplicationqwenb.domain.*;
import civilapplicationqwenb.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ApplicationCancelled extends AbstractEvent {

    private String applicationNumber;
    private String cancellationReason;
    private Date cancelledAt;

    public ApplicationCancelled(Application aggregate) {
        super(aggregate);
    }

    public ApplicationCancelled() {
        super();
    }
}
//>>> DDD / Domain Event
