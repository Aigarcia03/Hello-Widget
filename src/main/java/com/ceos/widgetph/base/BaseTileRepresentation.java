package com.ceos.widgetph.base;

import org.csstudio.display.builder.model.DirtyFlag;
import org.csstudio.display.builder.model.UntypedWidgetPropertyListener;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.representation.javafx.JFXUtil;
import org.csstudio.display.builder.representation.javafx.widgets.JFXBaseRepresentation;
import org.epics.vtype.Alarm;
import org.epics.vtype.AlarmSeverity;
import org.epics.vtype.VType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
        model.propBorderAlarmSensitive().addUntypedPropertyListener(listener);
        model.propAlarmColor().addUntypedPropertyListener(listener);
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
        updateAlarmBorder();
    }

    private void updateAlarmBorder() {
        if (model_widget.propBorderAlarmSensitive().getValue()) {
            VType vtype = model_widget.runtimePropValue().getValue();
            if (vtype != null) {
                Alarm alarm = Alarm.alarmOf(vtype);
                if (alarm != null && alarm.getSeverity() != AlarmSeverity.NONE
                        && alarm.getSeverity() != AlarmSeverity.UNDEFINED) {
                    Color alarmColor = JFXUtil.convert(model_widget.propAlarmColor().getValue());
                    boolean rounded = model_widget.propRoundedCorners().getValue();
                    CornerRadii radii = rounded ? new CornerRadii(8) : CornerRadii.EMPTY;
                    jfx_node.setBorder(new Border(new BorderStroke(alarmColor, BorderStrokeStyle.SOLID, radii, new BorderWidths(3))));
                    return;
                }
            }
        }
        jfx_node.setBorder(null);
    }
}
