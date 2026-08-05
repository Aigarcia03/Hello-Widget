package com.ceos.widgetph.linechart;

import org.epics.vtype.VNumber;
import org.epics.vtype.VType;
import org.csstudio.display.builder.representation.javafx.JFXUtil;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.chart.TilesFXSeries;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.paint.Color;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class LineChartTileRepresentation extends BaseTileRepresentation<GenericTile, LineChartTileWidget> {

    private static final int MAX_POINTS = 50;
    private int dataCount = 0;
    private Series<String, Number> series;

    @Override
    protected GenericTile createJFXNode() throws Exception {
        series = new Series<>();
        series.setName("Value");
        Color seriesColor = JFXUtil.convert(model_widget.propSeriesColor().getValue());
        TilesFXSeries<String, Number> tilesFXSeries = new TilesFXSeries<>(series, seriesColor);
        GenericTile node = new GenericTile(SkinType.SMOOTHED_CHART, tilesFXSeries);
        return node;
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        model_widget.propAxisTextColor().addUntypedPropertyListener(listener);
        model_widget.propSeriesColor().addUntypedPropertyListener(listener);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
            jfx_node.setAxisTextColor(JFXUtil.convert(model_widget.propAxisTextColor().getValue()));
            jfx_node.setTilesFXSeries(new TilesFXSeries<>(series, JFXUtil.convert(model_widget.propSeriesColor().getValue())));
        }

        VType vtype = model_widget.runtimePropValue().getValue();
        if (vtype instanceof VNumber) {
            double value = ((VNumber) vtype).getValue().doubleValue();
            if (dataCount >= MAX_POINTS) {
                series.getData().clear();
                dataCount = 0;
            }
            series.getData().add(new XYChart.Data<>(Integer.toString(dataCount++), value));
        }
    }
}
