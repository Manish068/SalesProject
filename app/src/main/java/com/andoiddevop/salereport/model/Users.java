package com.andoiddevop.salereport.model;

public class Users {
    private String party_name;
    private String party_number;

    public Users() {
    }

    public Users(String party_name, String party_number) {
        this.party_name = party_name;
        this.party_number = party_number;
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
}
