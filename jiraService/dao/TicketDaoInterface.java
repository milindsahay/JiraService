package test.dao;

import test.models.Ticket;
import test.models.TicketStatus;

import java.util.List;
import java.util.Map;

public interface TicketDaoInterface {
    void addTicket(Ticket ticket);
    Ticket getTicket(String id);
    void deleteTicket(String id);
    void updateTicket(Ticket ticket);

    Map<TicketStatus, List<Ticket>> getAggregateView();

    Map<TicketStatus, Long> getNumberOfTasksStatusWise();

}
