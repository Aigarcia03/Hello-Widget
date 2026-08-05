package com.ceos.widgetph.radialchart;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class RadialChartTileDescriptor extends WidgetDescriptor {

    public RadialChartTileDescriptor() {
        super(RadialChartTileWidget.WIDGET_TYPE,
              WidgetCategory.PLOT,
              "Radial Chart",
              "/chart_pie.png",
              "Gráfico radial.");
    }

    @Override
    public Widget createWidget() { return new RadialChartTileWidget(); }

    @Override
    public String getType()      { return RadialChartTileWidget.WIDGET_TYPE; }

    @Override
    public String getName()      { return "Radial Chart Tile"; }

    @Override
    public WidgetCategory getCategory() { return WidgetCategory.PLOT; }

    @Override
    public String getDescription() { return "Gráfico radial."; }
}
