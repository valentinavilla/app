package com.example.application.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.application.views.questionario.QuestionarioForm;

@Entity
public class Contact extends AbstractEntity {

    private Integer IDPamac;
    private String indirizzo;

    @NotNull
    @ManyToOne
    private Richiesta richieste;

    private QuestionarioForm questionario;
    private Integer indiceFragilitàFisica=0;
    private Integer indiceFragilitàPsicologica=0;
    private Integer indiceFragilitàSociale=0;

    @NotEmpty
    private String firstName = "";

    @NotEmpty
    private String lastName = "";

    @NotNull
    @ManyToOne
    private Status status;

    @Email
    @NotEmpty
    private String email = "";

    private Genere sesso=Genere.M;

    public void setGenere(Genere s){
        this.sesso=s;
    }
    
    public Genere getGenere(){
        return sesso;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public Integer getIDPamac(){return IDPamac;}
    public void setIDPamac(Integer newID){this.IDPamac=newID;}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        if(sesso==Genere.M){return "images/tipo.jpg";}
        if (sesso==Genere.F) {return "images/tipa.jpg";}
        return "images/pic.jpg";
    }

    public QuestionarioForm getQuestionario() {
        if(questionario==null){questionario=new QuestionarioForm();}
        return questionario;
    }

    
    public void setRichiesta(Richiesta richiesta){
       // richieste.add(richiesta);
       this.richieste=richiesta;
    }

    public Richiesta getRichieste(){
        return richieste;
    }

    public void setIndiceFragilitàFisica(int i){this.indiceFragilitàFisica=i;}
    public void setIndiceFragilitàPsicologica(int i){this.indiceFragilitàPsicologica=i;}
    public void setIndiceFragilitàSociale(int i){this.indiceFragilitàSociale=i;}
    public int getIndiceFragilitaFisica(){return indiceFragilitàFisica;}
    public int getIndiceFragilitaPsico(){return indiceFragilitàPsicologica;}
    public int getIndiceFragilitaSociale(){return indiceFragilitàSociale;}

    public void setAddress(String address){this.indirizzo=address;}
    public String getAddress(){return indirizzo;}

 }

