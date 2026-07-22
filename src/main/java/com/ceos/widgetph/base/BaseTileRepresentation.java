package com.ceos.widgetph.base;

import org.csstudio.display.builder.model.DirtyFlag;
import org.csstudio.display.builder.model.UntypedWidgetPropertyListener;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.representation.javafx.widgets.JFXBaseRepresentation;
import javafx.scene.layout.Pane;

public abstract class BaseTileRepresentation<N extends Pane, M extends BaseTileWidget>
        extends JFXBaseRepresentation<N, M> {

    protected DirtyFlag dirty_look = new DirtyFlag();
    protected final UntypedWidgetPropertyListener listener = this::manage;

    @Override
    protected void registerListeners() {
        super.registerListeners();

        final M model = model_widget;
        model.propTitle().addUntypedPropertyListener(listener);
        model.propUnit().addUntypedPropertyListener(listener);
        model.propWidth().addUntypedPropertyListener(listener);
        model.propHeight().addUntypedPropertyListener(listener);
        model.propBackgroundColor().addUntypedPropertyListener(listener);
        model.propTitleColor().addUntypedPropertyListener(listener);
        model.propValueColor().addUntypedPropertyListener(listener);
        model.propUnitColor().addUntypedPropertyListener(listener);
        model.propFont().addUntypedPropertyListener(listener);
        model.propDecimals().addUntypedPropertyListener(listener);
        model.propRoundedCorners().addUntypedPropertyListener(listener);
        model.propShadowsEnabled().addUntypedPropertyListener(listener);
        model.propValueVisible().addUntypedPropertyListener(listener);
        model.propMinValue().addUntypedPropertyListener(listener);
        model.propMaxValue().addUntypedPropertyListener(listener);
        model.propAnimated().addUntypedPropertyListener(listener);
        model.runtimePropValue().addUntypedPropertyListener(listener);
    }

    private void manage(final WidgetProperty<?> prop, final Object old, final Object val) {
        dirty_look.mark();
        toolkit.scheduleUpdate(this);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        jfx_node.setPrefWidth(model_widget.propWidth().getValue());
        jfx_node.setPrefHeight(model_widget.propHeight().getValue());
    }
}
