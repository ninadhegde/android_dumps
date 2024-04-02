package com.example.mad_el;

class User {

    String name,account;
    int id;

    public User(String account, String name) {
        this.name = name;
        this.account = account;
    }

    public User(String name, String account, int id) {
        this.name = name;
        this.account = account;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
