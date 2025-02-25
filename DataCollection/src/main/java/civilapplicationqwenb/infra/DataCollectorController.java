package civilapplicationqwenb.infra;

import civilapplicationqwenb.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/dataCollectors")
@Transactional
public class DataCollectorController {

    @Autowired
    DataCollectorRepository dataCollectorRepository;

    @RequestMapping(
        value = "/dataCollectors/createdatacollectiontask",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public DataCollector createDataCollectionTask(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody CreateDataCollectionTaskCommand createDataCollectionTaskCommand
    ) throws Exception {
        System.out.println(
            "##### /dataCollector/createDataCollectionTask  called #####"
        );
        DataCollector dataCollector = new DataCollector();
        dataCollector.createDataCollectionTask(createDataCollectionTaskCommand);
        dataCollectorRepository.save(dataCollector);
        return dataCollector;
    }

    @RequestMapping(
        value = "/dataCollectors/startdatacollection",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public DataCollector startDataCollection(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody StartDataCollectionCommand startDataCollectionCommand
    ) throws Exception {
        System.out.println(
            "##### /dataCollector/startDataCollection  called #####"
        );
        DataCollector dataCollector = new DataCollector();
        dataCollector.startDataCollection(startDataCollectionCommand);
        dataCollectorRepository.save(dataCollector);
        return dataCollector;
    }

    @RequestMapping(
        value = "/dataCollectors/{id}/completedatacollection",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public DataCollector completeDataCollection(
        @PathVariable(value = "id") String id,
        @RequestBody CompleteDataCollectionCommand completeDataCollectionCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /dataCollector/completeDataCollection  called #####"
        );
        Optional<DataCollector> optionalDataCollector = dataCollectorRepository.findById(
            id
        );

        optionalDataCollector.orElseThrow(() -> new Exception("No Entity Found")
        );
        DataCollector dataCollector = optionalDataCollector.get();
        dataCollector.completeDataCollection(completeDataCollectionCommand);

        dataCollectorRepository.save(dataCollector);
        return dataCollector;
    }

    @RequestMapping(
        value = "/dataCollectors/{id}/faildatacollection",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public DataCollector failDataCollection(
        @PathVariable(value = "id") String id,
        @RequestBody FailDataCollectionCommand failDataCollectionCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /dataCollector/failDataCollection  called #####"
        );
        Optional<DataCollector> optionalDataCollector = dataCollectorRepository.findById(
            id
        );

        optionalDataCollector.orElseThrow(() -> new Exception("No Entity Found")
        );
        DataCollector dataCollector = optionalDataCollector.get();
        dataCollector.failDataCollection(failDataCollectionCommand);

        dataCollectorRepository.save(dataCollector);
        return dataCollector;
    }

    @RequestMapping(
        value = "/dataCollectors/{id}/scheduledatacollection",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public DataCollector scheduleDataCollection(
        @PathVariable(value = "id") String id,
        @RequestBody ScheduleDataCollectionCommand scheduleDataCollectionCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /dataCollector/scheduleDataCollection  called #####"
        );
        Optional<DataCollector> optionalDataCollector = dataCollectorRepository.findById(
            id
        );

        optionalDataCollector.orElseThrow(() -> new Exception("No Entity Found")
        );
        DataCollector dataCollector = optionalDataCollector.get();
        dataCollector.scheduleDataCollection(scheduleDataCollectionCommand);

        dataCollectorRepository.save(dataCollector);
        return dataCollector;
    }
}
//>>> Clean Arch / Inbound Adaptor
