package com.ceos.widgetph.sparkline;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class SparklineTileDescriptor extends WidgetDescriptor {

    public SparklineTileDescriptor() {
        super(SparklineTileWidget.WIDGET_TYPE, WidgetCategory.PLOT, "Sparkline", "ruta/a/tu/icono16x16.png", "Widget de gráfico de línea temporal.");
    }

    @Override
    public Widget createWidget() {
        return new SparklineTileWidget();
    }

    @Override
    public String getType(){
        return SparklineTileWidget.WIDGET_TYPE;
    }

    @Override
    public String getName(){
        return "Sparkline Tile";
    }

    @Override
    public WidgetCategory getCategory(){
       return WidgetCategory.PLOT;
    }

    @Override
    public String getDescription(){
        return "Widget de gráfico de línea temporal.";
    }
}
