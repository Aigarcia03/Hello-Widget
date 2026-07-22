package com.ceos.widgetph.highlow;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class HighLowTileDescriptor extends WidgetDescriptor {

    public HighLowTileDescriptor() {
        super(HighLowTileWidget.WIDGET_TYPE, WidgetCategory.MONITOR, "Custom", "ruta/a/tu/ícono16x16.png", "High-Low tile widget");
    }

    @Override
    public Widget createWidget() {
        return new HighLowTileWidget();
    }

    @Override
    public String getType(){
        return HighLowTileWidget.WIDGET_TYPE;
    }

    @Override
    public String getName(){
        return "HighLow Tile";
    }

    @Override
    public WidgetCategory getCategory(){
       return WidgetCategory.MONITOR;
    }

    @Override
    public String getDescription(){
        return "High-Low indicator tile widget.";
    }
}
