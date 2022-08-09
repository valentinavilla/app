package com.example.application.data.entity;

import javax.persistence.Entity;

@Entity
public class Richiesta extends AbstractEntity{
    private String name;

    public enum StatoRichiesta{
        DaEsaminare,
        Annullata,
        Conclusa,
        Esaminata,
        DaConfermare
    }
    private StatoRichiesta statoRichiesta;

    public Richiesta(){}
    public Richiesta(String nome){this.name=nome;}

    public String getName(){return name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getStatoRichiesta(){return statoRichiesta.toString();}
    public void setStatoRichiesta(StatoRichiesta s){this.statoRichiesta=s;}

    @Override
    public String toString(){
        return name;
    }
}
