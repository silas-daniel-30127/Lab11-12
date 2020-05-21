package aut.utcluj.isp.ex4;


import aut.utcluj.isp.ex1.Ticket;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author stefan
 */
public class AirplaneTicketController {
    /**
     * Default number of tickets when a new instance is created
     */
    public static final int DEFAULT_NUMBER_OF_TICKETS = 10;
    private List<AirplaneTicket> tickets = new ArrayList<>();

    public AirplaneTicketController() {
        generateTickets();
    }

    /**
     * Generate default tickets
     */
    private void generateTickets() {
        for (int i = 0; i < DEFAULT_NUMBER_OF_TICKETS; i++) {
            String destination;
            double price;

            if (i < 3) {
                destination = "Cluj-Napoca";
                price = 10d;
            } else if (i < 6) {
                destination = "Baia Mare";
                price = 20d;
            } else {
                destination = "Timisoara";
                price = 15d;
            }

            final AirplaneTicket airplaneTicket = new AirplaneTicket("ID-" + i, price, destination);
            airplaneTicket.setStatus(TicketStatus.NEW);

            this.tickets.add(airplaneTicket);
        }
    }

    public List<AirplaneTicket> getTickets() {
        return tickets;
    }

    /**
     * Get ticket details by ticket id
     *
     * @param ticketId - unique ticket id
     * @return
     * @apiNote: this method should throw {@link NoTicketAvailableException} exception if ticket not found
     */
    public AirplaneTicket getTicketDetails(final String ticketId) {
        for (AirplaneTicket ticket : tickets) {
            if (ticket.getId().equals(ticketId)) {
                return ticket;
            }
        }

        throw new NoTicketAvailableException();
    }

    /**
     * Buy ticket with specific destination
     * Ticket information should be updated: customer name and status {@link TicketStatus#ACTIVE}
     *
     * @param destination - destination
     * @param customerId  - customer name
     * @return
     * @apiNote: this method should throw the following exceptions:
     * {@link NoDestinationAvailableException} - if destination not supported by AirplaneTicketController
     * {@link NoTicketAvailableException} - if destination exists but no ticket with NEW status available
     */
    public void buyTicket(final String destination, final String customerId) {
        boolean flag = true;
        boolean ticketFlag = true;
        for (AirplaneTicket ticket : tickets) {
            if (ticket.getDestination().equals(destination)) {
                flag = false;
                if (ticket.getStatus().equals(TicketStatus.NEW)) {
                    ticket.setCustomerId(customerId);
                    ticket.setStatus(TicketStatus.ACTIVE);
                    ticketFlag = false;
                    break;
                }
            }
        }

        if (flag) {
            throw new NoDestinationAvailableException();
        }
        if (ticketFlag) {
            throw new NoTicketAvailableException();
        }
    }

    /**
     * Cancel specific ticket
     * Status of the ticket should be set to {@link TicketStatus#CANCELED}
     *
     * @param ticketId - unique ticket id
     * @return
     * @apiNote: this method should throw the following exceptions:
     * {@link NoTicketAvailableException} - if ticket with this id does not exist
     * {@link TicketNotAssignedException} - if ticket is not assigned to any user
     */
    public void cancelTicket(final String ticketId) {
        boolean flag = false;
        for (AirplaneTicket ticket : tickets) {
            if (ticket.getId().equals(ticketId)) {
                flag = true;
                if (ticket.getStatus().equals(TicketStatus.ACTIVE)) {
                    ticket.setStatus(TicketStatus.CANCELED);
                } else {
                    throw new TicketNotAssignedException();
                }
            }
        }
        if (!flag) {
            throw new NoTicketAvailableException();
        }
    }

    /**
     * Change ticket customer name by specific ticket id
     *
     * @param ticketId   - unique ticket id
     * @param customerId - unique customer name
     * @return
     * @apiNote: this method should throw the following exceptions:
     * {@link NoTicketAvailableException} - if ticket with this id does not exist
     * {@link TicketNotAssignedException} - if ticket is not assigned to any user
     */
    public void changeTicketCustomerId(final String ticketId, final String customerId) {
        boolean flag = false;
        for (AirplaneTicket ticket : tickets) {
            if (ticket.getId().equals(ticketId)) {
                flag = true;
                if (ticket.getStatus() == TicketStatus.ACTIVE) {
                    ticket.setCustomerId(customerId);
                } else {
                    throw new TicketNotAssignedException();
                }
            }
        }
        if (!flag) {
            throw new NoTicketAvailableException();
        }
    }

    /**
     * This method should filter all tickets by provided status.
     * An empty list should be returned if no tickets available with desired status
     *
     * @param status - ticket status
     * @return
     */
    public List<AirplaneTicket> filterTicketsByStatus(final TicketStatus status) {
        List<AirplaneTicket> airplaneTickets = new ArrayList<>();
        for (AirplaneTicket ticket : tickets) {
            if (ticket.getStatus() == status) {
                airplaneTickets.add(ticket);
            }
        }
        return airplaneTickets;
    }

    /**
     * Return the tickets grouped by customer id
     *
     * @return dictionary where the key is the customer name and the value is a list of tickets for that customer
     * @apiNote: only tickets with available name should be returned
     */
    public Map<String, List<AirplaneTicket>> groupTicketsByCustomerId() {
        Map<String, List<AirplaneTicket>> groupedTickets = new HashMap<>();
        Set<String> customersList = new HashSet<>();

        for (AirplaneTicket ticket : tickets) {
            if (ticket.getCustomerId() != null) {
                customersList.add(ticket.getCustomerId());
            }
        }

        for (String customerId : customersList) {
            List<AirplaneTicket> customerTickets = new ArrayList<>();
            for (AirplaneTicket ticket : tickets) {
                if (ticket.getCustomerId() != null && ticket.getCustomerId().equals(customerId)) {
                    customerTickets.add(ticket);
                }
            }
            groupedTickets.put(customerId, customerTickets);
        }
        return groupedTickets;
    }

}
