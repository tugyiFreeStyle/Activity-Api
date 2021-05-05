package com.tacx.activity.application.command.deleteactivity;

import com.tacx.activity.domain.ActivityService;
import com.tacx.activity.infrastructure.bus.RequestHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class DeleteActivityHandler implements RequestHandler<DeleteActivityCommand, DeleteActivityResponse> {

    private final ActivityService activityService;

    @Override
    public DeleteActivityResponse handle(DeleteActivityCommand request) {
        log.info("Handling delete-activity request with activityId:{}", request.getActivityId());

        activityService.delete(request.getActivityId());
        return new DeleteActivityResponse();
    }
}
