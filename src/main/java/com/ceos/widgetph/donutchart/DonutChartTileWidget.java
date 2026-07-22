package com.ceos.widgetph.donutchart;

import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import com.ceos.widgetph.base.BaseTileWidget;

public class DonutChartTileWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "donutchart";

    private WidgetProperty<Double> value1;
    private WidgetProperty<Double> value2;
    private WidgetProperty<Double> value3;
    private WidgetProperty<Double> value4;

    public DonutChartTileWidget() {
        super(WIDGET_TYPE);
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);

        WidgetPropertyDescriptor<Double> valDesc = CommonWidgetProperties.newDoublePropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "value1", "Value 1");
        value1 = valDesc.createProperty(this, 25.0);
        properties.add(value1);

        valDesc = CommonWidgetProperties.newDoublePropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "value2", "Value 2");
        value2 = valDesc.createProperty(this, 25.0);
        properties.add(value2);

        valDesc = CommonWidgetProperties.newDoublePropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "value3", "Value 3");
        value3 = valDesc.createProperty(this, 25.0);
        properties.add(value3);

        valDesc = CommonWidgetProperties.newDoublePropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "value4", "Value 4");
        value4 = valDesc.createProperty(this, 25.0);
        properties.add(value4);
    }

    public WidgetProperty<Double> propValue1() { return value1; }
    public WidgetProperty<Double> propValue2() { return value2; }
    public WidgetProperty<Double> propValue3() { return value3; }
    public WidgetProperty<Double> propValue4() { return value4; }
}
