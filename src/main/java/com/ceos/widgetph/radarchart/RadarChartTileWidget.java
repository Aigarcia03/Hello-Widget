package com.ceos.widgetph.radarchart;

import java.util.List;
import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.csstudio.display.builder.model.properties.EnumWidgetProperty;
import org.epics.vtype.VType;
import org.phoebus.ui.color.WidgetColor;
import com.ceos.widgetph.base.BaseTileWidget;
import eu.hansolo.tilesfx.chart.RadarChartMode;

public class RadarChartTileWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "radarchart";

    private WidgetProperty<RadarChartMode> mode;
    private WidgetProperty<WidgetColor> chartFillColor;
    private WidgetProperty<WidgetColor> gridColor;
    private WidgetProperty<Integer> numSectors;

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

    public RadarChartTileWidget() {
        super(WIDGET_TYPE);
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);

        WidgetPropertyDescriptor<RadarChartMode> modeDesc = new WidgetPropertyDescriptor<>(
                WidgetPropertyCategory.DISPLAY, "mode", "Mode") {
            @Override
            public WidgetProperty<RadarChartMode> createProperty(Widget widget, RadarChartMode defaultValue) {
                return new EnumWidgetProperty<>(this, widget, defaultValue);
            }
        };
        mode = modeDesc.createProperty(this, RadarChartMode.SECTOR);
        properties.add(mode);

        WidgetPropertyDescriptor<WidgetColor> fillDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "chartFillColor", "Chart Fill");
        chartFillColor = fillDesc.createProperty(this, new WidgetColor(100, 150, 255, 128));
        properties.add(chartFillColor);

        WidgetPropertyDescriptor<WidgetColor> gridDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "gridColor", "Grid Color");
        gridColor = gridDesc.createProperty(this, new WidgetColor(200, 200, 200));
        properties.add(gridColor);

        WidgetPropertyDescriptor<Integer> numDesc = CommonWidgetProperties.newIntegerPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "numSectors", "Number of Sectors");
        numSectors = numDesc.createProperty(this, 6);
        properties.add(numSectors);

        pv1 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "pv1", "Sector 1 PV").createProperty(this, "");
        properties.add(pv1);
        pv2 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "pv2", "Sector 2 PV").createProperty(this, "");
        properties.add(pv2);
        pv3 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "pv3", "Sector 3 PV").createProperty(this, "");
        properties.add(pv3);
        pv4 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "pv4", "Sector 4 PV").createProperty(this, "");
        properties.add(pv4);
        pv5 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "pv5", "Sector 5 PV").createProperty(this, "");
        properties.add(pv5);
        pv6 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "pv6", "Sector 6 PV").createProperty(this, "");
        properties.add(pv6);

        runtimeValue1 = CommonWidgetProperties.newRuntimeValue("runtimeValue1", "Sector 1 Value")
                .createProperty(this, null);
        properties.add(runtimeValue1);
        runtimeValue2 = CommonWidgetProperties.newRuntimeValue("runtimeValue2", "Sector 2 Value")
                .createProperty(this, null);
        properties.add(runtimeValue2);
        runtimeValue3 = CommonWidgetProperties.newRuntimeValue("runtimeValue3", "Sector 3 Value")
                .createProperty(this, null);
        properties.add(runtimeValue3);
        runtimeValue4 = CommonWidgetProperties.newRuntimeValue("runtimeValue4", "Sector 4 Value")
                .createProperty(this, null);
        properties.add(runtimeValue4);
        runtimeValue5 = CommonWidgetProperties.newRuntimeValue("runtimeValue5", "Sector 5 Value")
                .createProperty(this, null);
        properties.add(runtimeValue5);
        runtimeValue6 = CommonWidgetProperties.newRuntimeValue("runtimeValue6", "Sector 6 Value")
                .createProperty(this, null);
        properties.add(runtimeValue6);
    }

    public WidgetProperty<RadarChartMode> propMode() { return mode; }
    public WidgetProperty<WidgetColor> propChartFillColor() { return chartFillColor; }
    public WidgetProperty<WidgetColor> propGridColor() { return gridColor; }
    public WidgetProperty<Integer> propNumSectors() { return numSectors; }
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
}