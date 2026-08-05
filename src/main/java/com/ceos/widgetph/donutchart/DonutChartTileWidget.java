package com.ceos.widgetph.donutchart;

import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.epics.vtype.VType;
import org.phoebus.ui.color.WidgetColor;
import com.ceos.widgetph.base.BaseTileWidget;

public class DonutChartTileWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "donutchart";

    private WidgetProperty<WidgetColor> color1;
    private WidgetProperty<WidgetColor> color2;
    private WidgetProperty<WidgetColor> color3;
    private WidgetProperty<WidgetColor> color4;
    private WidgetProperty<WidgetColor> textColor;
    private WidgetProperty<WidgetColor> textColor1;
    private WidgetProperty<WidgetColor> textColor2;
    private WidgetProperty<WidgetColor> textColor3;
    private WidgetProperty<WidgetColor> textColor4;

    private WidgetProperty<String> pv1;
    private WidgetProperty<String> pv2;
    private WidgetProperty<String> pv3;
    private WidgetProperty<String> pv4;

    private WidgetProperty<VType> runtimeValue1;
    private WidgetProperty<VType> runtimeValue2;
    private WidgetProperty<VType> runtimeValue3;
    private WidgetProperty<VType> runtimeValue4;

    public DonutChartTileWidget() {
        super(WIDGET_TYPE);
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);

        WidgetPropertyDescriptor<WidgetColor> colorDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "color1", "Color 1");
        color1 = colorDesc.createProperty(this, new WidgetColor(52, 152, 219));
        properties.add(color1);

        colorDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "color2", "Color 2");
        color2 = colorDesc.createProperty(this, new WidgetColor(231, 76, 60));
        properties.add(color2);

        colorDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "color3", "Color 3");
        color3 = colorDesc.createProperty(this, new WidgetColor(46, 204, 113));
        properties.add(color3);

        colorDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "color4", "Color 4");
        color4 = colorDesc.createProperty(this, new WidgetColor(241, 196, 15));
        properties.add(color4);

        colorDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "textColor", "Text Color");
        textColor = colorDesc.createProperty(this, new WidgetColor(0, 0, 0));
        properties.add(textColor);

        colorDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "textColor1", "Text Color 1");
        textColor1 = colorDesc.createProperty(this, new WidgetColor(0, 0, 0));
        properties.add(textColor1);

        colorDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "textColor2", "Text Color 2");
        textColor2 = colorDesc.createProperty(this, new WidgetColor(0, 0, 0));
        properties.add(textColor2);

        colorDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "textColor3", "Text Color 3");
        textColor3 = colorDesc.createProperty(this, new WidgetColor(0, 0, 0));
        properties.add(textColor3);

        colorDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "textColor4", "Text Color 4");
        textColor4 = colorDesc.createProperty(this, new WidgetColor(0, 0, 0));
        properties.add(textColor4);

        pv1 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "pv1", "Segment 1 PV").createProperty(this, "");
        properties.add(pv1);
        pv2 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "pv2", "Segment 2 PV").createProperty(this, "");
        properties.add(pv2);
        pv3 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "pv3", "Segment 3 PV").createProperty(this, "");
        properties.add(pv3);
        pv4 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "pv4", "Segment 4 PV").createProperty(this, "");
        properties.add(pv4);

        runtimeValue1 = CommonWidgetProperties.newRuntimeValue("runtimeValue1", "Segment 1 Value")
                .createProperty(this, null);
        properties.add(runtimeValue1);
        runtimeValue2 = CommonWidgetProperties.newRuntimeValue("runtimeValue2", "Segment 2 Value")
                .createProperty(this, null);
        properties.add(runtimeValue2);
        runtimeValue3 = CommonWidgetProperties.newRuntimeValue("runtimeValue3", "Segment 3 Value")
                .createProperty(this, null);
        properties.add(runtimeValue3);
        runtimeValue4 = CommonWidgetProperties.newRuntimeValue("runtimeValue4", "Segment 4 Value")
                .createProperty(this, null);
        properties.add(runtimeValue4);
    }

    public WidgetProperty<WidgetColor> propColor1() { return color1; }
    public WidgetProperty<WidgetColor> propColor2() { return color2; }
    public WidgetProperty<WidgetColor> propColor3() { return color3; }
    public WidgetProperty<WidgetColor> propColor4() { return color4; }
    public WidgetProperty<WidgetColor> propTextColor() { return textColor; }
    public WidgetProperty<WidgetColor> propTextColor1() { return textColor1; }
    public WidgetProperty<WidgetColor> propTextColor2() { return textColor2; }
    public WidgetProperty<WidgetColor> propTextColor3() { return textColor3; }
    public WidgetProperty<WidgetColor> propTextColor4() { return textColor4; }
    public WidgetProperty<String> propPV1() { return pv1; }
    public WidgetProperty<String> propPV2() { return pv2; }
    public WidgetProperty<String> propPV3() { return pv3; }
    public WidgetProperty<String> propPV4() { return pv4; }
    public WidgetProperty<VType> runtimeValue1() { return runtimeValue1; }
    public WidgetProperty<VType> runtimeValue2() { return runtimeValue2; }
    public WidgetProperty<VType> runtimeValue3() { return runtimeValue3; }
    public WidgetProperty<VType> runtimeValue4() { return runtimeValue4; }
}
