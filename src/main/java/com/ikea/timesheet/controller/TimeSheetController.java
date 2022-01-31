package com.ikea.timesheet.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.pubsub.v1.PubsubMessage;
import com.ikea.timesheet.model.Timesheet;
import com.ikea.timesheet.repository.TimeSheetRepository;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import org.springframework.http.MediaType;

@CrossOrigin(origins = "*")
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
        System.out.println("ddddddddddddd");
        System.out.println("AAAAAAAAAAA" + timesheet.getCurrDate());
        System.out.println("BBBBBBBBBBB" + timesheet.getLoginTime());
        System.out.println("CCCCCCCCCC" + timesheet.getLogoutTime());
        return timeSheetRepo.saveTimesheet(timesheet.getId(), timesheet.getCurrDate(), timesheet.getLoginTime(),
                timesheet.getLogoutTime());
    }

    @PostMapping(value = "/crtime", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE })
    public int createTimesheetEvent(@RequestBody String timesheet)
            throws InvalidProtocolBufferException, ParseException, UnsupportedEncodingException {
        System.out.println("ddddddddddddd");
        System.out.println("AAAAAAAAAAA" + timesheet);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(timesheet);
        System.out.println(json.get("data"));
        String aa = (String) json.get("data");
        String s2 = new String(aa.getBytes(), "UTF-8");
        System.out.println("---------" + s2);
        // String dd =
        // PubsubMessage.parser().parseFrom(timesheet.getBytes()).getData().toStringUtf8();
        // System.out.println("Data: " + dd);

        return 1;
    }
    // get timesheet by id rest api

    @GetMapping("/timesheets/{id}")
    public ResponseEntity<Timesheet> getTimesheetById(@PathVariable Long id) {
        Timesheet timesheet = timeSheetRepo.findById(id).get(0);
        return ResponseEntity.ok(timesheet);
    }

    // update timesheet rest api

    @PutMapping("/timesheets/{id}")
    public int updateTimesheet(@PathVariable Long id, @RequestBody Timesheet timesheet) {
        return timeSheetRepo.updateTimesheet(id, timesheet.getCurrDate(), timesheet.getLoginTime(),
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
