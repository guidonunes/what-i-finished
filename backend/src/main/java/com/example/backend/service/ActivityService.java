package com.example.backend.service;


import com.example.backend.model.Activity;
import com.example.backend.model.Category;
import com.example.backend.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final CategoryService categoryService;

    public ActivityService(ActivityRepository activityRepository, CategoryService categoryService) {
        this.activityRepository = activityRepository;
        this.categoryService = categoryService;
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public List<Activity> getActivitiesByCategory(Long categoryId) {
        return activityRepository.findByCategoryId(categoryId);
    }

    public Activity addActivity(Activity activity, Long categoryId) {
        // 1. Find the category using our other service
        Category category = categoryService.getCategoryById(categoryId);

        // 2. Link the category to the activity
        activity.setCategory(category);

        // 3. Save the activity to the database
        return activityRepository.save(activity);
    }

    public Activity updateActivity(Long id, Activity updatedActivity, Long categoryId) {
        Activity existingActivity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));

        existingActivity.setTitle(updatedActivity.getTitle());
        existingActivity.setCompletionDate(updatedActivity.getCompletionDate());
        existingActivity.setRating(updatedActivity.getRating());
        existingActivity.setNotes(updatedActivity.getNotes());

        if (categoryId != null && !existingActivity.getCategory().getId().equals(categoryId)) {
            Category newCategory = categoryService.getCategoryById(categoryId);
            existingActivity.setCategory(newCategory);
        }

        // 4. Save and return
        return activityRepository.save(existingActivity);
    }


    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }
}
