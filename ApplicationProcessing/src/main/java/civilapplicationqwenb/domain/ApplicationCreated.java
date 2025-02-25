package civilapplicationqwenb.domain;

import civilapplicationqwenb.domain.*;
import civilapplicationqwenb.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ApplicationCreated extends AbstractEvent {

    private String applicationNumber;
    private ServiceType serviceType;
    private Date applicationDate;
    private ApplicationStatus status;
    private UserData userData;

    public ApplicationCreated(Application aggregate) {
        super(aggregate);
    }

    public ApplicationCreated() {
        super();
    }
}
//>>> DDD / Domain Event
