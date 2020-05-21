package aut.utcluj.isp.ex4;

import java.util.Objects;

/**
 * @author stefan
 */
public class AirplaneTicket {
    private String id;
    private Double price;
    private String destination;
    private String customerId;
    private TicketStatus status;

    public AirplaneTicket(String id, Double price, String destination) {
        this.id = id;
        this.price = price;
        this.destination = destination;
        //throw new UnsupportedOperationException("Not supported yet.");

    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public String getDestination() {
        return destination;
    }

    public String getCustomerId() {
        return customerId;
    }

    public TicketStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "AirplaneTicket{" +
                "id='" + id + '\'' +
                ", destination='" + destination + '\'' +
                ", customerId='" + customerId + '\'' +
                ", status=" + status +
                '}' + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirplaneTicket ticket = (AirplaneTicket) o;
        return Objects.equals(id, ticket.id) &&
                Objects.equals(price, ticket.price) &&
                Objects.equals(destination, ticket.destination) &&
                Objects.equals(customerId, ticket.customerId) &&
                status == ticket.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, destination, customerId, status);
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}
