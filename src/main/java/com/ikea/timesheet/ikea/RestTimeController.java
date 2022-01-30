package com.ikea.timesheet.ikea;

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
    public List<String> getTuples() {
        return this.jdbcTemplate.queryForList("SELECT * FROM timesheets").stream()
                .map(m -> m.values().toString())
                .collect(java.util.stream.Collectors.toList());
    }
}
