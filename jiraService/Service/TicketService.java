package test.Service;

import test.dao.ProjectDaoInterface;
import test.dao.TicketDaoInterface;
import test.models.*;

import java.util.List;
import java.util.Map;

public class TicketService {
    private TicketDaoInterface ticketDao;

    private ProjectDaoInterface projectDao;

    public TicketService(TicketDaoInterface ticketDao, ProjectDaoInterface projectDao) {
        this.ticketDao = ticketDao;
        this.projectDao = projectDao;
    }

    public Ticket createStory(String storyId, String description, TicketStatus ticketStatus, double estimate, String projectId, String assigneeId) {
        Story story = new Story(storyId, description, ticketStatus, assigneeId, estimate, projectId);
        ticketDao.addTicket(story);
        return story;
    }

    public Ticket createTask(String title, String description, TicketStatus status, String assigneeId, double estimate, String projectId) {
        Task task = new Task(title, description, status,  assigneeId,  estimate,  projectId);
        ticketDao.addTicket(task);
        return task;
    }

    public Ticket createSubtask(String title, String description, TicketStatus status, String assigneeId, double estimate, String projectId) {
        SubTask subtask = new SubTask(title, description, status,  assigneeId,  estimate,  projectId);
        ticketDao.addTicket(subtask);
        return subtask;
    }

    public void addTaskToStory(String storyId, String taskId) {
        Ticket task = ticketDao.getTicket(taskId);
        Ticket story = ticketDao.getTicket(storyId);

        if (task instanceof test.models.Task && story instanceof test.models.Story) {
            story.addToChildTasks(task);
            ((Task) task).setParentStoryId(storyId);
        }
        else {
            //todo throw proper customised exception
            throw new RuntimeException("Task or Story not found");
        }
    }

    public void addSubtaskToTask(String taskId, String subTaskId) {
        Ticket task = ticketDao.getTicket(taskId);
        Ticket subTask = ticketDao.getTicket(subTaskId);

        if (task instanceof test.models.Task && subTask instanceof test.models.SubTask) {
            task.addToChildTasks(subTask);
            ((SubTask) subTask).setParentTaskId(taskId);
        }
        else {
            //todo throw proper customised exception
            throw new RuntimeException("Task or SubTask not found");
        }
    }

    public void removeSubtask(String subTaskId) {
        //get subtask from dao
        Ticket subTask = ticketDao.getTicket(subTaskId);
        if (subTask instanceof test.models.SubTask) {
            //get parent task from dao
            Ticket parentTask = ticketDao.getTicket(((SubTask) subTask).getParentTaskId());
            //remove child from parent
            parentTask.getChildTasks().remove(subTask);

            //remove from Dao
            ticketDao.deleteTicket(subTaskId);
        }
        else {
            //todo throw proper customised exception
            throw new RuntimeException("SubTask not found");
        }
    }

    public void removeTask(String taskId) {
        //get subtask from dao
        Ticket task = ticketDao.getTicket(taskId);
        if (task instanceof test.models.Task) {
            //get parent task from dao
            Ticket parentTask = ticketDao.getTicket(((Task) task).getParentStoryId());
            //remove child from parent
            parentTask.getChildTasks().remove(task);

            //get child tasks and remove them from DAO
            for (Ticket subTask : task.getChildTasks()) {
                //remove subTasks from Dao
                ticketDao.deleteTicket(subTask.getId());
            }
            //remove task from Dao
            ticketDao.deleteTicket(taskId);
        }
        else {
            //todo throw proper customised exception
            throw new RuntimeException("SubTask not found");
        }
    }

    public void removeStory(String storyId) {
        //get story from dao
        Ticket story = ticketDao.getTicket(storyId);
        if (story instanceof test.models.Story) {
            for (Ticket task : story.getChildTasks()) {
                //remove task and subtask from Dao
                for (Ticket subTask : task.getChildTasks()) {
                    //remove subTasks from Dao
                    ticketDao.deleteTicket(subTask.getId());
                }
                    //remove task from Dao
                ticketDao.deleteTicket(task.getId());
            }

            //remove story from Dao
            ticketDao.deleteTicket(storyId);
        }
        else {
            //todo throw proper customised exception
            throw new RuntimeException("Story not found");
        }
    }

    public void deleteTicket(String ticketId, User user) {
        if(user.isAdmin()){
            ticketDao.deleteTicket(ticketId);
        }
        else {
            throw new RuntimeException("User is not an admin");
        }
    }

    public void changeTicketStatus(String ticketId, TicketStatus status) {
        Ticket ticket = ticketDao.getTicket(ticketId);
        ticket.setStatus(status);
    }

    public  Map<TicketStatus, List<Ticket>> getAggregateView() {
        return ticketDao.getAggregateView();
    }
    
    public Map<TicketStatus, Long> getNumberOfTasksStatusWise() {
        return ticketDao.getNumberOfTasksStatusWise();
    }
}
