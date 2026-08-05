package com.ceos.widgetph.radialchart;

import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.epics.vtype.VType;
import org.phoebus.ui.color.WidgetColor;
import com.ceos.widgetph.base.BaseTileWidget;

public class RadialChartTileWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "radialchart";

    private WidgetProperty<String> pv1;
    private WidgetProperty<String> pv2;
    private WidgetProperty<String> pv3;
    private WidgetProperty<String> pv4;
    private WidgetProperty<String> pv5;
    private WidgetProperty<String> pv6;

    private WidgetProperty<VType> runtimeValue1;
    private WidgetProperty<VType> runtimeValue2;
    private WidgetProperty<VType> runtimeValue3;
    private WidgetProperty<VType> runtimeValue4;
    private WidgetProperty<VType> runtimeValue5;
    private WidgetProperty<VType> runtimeValue6;

    private WidgetProperty<WidgetColor> color1;
    private WidgetProperty<WidgetColor> color2;
    private WidgetProperty<WidgetColor> color3;
    private WidgetProperty<WidgetColor> color4;
    private WidgetProperty<WidgetColor> color5;
    private WidgetProperty<WidgetColor> color6;
    private WidgetProperty<WidgetColor> lineTextColor;

    public RadialChartTileWidget() {
        super(WIDGET_TYPE);
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);

        pv1 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "pv1", "Value 1 PV").createProperty(this, "");
        properties.add(pv1);
        pv2 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "pv2", "Value 2 PV").createProperty(this, "");
        properties.add(pv2);
        pv3 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "pv3", "Value 3 PV").createProperty(this, "");
        properties.add(pv3);
        pv4 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "pv4", "Value 4 PV").createProperty(this, "");
        properties.add(pv4);
        pv5 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "pv5", "Value 5 PV").createProperty(this, "");
        properties.add(pv5);
        pv6 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "pv6", "Value 6 PV").createProperty(this, "");
        properties.add(pv6);

        runtimeValue1 = CommonWidgetProperties.newRuntimeValue("runtimeValue1", "Value 1")
                .createProperty(this, null);
        properties.add(runtimeValue1);
        runtimeValue2 = CommonWidgetProperties.newRuntimeValue("runtimeValue2", "Value 2")
                .createProperty(this, null);
        properties.add(runtimeValue2);
        runtimeValue3 = CommonWidgetProperties.newRuntimeValue("runtimeValue3", "Value 3")
                .createProperty(this, null);
        properties.add(runtimeValue3);
        runtimeValue4 = CommonWidgetProperties.newRuntimeValue("runtimeValue4", "Value 4")
                .createProperty(this, null);
        properties.add(runtimeValue4);
        runtimeValue5 = CommonWidgetProperties.newRuntimeValue("runtimeValue5", "Value 5")
                .createProperty(this, null);
        properties.add(runtimeValue5);
        runtimeValue6 = CommonWidgetProperties.newRuntimeValue("runtimeValue6", "Value 6")
                .createProperty(this, null);
        properties.add(runtimeValue6);

        WidgetPropertyDescriptor<WidgetColor> colorDesc;
        colorDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "color1", "Color 1");
        color1 = colorDesc.createProperty(this, new WidgetColor(0, 114, 178));
        properties.add(color1);
        colorDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "color2", "Color 2");
        color2 = colorDesc.createProperty(this, new WidgetColor(214, 39, 40));
        properties.add(color2);
        colorDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "color3", "Color 3");
        color3 = colorDesc.createProperty(this, new WidgetColor(44, 160, 44));
        properties.add(color3);
        colorDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "color4", "Color 4");
        color4 = colorDesc.createProperty(this, new WidgetColor(255, 127, 14));
        properties.add(color4);
        colorDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "color5", "Color 5");
        color5 = colorDesc.createProperty(this, new WidgetColor(148, 103, 189));
        properties.add(color5);
        colorDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "color6", "Color 6");
        color6 = colorDesc.createProperty(this, new WidgetColor(140, 86, 75));
        properties.add(color6);

        WidgetPropertyDescriptor<WidgetColor> ltDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "lineTextColor", "Line / Text Color");
        lineTextColor = ltDesc.createProperty(this, new WidgetColor(160, 160, 160));
        properties.add(lineTextColor);
    }

    public WidgetProperty<String> propPV1() { return pv1; }
    public WidgetProperty<String> propPV2() { return pv2; }
    public WidgetProperty<String> propPV3() { return pv3; }
    public WidgetProperty<String> propPV4() { return pv4; }
    public WidgetProperty<String> propPV5() { return pv5; }
    public WidgetProperty<String> propPV6() { return pv6; }
    public WidgetProperty<VType> runtimeValue1() { return runtimeValue1; }
    public WidgetProperty<VType> runtimeValue2() { return runtimeValue2; }
    public WidgetProperty<VType> runtimeValue3() { return runtimeValue3; }
    public WidgetProperty<VType> runtimeValue4() { return runtimeValue4; }
    public WidgetProperty<VType> runtimeValue5() { return runtimeValue5; }
    public WidgetProperty<VType> runtimeValue6() { return runtimeValue6; }
    public WidgetProperty<WidgetColor> propColor1() { return color1; }
    public WidgetProperty<WidgetColor> propColor2() { return color2; }
    public WidgetProperty<WidgetColor> propColor3() { return color3; }
    public WidgetProperty<WidgetColor> propColor4() { return color4; }
    public WidgetProperty<WidgetColor> propColor5() { return color5; }
    public WidgetProperty<WidgetColor> propColor6() { return color6; }
    public WidgetProperty<WidgetColor> propLineTextColor() { return lineTextColor; }
}
