package civilapplicationqwenb.domain;

import civilapplicationqwenb.ApplicationProcessingApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Application_table")
@Data
//<<< DDD / Aggregate Root
public class Application {

    @Id
    private String applicationNumber;

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    private Date applicationDate;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @Embedded
    private Document documents;

    @Embedded
    private UserData userData;

    @Embedded
    private DataCollectorId dataCollectorId;

    public static ApplicationRepository repository() {
        ApplicationRepository applicationRepository = ApplicationProcessingApplication.applicationContext.getBean(
            ApplicationRepository.class
        );
        return applicationRepository;
    }

    //<<< Clean Arch / Port Method
    public void createApplication(
        CreateApplicationCommand createApplicationCommand
    ) {
        //implement business logic here:

        ApplicationCreated applicationCreated = new ApplicationCreated(this);
        applicationCreated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void submitApplication(
        SubmitApplicationCommand submitApplicationCommand
    ) {
        //implement business logic here:

        ApplicationSubmitted applicationSubmitted = new ApplicationSubmitted(
            this
        );
        applicationSubmitted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void cancelApplication(
        CancelApplicationCommand cancelApplicationCommand
    ) {
        //implement business logic here:

        ApplicationCancelled applicationCancelled = new ApplicationCancelled(
            this
        );
        applicationCancelled.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
