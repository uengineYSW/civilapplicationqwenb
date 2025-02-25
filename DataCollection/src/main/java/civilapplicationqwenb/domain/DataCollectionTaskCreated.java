package civilapplicationqwenb.domain;

import civilapplicationqwenb.domain.*;
import civilapplicationqwenb.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class DataCollectionTaskCreated extends AbstractEvent {

    private String sourceSystem;
    private String collectionSchedule;
    private CollectionStatus status;
    private Date nextRunTime;

    public DataCollectionTaskCreated(DataCollector aggregate) {
        super(aggregate);
    }

    public DataCollectionTaskCreated() {
        super();
    }
}
//>>> DDD / Domain Event
