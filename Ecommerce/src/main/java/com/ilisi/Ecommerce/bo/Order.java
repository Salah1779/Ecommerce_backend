package com.ilisi.Ecommerce.bo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;
    private Date orderDate;
    private double total;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
    @OneToMany(mappedBy = "order")
    private Set<OrderLine> orderLines;

public int getOrderID() {
    return orderID;
}

public void setOrderID(int orderID) {
    this.orderID = orderID;
}

public Date getOrderDate() {
    return orderDate;
}

public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
}

public double getTotal() {
    return total;
}

public void setTotal(double total) {
    this.total = total;
}

public Client getClient() {
    return client;
}

public void setClient(Client client) {
    this.client = client;
}

public Set<OrderLine> getOrderLines() {
    return orderLines;
}

public void setOrderLines(Set<OrderLine> orderLines) {
    this.orderLines = orderLines;
}

}