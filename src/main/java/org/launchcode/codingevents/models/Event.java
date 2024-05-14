package org.launchcode.codingevents.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Objects;
//import jakarta.validation.constraints.*;


public class Event {

    private int id;
    private static int nextId = 1;

    @NotBlank(message = "name is required")
    @Size(min = 3, max = 50, message = "name must be between 3 and 50")
    private String name;


    @Size(max=500, message = "description too long")
    private String description;

    @NotBlank(message = "email is required")
    @Email(message = "Invalid email try again")
    private String contactEmail;

    @NotBlank(message = "Location cannot be left blank.")
    private String location;

    private String isPresent;

    @Positive(message = "Number of attendees must be one or more.")
    private int numberOfAttendees;


    public Event(String name, String description, String contactEmail, String location, String isPresent, int numberOfAttendees, EventType type) {
        this();
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.location = location;
        this.isPresent = isPresent;
        this.numberOfAttendees = numberOfAttendees;

    }

    private EventType type;

    public Event() {
        this.id = nextId;
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String isPresent() {
        return isPresent;
    }

    public void setPresent(String present) {
        isPresent = present;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


