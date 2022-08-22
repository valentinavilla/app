package com.example.application.data.entity;

import org.hibernate.annotations.Formula;

public enum Genere {
    M,
    F;

    public String getName() {
        if(this==Genere.F)return "F";
        else{return "M";}
    }

    @Formula("(select count(c.id) from Contact c where c.sesso=M)") 
    private int MCount;

    public int getMCount() {
        return MCount;
    }

    public int getFCount() {
        return 50-MCount;}
    

    @Formula("(select count(c.id) from Contact c where c.sesso=M)") 
    private int genereCount;

    public Number getgenereCount() {
        return genereCount;
    }

}
