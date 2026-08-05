package com.ceos.widgetph.plusminus;

import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.phoebus.ui.color.WidgetColor;
import com.ceos.widgetph.base.BaseTileWidget;

public class PlusMinusTileWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "plusminus";

    private WidgetProperty<WidgetColor> buttonColor;

    public PlusMinusTileWidget() {
        super(WIDGET_TYPE);
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);

        WidgetPropertyDescriptor<WidgetColor> desc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "buttonColor", "Button Color");
        buttonColor = desc.createProperty(this, new WidgetColor(223, 223, 223));
        properties.add(buttonColor);
    }

    public WidgetProperty<WidgetColor> propButtonColor() { return buttonColor; }
}
