package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return "events/create";
   }

   @PostMapping("create")
   public String createEvent(@RequestParam String eventName) {
        //events.add(eventName);
//       return "redirect:";
       //events.add(new Event(eventName));
       return "redirect:/events";
   }

    @PostMapping("save")
    public String processCreateEventForm(@RequestParam String eventName,
                                         @RequestParam String eventDescription) {
        //events.put(eventName, eventDescription);
        EventData.add(new Event(eventName, eventDescription));
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

}
