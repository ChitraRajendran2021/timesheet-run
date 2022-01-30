package com.ikea.timesheet.ikea;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://hello-cloud-run-web-2ia6hqkrja-uc.a.run.app")
@RestController
public class RestTimeController {
    @Autowired
    TimeSheetmapper itemRepo;

    @RequestMapping("/getTimes")
    @ResponseBody
    public List<Timesheet> getAllItems() {
        return itemRepo.getAllItems();
    }
}
