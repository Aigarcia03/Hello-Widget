package com.ceos.widgetph.linechart;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class LineChartTileDescriptor extends WidgetDescriptor {

    public LineChartTileDescriptor() {
        super(LineChartTileWidget.WIDGET_TYPE,
              WidgetCategory.PLOT,
              "Line Chart",
              "ruta/a/tu/icono16x16.png",
              "Gráfico de línea.");
    }

    @Override
    public Widget createWidget() { return new LineChartTileWidget(); }

    @Override
    public String getType()      { return LineChartTileWidget.WIDGET_TYPE; }

    @Override
    public String getName()      { return "Line Chart Tile"; }

    @Override
    public WidgetCategory getCategory() { return WidgetCategory.PLOT; }

    @Override
    public String getDescription() { return "Gráfico de línea."; }
}
