package com.ceos.widgetph.radialchart;

import org.csstudio.display.builder.model.util.VTypeUtil;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.representation.javafx.JFXUtil;
import org.epics.vtype.VType;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.chart.ChartData;
import javafx.scene.paint.Color;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class RadialChartTileRepresentation extends BaseTileRepresentation<GenericTile, RadialChartTileWidget> {

    private final ChartData[] valueData = new ChartData[6];

    @Override
    protected GenericTile createJFXNode() throws Exception {
        for (int i = 0; i < valueData.length; i++) {
            valueData[i] = new ChartData("", 0, Color.web("#0072B2"));
        }
        GenericTile node = new GenericTile(SkinType.RADIAL_CHART);
        node.setChartData(valueData);
        return node;
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        model_widget.runtimeValue1().addUntypedPropertyListener(listener);
        model_widget.runtimeValue2().addUntypedPropertyListener(listener);
        model_widget.runtimeValue3().addUntypedPropertyListener(listener);
        model_widget.runtimeValue4().addUntypedPropertyListener(listener);
        model_widget.runtimeValue5().addUntypedPropertyListener(listener);
        model_widget.runtimeValue6().addUntypedPropertyListener(listener);
        model_widget.propColor1().addUntypedPropertyListener(listener);
        model_widget.propColor2().addUntypedPropertyListener(listener);
        model_widget.propColor3().addUntypedPropertyListener(listener);
        model_widget.propColor4().addUntypedPropertyListener(listener);
        model_widget.propColor5().addUntypedPropertyListener(listener);
        model_widget.propColor6().addUntypedPropertyListener(listener);
        model_widget.propLineTextColor().addUntypedPropertyListener(listener);
    }

    @Override
    protected void unregisterListeners() {
        super.unregisterListeners();
        model_widget.runtimeValue1().removePropertyListener(listener);
        model_widget.runtimeValue2().removePropertyListener(listener);
        model_widget.runtimeValue3().removePropertyListener(listener);
        model_widget.runtimeValue4().removePropertyListener(listener);
        model_widget.runtimeValue5().removePropertyListener(listener);
        model_widget.runtimeValue6().removePropertyListener(listener);
        model_widget.propColor1().removePropertyListener(listener);
        model_widget.propColor2().removePropertyListener(listener);
        model_widget.propColor3().removePropertyListener(listener);
        model_widget.propColor4().removePropertyListener(listener);
        model_widget.propColor5().removePropertyListener(listener);
        model_widget.propColor6().removePropertyListener(listener);
        model_widget.propLineTextColor().removePropertyListener(listener);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
            jfx_node.setTextColor(JFXUtil.convert(model_widget.propLineTextColor().getValue()));
            valueData[0].setFillColor(JFXUtil.convert(model_widget.propColor1().getValue()));
            valueData[1].setFillColor(JFXUtil.convert(model_widget.propColor2().getValue()));
            valueData[2].setFillColor(JFXUtil.convert(model_widget.propColor3().getValue()));
            valueData[3].setFillColor(JFXUtil.convert(model_widget.propColor4().getValue()));
            valueData[4].setFillColor(JFXUtil.convert(model_widget.propColor5().getValue()));
            valueData[5].setFillColor(JFXUtil.convert(model_widget.propColor6().getValue()));
        }

        valueData[0].setValue(sectorValue(model_widget.runtimeValue1()));
        valueData[1].setValue(sectorValue(model_widget.runtimeValue2()));
        valueData[2].setValue(sectorValue(model_widget.runtimeValue3()));
        valueData[3].setValue(sectorValue(model_widget.runtimeValue4()));
        valueData[4].setValue(sectorValue(model_widget.runtimeValue5()));
        valueData[5].setValue(sectorValue(model_widget.runtimeValue6()));
    }

    private static double sectorValue(final WidgetProperty<VType> runtime) {
        if (runtime.getValue() != null) {
            final Number num = VTypeUtil.getValueNumber(runtime.getValue());
            if (num != null && !Double.isNaN(num.doubleValue())) {
                return num.doubleValue();
            }
        }
        return 0.0;
    }
}
