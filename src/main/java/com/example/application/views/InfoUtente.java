package com.example.application.views;

import com.example.application.data.entity.Contact;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "Info utente", layout = MainLayout.class) 
@PageTitle("Info Utente")
public class InfoUtente extends VerticalLayout {
    private Contact contatto;
    private Tab paziente;
	private Tab questionario;
	private Tab altro;
	private VerticalLayout content;

    public InfoUtente(Contact c){
        this.contatto=c;
        addClassName("infoUtente-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER); 
        configureInfo(c);
        configureForm();
        add(getTabs(), content);
    }

    private void configureForm() {
    }

    private Component configureInfo(Contact c) {
        VerticalLayout Vl=new VerticalLayout();
        return Vl;
    }

    private Tabs getTabs() {
        paziente = new Tab("Paziente");
        questionario= new Tab("Questionario");
        altro= new Tab("Altro");

        Tabs tabs = new Tabs(paziente, questionario, altro);
        tabs.addSelectedChangeListener(event ->
	    setContent(event.getSelectedTab())
        );

        
		content = new VerticalLayout();
		content.setSpacing(false);
		setContent(tabs.getSelectedTab());

		return tabs;
	}

     private void setContent(Tab tab) {
		content.removeAll();

		if (tab.equals(paziente)) {
            content.add(configureInfo(contatto));
			content.add(new Paragraph("qui vedrai le info del paziente"));
		} else if (tab.equals(questionario)) {
			content.add(new Paragraph("qui vedrai nuovo questionario"));
		} else {
			content.add(new Paragraph("Qui vedrai eventuali richieste/annotazioni"));
		}
	}

}
