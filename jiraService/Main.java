package test;

import test.Service.ProjectService;
import test.Service.TicketService;
import test.dao.ProjectDao;
import test.dao.TicketDao;
import test.dao.UserDao;
import test.models.*;

public class Main {
    public static void main(String[] args) {
        User user = new User("John Doe", "123", true);
        System.out.println(user.getName());

        //defining persistence layer
        UserDao userDao = new UserDao();
        TicketDao ticketDao = new TicketDao();
        ProjectDao projectDao = new ProjectDao();

        //creating service and injecting persistance layer
        ProjectService projectService = new ProjectService(projectDao, ticketDao);
        TicketService ticketService = new TicketService(ticketDao, projectDao);

        Project project = projectService.createProject("project_1", "Project 1");

        Ticket story = ticketService.createStory("story_1", "Ticket 1",  TicketStatus.TODO, 0, "project_1", null);
        Ticket task = ticketService.createTask("task_1", "Task 1",  TicketStatus.TODO, null,0, "project_1");
        Ticket subTask = ticketService.createSubtask("subtask_1", "SubTask 1",  TicketStatus.TODO, null,0, "project_1");




        //linking the tickets
        ticketService.addTaskToStory(story.getId(), task.getId());
        ticketService.addSubtaskToTask(task.getId(), subTask.getId());


        //System.out.println("Project: " + project.toString());


        //deleting subtask
        //ticketService.removeSubtask(subTask.getId());

        //deleting task
        //ticketService.removeTask(task.getId());

        //deleting story
        //ticketService.removeStory(story.getId());

        System.out.println("Story: " + story.toString());


        Project project2 = projectService.createProject("project_2", "Project 2");
        projectService.moveProjectFromTicket(story.getId(), project2);

        System.out.println("Story: " + story.toString());

        ticketService.changeTicketStatus(subTask.getId(), TicketStatus.IN_PROGRESS);

        //Aggregate view on the tickets based on the status
        System.out.println(ticketService.getAggregateView());
        // Number of tasks in different statuses.

        System.out.println(ticketService.getNumberOfTasksStatusWise());





    }
}
