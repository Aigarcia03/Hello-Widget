package com.ceos.widgetph.areachart;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class AreaChartTileDescriptor extends WidgetDescriptor {

    public AreaChartTileDescriptor() {
        super(AreaChartTileWidget.WIDGET_TYPE,
              WidgetCategory.PLOT,
              "Area Chart",
              "ruta/a/tu/icono16x16.png",
              "Gráfico de área.");
    }

    @Override
    public Widget createWidget() { return new AreaChartTileWidget(); }

    @Override
    public String getType()      { return AreaChartTileWidget.WIDGET_TYPE; }

    @Override
    public String getName()      { return "Area Chart Tile"; }

    @Override
    public WidgetCategory getCategory() { return WidgetCategory.PLOT; }

    @Override
    public String getDescription() { return "Gráfico de área."; }
}
