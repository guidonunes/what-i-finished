package com.example.backend.service;


import com.example.backend.model.Activity;
import com.example.backend.model.Category;
import com.example.backend.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public List<Activity> getActivitiesByCategory(Category category) {
        return activityRepository.findByCategory(category);
    }

    public Activity addActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity updateActivity(Long id, Activity updatedActivity, Long categoryId) {
        Activity existingActivity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));

        existingActivity.setTitle(updatedActivity.getTitle());
        existingActivity.setCompletionDate(updatedActivity.getCompletionDate());
        existingActivity.setRating(updatedActivity.getRating());
        existingActivity.setNotes(updatedActivity.getNotes());

        existingActivity.setCategory(updatedActivity.getCategory());
        
        return activityRepository.save(existingActivity);
    }


    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }
}
