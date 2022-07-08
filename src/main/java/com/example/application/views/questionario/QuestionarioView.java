package com.example.application.views.questionario;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "questionario", layout = MainLayout.class) 
@PageTitle("questionario")
public class QuestionarioView extends VerticalLayout{
    QuestionarioForm qform;

    public QuestionarioView(){
        addClassName("questionario-view");
        configureQForm();
        add(qform);
    }

    private void configureQForm() {
        qform = new QuestionarioForm(); 
        qform.setSizeFull();;
    }

}
