package com.example.application.data.entity;

import org.hibernate.annotations.Formula;

public enum Genere {
    M,
    F;

    public String getName() {
        if(this==Genere.F)return "F";
        else{return "M";}
    }

    @Formula("(select count(c.id) from Contact c where c.sesso_id = id)") 
    private int genereCount;

    public int getGenereCount() {
        return genereCount;
    }

}
