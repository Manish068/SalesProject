package com.andoiddevop.salereport.model;

public class Billing {
    String purches_party_name, purches_item_name, purches_item_quantity, purches_item_price;
    int total_purches;

    public Billing() {
    }

    public Billing(String purches_party_name, String purches_item_name, String purches_item_quantity, String purches_item_price, int total_purches) {
        this.purches_party_name = purches_party_name;
        this.purches_item_name = purches_item_name;
        this.purches_item_quantity = purches_item_quantity;
        this.purches_item_price = purches_item_price;
        this.total_purches = total_purches;
    }

    public String getPurches_party_name() {
        return purches_party_name;
    }

    public void setPurches_party_name(String purches_party_name) {
        this.purches_party_name = purches_party_name;
    }

    public String getPurches_item_name() {
        return purches_item_name;
    }

    public void setPurches_item_name(String purches_item_name) {
        this.purches_item_name = purches_item_name;
    }

    public String getPurches_item_quantity() {
        return purches_item_quantity;
    }

    public void setPurches_item_quantity(String purches_item_quantity) {
        this.purches_item_quantity = purches_item_quantity;
    }

    public String getPurches_item_price() {
        return purches_item_price;
    }

    public void setPurches_item_price(String purches_item_price) {
        this.purches_item_price = purches_item_price;
    }

    public int getTotal_purches() {
        return total_purches;
    }

    public void setTotal_purches(int total_purches) {
        this.total_purches = total_purches;
    }
}