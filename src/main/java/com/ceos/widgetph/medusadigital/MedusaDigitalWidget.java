package com.ceos.widgetph.medusadigital;

import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.phoebus.ui.color.WidgetColor;
import com.ceos.widgetph.base.BaseTileWidget;

public class MedusaDigitalWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "medusadigital";

    private WidgetProperty<WidgetColor> foregroundColor;
    private WidgetProperty<WidgetColor> barColor;

    public MedusaDigitalWidget() {
        super(WIDGET_TYPE);
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);

        WidgetPropertyDescriptor<WidgetColor> fgDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "foregroundColor", "Foreground Color");
        foregroundColor = fgDesc.createProperty(this, new WidgetColor(220, 220, 220));
        properties.add(foregroundColor);

        WidgetPropertyDescriptor<WidgetColor> barDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "barColor", "Bar Color");
        barColor = barDesc.createProperty(this, new WidgetColor(223, 223, 223));
        properties.add(barColor);
    }

    public WidgetProperty<WidgetColor> propForegroundColor() { return foregroundColor; }
    public WidgetProperty<WidgetColor> propBarColor() { return barColor; }
}
