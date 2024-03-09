package test.dao;

import test.models.Ticket;
import test.models.TicketStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//BASIC CRUD OPERATIONS
public class TicketDao implements TicketDaoInterface{
    private HashMap<String, Ticket> tickets = new HashMap<String, Ticket>();

    public void addTicket(Ticket ticket) {
        tickets.put(ticket.getId(), ticket);
    }

    public Ticket getTicket(String id) {
        return tickets.get(id);
    }

    public void deleteTicket(String id) {
        tickets.remove(id);
    }

    public void updateTicket(Ticket ticket) {
        tickets.put(ticket.getId(), ticket);
    }

    public Map<TicketStatus, List<Ticket>> getAggregateView() {
        return tickets.values().stream().collect(Collectors.groupingBy(Ticket::getStatus));
    }

    public Map<TicketStatus, Long> getNumberOfTasksStatusWise() {
        return tickets.values()
                .stream()
                .collect(Collectors.groupingBy(Ticket::getStatus, Collectors.counting()));
    }
}
