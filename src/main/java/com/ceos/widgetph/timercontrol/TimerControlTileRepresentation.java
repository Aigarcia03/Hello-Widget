package com.ceos.widgetph.timercontrol;

import eu.hansolo.tilesfx.Tile.SkinType;
import org.csstudio.display.builder.representation.javafx.JFXUtil;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class TimerControlTileRepresentation extends BaseTileRepresentation<GenericTile, TimerControlTileWidget> {

    @Override
    protected GenericTile createJFXNode() throws Exception {
        return new GenericTile(SkinType.TIMER_CONTROL);
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        final TimerControlTileWidget model = model_widget;
        model.propHourColor().addUntypedPropertyListener(listener);
        model.propMinuteColor().addUntypedPropertyListener(listener);
        model.propSecondColor().addUntypedPropertyListener(listener);
        model.propKnobColor().addUntypedPropertyListener(listener);
        model.propForegroundColor().addUntypedPropertyListener(listener);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
            applyTimerColors();
        }
    }

    private void applyTimerColors() {
        jfx_node.setHourColor(JFXUtil.convert(model_widget.propHourColor().getValue()));
        jfx_node.setMinuteColor(JFXUtil.convert(model_widget.propMinuteColor().getValue()));
        jfx_node.setSecondColor(JFXUtil.convert(model_widget.propSecondColor().getValue()));
        jfx_node.setKnobColor(JFXUtil.convert(model_widget.propKnobColor().getValue()));
        jfx_node.setForegroundColor(JFXUtil.convert(model_widget.propForegroundColor().getValue()));
    }
}
