package test.Service;

import test.dao.ProjectDaoInterface;
import test.dao.TicketDaoInterface;
import test.models.Project;
import test.models.Ticket;

import java.util.Objects;

public class ProjectService {

    ProjectDaoInterface projectDao;

    TicketDaoInterface ticketDao;

    public ProjectService(ProjectDaoInterface projectDao, TicketDaoInterface ticketDao) {
        this.projectDao = projectDao;
        this.ticketDao = ticketDao;
    }

    public Project createProject(String projectId, String description) {
        Project project = new Project(projectId, description);
        projectDao.addProject(project);
        return project;
    }

    public void moveProjectFromTicket(String ticketId, Project newProject) {
        Ticket ticket = ticketDao.getTicket(ticketId);
        if(Objects.nonNull(ticket.getChildTasks())){
            ticket.setProjectId(newProject.getId());
            for (Ticket tix: ticket.getChildTasks()){
                tix.setProjectId(newProject.getId());
                moveProjectFromTicket(tix.getId(), newProject);
            }
        }
    }
}
