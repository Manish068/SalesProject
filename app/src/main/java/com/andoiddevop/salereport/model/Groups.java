package com.andoiddevop.salereport.model;

public class Groups {
    private int id;
    private String product_group_name, product_group_Item_name, product_Item_unit;

    public Groups() {
    }

    public Groups(int id, String product_group_name, String product_group_Item_name, String product_Item_unit) {
        this.id = id;
        this.product_group_name = product_group_name;
        this.product_group_Item_name = product_group_Item_name;
        this.product_Item_unit = product_Item_unit;
    }

    //toString method is used to print all the objects


    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id +
                ", product_group_name='" + product_group_name + '\'' +
                ", product_group_Item_name='" + product_group_Item_name + '\'' +
                ", product_Item_unit='" + product_Item_unit + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_group_name() {
        return product_group_name;
    }

    public void setProduct_group_name(String product_group_name) {
        this.product_group_name = product_group_name;
    }

    public String getProduct_group_Item_name() {
        return product_group_Item_name;
    }

    public void setProduct_group_Item_name(String product_group_Item_name) {
        this.product_group_Item_name = product_group_Item_name;
    }

    public String getProduct_Item_unit() {
        return product_Item_unit;
    }

    public void setProduct_Item_unit(String product_Item_unit) {
        this.product_Item_unit = product_Item_unit;
    }
}
