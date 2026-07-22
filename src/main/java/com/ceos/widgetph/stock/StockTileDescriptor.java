package com.ceos.widgetph.stock;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class StockTileDescriptor extends WidgetDescriptor {

    public StockTileDescriptor() {
        super(StockTileWidget.WIDGET_TYPE, WidgetCategory.MONITOR, "Custom", "ruta/a/tu/ícono16x16.png", "Stock tile widget");
    }

    @Override
    public Widget createWidget() {
        return new StockTileWidget();
    }

    @Override
    public String getType(){
        return StockTileWidget.WIDGET_TYPE;
    }

    @Override
    public String getName(){
        return "Stock Tile";
    }

    @Override
    public WidgetCategory getCategory(){
       return WidgetCategory.MONITOR;
    }

    @Override
    public String getDescription(){
        return "Stock/indicator tile widget.";
    }
}
