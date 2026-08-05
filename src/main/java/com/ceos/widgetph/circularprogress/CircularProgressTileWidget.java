package com.ceos.widgetph.circularprogress;

import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.phoebus.ui.color.WidgetColor;
import com.ceos.widgetph.base.BaseTileWidget;

public class CircularProgressTileWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "circularprogress";

    private WidgetProperty<WidgetColor> barColor;

    public CircularProgressTileWidget() {
        super(WIDGET_TYPE);
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);

        WidgetPropertyDescriptor<WidgetColor> barColorDesc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "barColor", "Bar Color");
        barColor = barColorDesc.createProperty(this, new WidgetColor(0, 198, 211));
        properties.add(barColor);
    }

    public WidgetProperty<WidgetColor> propBarColor() { return barColor; }
}
