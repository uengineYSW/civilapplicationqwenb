package civilapplicationqwenb.infra;

import civilapplicationqwenb.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ApplicationHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Application>> {

    @Override
    public EntityModel<Application> process(EntityModel<Application> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/createapplication"
                )
                .withRel("createapplication")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/submitapplication"
                )
                .withRel("submitapplication")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/cancelapplication"
                )
                .withRel("cancelapplication")
        );

        return model;
    }
}
