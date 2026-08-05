package com.ceos.widgetph.sparkline;

import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.phoebus.ui.color.WidgetColor;
import com.ceos.widgetph.base.BaseTileWidget;

public class SparklineTileWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "sparkline";

    private WidgetProperty<WidgetColor> lineColor;

    public SparklineTileWidget() {
        super(WIDGET_TYPE);
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);

        WidgetPropertyDescriptor<WidgetColor> lineDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "lineColor", "Line Color");
        lineColor = lineDesc.createProperty(this, new WidgetColor(50, 150, 255));
        properties.add(lineColor);
    }

    public WidgetProperty<WidgetColor> propLineColor() { return lineColor; }
}
