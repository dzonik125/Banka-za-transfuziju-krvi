package group8.bloodbank.controller;

import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.MonthlySubscription;
import group8.bloodbank.service.interfaces.MonthlySubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monthlySubscriptions")
@CrossOrigin(origins = "http://localhost:4201")
public class MonthlySubscriptionController {

    private final MonthlySubscriptionService monthlySubscriptionService;

    @Autowired
    public MonthlySubscriptionController(MonthlySubscriptionService monthlySubscriptionService) {
        this.monthlySubscriptionService = monthlySubscriptionService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonthlySubscription> getAll() {
        return monthlySubscriptionService.getAll();
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void changeSubscriptionStatus(@RequestBody MonthlySubscription monthlySubscription) {
        monthlySubscriptionService.changeStatus(monthlySubscription);
    }



}
