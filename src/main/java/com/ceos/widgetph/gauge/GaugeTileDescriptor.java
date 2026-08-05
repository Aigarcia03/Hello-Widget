package com.ceos.widgetph.gauge;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class GaugeTileDescriptor extends WidgetDescriptor {

    public GaugeTileDescriptor() {
        super(GaugeTileWidget.WIDGET_TYPE, WidgetCategory.MONITOR, "Custom", "/speedometer.png", "Una descripción corta");
    }

    @Override
    public Widget createWidget() {
        return new GaugeTileWidget();
    }

    @Override
    public String getType(){
        return GaugeTileWidget.WIDGET_TYPE;
    }

    @Override
    public String getName(){
        return "Gauge Tile";
    }

    @Override
    public WidgetCategory getCategory(){
       return WidgetCategory.MONITOR;
    }

    @Override
    public String getDescription(){
        return "Widget básico para demostración.";
    }
}
