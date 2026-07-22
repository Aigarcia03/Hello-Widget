package com.ceos.widgetph.areachart;

import java.util.Random;
import org.epics.vtype.VNumber;
import org.epics.vtype.VType;
import eu.hansolo.tilesfx.Tile.SkinType;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class AreaChartTileRepresentation extends BaseTileRepresentation<GenericTile, AreaChartTileWidget> {

    private static final int MAX_POINTS = 50;
    private int dataCount = 0;

    @Override
    protected GenericTile createJFXNode() throws Exception {
        GenericTile node = new GenericTile(SkinType.SMOOTH_AREA_CHART);
        Random rnd = new Random();
        for (int i = 0; i < 20; i++) {
            node.addChartData("Value", rnd.nextDouble() * 100);
        }
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
            if (dataCount >= MAX_POINTS) {
                jfx_node.clearChartData();
                dataCount = 0;
            }
            jfx_node.addChartData("Value", value);
            dataCount++;
        }
    }
}
