package test.models;

import java.util.List;

public class SubTask extends Ticket{

    private String parentTaskId;
    public SubTask(String title, String description, TicketStatus status, String assigneeId, double estimate, String projectId) {
        //keeping null for subtasks child as it cannot have any child
        super(title, description, status, assigneeId, estimate, projectId, null);
        this.type = TicketType.SUBTASK;
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }
}
