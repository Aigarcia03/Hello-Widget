package com.ceos.widgetph.regulator;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class RegulatorTileDescriptor extends WidgetDescriptor {

    public RegulatorTileDescriptor() {
        super(RegulatorTileWidget.WIDGET_TYPE, WidgetCategory.CONTROL, "Custom", "/plugin.png", "Regulator control widget");
    }

    @Override
    public Widget createWidget() { return new RegulatorTileWidget(); }

    @Override
    public String getType() { return RegulatorTileWidget.WIDGET_TYPE; }

    @Override
    public String getName() { return "Regulator Tile"; }

    @Override
    public WidgetCategory getCategory() { return WidgetCategory.CONTROL; }

    @Override
    public String getDescription() { return "Regulator tile widget."; }
}
