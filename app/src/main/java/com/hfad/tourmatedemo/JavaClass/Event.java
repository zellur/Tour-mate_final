package com.hfad.tourmatedemo.JavaClass;

/**
 * Created by rakib on 2/8/18.
 */

public class Event {
    private String id;
    private String eventName;
    private String eventStart;
    private String eventCreated;
    private String eventDestination;
    private double eventBudget;
    private String eventLocation;

    public Event() {

    }

    public Event(String id, String eventName, String eventStart,String eventCreated, String eventDestination, double eventBudget, String eventLocation) {
        this.id = id;
        this.eventName = eventName;
        this.eventStart = eventStart;
        this.eventDestination = eventDestination;
        this.eventBudget = eventBudget;
        this.eventLocation = eventLocation;
        this.eventCreated=eventCreated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventStart() {
        return eventStart;
    }

    public void setEventStart(String eventStart) {
        this.eventStart = eventStart;
    }

    public String getEventDestination() {
        return eventDestination;
    }

    public void setEventDestination(String eventDestination) {
        this.eventDestination = eventDestination;
    }

    public double getEventBudget() {
        return eventBudget;
    }

    public void setEventBudget(double eventBudget) {
        this.eventBudget = eventBudget;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventCreated() {
        return eventCreated;
    }

    public void setEventCreated(String eventCreated) {
        this.eventCreated = eventCreated;
    }
}
