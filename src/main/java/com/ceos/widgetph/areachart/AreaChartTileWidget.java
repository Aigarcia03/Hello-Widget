package com.ceos.widgetph.areachart;

import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.phoebus.ui.color.WidgetColor;
import com.ceos.widgetph.base.BaseTileWidget;

public class AreaChartTileWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "areachart";

    private WidgetProperty<WidgetColor> axisTextColor;
    private WidgetProperty<WidgetColor> seriesColor;

    public AreaChartTileWidget() {
        super(WIDGET_TYPE);
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);

        WidgetPropertyDescriptor<WidgetColor> colorDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "axisTextColor", "Axis Text Color");
        axisTextColor = colorDesc.createProperty(this, new WidgetColor(0, 0, 0));
        properties.add(axisTextColor);

        WidgetPropertyDescriptor<WidgetColor> seriesDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "seriesColor", "Series Color");
        seriesColor = seriesDesc.createProperty(this, new WidgetColor(0, 114, 178));
        properties.add(seriesColor);
    }

    public WidgetProperty<WidgetColor> propAxisTextColor() { return axisTextColor; }
    public WidgetProperty<WidgetColor> propSeriesColor() { return seriesColor; }
}
