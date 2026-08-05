package com.ceos.widgetph.clock;

import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.phoebus.ui.color.WidgetColor;
import com.ceos.widgetph.base.BaseTileWidget;

public class ClockTileWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "clocktile";

    private WidgetProperty<WidgetColor> dateColor;

    public ClockTileWidget() {
        super(WIDGET_TYPE);
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);

        dateColor = createColorProp("dateColor", "Date Color", new WidgetColor(0, 0, 0));
        properties.add(dateColor);


    }

    private WidgetProperty<WidgetColor> createColorProp(String name, String description, WidgetColor defaultValue) {
        WidgetPropertyDescriptor<WidgetColor> desc = CommonWidgetProperties.newColorPropertyDescriptor(
                org.csstudio.display.builder.model.WidgetPropertyCategory.DISPLAY, name, description);
        return desc.createProperty(this, defaultValue);
    }

    public WidgetProperty<WidgetColor> propDateColor() { return dateColor; }
}
