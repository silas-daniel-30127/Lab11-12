package aut.utcluj.isp.ex3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stefan
 */
public class AirplaneTicketController {
    private List<AirplaneTicket> tickets;

    public AirplaneTicketController() {
        tickets = new ArrayList<>();
    }

    /**
     * Add new airplane ticket
     *
     * @param airplaneTicket - airplane ticket object
     */
    public void addNewTicket(final AirplaneTicket airplaneTicket) {
        tickets.add(airplaneTicket);
    }

    /**
     * Get all tickets
     *
     * @return
     */
    public List<AirplaneTicket> getTickets() {
        return tickets;
    }

    /**
     * Return total number of tickets
     */
    public int getTotalNumberOfTickets() {
        return tickets.size();
    }

    /**
     * Remove a specific ticket by ticket id
     *
     * @param ticketId - unique ticket id
     */
    public void removeTicketById(final String ticketId) {
        AirplaneTicket Ticket = new AirplaneTicket();
        Ticket.setId(ticketId);
        tickets.removeIf(ticket -> ticket.equals(Ticket));
    }

    /**
     * Update destination for a specific ticket
     *
     * @param ticketId    - unique ticket id
     * @param destination - new destination
     */
    public void updateTicketDestination(final String ticketId, final String destination) {
        AirplaneTicket Ticket = new AirplaneTicket();
        Ticket.setId(ticketId);
        for (AirplaneTicket ticket : tickets) {
            if (ticket.equals(Ticket)) {
                ticket.setDestination(destination);
            }
        }
    }

    /**
     * Filter airplane tickets with price between [minPrice, maxPrice]
     *
     * @param minPrice - ticket min prince
     * @param maxPrice - ticket max price
     * @return
     */
    public List<AirplaneTicket> filterTicketsBetweenMinMaxPrice(final Double minPrice, final Double maxPrice) {
        List<AirplaneTicket> ticketList = new ArrayList<>();
        for (AirplaneTicket ticket : tickets) {
            if (ticket.getPrice().compareTo(minPrice) >= 0 && ticket.getPrice().compareTo(maxPrice) <= 0) {
                ticketList.add(ticket);
            }
        }
        return ticketList;
    }

    /**
     * Filter airplane tickets with price between [minPrice, maxPrice] and destination
     *
     * @param minPrice    - ticket min price
     * @param maxPrice    - ticket max price
     * @param destination - destination
     * @return
     */
    public List<AirplaneTicket> filterTicketsWithPriceAndDestination(final Double minPrice, final Double maxPrice, final String destination) {
        List<AirplaneTicket> ticketList;
        ticketList = filterTicketsBetweenMinMaxPrice(minPrice, maxPrice);

        List<AirplaneTicket> filtredList = new ArrayList<>();
        for (AirplaneTicket ticket : ticketList) {
            if (ticket.getDestination().equals(destination)) {
                filtredList.add(ticket);
            }
        }
        return filtredList;
    }

}
