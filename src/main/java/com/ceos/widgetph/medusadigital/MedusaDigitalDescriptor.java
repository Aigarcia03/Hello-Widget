package com.ceos.widgetph.medusadigital;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class MedusaDigitalDescriptor extends WidgetDescriptor {

    public MedusaDigitalDescriptor() {
        super(MedusaDigitalWidget.WIDGET_TYPE, WidgetCategory.MONITOR, "Custom", "/speedometer.png", "Medusa digital gauge widget");
    }

    @Override
    public Widget createWidget() {
        return new MedusaDigitalWidget();
    }

    @Override
    public String getType(){
        return MedusaDigitalWidget.WIDGET_TYPE;
    }

    @Override
    public String getName(){
        return "Medusa Digital";
    }

    @Override
    public WidgetCategory getCategory(){
       return WidgetCategory.MONITOR;
    }

    @Override
    public String getDescription(){
        return "Medusa digital gauge widget.";
    }
}
