package com.example.test;


public enum EnumSportTickets  {

    DAILY("K", 1),
    WEEKLY("G", 2),
    BUNDLE("O", 3),
    UNKNOWN("", 10);

    private String id;
    private int weigth;

    private EnumSportTickets(String _id, int _weigth ) {
        this.id = _id;
        this.weigth = _weigth;
    }

    public String getId() {
        return this.id;
    }
    public int getWeigth() {
        return this.weigth;
    }



}