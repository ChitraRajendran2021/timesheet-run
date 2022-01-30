package com.ikea.timesheet.ikea;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public class TimeSheetmapper {
    @Autowired
    JdbcTemplate template;

    /* Getting all Items from table */
    public List<Timesheet> getAllItems() {
        List<Timesheet> items = template.query("select id, curr_date,login_time,logout_time from timesheets",
                (result, rowNum) -> new Timesheet(result.getInt("id"),
                        result.getString("curr_date"), result.getString("login_time"),
                        result.getString("logout_time")));
        return items;
    }
}
