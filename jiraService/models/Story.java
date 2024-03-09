package test.models;

import java.util.ArrayList;

public class Story extends Ticket{
    public Story(String title, String description, TicketStatus status, String assigneeId, double estimate, String projectId){
        super(title, description, status, assigneeId, estimate, projectId, new ArrayList<>());
        this.type = TicketType.STORY;
    }
}
