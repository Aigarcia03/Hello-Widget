package com.ceos.widgetph.plusminus;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class PlusMinusTileDescriptor extends WidgetDescriptor {

    public PlusMinusTileDescriptor() {
        super(PlusMinusTileWidget.WIDGET_TYPE, WidgetCategory.MONITOR, "Custom", "ruta/a/tu/ícono16x16.png", "Plus-Minus tile widget");
    }

    @Override
    public Widget createWidget() {
        return new PlusMinusTileWidget();
    }

    @Override
    public String getType(){
        return PlusMinusTileWidget.WIDGET_TYPE;
    }

    @Override
    public String getName(){
        return "PlusMinus Tile";
    }

    @Override
    public WidgetCategory getCategory(){
       return WidgetCategory.MONITOR;
    }

    @Override
    public String getDescription(){
        return "Plus-Minus adjustment tile widget.";
    }
}
