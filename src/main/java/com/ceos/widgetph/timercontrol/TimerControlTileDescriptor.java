package com.ceos.widgetph.timercontrol;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class TimerControlTileDescriptor extends WidgetDescriptor {

    public TimerControlTileDescriptor() {
        super(TimerControlTileWidget.WIDGET_TYPE, WidgetCategory.MONITOR, "Custom", "ruta/a/tu/ícono16x16.png", "Timer Control tile widget");
    }

    @Override
    public Widget createWidget() {
        return new TimerControlTileWidget();
    }

    @Override
    public String getType(){
        return TimerControlTileWidget.WIDGET_TYPE;
    }

    @Override
    public String getName(){
        return "Timer Control Tile";
    }

    @Override
    public WidgetCategory getCategory(){
       return WidgetCategory.MONITOR;
    }

    @Override
    public String getDescription(){
        return "Timer control tile widget.";
    }
}
