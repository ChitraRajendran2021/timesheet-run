package com.ikea.timesheet.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.*;
import com.ikea.timesheet.model.Timesheet;

@Repository

public class TimeSheetRepository {
    @Autowired
    JdbcTemplate template;
    LocalDate today = LocalDate.now();

    // Go backward to get Monday
    LocalDate monday = today;

    /* Getting all Items from table */
    public List<Timesheet> getAllItems() {
        List<Timesheet> items = template.query("select id, curr_date,login_time,logout_time,over_time from timesheets",
                (result, rowNum) -> new Timesheet(result.getInt("id"),
                        result.getString("curr_date"), result.getTimestamp("login_time"),
                        result.getTimestamp("logout_time"), result.getLong("over_time")));

        return items;
    }

    /* Getting all Items from table */
    public List<Timesheet> findById(Long itemId) {
        List<Timesheet> items = template.query("SELECT * FROM timesheets WHERE ID=" + itemId,
                (result, rowNum) -> new Timesheet(result.getInt("id"),
                        result.getString("curr_date"), result.getTimestamp("login_time"),
                        result.getTimestamp("logout_time"), result.getLong("over_time")));
        return items;
    }

    public int getOverall() {
        while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
            monday = monday.minusDays(1);
        }

        // Go forward to get Sunday
        LocalDate sunday = today;
        while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
            sunday = sunday.plusDays(1);
        }
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        String query = ("SELECT SUM(over_time) FROM mytime.timesheets where SUBSTRING(curr_date, 1, 1) = "
                + month);
        return template.queryForObject(query, Integer.class);

    }

    public int saveTimesheet(long id, String currDate, Timestamp loginTime, Timestamp logoutTime, long overTime) {
        String query = "INSERT INTO timesheets VALUES(?,?,?,?,?)";
        return template.update(query, id, currDate, loginTime, logoutTime, overTime);
    }

    public int updateTimesheet(long id, String currDate, Timestamp loginTime, Timestamp logoutTime, long overTime) {
        String query = "UPDATE timesheets SET curr_date = ?, login_time = ?, logout_time = ?, over_time = ? WHERE id= ?";
        return template.update(query, currDate, loginTime, logoutTime, overTime, id);
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
