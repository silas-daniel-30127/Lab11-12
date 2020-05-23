package aut.utcluj.isp.ex3;

import java.util.Objects;

/**
 * @author stefan
 */
public class AirplaneTicket {
    private String id;
    private Double price;
    private String destination;

    public AirplaneTicket(String id, Double price, String destination) {
        this.id = id;
        this.price = price;
        this.destination = destination;
    }

    public AirplaneTicket() {
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

    public void setId(String id) {
        this.id = id;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AirplaneTicket)) return false;
        AirplaneTicket ticket = (AirplaneTicket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
