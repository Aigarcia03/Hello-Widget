package com.ceos.widgetph.radarchart;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class RadarChartTileDescriptor extends WidgetDescriptor {

    public RadarChartTileDescriptor() {
        super(RadarChartTileWidget.WIDGET_TYPE, WidgetCategory.PLOT, "Custom", "/add.png", "Radar chart widget");
    }

    @Override
    public Widget createWidget() {
        return new RadarChartTileWidget();
    }

    @Override
    public String getType(){
        return RadarChartTileWidget.WIDGET_TYPE;
    }

    @Override
    public String getName(){
        return "RadarChart";
    }

    @Override
    public WidgetCategory getCategory(){
       return WidgetCategory.PLOT;
    }

    @Override
    public String getDescription(){
        return "Radar chart widget with SECTOR and POLYGON modes.";
    }
}
