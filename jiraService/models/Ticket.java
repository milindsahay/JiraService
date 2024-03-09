package test.models;

import java.util.List;

public abstract class Ticket {
    private String id;
    private String title;
    private String description;
    private TicketStatus status;
    private String assigneeId;
    private double estimate;

    private String projectId;

    protected TicketType type;
    //empty for subtasks
    private List<Ticket> childTasks;

    public Ticket(String id, String title, TicketStatus status, String assigneeId, double estimate, String projectId, List<Ticket> childTasks) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.assigneeId = assigneeId;
        this.estimate = estimate;
        this.projectId = projectId;
        this.childTasks = childTasks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assignee) {
        this.assigneeId = assignee;
    }

    public double getEstimate() {
        return estimate;
    }

    public void setEstimate(double estimate) {
        this.estimate = estimate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }

    public List<Ticket> getChildTasks() {
        return childTasks;
    }
    public void addToChildTasks(Ticket task) {
        this.childTasks.add(task);
    }

    public TicketType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", assigneeId='" + assigneeId + '\'' +
                ", estimate=" + estimate +
                ", projectId='" + projectId + '\'' +
                ", type=" + type +
                ", childTasks=" + childTasks +
                '}';
    }
}