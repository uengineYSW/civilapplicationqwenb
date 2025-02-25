package civilapplicationqwenb.domain;

import civilapplicationqwenb.DataCollectionApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DataCollector_table")
@Data
//<<< DDD / Aggregate Root
public class DataCollector {

    @Id
    private String dataCollectorId;

    @Enumerated(EnumType.STRING)
    private CollectionStatus status;

    private String schedule;

    private Date nextRunTime;

    @Embedded
    private UserData userData;

    @Embedded
    private Document document;

    @Embedded
    private ApplicationId applicationId;

    public static DataCollectorRepository repository() {
        DataCollectorRepository dataCollectorRepository = DataCollectionApplication.applicationContext.getBean(
            DataCollectorRepository.class
        );
        return dataCollectorRepository;
    }

    //<<< Clean Arch / Port Method
    public void createDataCollectionTask(
        CreateDataCollectionTaskCommand createDataCollectionTaskCommand
    ) {
        //implement business logic here:

        DataCollectionTaskCreated dataCollectionTaskCreated = new DataCollectionTaskCreated(
            this
        );
        dataCollectionTaskCreated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void startDataCollection(
        StartDataCollectionCommand startDataCollectionCommand
    ) {
        //implement business logic here:

        DataCollectionStarted dataCollectionStarted = new DataCollectionStarted(
            this
        );
        dataCollectionStarted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void completeDataCollection(
        CompleteDataCollectionCommand completeDataCollectionCommand
    ) {
        //implement business logic here:

        DataCollectionCompleted dataCollectionCompleted = new DataCollectionCompleted(
            this
        );
        dataCollectionCompleted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void failDataCollection(
        FailDataCollectionCommand failDataCollectionCommand
    ) {
        //implement business logic here:

        DataCollectionFailed dataCollectionFailed = new DataCollectionFailed(
            this
        );
        dataCollectionFailed.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void scheduleDataCollection(
        ScheduleDataCollectionCommand scheduleDataCollectionCommand
    ) {
        //implement business logic here:

        DataCollectionScheduleUpdated dataCollectionScheduleUpdated = new DataCollectionScheduleUpdated(
            this
        );
        dataCollectionScheduleUpdated.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
