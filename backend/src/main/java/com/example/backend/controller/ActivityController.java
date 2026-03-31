package com.example.backend.controller;


import com.example.backend.model.Activity;
import com.example.backend.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;


    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public List<Activity> getActivities() {
        return activityService.getAllActivities();
    }

    @PostMapping
    public ResponseEntity<Activity> addActivity(
            @RequestBody Activity activity,
            @RequestParam Long categoryId
            ) {
        return ResponseEntity.ok(activityService.addActivity(activity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(
            @PathVariable Long id,
            @RequestBody Activity activity,
            @RequestParam(required = false) Long newCategoryId
    ) {
        return ResponseEntity.ok(activityService.updateActivity(id, activity, newCategoryId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Activity> deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }

}
