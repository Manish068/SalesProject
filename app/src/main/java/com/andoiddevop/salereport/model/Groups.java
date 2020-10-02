package com.andoiddevop.salereport.model;

public class Groups {
    String product_group_name, product_group_Item_name, product_Item_unit;

    public Groups() {
    }

    public Groups(String product_group_name, String product_group_Item_name, String product_Item_unit) {
        this.product_group_name = product_group_name;
        this.product_group_Item_name = product_group_Item_name;
        this.product_Item_unit = product_Item_unit;
    }

    public String getProduct_group_name() {
        return product_group_name;
    }//whre3 is your activitis

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
