package com.example.application.views;

import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "dashboard", layout = MainLayout.class) 
@PageTitle("Dashboard ")
public class DashboardView extends VerticalLayout {
    private final CrmService service;

 public DashboardView(CrmService service) { 
        this.service = service;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER); 
        add(getContactStats(), getStatusChart(), getGenderChart());
    }   

    private Component getContactStats() {
        Span stats = new Span(service.countContacts() + " contacts"); 
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }

    private Chart getStatusChart() {
        Chart chart = new Chart(ChartType.PIE); 

        DataSeries dataSeries = new DataSeries();
        service.findAllStatuses().forEach(status ->
            dataSeries.add(new DataSeriesItem(status.getName(), status.getStatusCount()))); 
        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }

    private Chart getGenderChart() {
        Chart chart = new Chart(ChartType.PIE); 

        DataSeries dataSeries = new DataSeries();
        service.findAllGender().forEach(genere-> dataSeries.add(new DataSeriesItem(genere.getName(), genere.getGenereCount())));
        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }
}

