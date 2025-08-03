package com.project.pos_system_update.dining.controller;

import com.project.pos_system_update.dining.service.DiningService;
import com.project.pos_system_update.dining.entity.Dining;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DiningController {
    private final DiningService diningService;

    public DiningController(DiningService diningService) {
        this.diningService = diningService;
    }

    @PostMapping("/dining")
    public List<Dining> getAllDining(@RequestBody Map<String, Object> body) {
        String businessId = (String)body.get("businessId");
        System.out.println(businessId);
        return diningService.getAllTableByBusinessId(businessId);
    }
}
