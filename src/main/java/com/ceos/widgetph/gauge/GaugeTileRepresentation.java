package com.ceos.widgetph.gauge;

import eu.hansolo.tilesfx.Tile.SkinType;
import org.csstudio.display.builder.representation.javafx.JFXUtil;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class GaugeTileRepresentation extends BaseTileRepresentation<GenericTile, GaugeTileWidget> {

    @Override
    protected GenericTile createJFXNode() throws Exception {
        return new GenericTile(SkinType.GAUGE);
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        final GaugeTileWidget model = model_widget;
        model.propNeedleColor().addUntypedPropertyListener(listener);
        model.propBarColor().addUntypedPropertyListener(listener);
        model.propBarBackgroundColor().addUntypedPropertyListener(listener);
        model.propThreshold().addUntypedPropertyListener(listener);
        model.propThresholdVisible().addUntypedPropertyListener(listener);
        model.propThresholdColor().addUntypedPropertyListener(listener);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
            applyGaugeProperties();
        }
    }

    private void applyGaugeProperties() {
        jfx_node.setNeedleColor(JFXUtil.convert(model_widget.propNeedleColor().getValue()));
        jfx_node.setBarColor(JFXUtil.convert(model_widget.propBarColor().getValue()));
        jfx_node.setBarBackgroundColor(JFXUtil.convert(model_widget.propBarBackgroundColor().getValue()));
        jfx_node.setThreshold(model_widget.propThreshold().getValue());
        jfx_node.setThresholdVisible(model_widget.propThresholdVisible().getValue());
        jfx_node.setThresholdColor(JFXUtil.convert(model_widget.propThresholdColor().getValue()));
    }
}
