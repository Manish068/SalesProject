package com.andoiddevop.salereport.model;

public class Groups {
    String groupName, itemName , unit ;

    public Groups() {
    }

    public Groups(String groupName, String itemName, String unit) {
        this.groupName = groupName;
        this.itemName = itemName;
        this.unit = unit;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
