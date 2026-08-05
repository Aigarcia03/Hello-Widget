package com.ceos.widgetph.ikonli;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class IkonliIconDescriptor extends WidgetDescriptor {

    public IkonliIconDescriptor() {
        super(IkonliIconWidget.WIDGET_TYPE, WidgetCategory.MISC, "Custom", "/add.png", "Ikonli icon widget");
    }

    @Override
    public Widget createWidget() {
        return new IkonliIconWidget();
    }

    @Override
    public String getType(){
        return IkonliIconWidget.WIDGET_TYPE;
    }

    @Override
    public String getName(){
        return "Ikonli Icon";
    }

    @Override
    public WidgetCategory getCategory(){
       return WidgetCategory.MISC;
    }

    @Override
    public String getDescription(){
        return "Ikonli icon display widget.";
    }
}
