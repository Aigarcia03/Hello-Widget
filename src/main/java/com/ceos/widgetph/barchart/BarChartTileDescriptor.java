package com.ceos.widgetph.barchart;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class BarChartTileDescriptor extends WidgetDescriptor {

    public BarChartTileDescriptor() {
        super(BarChartTileWidget.WIDGET_TYPE, WidgetCategory.PLOT, "Custom", "/chart_bar.png", "Bar chart tile widget");
    }

    @Override
    public Widget createWidget() {
        return new BarChartTileWidget();
    }

    @Override
    public String getType(){
        return BarChartTileWidget.WIDGET_TYPE;
    }

    @Override
    public String getName(){
        return "BarChart Tile";
    }

    @Override
    public WidgetCategory getCategory(){
       return WidgetCategory.PLOT;
    }

    @Override
    public String getDescription(){
        return "Bar chart tile widget with one PV per bar.";
    }
}
