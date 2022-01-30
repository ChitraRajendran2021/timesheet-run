package com.ikea.timesheet.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

import com.ikea.timesheet.model.Timesheet;

@Repository

public class TimeSheetRepository {
    @Autowired
    JdbcTemplate template;

    /* Getting all Items from table */
    public List<Timesheet> getAllItems() {
        List<Timesheet> items = template.query("select id, curr_date,login_time,logout_time from timesheets",
                (result, rowNum) -> new Timesheet(result.getInt("id"),
                        result.getString("curr_date"), result.getTimestamp("login_time"),
                        result.getTimestamp("logout_time")));
        return items;
    }

    /* Getting all Items from table */
    public List<Timesheet> findById(Long itemId) {
        List<Timesheet> items = template.query("SELECT * FROM timesheets WHERE ID=" + itemId,
                (result, rowNum) -> new Timesheet(result.getInt("id"),
                        result.getString("curr_date"), result.getTimestamp("login_time"),
                        result.getTimestamp("logout_time")));
        return items;
    }

    public int saveTimesheet(long id, String currDate, Timestamp loginTime, Timestamp logoutTime) {
        String query = "INSERT INTO timesheets VALUES(?,?,?,?)";
        return template.update(query, id, currDate, loginTime, logoutTime);
    }

    public int updateTimesheet(long id, String currDate, Timestamp loginTime, Timestamp logoutTime) {
        String query = "UPDATE timesheets SET curr_date = ?, login_time = ?, logout_time = ? WHERE id= ?";
        return template.update(query, currDate, loginTime, logoutTime, id);
    }

    /*
     * public Timesheet findById(Long itemId) {
     * String query = "SELECT * FROM timesheets WHERE ID=?";
     * Timesheet timesheet = template.queryForObject(query, new Object[] { itemId },
     * new BeanPropertyRowMapper<>(Timesheet.class));
     * 
     * return timesheet;
     * }
     */
    /* delete an item from database */
    public int deleteTimeSheet(Long id) {
        String query = "DELETE FROM timesheets WHERE ID =?";
        return template.update(query, id);
    }
}
