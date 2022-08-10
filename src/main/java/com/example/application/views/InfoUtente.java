package com.example.application.views;

import java.time.LocalDate;

import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Richiesta;
import com.example.application.data.service.CrmService;
import com.example.application.views.list.ContactForm;
import com.example.application.views.questionario.QuestionarioView;
import com.example.application.views.utili.BorderRadius;
import com.example.application.views.utili.Bottom;
import com.example.application.views.utili.Horizontal;
import com.example.application.views.utili.ListItem;
import com.example.application.views.utili.MyFlexLayout;
import com.example.application.views.utili.Top;
import com.example.application.views.utili.Vertical;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexWrap;
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
    public int VISIBLE_RECENT_TRANSACTIONS = 4;

    //private static final ThreadLocal<DateTimeFormatter> dateFormat = ThreadLocal
   // .withInitial(() -> DateTimeFormatter.ofPattern("MMM dd, YYYY"));

    ListItem nome;
    ListItem stat;
    ListItem updated;


    public InfoUtente(CrmService s){
        this.service=s;
        if (service.getContact(idContatto)==null){
            VerticalLayout v=new VerticalLayout();
            v.add("UTENTE NON TROVATO");
            add(v);}
        else{
            contatto=service.getContact(idContatto);
            add(getTabs(),content);}
    }

    private void configureForm() {
        form = new ContactForm( service.findAllStatuses()); 
        form.setWidth("25em");

        //form.addListener(ContactForm.SaveEvent.class, this::saveContact); 
        //form.addListener(ContactForm.DeleteEvent.class, this::deleteContact); 
        //form.addListener(ContactForm.CloseEvent.class, e -> closeEditor());
    }

    private Component configureInfo(Contact c) {
        MyFlexLayout Vl=new MyFlexLayout(
            createImageSection(),
            createRecentRequestsHeader(),
            //createRecentRequestsList()
            createIndiceHeader(),
            createIndiciSection()
            );
        Vl.setFlexDirection(FlexDirection.COLUMN);
        Vl.setMargin(Horizontal.AUTO, Vertical.RESPONSIVE_L);
        Vl.setMaxWidth("840px");
        return Vl;
    }


   /* private Component createRecentRequestsList() {
        Div items = new Div();
		items.addClassNames("bsb-b", "padding-b-l");

		for (int i = 0; i < VISIBLE_RECENT_TRANSACTIONS; i++) {
			Richiesta request = contatto.getRichieste().get(i);
			Label label = new Label(request.getStatoRichiesta());
            label.addClassName("h5");

			if (request.getStatoRichiesta()=="Conclusa") {
                label.getElement().getStyle().set("color","var(--lumo-success-text-color)");
			} else {
				label.getElement().getStyle().set("color", "var(--lumo-success-contrast-color)");
			}
			ListItem item = new ListItem(
					
					request.getName(),
					formatDate(LocalDate.now().minusDays(i)),
					label
			);
			// Dividers for all but the last item
			item.setDividerVisible(i < VISIBLE_RECENT_TRANSACTIONS - 1);
			items.add(item);
        }
        return items;
    }*/

    private Component createIndiciSection() {
        ListItem indiceF=new ListItem(" "+contatto.getIndiceFragilitaFisica(),"fragilità fisica:");
        indiceF.addClassName("h2");
        indiceF.getElement().setAttribute("with-divider", true);
        indiceF.setId("fisica");
        indiceF.setFlexDirection(FlexDirection.ROW);

        ListItem indiceP=new ListItem(" "+contatto.getIndiceFragilitaPsico(),"fragilità psicologica:");
        indiceP.addClassName("h2");
        indiceP.getElement().setAttribute("with-divider", true);
        indiceP.setId("psicologica");
        indiceP.setFlexDirection(FlexDirection.ROW);

        ListItem indiceS=new ListItem(" "+contatto.getIndiceFragilitaSociale(),"fragilità sociale:");
        indiceS.addClassName("h2");
        indiceF.getElement().setAttribute("with-divider", true);
        indiceS.setId("sociale");
        indiceS.setFlexDirection(FlexDirection.ROW);

        MyFlexLayout listaIndici = new MyFlexLayout(indiceF, indiceP, indiceS);
		listaIndici.setFlexDirection(FlexDirection.COLUMN);
        listaIndici.getElement().setAttribute("with-divider", true);

        return listaIndici;

    }


    private Component createIndiceHeader() {
        Label header = new Label("Indici di fragilità:");
		header.addClassNames("margin-r-v-l", "margin-r-h-l");
		return header;
    }


    public static String formatDate(LocalDate date) {
        return date.getDayOfMonth()+"/"+date.getMonthValue()+"/"+date.getYear();
	}

    private Component createImageSection() {
        Image image = new Image(contatto.getImageUrl(),"Immagine profilo");
        image.addClassName("margin-h-l");
        setBorderRadius(BorderRadius._50, image);
		image.setHeight("200px");
		image.setWidth("200px");

        nome=new ListItem(createTertiaryIcon(VaadinIcon.MALE)," "+contatto.getFirstName()+" "+contatto.getLastName(),"nome");
        nome.addClassName("h2");
        nome.getElement().setAttribute("with-divider", true);
        nome.setId("nome");
        nome.setFlexDirection(FlexDirection.ROW);

        stat=new ListItem(createTertiaryIcon(VaadinIcon.INFO)," "+contatto.getStatus().toString(),"stato");
        stat.getElement().setAttribute("with-divider", true);
        stat.setId("status");
        stat.getElement().setProperty("white.space", "pre-line");

		updated = new ListItem(createTertiaryIcon(VaadinIcon.CALENDAR),contatto.getQuestionario().getDataCompilazione().getDay()+"/"+contatto.getQuestionario().getDataCompilazione().getMonth(),"data ultimo questionario");
		updated.setFlexDirection(FlexDirection.ROW);

        MyFlexLayout listItems = new MyFlexLayout(nome, stat, updated);
		listItems.setFlexDirection(FlexDirection.COLUMN);
        listItems.getElement().setAttribute("with-divider", true);

		MyFlexLayout section = new MyFlexLayout(image, listItems);
		section.addClassName("bsb-b");
        section.setAlignItems(Alignment.CENTER);
        section.setFlexWrap(FlexWrap.WRAP);
        section.setJustifyContentMode(JustifyContentMode.CENTER);
        section.getElement().setAttribute("with-divider", true);

		return section;
    }

    public static Icon createTertiaryIcon(VaadinIcon icon) {
		Icon i = new Icon(icon);
        i.setColor("var(--lumo-tertiary-text-color)");
		return i;
	}

    public static void setBorderRadius(BorderRadius borderRadius,
    Component... components) {
    for (Component component : components) {
        component.getElement().getStyle().set("border-radius",
        borderRadius.getValue());
        }
    }

    private Component createRecentRequestsHeader() {
		Label titolo =new Label("Richieste più recenti:");
        titolo.addClassName("h2");

		Button viewAll = new Button("Vedi tutte");
        viewAll.addThemeVariants(ButtonVariant.LUMO_SMALL);
        viewAll.getElement().setAttribute("aria-label", "Vedi tutte");
		viewAll.addClickListener(
				e -> Notification.show("da implementare", 2000,Notification.Position.BOTTOM_CENTER));
		viewAll.addClassName("margin-l-a");

		MyFlexLayout header = new MyFlexLayout(titolo, viewAll);
		header.setAlignItems(Alignment.CENTER);
		header.setMargin(Bottom.M, Horizontal.RESPONSIVE_L, Top.L);
		return header;
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

