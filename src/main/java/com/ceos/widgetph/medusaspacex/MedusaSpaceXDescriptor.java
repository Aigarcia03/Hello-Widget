package com.ceos.widgetph.medusaspacex;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class MedusaSpaceXDescriptor extends WidgetDescriptor {

    public MedusaSpaceXDescriptor() {
        super(MedusaSpaceXWidget.WIDGET_TYPE, WidgetCategory.MONITOR, "Custom", "/speedometer.png", "Medusa SpaceX gauge widget");
    }

    @Override
    public Widget createWidget() {
        return new MedusaSpaceXWidget();
    }

    @Override
    public String getType(){
        return MedusaSpaceXWidget.WIDGET_TYPE;
    }

    @Override
    public String getName(){
        return "Medusa SpaceX";
    }

    @Override
    public WidgetCategory getCategory(){
       return WidgetCategory.MONITOR;
    }

    @Override
    public String getDescription(){
        return "Medusa SpaceX gauge widget.";
    }
}
