package com.example.grabitandgo;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

    private String order_id;
    private String coffee_id;
    private Double price;
    private int qty;
    private int size;
    private int sugar;
    private int milk;
    private Date orderDate;
    private Date pickUpDate;

    public Order(String order_id, String coffee_id, Double price, int qty, int size, int sugar, int milk, Date orderDate, Date pickUpDate) {
        this.order_id = order_id;
        this.coffee_id = coffee_id;
        this.price = price;
        this.qty = qty;
        this.size = size;
        this.sugar = sugar;
        this.milk = milk;
        this.orderDate = orderDate;
        this.pickUpDate = pickUpDate;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getCoffee_id() {
        return coffee_id;
    }

    public Double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public int getSize() {
        return size;
    }

    public int getSugar() {
        return sugar;
    }

    public int getMilk() {
        return milk;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getPickUpDateDate() { return pickUpDate;}


}


