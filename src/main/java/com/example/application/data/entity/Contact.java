package com.example.application.data.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.application.views.questionario.QuestionarioForm;

@Entity
public class Contact extends AbstractEntity {

    private Integer IDPamac;
    //private List<Richiesta> richieste;
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

    public enum genere{
        M,
        F
    }

    private genere sesso=genere.M;

    public void setGenere(genere s){
        this.sesso=s;
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
        if(sesso==genere.M){return "images/tipo.jpg";}
        if (sesso==genere.F) {return "images/tipa.jpg";}
        return "images/pic.jpg";
    }

    public QuestionarioForm getQuestionario() {
        if(questionario==null){questionario=new QuestionarioForm();}
        return questionario;
    }

    /*public void addNuovaRichiesta(String richiesta){
        richieste.add( new Richiesta(richiesta));
    }
    public void addNuovaRichiesta(Richiesta richiesta){
        richieste.add(richiesta);
    }

    public List<Richiesta> getRichieste(){
        return richieste;
    }*/

    public void setIndiceFragilitàFisica(int i){this.indiceFragilitàFisica=i;}
    public void setIndiceFragilitàPsicologica(int i){this.indiceFragilitàPsicologica=i;}
    public void setIndiceFragilitàSociale(int i){this.indiceFragilitàSociale=i;}
    public int getIndiceFragilitaFisica(){return indiceFragilitàFisica;}
    public int getIndiceFragilitaPsico(){return indiceFragilitàPsicologica;}
    public int getIndiceFragilitaSociale(){return indiceFragilitàSociale;}

 }

