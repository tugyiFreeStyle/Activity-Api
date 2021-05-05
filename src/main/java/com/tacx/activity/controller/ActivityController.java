package com.tacx.activity.controller;

import com.tacx.activity.application.command.deleteactivity.DeleteActivityCommand;
import com.tacx.activity.application.query.getactivitysummary.GetActivitySummaryQuery;
import com.tacx.activity.application.query.getactivitysummary.GetActivitySummaryResponse;
import com.tacx.activity.application.query.getactivitysummarylist.GetActivitySummaryListQuery;
import com.tacx.activity.application.query.getactivitysummarylist.GetActivitySummaryListResponse;
import com.tacx.activity.infrastructure.bus.Bus;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/activities")
public class ActivityController {

    private final Bus bus;

    @GetMapping("/summary")
    public GetActivitySummaryListResponse getActivitySummaryList() {
        return bus.execute(new GetActivitySummaryListQuery());
    }

    @GetMapping("/summary/{activityId}")
    public GetActivitySummaryResponse getActivitySummary(@PathVariable String activityId) {
        return bus.execute(new GetActivitySummaryQuery(activityId));
    }

    @DeleteMapping("/{activityId}")
    public void deleteActivity(@PathVariable String activityId) {
        bus.execute(new DeleteActivityCommand(activityId));
    }
}
