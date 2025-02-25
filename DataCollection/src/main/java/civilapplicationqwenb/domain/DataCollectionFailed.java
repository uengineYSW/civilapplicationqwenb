package civilapplicationqwenb.domain;

import civilapplicationqwenb.domain.*;
import civilapplicationqwenb.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class DataCollectionFailed extends AbstractEvent {

    private Date endTime;
    private CollectionStatus status;
    private String errorDetails;

    public DataCollectionFailed(DataCollector aggregate) {
        super(aggregate);
    }

    public DataCollectionFailed() {
        super();
    }
}
//>>> DDD / Domain Event
