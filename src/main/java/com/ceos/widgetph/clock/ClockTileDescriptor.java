package com.ceos.widgetph.clock;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class ClockTileDescriptor extends WidgetDescriptor {

    public ClockTileDescriptor() {
        super(ClockTileWidget.WIDGET_TYPE, WidgetCategory.MONITOR, "Custom", "ruta/a/tu/ícono16x16.png", "Clock widget");
    }

    @Override
    public Widget createWidget() {
        return new ClockTileWidget();
    }

    @Override
    public String getType(){
        return ClockTileWidget.WIDGET_TYPE;
    }

    @Override
    public String getName(){
        return "Clock Tile";
    }

    @Override
    public WidgetCategory getCategory(){
       return WidgetCategory.MONITOR;
    }

    @Override
    public String getDescription(){
        return "Clock tile widget.";
    }
}
