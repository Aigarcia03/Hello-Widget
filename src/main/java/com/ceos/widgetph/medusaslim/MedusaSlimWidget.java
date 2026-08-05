package com.ceos.widgetph.medusaslim;

import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.phoebus.ui.color.WidgetColor;
import com.ceos.widgetph.base.BaseTileWidget;

public class MedusaSlimWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "medusaslim";

    private WidgetProperty<WidgetColor> barColor;
    private WidgetProperty<WidgetColor> barBackgroundColor;

    public MedusaSlimWidget() {
        super(WIDGET_TYPE);
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);

        WidgetPropertyDescriptor<WidgetColor> desc;
        desc = CommonWidgetProperties.newColorPropertyDescriptor(WidgetPropertyCategory.DISPLAY, "barColor", "Bar Color");
        barColor = desc.createProperty(this, new WidgetColor(223, 223, 223));
        properties.add(barColor);

        desc = CommonWidgetProperties.newColorPropertyDescriptor(WidgetPropertyCategory.DISPLAY, "barBackgroundColor", "Bar Background");
        barBackgroundColor = desc.createProperty(this, new WidgetColor(36, 36, 36));
        properties.add(barBackgroundColor);
    }

    public WidgetProperty<WidgetColor> propBarColor() { return barColor; }
    public WidgetProperty<WidgetColor> propBarBackgroundColor() { return barBackgroundColor; }
}
