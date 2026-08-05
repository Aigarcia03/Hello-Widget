package com.ceos.widgetph.ikonli;

import java.util.List;
import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.csstudio.display.builder.model.properties.EnumWidgetProperty;
import org.phoebus.ui.color.WidgetColor;
import com.ceos.widgetph.base.BaseTileWidget;

public class IkonliIconWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "ikonliicon";

    private WidgetProperty<IkonliIconOption> iconOption;
    private WidgetProperty<Integer> iconSize;
    private WidgetProperty<WidgetColor> iconColor;

    public IkonliIconWidget() {
        super(WIDGET_TYPE);
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);

        WidgetPropertyDescriptor<IkonliIconOption> optDesc = new WidgetPropertyDescriptor<>(
                WidgetPropertyCategory.DISPLAY, "iconOption", "Icon") {
            @Override
            public WidgetProperty<IkonliIconOption> createProperty(Widget widget, IkonliIconOption defaultValue) {
                return new EnumWidgetProperty<>(this, widget, defaultValue);
            }
        };
        iconOption = optDesc.createProperty(this, IkonliIconOption.HOME);
        properties.add(iconOption);

        WidgetPropertyDescriptor<Integer> sizeDesc = CommonWidgetProperties.newIntegerPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "iconSize", "Icon Size");
        iconSize = sizeDesc.createProperty(this, 48);
        properties.add(iconSize);

        WidgetPropertyDescriptor<WidgetColor> colorDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "iconColor", "Icon Color");
        iconColor = colorDesc.createProperty(this, new WidgetColor(0, 0, 0));
        properties.add(iconColor);
    }

    public WidgetProperty<IkonliIconOption> propIconOption() { return iconOption; }
    public WidgetProperty<Integer> propIconSize() { return iconSize; }
    public WidgetProperty<WidgetColor> propIconColor() { return iconColor; }
}
