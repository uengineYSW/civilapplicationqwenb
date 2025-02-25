package civilapplicationqwenb.infra;

import civilapplicationqwenb.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class DataCollectorHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<DataCollector>> {

    @Override
    public EntityModel<DataCollector> process(
        EntityModel<DataCollector> model
    ) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/createdatacollectiontask"
                )
                .withRel("createdatacollectiontask")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/startdatacollection"
                )
                .withRel("startdatacollection")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/completedatacollection"
                )
                .withRel("completedatacollection")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/faildatacollection"
                )
                .withRel("faildatacollection")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/scheduledatacollection"
                )
                .withRel("scheduledatacollection")
        );

        return model;
    }
}
