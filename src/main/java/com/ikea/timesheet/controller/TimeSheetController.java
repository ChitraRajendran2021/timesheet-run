package com.ikea.timesheet.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ikea.timesheet.model.Timesheet;
import com.ikea.timesheet.repository.TimeSheetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "https://hello-cloud-run-web-2ia6hqkrja-uc.a.run.app")
@RestController
public class TimeSheetController {
    @Autowired
    TimeSheetRepository timeSheetRepo;

    @GetMapping("/getTimes")
    @ResponseBody
    public List<Timesheet> getAllItems() {
        return timeSheetRepo.getAllItems();

    }

    // create timesheet rest api
    @PostMapping("/timesheets")
    public int createTimesheet(@RequestBody Timesheet timesheet) {
        return timeSheetRepo.saveTimesheet(timesheet.getId(), timesheet.getCurrDate(), timesheet.getLoginTime(),
                timesheet.getLogoutTime());
    }

    // get timesheet by id rest api
    /*
     * @GetMapping("/timesheets/{id}")
     * public ResponseEntity<Timesheet> getTimesheetById(@PathVariable Long id) {
     * Timesheet timesheet = timeSheetRepo.findById(id);
     * return ResponseEntity.ok(timesheet);
     * }
     */

    // update timesheet rest api

    @PutMapping("/timesheets/{id}")
    public int updateTimesheet(@PathVariable Long id, @RequestBody Timesheet timesheet) {
        return timeSheetRepo.saveTimesheet(timesheet.getId(), timesheet.getCurrDate(), timesheet.getLoginTime(),
                timesheet.getLogoutTime());

    }

    // delete timesheet rest api
    @DeleteMapping("/timesheets/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTimesheet(@PathVariable Long id) {
        timeSheetRepo.deleteTimeSheet(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
