package com.ceos.widgetph.sparkline;

import org.csstudio.display.builder.representation.javafx.JFXUtil;
import eu.hansolo.tilesfx.Tile.SkinType;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class SparklineTileRepresentation extends BaseTileRepresentation<GenericTile, SparklineTileWidget> {

    @Override
    protected GenericTile createJFXNode() throws Exception {
        return new GenericTile(SkinType.SPARK_LINE);
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        model_widget.propLineColor().addUntypedPropertyListener(listener);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
            jfx_node.setBarColor(JFXUtil.convert(model_widget.propLineColor().getValue()));
        }
    }
}
