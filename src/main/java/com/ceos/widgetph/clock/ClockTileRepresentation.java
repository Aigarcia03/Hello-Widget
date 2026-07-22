package com.ceos.widgetph.clock;

import eu.hansolo.tilesfx.Tile.SkinType;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class ClockTileRepresentation extends BaseTileRepresentation<GenericTile, ClockTileWidget> {

    @Override
    protected GenericTile createJFXNode() throws Exception {
        return new GenericTile(SkinType.CLOCK);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
        }
    }
}
