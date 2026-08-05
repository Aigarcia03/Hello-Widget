package com.ceos.widgetph.regulator;

import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.phoebus.ui.color.WidgetColor;
import com.ceos.widgetph.base.BaseTileWidget;

public class RegulatorTileWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "regulator";

    private WidgetProperty<WidgetColor> knobColor;
    private WidgetProperty<WidgetColor> indicatorColor;
    private WidgetProperty<WidgetColor> barColor;

    public RegulatorTileWidget() {
        super(WIDGET_TYPE);
        propTitle().setValue("Regulator");
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);
        knobColor = createColorProp("knobColor", "Knob Color", new WidgetColor(192, 192, 192));
        properties.add(knobColor);
        indicatorColor = createColorProp("indicatorColor", "Indicator Color", new WidgetColor(58, 123, 213));
        properties.add(indicatorColor);
        barColor = createColorProp("barColor", "Bar Color", new WidgetColor(58, 123, 213));
        properties.add(barColor);
    }

    private WidgetProperty<WidgetColor> createColorProp(String name, String description, WidgetColor defaultValue) {
        WidgetPropertyDescriptor<WidgetColor> desc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, name, description);
        return desc.createProperty(this, defaultValue);
    }

    public WidgetProperty<WidgetColor> propKnobColor() { return knobColor; }
    public WidgetProperty<WidgetColor> propIndicatorColor() { return indicatorColor; }
    public WidgetProperty<WidgetColor> propBarColor() { return barColor; }
}
