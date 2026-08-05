package com.ceos.widgetph.medusabulletchart;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class MedusaBulletChartDescriptor extends WidgetDescriptor {

    public MedusaBulletChartDescriptor() {
        super(MedusaBulletChartWidget.WIDGET_TYPE, WidgetCategory.PLOT, "Custom", "/ui-progress-bar.png", "Medusa bullet chart gauge widget");
    }

    @Override
    public Widget createWidget() {
        return new MedusaBulletChartWidget();
    }

    @Override
    public String getType(){
        return MedusaBulletChartWidget.WIDGET_TYPE;
    }

    @Override
    public String getName(){
        return "Medusa Bullet Chart";
    }

    @Override
    public WidgetCategory getCategory(){
       return WidgetCategory.PLOT;
    }

    @Override
    public String getDescription(){
        return "Medusa bullet chart gauge widget.";
    }
}
