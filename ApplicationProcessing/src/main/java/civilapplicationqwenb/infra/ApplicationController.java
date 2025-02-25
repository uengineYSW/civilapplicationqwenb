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
// @RequestMapping(value="/applications")
@Transactional
public class ApplicationController {

    @Autowired
    ApplicationRepository applicationRepository;

    @RequestMapping(
        value = "/applications/createapplication",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Application createApplication(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody CreateApplicationCommand createApplicationCommand
    ) throws Exception {
        System.out.println(
            "##### /application/createApplication  called #####"
        );
        Application application = new Application();
        application.createApplication(createApplicationCommand);
        applicationRepository.save(application);
        return application;
    }

    @RequestMapping(
        value = "/applications/{id}/submitapplication",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Application submitApplication(
        @PathVariable(value = "id") String id,
        @RequestBody SubmitApplicationCommand submitApplicationCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /application/submitApplication  called #####"
        );
        Optional<Application> optionalApplication = applicationRepository.findById(
            id
        );

        optionalApplication.orElseThrow(() -> new Exception("No Entity Found"));
        Application application = optionalApplication.get();
        application.submitApplication(submitApplicationCommand);

        applicationRepository.save(application);
        return application;
    }

    @RequestMapping(
        value = "/applications/{id}/cancelapplication",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Application cancelApplication(
        @PathVariable(value = "id") String id,
        @RequestBody CancelApplicationCommand cancelApplicationCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /application/cancelApplication  called #####"
        );
        Optional<Application> optionalApplication = applicationRepository.findById(
            id
        );

        optionalApplication.orElseThrow(() -> new Exception("No Entity Found"));
        Application application = optionalApplication.get();
        application.cancelApplication(cancelApplicationCommand);

        applicationRepository.save(application);
        return application;
    }
}
//>>> Clean Arch / Inbound Adaptor
