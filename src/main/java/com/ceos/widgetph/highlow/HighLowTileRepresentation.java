package com.ceos.widgetph.highlow;

import eu.hansolo.tilesfx.Tile.SkinType;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class HighLowTileRepresentation extends BaseTileRepresentation<GenericTile, HighLowTileWidget> {

    @Override
    protected GenericTile createJFXNode() throws Exception {
        return new GenericTile(SkinType.HIGH_LOW);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
        }
    }
}
