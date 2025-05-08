package com.escuela.colegio.controller;

import com.escuela.colegio.model.Holiday;
import com.escuela.colegio.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class HolidaysController {

    @Autowired
    private HolidayRepository holidayRepository;

    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable String display, Model model) {
        if (null != display && display.equals("all")) {
            model.addAttribute("festival", true);
            model.addAttribute("federal", true);
        } else if (null != display && display.equals("federal")) {
            model.addAttribute("federal", true);
        } else if (null != display && display.equals("festival")) {
            model.addAttribute("festival", true);
        }
        Iterable<Holiday> holidaysIterable = holidayRepository.findAll();
        List<Holiday> holidays = StreamSupport.stream(holidaysIterable.spliterator(), false).toList();
        //DEPRECATED
        //JDBC example:
//        List<Holiday> holidays = holidayRepository.findAllHolidays();
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    holidays.stream().filter(holiday ->
                            holiday.getType().equals(type)).collect(Collectors.toList()));
        }
        return "holidays.html";
    }
}
