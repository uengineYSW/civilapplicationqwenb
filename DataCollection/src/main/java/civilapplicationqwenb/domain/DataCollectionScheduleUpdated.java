package civilapplicationqwenb.domain;

import civilapplicationqwenb.domain.*;
import civilapplicationqwenb.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class DataCollectionScheduleUpdated extends AbstractEvent {

    private String newSchedule;
    private Date nextRunTime;

    public DataCollectionScheduleUpdated(DataCollector aggregate) {
        super(aggregate);
    }

    public DataCollectionScheduleUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
