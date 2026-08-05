package com.ceos.widgetph.circularprogress;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class CircularProgressTileDescriptor extends WidgetDescriptor {

    public CircularProgressTileDescriptor() {
        super(CircularProgressTileWidget.WIDGET_TYPE,
              WidgetCategory.MONITOR,
              "Circular Progress",
              "/ui-progress-bar.png",
              "Progreso circular.");
    }

    @Override
    public Widget createWidget() { return new CircularProgressTileWidget(); }

    @Override
    public String getType()      { return CircularProgressTileWidget.WIDGET_TYPE; }

    @Override
    public String getName()      { return "Circular Progress Tile"; }

    @Override
    public WidgetCategory getCategory() { return WidgetCategory.MONITOR; }

    @Override
    public String getDescription() { return "Progreso circular."; }
}
