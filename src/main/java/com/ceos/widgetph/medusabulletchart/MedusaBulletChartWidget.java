package com.ceos.widgetph.medusabulletchart;

import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.phoebus.ui.color.WidgetColor;
import com.ceos.widgetph.base.BaseTileWidget;

public class MedusaBulletChartWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "medusabulletchart";

    private WidgetProperty<WidgetColor> barColor;
    private WidgetProperty<WidgetColor> barBackgroundColor;
    private WidgetProperty<WidgetColor> tickMarkColor;
    private WidgetProperty<WidgetColor> tickLabelColor;
    private WidgetProperty<WidgetColor> foregroundColor;
    private WidgetProperty<Double> threshold;
    private WidgetProperty<WidgetColor> thresholdColor;

    public MedusaBulletChartWidget() {
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

        desc = CommonWidgetProperties.newColorPropertyDescriptor(WidgetPropertyCategory.DISPLAY, "tickMarkColor", "Tick Mark Color");
        tickMarkColor = desc.createProperty(this, new WidgetColor(160, 160, 160));
        properties.add(tickMarkColor);

        desc = CommonWidgetProperties.newColorPropertyDescriptor(WidgetPropertyCategory.DISPLAY, "tickLabelColor", "Tick Label Color");
        tickLabelColor = desc.createProperty(this, new WidgetColor(160, 160, 160));
        properties.add(tickLabelColor);

        desc = CommonWidgetProperties.newColorPropertyDescriptor(WidgetPropertyCategory.DISPLAY, "foregroundColor", "Foreground Color");
        foregroundColor = desc.createProperty(this, new WidgetColor(220, 220, 220));
        properties.add(foregroundColor);

        WidgetPropertyDescriptor<Double> thresholdDesc = CommonWidgetProperties.newDoublePropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "threshold", "Threshold");
        threshold = thresholdDesc.createProperty(this, 100.0);
        properties.add(threshold);

        desc = CommonWidgetProperties.newColorPropertyDescriptor(WidgetPropertyCategory.DISPLAY, "thresholdColor", "Threshold Color");
        thresholdColor = desc.createProperty(this, new WidgetColor(255, 0, 0));
        properties.add(thresholdColor);
    }

    public WidgetProperty<WidgetColor> propBarColor() { return barColor; }
    public WidgetProperty<WidgetColor> propBarBackgroundColor() { return barBackgroundColor; }
    public WidgetProperty<WidgetColor> propTickMarkColor() { return tickMarkColor; }
    public WidgetProperty<WidgetColor> propTickLabelColor() { return tickLabelColor; }
    public WidgetProperty<WidgetColor> propForegroundColor() { return foregroundColor; }
    public WidgetProperty<Double> propThreshold() { return threshold; }
    public WidgetProperty<WidgetColor> propThresholdColor() { return thresholdColor; }
}
