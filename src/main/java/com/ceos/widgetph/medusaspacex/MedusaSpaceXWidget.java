package com.ceos.widgetph.medusaspacex;

import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.phoebus.ui.color.WidgetColor;
import com.ceos.widgetph.base.BaseTileWidget;

public class MedusaSpaceXWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "medusaspacex";

    private WidgetProperty<WidgetColor> barColor;
    private WidgetProperty<WidgetColor> barBackgroundColor;

    public MedusaSpaceXWidget() {
        super(WIDGET_TYPE);
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);

        WidgetPropertyDescriptor<WidgetColor> desc;
        desc = CommonWidgetProperties.newColorPropertyDescriptor(WidgetPropertyCategory.DISPLAY, "barColor", "Bar Color");
        barColor = desc.createProperty(this, new WidgetColor(50, 150, 255));
        properties.add(barColor);

        desc = CommonWidgetProperties.newColorPropertyDescriptor(WidgetPropertyCategory.DISPLAY, "barBackgroundColor", "Bar Background");
        barBackgroundColor = desc.createProperty(this, new WidgetColor(40, 40, 40));
        properties.add(barBackgroundColor);
    }

    public WidgetProperty<WidgetColor> propBarColor() { return barColor; }
    public WidgetProperty<WidgetColor> propBarBackgroundColor() { return barBackgroundColor; }
}
