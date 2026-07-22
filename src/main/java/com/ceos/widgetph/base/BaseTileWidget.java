package com.ceos.widgetph.base;

import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.persist.WidgetFontService;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.csstudio.display.builder.model.properties.WidgetFont;
import org.csstudio.display.builder.model.widgets.PVWidget;
import org.phoebus.ui.color.WidgetColor;

public abstract class BaseTileWidget extends PVWidget {

    private WidgetProperty<WidgetColor> backgroundColor;
    private WidgetProperty<WidgetColor> titleColor;
    private WidgetProperty<WidgetColor> valueColor;
    private WidgetProperty<WidgetColor> unitColor;
    private WidgetProperty<String> title;
    private WidgetProperty<String> unit;
    private WidgetProperty<WidgetFont> font;
    private WidgetProperty<Integer> decimals;

    private WidgetProperty<Boolean> roundedCorners;
    private WidgetProperty<Boolean> shadowsEnabled;
    private WidgetProperty<Boolean> valueVisible;

    private WidgetProperty<Double> minValue;
    private WidgetProperty<Double> maxValue;
    private WidgetProperty<Boolean> animated;

    public BaseTileWidget(String type) {
        super(type);
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);

        backgroundColor = CommonWidgetProperties.propBackgroundColor.createProperty(this, new WidgetColor(42, 42, 42, 0));
        properties.add(backgroundColor);

        titleColor = createColorProp("titleColor", "Title Color", new WidgetColor(0, 0, 0));
        properties.add(titleColor);

        valueColor = createColorProp("valueColor", "Value Color", new WidgetColor(0, 0, 0));
        properties.add(valueColor);

        unitColor = createColorProp("unitColor", "Unit Color", new WidgetColor(0, 0, 0));
        properties.add(unitColor);

        title = CommonWidgetProperties.propText.createProperty(this, "Tile");
        properties.add(title);

        WidgetPropertyDescriptor<String> unitDesc = CommonWidgetProperties.newStringPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "unit", "Unit");
        unit = unitDesc.createProperty(this, "V");
        properties.add(unit);

        font = CommonWidgetProperties.propFont.createProperty(this, WidgetFontService.get("Default Bold"));
        properties.add(font);

        WidgetPropertyDescriptor<Integer> decimalsDesc = CommonWidgetProperties.newIntegerPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "decimals", "Decimals");
        decimals = decimalsDesc.createProperty(this, 1);
        properties.add(decimals);

        WidgetPropertyDescriptor<Boolean> roundedDesc = CommonWidgetProperties.newBooleanPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "roundedCorners", "Rounded Corners");
        roundedCorners = roundedDesc.createProperty(this, true);
        properties.add(roundedCorners);

        WidgetPropertyDescriptor<Boolean> shadowsDesc = CommonWidgetProperties.newBooleanPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "shadowsEnabled", "Shadows");
        shadowsEnabled = shadowsDesc.createProperty(this, true);
        properties.add(shadowsEnabled);

        WidgetPropertyDescriptor<Boolean> valueVisDesc = CommonWidgetProperties.newBooleanPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "valueVisible", "Value Visible");
        valueVisible = valueVisDesc.createProperty(this, true);
        properties.add(valueVisible);

        minValue = CommonWidgetProperties.propMinimum.createProperty(this, 0.0);
        properties.add(minValue);

        maxValue = CommonWidgetProperties.propMaximum.createProperty(this, 100.0);
        properties.add(maxValue);

        WidgetPropertyDescriptor<Boolean> animDesc = CommonWidgetProperties.newBooleanPropertyDescriptor(
                WidgetPropertyCategory.BEHAVIOR, "animated", "Animated");
        animated = animDesc.createProperty(this, true);
        properties.add(animated);
    }

    private WidgetProperty<WidgetColor> createColorProp(String name, String description, WidgetColor defaultValue) {
        WidgetPropertyDescriptor<WidgetColor> desc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, name, description);
        return desc.createProperty(this, defaultValue);
    }

    public WidgetProperty<WidgetColor> propBackgroundColor() { return backgroundColor; }
    public WidgetProperty<WidgetColor> propTitleColor() { return titleColor; }
    public WidgetProperty<WidgetColor> propValueColor() { return valueColor; }
    public WidgetProperty<WidgetColor> propUnitColor() { return unitColor; }
    public WidgetProperty<String> propTitle() { return title; }
    public WidgetProperty<String> propUnit() { return unit; }
    public WidgetProperty<WidgetFont> propFont() { return font; }
    public WidgetProperty<Integer> propDecimals() { return decimals; }
    public WidgetProperty<Boolean> propRoundedCorners() { return roundedCorners; }
    public WidgetProperty<Boolean> propShadowsEnabled() { return shadowsEnabled; }
    public WidgetProperty<Boolean> propValueVisible() { return valueVisible; }
    public WidgetProperty<Double> propMinValue() { return minValue; }
    public WidgetProperty<Double> propMaxValue() { return maxValue; }
    public WidgetProperty<Boolean> propAnimated() { return animated; }
}
