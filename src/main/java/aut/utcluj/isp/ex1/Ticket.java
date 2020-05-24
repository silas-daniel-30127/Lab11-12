package aut.utcluj.isp.ex1;

import java.util.Objects;

/**
 * @author stefan
 */
public class Ticket {
    private String id;
    private String customerName;
    private Double price;

    public Ticket(String customerName, Double price) {
        this.id = String.valueOf(Math.random());
        this.customerName = customerName;
        this.price = price;
    }

    public Ticket(String id, String customerName, Double price) {
        this.id = id;
        this.customerName = customerName;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) &&
                Objects.equals(customerName, ticket.customerName) &&
                Objects.equals(price, ticket.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, price);
    }
}