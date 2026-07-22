package com.ceos.widgetph.gauge;

import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.csstudio.display.builder.model.properties.WidgetFont;
import org.phoebus.ui.color.WidgetColor;
import com.ceos.widgetph.base.BaseTileWidget;

public class GaugeTileWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "ejemplo";

    private WidgetProperty<WidgetColor> needleColor;
    private WidgetProperty<WidgetColor> barColor;
    private WidgetProperty<WidgetColor> barBackgroundColor;
    private WidgetProperty<WidgetColor> tickMarkColor;
    private WidgetProperty<WidgetColor> tickLabelColor;

    private WidgetProperty<Double> threshold;
    private WidgetProperty<Boolean> thresholdVisible;

    public GaugeTileWidget() {
        super(WIDGET_TYPE);
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);

        needleColor = createColorProp("needleColor", "Needle Color", new WidgetColor(255, 68, 68));
        properties.add(needleColor);

        barColor = createColorProp("barColor", "Bar Color", new WidgetColor(50, 150, 255));
        properties.add(barColor);

        barBackgroundColor = createColorProp("barBackgroundColor", "Bar Background", new WidgetColor(40, 40, 40));
        properties.add(barBackgroundColor);

        tickMarkColor = createColorProp("tickMarkColor", "Tick Mark Color", new WidgetColor(160, 160, 160));
        properties.add(tickMarkColor);

        tickLabelColor = createColorProp("tickLabelColor", "Tick Label Color", new WidgetColor(160, 160, 160));
        properties.add(tickLabelColor);

        WidgetPropertyDescriptor<Double> threshDesc = CommonWidgetProperties.newDoublePropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "threshold", "Threshold");
        threshold = threshDesc.createProperty(this, 75.0);
        properties.add(threshold);

        WidgetPropertyDescriptor<Boolean> threshVisDesc = CommonWidgetProperties.newBooleanPropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "thresholdVisible", "Threshold Visible");
        thresholdVisible = threshVisDesc.createProperty(this, true);
        properties.add(thresholdVisible);
    }

    private WidgetProperty<WidgetColor> createColorProp(String name, String description, WidgetColor defaultValue) {
        WidgetPropertyDescriptor<WidgetColor> desc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, name, description);
        return desc.createProperty(this, defaultValue);
    }

    public WidgetProperty<WidgetColor> propNeedleColor() { return needleColor; }
    public WidgetProperty<WidgetColor> propBarColor() { return barColor; }
    public WidgetProperty<WidgetColor> propBarBackgroundColor() { return barBackgroundColor; }
    public WidgetProperty<WidgetColor> propTickMarkColor() { return tickMarkColor; }
    public WidgetProperty<WidgetColor> propTickLabelColor() { return tickLabelColor; }
    public WidgetProperty<Double> propThreshold() { return threshold; }
    public WidgetProperty<Boolean> propThresholdVisible() { return thresholdVisible; }
}
