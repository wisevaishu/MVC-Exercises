package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.validation.*;
import org.springframework.validation.Errors;

import jakarta.validation.Valid;


@Controller
@RequestMapping("events")
public class EventController {

    //private static List<String> events = new ArrayList<>();

    //private static HashMap<String, String> events = new HashMap<>();
   // private static List<Event> events = new ArrayList<>();

//    static {
//        // Initialize some events with their descriptions
//        events.put("Menteaship", "A fun meetup for connecting with mentors");
//        events.put("Code With Pride", "A fun meetup sponsored by LaunchCode");
//        events.put("Javascripty", "An imaginary meetup for Javascript developers");
//    }

    @GetMapping
    public String displayEvents(Model model) {
//        List<String> eventNames = new ArrayList<>();
//        eventNames.add("Coding");
//        eventNames.add("Tech");
//        eventNames.add("Hackathon");
//        model.addAttribute("eventNames", eventNames);

        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }



    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute("event", new Event());
        model.addAttribute("types" ,EventType.values());
        return "events/create";
   }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent,
                                         Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            //model.addAttribute("errMsg", errors.getAllErrors().get(0).getDefaultMessage());
            return "events/create";
        }
        EventData.add(newEvent);
        return "redirect:/events";
    }


    @PostMapping("save")
//    public String processCreateEventForm(@RequestParam String eventName,
//                                         @RequestParam String eventDescription) {
    public String processCreateEventForm(@ModelAttribute Event newEvent) {
        //events.put(eventName, eventDescription);
        EventData.add(newEvent);
        return "redirect:/events";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEvents(@RequestParam(required = false) int[] eventIds) {
        if(eventIds != null) {


            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        return "redirect:/events";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable Integer eventId) {
        // controller code will go here
        if (eventId == null) {

            return "error"; // Replace "error" with the appropriate view name
        }
        Event eventToEdit = EventData.getById(eventId);
        if (eventToEdit == null) {
            return "null";
        }
        model.addAttribute("event", eventToEdit);
        String title = "Edit Event " + eventToEdit.getName() + " (id=" + eventToEdit.getId() + ")";
        model.addAttribute("title", title );
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(Integer eventId, String name, String description) {
        // controller code will go here
        Event eventToEdit = EventData.getById(eventId);
        if (eventToEdit != null) {
            eventToEdit.setName(name);
            eventToEdit.setDescription(description);

        }
        return "redirect:/events";
    }

}
