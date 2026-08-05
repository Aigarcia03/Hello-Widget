package com.ceos.widgetph.barchart;

import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.csstudio.display.builder.model.properties.EnumWidgetProperty;
import org.epics.vtype.VType;
import org.phoebus.ui.color.WidgetColor;
import com.ceos.widgetph.base.BaseTileWidget;
import eu.hansolo.tilesfx.Tile.ItemSorting;

public class BarChartTileWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "barchart";

    private WidgetProperty<Integer> numItems;
    private WidgetProperty<ItemSorting> sorting;

    private WidgetProperty<String> name1;
    private WidgetProperty<String> name2;
    private WidgetProperty<String> name3;
    private WidgetProperty<String> name4;
    private WidgetProperty<String> name5;
    private WidgetProperty<String> name6;

    private WidgetProperty<WidgetColor> color1;
    private WidgetProperty<WidgetColor> color2;
    private WidgetProperty<WidgetColor> color3;
    private WidgetProperty<WidgetColor> color4;
    private WidgetProperty<WidgetColor> color5;
    private WidgetProperty<WidgetColor> color6;

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

    public BarChartTileWidget() {
        super(WIDGET_TYPE);
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);

        WidgetPropertyDescriptor<Integer> numDesc = CommonWidgetProperties.newIntegerPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "numItems", "Number of Items");
        numItems = numDesc.createProperty(this, 6);
        properties.add(numItems);

        WidgetPropertyDescriptor<ItemSorting> sortDesc = new WidgetPropertyDescriptor<>(
                WidgetPropertyCategory.DISPLAY, "sorting", "Sorting") {
            @Override
            public WidgetProperty<ItemSorting> createProperty(org.csstudio.display.builder.model.Widget widget,
                    ItemSorting defaultValue) {
                return new EnumWidgetProperty<>(this, widget, defaultValue);
            }
        };
        sorting = sortDesc.createProperty(this, ItemSorting.DESCENDING);
        properties.add(sorting);

        addItemProps(properties, 1, "Item 1");
        addItemProps(properties, 2, "Item 2");
        addItemProps(properties, 3, "Item 3");
        addItemProps(properties, 4, "Item 4");
        addItemProps(properties, 5, "Item 5");
        addItemProps(properties, 6, "Item 6");
    }

    private void addItemProps(final List<WidgetProperty<?>> properties, final int index, final String defaultName) {
        final String num = Integer.toString(index);
        if (index == 1) {
            name1 = CommonWidgetProperties.newStringPropertyDescriptor(
                    WidgetPropertyCategory.DISPLAY, "name" + num, "Name " + index).createProperty(this, defaultName);
            properties.add(name1);
        } else if (index == 2) {
            name2 = CommonWidgetProperties.newStringPropertyDescriptor(
                    WidgetPropertyCategory.DISPLAY, "name" + num, "Name " + index).createProperty(this, defaultName);
            properties.add(name2);
        } else if (index == 3) {
            name3 = CommonWidgetProperties.newStringPropertyDescriptor(
                    WidgetPropertyCategory.DISPLAY, "name" + num, "Name " + index).createProperty(this, defaultName);
            properties.add(name3);
        } else if (index == 4) {
            name4 = CommonWidgetProperties.newStringPropertyDescriptor(
                    WidgetPropertyCategory.DISPLAY, "name" + num, "Name " + index).createProperty(this, defaultName);
            properties.add(name4);
        } else if (index == 5) {
            name5 = CommonWidgetProperties.newStringPropertyDescriptor(
                    WidgetPropertyCategory.DISPLAY, "name" + num, "Name " + index).createProperty(this, defaultName);
            properties.add(name5);
        } else {
            name6 = CommonWidgetProperties.newStringPropertyDescriptor(
                    WidgetPropertyCategory.DISPLAY, "name" + num, "Name " + index).createProperty(this, defaultName);
            properties.add(name6);
        }

        if (index == 1) {
            color1 = CommonWidgetProperties.newColorPropertyDescriptor(
                    WidgetPropertyCategory.DISPLAY, "color" + num, "Color " + index)
                    .createProperty(this, new WidgetColor(52, 152, 219));
            properties.add(color1);
        } else if (index == 2) {
            color2 = CommonWidgetProperties.newColorPropertyDescriptor(
                    WidgetPropertyCategory.DISPLAY, "color" + num, "Color " + index)
                    .createProperty(this, new WidgetColor(231, 76, 60));
            properties.add(color2);
        } else if (index == 3) {
            color3 = CommonWidgetProperties.newColorPropertyDescriptor(
                    WidgetPropertyCategory.DISPLAY, "color" + num, "Color " + index)
                    .createProperty(this, new WidgetColor(46, 204, 113));
            properties.add(color3);
        } else if (index == 4) {
            color4 = CommonWidgetProperties.newColorPropertyDescriptor(
                    WidgetPropertyCategory.DISPLAY, "color" + num, "Color " + index)
                    .createProperty(this, new WidgetColor(241, 196, 15));
            properties.add(color4);
        } else if (index == 5) {
            color5 = CommonWidgetProperties.newColorPropertyDescriptor(
                    WidgetPropertyCategory.DISPLAY, "color" + num, "Color " + index)
                    .createProperty(this, new WidgetColor(155, 89, 182));
            properties.add(color5);
        } else {
            color6 = CommonWidgetProperties.newColorPropertyDescriptor(
                    WidgetPropertyCategory.DISPLAY, "color" + num, "Color " + index)
                    .createProperty(this, new WidgetColor(26, 188, 156));
            properties.add(color6);
        }

        if (index == 1) {
            pv1 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                    WidgetPropertyCategory.BEHAVIOR, "pv" + num, "Item " + index + " PV").createProperty(this, "");
            properties.add(pv1);
        } else if (index == 2) {
            pv2 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                    WidgetPropertyCategory.BEHAVIOR, "pv" + num, "Item " + index + " PV").createProperty(this, "");
            properties.add(pv2);
        } else if (index == 3) {
            pv3 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                    WidgetPropertyCategory.BEHAVIOR, "pv" + num, "Item " + index + " PV").createProperty(this, "");
            properties.add(pv3);
        } else if (index == 4) {
            pv4 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                    WidgetPropertyCategory.BEHAVIOR, "pv" + num, "Item " + index + " PV").createProperty(this, "");
            properties.add(pv4);
        } else if (index == 5) {
            pv5 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                    WidgetPropertyCategory.BEHAVIOR, "pv" + num, "Item " + index + " PV").createProperty(this, "");
            properties.add(pv5);
        } else {
            pv6 = CommonWidgetProperties.newPVNamePropertyDescriptor(
                    WidgetPropertyCategory.BEHAVIOR, "pv" + num, "Item " + index + " PV").createProperty(this, "");
            properties.add(pv6);
        }

        if (index == 1) {
            runtimeValue1 = CommonWidgetProperties.newRuntimeValue("runtimeValue" + num, "Item " + index + " Value")
                    .createProperty(this, null);
            properties.add(runtimeValue1);
        } else if (index == 2) {
            runtimeValue2 = CommonWidgetProperties.newRuntimeValue("runtimeValue" + num, "Item " + index + " Value")
                    .createProperty(this, null);
            properties.add(runtimeValue2);
        } else if (index == 3) {
            runtimeValue3 = CommonWidgetProperties.newRuntimeValue("runtimeValue" + num, "Item " + index + " Value")
                    .createProperty(this, null);
            properties.add(runtimeValue3);
        } else if (index == 4) {
            runtimeValue4 = CommonWidgetProperties.newRuntimeValue("runtimeValue" + num, "Item " + index + " Value")
                    .createProperty(this, null);
            properties.add(runtimeValue4);
        } else if (index == 5) {
            runtimeValue5 = CommonWidgetProperties.newRuntimeValue("runtimeValue" + num, "Item " + index + " Value")
                    .createProperty(this, null);
            properties.add(runtimeValue5);
        } else {
            runtimeValue6 = CommonWidgetProperties.newRuntimeValue("runtimeValue" + num, "Item " + index + " Value")
                    .createProperty(this, null);
            properties.add(runtimeValue6);
        }
    }

    public WidgetProperty<Integer> propNumItems() { return numItems; }
    public WidgetProperty<ItemSorting> propSorting() { return sorting; }
    public WidgetProperty<String> propName1() { return name1; }
    public WidgetProperty<String> propName2() { return name2; }
    public WidgetProperty<String> propName3() { return name3; }
    public WidgetProperty<String> propName4() { return name4; }
    public WidgetProperty<String> propName5() { return name5; }
    public WidgetProperty<String> propName6() { return name6; }
    public WidgetProperty<WidgetColor> propColor1() { return color1; }
    public WidgetProperty<WidgetColor> propColor2() { return color2; }
    public WidgetProperty<WidgetColor> propColor3() { return color3; }
    public WidgetProperty<WidgetColor> propColor4() { return color4; }
    public WidgetProperty<WidgetColor> propColor5() { return color5; }
    public WidgetProperty<WidgetColor> propColor6() { return color6; }
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
