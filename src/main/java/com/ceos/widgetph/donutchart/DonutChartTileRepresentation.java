package com.ceos.widgetph.donutchart;

import org.epics.vtype.VNumber;
import org.epics.vtype.VType;
import eu.hansolo.tilesfx.Tile.SkinType;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class DonutChartTileRepresentation extends BaseTileRepresentation<GenericTile, DonutChartTileWidget> {

    @Override
    protected GenericTile createJFXNode() throws Exception {
        GenericTile node = new GenericTile(SkinType.DONUT_CHART);
        node.setChartData("Seg1", 0);
        node.addChartData("Seg2", 0);
        node.addChartData("Seg3", 0);
        node.addChartData("Seg4", 0);
        return node;
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        final DonutChartTileWidget model = model_widget;
        model.propValue1().addUntypedPropertyListener(listener);
        model.propValue2().addUntypedPropertyListener(listener);
        model.propValue3().addUntypedPropertyListener(listener);
        model.propValue4().addUntypedPropertyListener(listener);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
        }

        DonutChartTileWidget model = model_widget;
        double v1 = model.propValue1().getValue();
        VType pv = model.runtimePropValue().getValue();
        if (pv instanceof VNumber) {
            v1 = ((VNumber) pv).getValue().doubleValue();
        }
        jfx_node.clearChartData();
        jfx_node.addChartData("Seg1", v1);
        jfx_node.addChartData("Seg2", model.propValue2().getValue());
        jfx_node.addChartData("Seg3", model.propValue3().getValue());
        jfx_node.addChartData("Seg4", model.propValue4().getValue());
    }
}
