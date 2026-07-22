package com.ceos.widgetph.timercontrol;

import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.phoebus.ui.color.WidgetColor;
import com.ceos.widgetph.base.BaseTileWidget;

public class TimerControlTileWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "timercontrol";

    private WidgetProperty<WidgetColor> hourColor;
    private WidgetProperty<WidgetColor> minuteColor;
    private WidgetProperty<WidgetColor> secondColor;
    private WidgetProperty<WidgetColor> knobColor;
    private WidgetProperty<WidgetColor> foregroundColor;

    public TimerControlTileWidget() {
        super(WIDGET_TYPE);
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);

        hourColor = createColorProp("hourColor", "Hour Hand Color", new WidgetColor(0, 0, 0));
        properties.add(hourColor);

        minuteColor = createColorProp("minuteColor", "Minute Hand Color", new WidgetColor(0, 0, 0));
        properties.add(minuteColor);

        secondColor = createColorProp("secondColor", "Second Hand Color", new WidgetColor(0, 0, 0));
        properties.add(secondColor);

        knobColor = createColorProp("knobColor", "Knob Color", new WidgetColor(0, 0, 0));
        properties.add(knobColor);

        foregroundColor = createColorProp("foregroundColor", "Foreground Color", new WidgetColor(0, 0, 0));
        properties.add(foregroundColor);
    }

    private WidgetProperty<WidgetColor> createColorProp(String name, String description, WidgetColor defaultValue) {
        WidgetPropertyDescriptor<WidgetColor> desc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, name, description);
        return desc.createProperty(this, defaultValue);
    }

    public WidgetProperty<WidgetColor> propHourColor() { return hourColor; }
    public WidgetProperty<WidgetColor> propMinuteColor() { return minuteColor; }
    public WidgetProperty<WidgetColor> propSecondColor() { return secondColor; }
    public WidgetProperty<WidgetColor> propKnobColor() { return knobColor; }
    public WidgetProperty<WidgetColor> propForegroundColor() { return foregroundColor; }
}
