package com.ikea.timesheet.ikea;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://hello-cloud-run-web-2ia6hqkrja-uc.a.run.app")

@RestController
public class RestTimeController {
    private final JdbcTemplate jdbcTemplate;

    public RestTimeController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/getTimes")
    public List<Timesheet> getTuples() {
        List<Timesheet> tml = new ArrayList<Timesheet>();
        Timesheet tm = new Timesheet();

        int id = 10;

        String query = "SELECT * FROM timesheets";
        Timesheet tmlm = jdbcTemplate.queryForObject(
                query, new Object[] { id }, new TimeSheetmapper());

        tml.add(tmlm);
        return tml;
    }
}
