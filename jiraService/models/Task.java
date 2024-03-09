package test.models;

import java.util.ArrayList;

public class Task extends Ticket{

    private String parentStoryId;
    public Task(String title, String description, TicketStatus status, String assigneeId, double estimate, String projectId) {
        super(title, description, status, assigneeId, estimate, projectId, new ArrayList<>());
        this.type = TicketType.TASK;
    }

    public String getParentStoryId() {
        return parentStoryId;
    }

    public void setParentStoryId(String parentStoryId) {
        this.parentStoryId = parentStoryId;
    }
}
