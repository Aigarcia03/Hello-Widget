package com.ceos.widgetph.donutchart;

import eu.hansolo.tilesfx.Tile.SkinType;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.util.VTypeUtil;
import org.csstudio.display.builder.representation.javafx.JFXUtil;
import org.epics.vtype.VType;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class DonutChartTileRepresentation extends BaseTileRepresentation<GenericTile, DonutChartTileWidget> {

    @Override
    protected GenericTile createJFXNode() throws Exception {
        return new GenericTile(SkinType.DONUT_CHART);
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        final DonutChartTileWidget model = model_widget;
        model.runtimeValue1().addUntypedPropertyListener(listener);
        model.runtimeValue2().addUntypedPropertyListener(listener);
        model.runtimeValue3().addUntypedPropertyListener(listener);
        model.runtimeValue4().addUntypedPropertyListener(listener);
        model.propColor1().addUntypedPropertyListener(listener);
        model.propColor2().addUntypedPropertyListener(listener);
        model.propColor3().addUntypedPropertyListener(listener);
        model.propColor4().addUntypedPropertyListener(listener);
        model.propTextColor().addUntypedPropertyListener(listener);
        model.propTextColor1().addUntypedPropertyListener(listener);
        model.propTextColor2().addUntypedPropertyListener(listener);
        model.propTextColor3().addUntypedPropertyListener(listener);
        model.propTextColor4().addUntypedPropertyListener(listener);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
            jfx_node.setTextColor(JFXUtil.convert(model_widget.propTextColor().getValue()));
        }

        DonutChartTileWidget model = model_widget;
        jfx_node.clearChartData();
        jfx_node.addChartData("Seg1", segmentValue(model.runtimeValue1()), JFXUtil.convert(model.propColor1().getValue()), JFXUtil.convert(model.propTextColor1().getValue()));
        jfx_node.addChartData("Seg2", segmentValue(model.runtimeValue2()), JFXUtil.convert(model.propColor2().getValue()), JFXUtil.convert(model.propTextColor2().getValue()));
        jfx_node.addChartData("Seg3", segmentValue(model.runtimeValue3()), JFXUtil.convert(model.propColor3().getValue()), JFXUtil.convert(model.propTextColor3().getValue()));
        jfx_node.addChartData("Seg4", segmentValue(model.runtimeValue4()), JFXUtil.convert(model.propColor4().getValue()), JFXUtil.convert(model.propTextColor4().getValue()));
    }

    private static double segmentValue(final WidgetProperty<VType> runtime) {
        if (runtime.getValue() != null) {
            final Number num = VTypeUtil.getValueNumber(runtime.getValue());
            if (num != null && !Double.isNaN(num.doubleValue())) {
                return num.doubleValue();
            }
        }
        return 0.0;
    }
}
