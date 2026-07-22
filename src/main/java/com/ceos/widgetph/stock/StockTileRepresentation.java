package com.ceos.widgetph.stock;

import eu.hansolo.tilesfx.Tile.SkinType;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class StockTileRepresentation extends BaseTileRepresentation<GenericTile, StockTileWidget> {

    @Override
    protected GenericTile createJFXNode() throws Exception {
        return new GenericTile(SkinType.STOCK);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
        }
    }
}
