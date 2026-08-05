package com.ceos.widgetph.medusaslim;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class MedusaSlimDescriptor extends WidgetDescriptor {

    public MedusaSlimDescriptor() {
        super(MedusaSlimWidget.WIDGET_TYPE, WidgetCategory.MONITOR, "Custom", "/speedometer.png", "Medusa slim gauge widget");
    }

    @Override
    public Widget createWidget() {
        return new MedusaSlimWidget();
    }

    @Override
    public String getType(){
        return MedusaSlimWidget.WIDGET_TYPE;
    }

    @Override
    public String getName(){
        return "Medusa Slim";
    }

    @Override
    public WidgetCategory getCategory(){
       return WidgetCategory.MONITOR;
    }

    @Override
    public String getDescription(){
        return "Medusa slim gauge widget.";
    }
}
