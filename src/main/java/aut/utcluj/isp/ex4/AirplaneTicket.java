package aut.utcluj.isp.ex4;

/**
 * @author stefan
 */
public class AirplaneTicket {
    private String id;
    private Double price;
    private String destination;
    private String customerId;
    private TicketStatus status;

    public AirplaneTicket(String id, Double price, String destination)  {
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

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}
