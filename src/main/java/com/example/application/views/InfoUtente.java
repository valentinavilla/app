package com.example.application.views;

import com.example.application.data.entity.Contact;
import com.example.application.data.service.CrmService;
import com.example.application.views.list.ContactForm;
import com.example.application.views.questionario.QuestionarioView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "InfoUtente",layout=MainLayout.class) 
@PageTitle("InfoUtente")
public class InfoUtente extends VerticalLayout implements HasUrlParameter<Integer> ,BeforeEnterObserver {
    private Contact contatto;
    private Integer idContatto;
    private CrmService service;
    private Tab paziente;
	private Tab questionario;
	private Tab altro;
	private VerticalLayout content=new VerticalLayout();
    private ContactForm form;


    public InfoUtente(CrmService s){
        this.service=s;
        if (service.getContact(idContatto)==null){
            VerticalLayout v=new VerticalLayout();
            v.add("UTENTE NON TROVATO");
            add(v);}
        else{
            contatto=service.getContact(idContatto);
            add(getTabs(), content);}
    }

    
    private void configureForm() {
        form = new ContactForm( service.findAllStatuses()); 
        form.setWidth("25em");

        //form.addListener(ContactForm.SaveEvent.class, this::saveContact); 
        //form.addListener(ContactForm.DeleteEvent.class, this::deleteContact); 
        //form.addListener(ContactForm.CloseEvent.class, e -> closeEditor());
    }

    private Component configureInfo(Contact c) {
        VerticalLayout Vl=new VerticalLayout();
        Vl.add(
            createImageSection()
            
            );
        return Vl;
    }

    private Component createImageSection() {
        Image image = new Image(contatto.getImageUrl(),"Immagine profilo");
        image.addClassName("margin-h-l");
		image.setHeight("200px");
		image.setWidth("200px");

        FlexLayout nome=new FlexLayout(createTertiaryIcon(VaadinIcon.MALE));
        nome.addClassName("h2");
        nome.getElement().setAttribute("with-divider", true);
        nome.setId("nome");
        nome.setFlexDirection(FlexDirection.COLUMN_REVERSE);

        FlexLayout stat=new FlexLayout(createTertiaryIcon(VaadinIcon.INFO));
        stat.getElement().setAttribute("with-divider", true);
        stat.setId("status");
        stat.setFlexDirection(FlexDirection.COLUMN_REVERSE);
        stat.getElement().setProperty("white.space", "pre-line");

		FlexLayout updated = new FlexLayout(createTertiaryIcon(VaadinIcon.CALENDAR));
		updated.setFlexDirection(FlexDirection.COLUMN_REVERSE);

        FlexLayout listItems = new FlexLayout(nome, stat, updated);
		listItems.setFlexDirection(FlexDirection.COLUMN);

		FlexLayout section = new FlexLayout(image, listItems);
		section.addClassName("bsb-b");
        section.getElement().getStyle().set("flex", "1");

		return section;
    }

    public static Icon createTertiaryIcon(VaadinIcon icon) {
		Icon i = new Icon(icon);
        i.setColor("var(--lumo-tertiary-text-color)");
		return i;
	}

    private Tabs getTabs() {
        paziente = new Tab("Paziente");
        questionario= new Tab("Questionario");
        altro= new Tab("Modifica");

        Tabs tabs = new Tabs(paziente, questionario, altro);
        tabs.setWidthFull();
        tabs.addThemeVariants(TabsVariant.LUMO_EQUAL_WIDTH_TABS);
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
            content.add(configureInfo(contatto));
		} else if (tab.equals(questionario)) {
			content.add(new QuestionarioView());
		} else {
			content.add(new Paragraph("Qui vedrai eventuali richieste/annotazioni"));
		}
	}

    @Override
    public void setParameter(BeforeEvent event, Integer parameter) {
        this.idContatto=parameter;
    }

    
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
    }
    

}

