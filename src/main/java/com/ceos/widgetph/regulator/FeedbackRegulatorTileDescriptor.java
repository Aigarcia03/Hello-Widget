package com.ceos.widgetph.regulator;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class FeedbackRegulatorTileDescriptor extends WidgetDescriptor {

    public FeedbackRegulatorTileDescriptor() {
        super(FeedbackRegulatorTileWidget.WIDGET_TYPE, WidgetCategory.CONTROL, "Custom", "/plugin.png", "Feedback Regulator control widget");
    }

    @Override
    public Widget createWidget() { return new FeedbackRegulatorTileWidget(); }

    @Override
    public String getType() { return FeedbackRegulatorTileWidget.WIDGET_TYPE; }

    @Override
    public String getName() { return "Feedback Regulator Tile"; }

    @Override
    public WidgetCategory getCategory() { return WidgetCategory.CONTROL; }

    @Override
    public String getDescription() { return "Feedback regulator tile widget."; }
}
