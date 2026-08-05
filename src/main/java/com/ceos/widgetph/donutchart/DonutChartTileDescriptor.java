package com.ceos.widgetph.donutchart;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class DonutChartTileDescriptor extends WidgetDescriptor {

    public DonutChartTileDescriptor() {
        super(DonutChartTileWidget.WIDGET_TYPE, WidgetCategory.PLOT, "Custom", "/chart_pie.png", "Donut chart tile widget");
    }

    @Override
    public Widget createWidget() {
        return new DonutChartTileWidget();
    }

    @Override
    public String getType(){
        return DonutChartTileWidget.WIDGET_TYPE;
    }

    @Override
    public String getName(){
        return "DonutChart Tile";
    }

    @Override
    public WidgetCategory getCategory(){
       return WidgetCategory.PLOT;
    }

    @Override
    public String getDescription(){
        return "Donut chart tile widget.";
    }
}
