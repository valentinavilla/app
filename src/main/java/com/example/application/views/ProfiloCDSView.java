package com.example.application.views;

import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "profilo", layout = MainLayout.class) 
@PageTitle("Il tuo profilo")
public class ProfiloCDSView extends VerticalLayout {
    CrmService service;


    public ProfiloCDSView(CrmService service) { 
        this.service = service;
        addClassName("profilo-view");
        setSpacing(false);

        add(new H1("dati personali:"));

        Image img=new Image("images/pic.jpg", "foto profilo");
        img.setWidth("400px");

        HorizontalLayout lay= new HorizontalLayout(img, new VerticalLayout(new Paragraph("Nome: valentina"),new Paragraph("Cogome: villa"),new Paragraph("Ruolo: Consulente Della Salute"),new Paragraph("ID: 0")));

        add(lay);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.START);
        setDefaultHorizontalComponentAlignment(Alignment.START);
        getStyle().set("text-align", "center");
    }

    //private Component getCalendar(){ }
    }   
    
