package com.ceos.widgetph.radialchart;

import org.epics.vtype.VNumber;
import org.epics.vtype.VType;
import eu.hansolo.tilesfx.Tile.SkinType;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class RadialChartTileRepresentation extends BaseTileRepresentation<GenericTile, RadialChartTileWidget> {

    @Override
    protected GenericTile createJFXNode() throws Exception {
        GenericTile node = new GenericTile(SkinType.RADIAL_CHART);
        node.setChartData("Value", 0);
        return node;
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
        }

        VType vtype = model_widget.runtimePropValue().getValue();
        if (vtype instanceof VNumber) {
            double value = ((VNumber) vtype).getValue().doubleValue();
            jfx_node.setChartData("Value", value);
        }
    }
}
