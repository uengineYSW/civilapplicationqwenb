package civilapplicationqwenb.infra;

import civilapplicationqwenb.config.kafka.KafkaProcessor;
import civilapplicationqwenb.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    DataCollectorRepository dataCollectorRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ApplicationCreated'"
    )
    public void wheneverApplicationCreated_DataCollectionTaskCreationPolicy(
        @Payload ApplicationCreated applicationCreated
    ) {
        ApplicationCreated event = applicationCreated;
        System.out.println(
            "\n\n##### listener DataCollectionTaskCreationPolicy : " +
            applicationCreated +
            "\n\n"
        );

        // Comments //
        //신청이 생성되면 데이터 수집 작업을 자동으로 생성합니다.

        // Sample Logic //

        CreateDataCollectionTaskCommand command = new CreateDataCollectionTaskCommand();
        //command.setSourceSystem("???");
        //command.setCollectionSchedule("???");
        //command.setStatus("???");
        // DataCollector.createDataCollectionTask(command);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ApplicationSubmitted'"
    )
    public void wheneverApplicationSubmitted_DataCollectionStartPolicy(
        @Payload ApplicationSubmitted applicationSubmitted
    ) {
        ApplicationSubmitted event = applicationSubmitted;
        System.out.println(
            "\n\n##### listener DataCollectionStartPolicy : " +
            applicationSubmitted +
            "\n\n"
        );

        // Comments //
        //신청이 제출되면 데이터 수집을 시작합니다.

        // Sample Logic //

        StartDataCollectionCommand command = new StartDataCollectionCommand();
        //command.setRunTime("???");
        // DataCollector.startDataCollection(command);
    }
}
//>>> Clean Arch / Inbound Adaptor
