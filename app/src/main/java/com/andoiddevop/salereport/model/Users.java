package com.andoiddevop.salereport.model;

public class Users {
    private String party_name;
    private String party_number;
    private String party_address;
    private boolean Expanded;


    public Users() {
        this.Expanded = false;
    }

    public Users(String party_name, String party_number, String party_address) {
        this.party_name = party_name;
        this.party_number = party_number;
        this.party_address = party_address;
        this.Expanded = false;
    }

    public String getParty_name() {
        return party_name;
    }

    public void setParty_name(String party_name) {
        this.party_name = party_name;
    }

    public String getParty_number() {
        return party_number;
    }

    public void setParty_number(String party_number) {
        this.party_number = party_number;
    }

    public String getParty_address() {
        return party_address;
    }

    public void setParty_address(String party_address) {
        this.party_address = party_address;
    }

    public boolean isExpanded() {
        return Expanded;
    }

    public void setExpanded(boolean expanded) {
        Expanded = expanded;
    }

}
