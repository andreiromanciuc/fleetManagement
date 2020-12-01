package com.andrei.fleetManagement.webController;

import com.andrei.fleetManagement.domain.Workmanship;
import com.andrei.fleetManagement.service.WorkmanshipService;
import com.andrei.fleetManagement.transfer.CreateWorkmanship;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/work")
public class WorkmanshipController {

    private final WorkmanshipService workmanshipService;

    public WorkmanshipController(WorkmanshipService workmanshipService) {
        this.workmanshipService = workmanshipService;
    }

    @PostMapping
    public Workmanship createWorkmanship(@RequestParam long contractId,
                                         @RequestParam long partnerId,
                                         @RequestBody CreateWorkmanship createWorkmanship) {
      return workmanshipService.createWorkmanship(contractId, partnerId, createWorkmanship);
    }

}
