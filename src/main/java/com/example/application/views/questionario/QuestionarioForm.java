package com.example.application.views.questionario;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;

public class QuestionarioForm extends FormLayout {
    TextField firstName = new TextField("nome"); 
    TextField lastName = new TextField("cognome");

    RadioButtonGroup<String> genere=new RadioButtonGroup<>();
    RadioButtonGroup<String> luogoNascita=new RadioButtonGroup<>();
    TextField luogo=new TextField("provincia luogo di nascita (o stato se nato all'estero):");
   
    DatePicker dataNascita=new DatePicker("data di nascita:");

    RadioButtonGroup<String> titoloStudio=new RadioButtonGroup<>();

    TextField lavoro=new TextField("attività lavorativa prima del pensionamento: ");
 
    RadioButtonGroup<String> stileVita=new RadioButtonGroup<>();
    RadioButtonGroup<String> BoolMalattie=new RadioButtonGroup<>();
    TextField malattie=new TextField("Se ha malattie croniche indichi quali (es. diabete,ipertensione,artrite,osteoporosi,..)");
   
    
    RadioButtonGroup<String> morte=new RadioButtonGroup<>();
    RadioButtonGroup<String> malattiagrave=new RadioButtonGroup<>();
    RadioButtonGroup<String>  malattiafam=new RadioButtonGroup<>();
    RadioButtonGroup<String> divorzio=new RadioButtonGroup<>();
    RadioButtonGroup<String> incidente=new RadioButtonGroup<>();
    RadioButtonGroup<String> reato=new RadioButtonGroup<>();
    RadioButtonGroup<String> soddisfazione=new RadioButtonGroup<>();

    RadioButtonGroup<String> attivitaFisica=new RadioButtonGroup<>();
    RadioButtonGroup<String> peso=new RadioButtonGroup<>();

    public QuestionarioForm() {
        addClassName("questionario-form"); 
        H1 a=new H1("PARTE A, determinanti della fragilità");
        H1 b=new H1("PARTE B, componenti della fragilità");
        H1 b1=new H1("componenti fisiche");
        H1 b2=new H1("componenti psicologiche");
        H1 b3=new H1("componenti sociali");


        add(a,
            firstName, 
            lastName,
            getGenere(),
            getLuogoNascita(),
            luogo,
            getTitoloStudio(),
            lavoro,
            getStileVita(),
            getBoolMalattie(),
            malattie,
            new H2("ha avuto esperienza di uno o più dei seguenti eventi nel corso dell'ultimo anno?"),
            getInfoUltimoAnno(),
            b,
            b1,
            getBoolAttività(),
            getBoolPeso(),
            new H2("nella sua via quotidiana, riscontra problemi dovuti a :"),
            getInfoVitaQuotidiana(),
            b2,
            getInfoPsicologiche(),
            b3,
            getInfoSociali()
            );
      }

    private Component getInfoSociali() {
        RadioButtonGroup<String> vive=new RadioButtonGroup<>();
        vive.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        vive.setLabel("vive da solo?");
        vive.setItems("si", "no");

        RadioButtonGroup<String> mancanza=new RadioButtonGroup<>();
        mancanza.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        mancanza.setLabel("A volte sente la mancanza di persone intorno a lei?");
        mancanza.setItems("si","qualche volta", "no");

        RadioButtonGroup<String> sostegno=new RadioButtonGroup<>();
        sostegno.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        sostegno.setLabel("Riceve sufficiente sostegno dalle altre persone?");
        sostegno.setItems("si", "no");

        return new VerticalLayout(vive,mancanza,sostegno);
    }

    private Component getInfoPsicologiche() {
        RadioButtonGroup<String> memo=new RadioButtonGroup<>();
        memo.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        memo.setLabel("Ha problemi di memoria?");
        memo.setItems("si", "qualche volta","no");

        RadioButtonGroup<String> morale=new RadioButtonGroup<>();
        morale.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        morale.setLabel("Si è sentito giù di morale durante l'ultimo mese?");
        morale.setItems("si","qualche volta", "no");

        RadioButtonGroup<String> ansia=new RadioButtonGroup<>();
        ansia.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        ansia.setLabel("Si è sentito nervoso o ansioso durante l'ultimo mese?");
        ansia.setItems("si","qualche volta" ,"no");

        RadioButtonGroup<String> problem=new RadioButtonGroup<>();
        problem.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        problem.setLabel("Si è sentito giù di morale durante l'ultimo mese?");
        problem.setItems("si", "no");

        return new VerticalLayout(memo,morale,ansia,problem);
    }

    private Component getInfoVitaQuotidiana() {
        RadioButtonGroup<String> deambu=new RadioButtonGroup<>();
        deambu.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        deambu.setLabel("Difficoltà nella deambulazione?");
        deambu.setItems("si", "no");

        RadioButtonGroup<String> equi=new RadioButtonGroup<>();
        equi.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        equi.setLabel("Difficoltà nel mantenere l'equilibrio?");
        equi.setItems("si", "no");

        RadioButtonGroup<String> udito=new RadioButtonGroup<>();
        udito.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        udito.setLabel("scarso udito?");
        udito.setItems("si", "no");

        RadioButtonGroup<String> vista=new RadioButtonGroup<>();
        vista.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        vista.setLabel("scarsa vista?");
        vista.setItems("si", "no");

        RadioButtonGroup<String> mani=new RadioButtonGroup<>();
        mani.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        mani.setLabel("mancanza di forza nelle mani?");
        mani.setItems("si", "no");

        RadioButtonGroup<String> stanco=new RadioButtonGroup<>();
        stanco.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        stanco.setLabel("Stanchezza fisica?");
        stanco.setItems("si", "no");
        return new VerticalLayout(deambu,equi,udito,vista,mani,stanco);
    }

    private Component getBoolPeso() {
        peso.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        peso.setLabel(" Recentemente ha perso molto peso non intenzionalmete (per molto si intende 6kg o più negli ultimi 6 mesi, o 3kg o più negli ultimi 3 mesi)");
        peso.setItems("si", "no");
        return peso;
    }

    private Component getBoolAttività() {
        attivitaFisica.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        attivitaFisica.setLabel("Pensa di essere sufficientemente attivo dal punto di vista fisico?");
        attivitaFisica.setItems("si", "no");
        return attivitaFisica;
    }

    private Component getInfoUltimoAnno(){
        morte.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        morte.setLabel("La morte di una persona cara");
        morte.setItems("si", "no");
        malattiagrave.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        malattiagrave.setLabel("Una grave malattia di cui ha sofferto");
        malattiagrave.setItems("si", "no");
        malattiafam.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        malattiafam.setLabel("Una grave malattia di cui ha sofferto una persona a lei cara");
        malattiafam.setItems("si", "no");
        divorzio.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        divorzio.setLabel("Un divorzio o la fine di una relazione sentimentale importante");
        divorzio.setItems("si", "no");
        incidente.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        incidente.setLabel("Un incidente stradale");
        incidente.setItems("si", "no");
        reato.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        reato.setLabel("Un reato (es. truffa, furto,..)");
        reato.setItems("si", "no");
        
        VerticalLayout v=new VerticalLayout(
            morte,malattiagrave,malattiafam,divorzio,incidente,reato
        );
        return v;
    }

    private Component getBoolMalattie() {
        BoolMalattie.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        BoolMalattie.setLabel("Ha una o più malattie croniche?");
        BoolMalattie.setItems("si", "no");
    
        return BoolMalattie;
    }

    private Component getStileVita() {
        stileVita.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        stileVita.setLabel("Quanto pensa il suo stile di vita sia salutare?");
        stileVita.setItems("salutare", "più o meno salutare","non salutare");
    
        return stileVita;
    }

    private Component getTitoloStudio() {
        titoloStudio.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        titoloStudio.setLabel("Titolo di studio più alto conseguito:");
        titoloStudio.setItems("nessun titolo di studio", "licenza elementare","licenza media inferiore/avviamento professionale","diploma scuola superiore", "laurea o diploma universitario");
    
        return titoloStudio;
    }

    private Component getLuogoNascita(){
        luogoNascita.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        luogoNascita.setLabel("Luogo di nascita:");
        luogoNascita.setItems("Italia", "estero");
    
        return luogoNascita;
    }

    private Component getGenere() {
        
        genere.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        genere.setLabel("Sesso:");
        genere.setItems("maschio", "femmina");
    
        return genere;
    }

}