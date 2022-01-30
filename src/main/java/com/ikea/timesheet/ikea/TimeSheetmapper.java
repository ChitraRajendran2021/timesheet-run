package com.ikea.timesheet.ikea;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TimeSheetmapper implements RowMapper<Timesheet> {

    @Override
    public Timesheet mapRow(ResultSet arg0, int arg1) throws SQLException {
        Timesheet tm = new Timesheet();

        tm.setId(arg0.getInt("id"));
        tm.setCurrDate(arg0.getString("curr_date"));
        tm.setLoginTime(arg0.getString("login_time"));
        tm.setLogoutTime(arg0.getString("logout_time"));

        return tm;
    }

}
