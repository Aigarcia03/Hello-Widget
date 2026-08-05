package com.ceos.widgetfx;

import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.chart.RadarChart;
import eu.hansolo.tilesfx.chart.RadarChartMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class RadarChartNode extends Pane {
    private final RadarChart chart;

    public RadarChartNode() {
        chart = new RadarChart();
        chart.setMouseTransparent(true);
        this.getChildren().add(chart);
    }

    public void initSectors(int n) {
        chart.setNoOfSectors(n);
        chart.getData().clear();
        for (int i = 0; i < n; i++) {
            chart.getData().add(new ChartData("S" + (i + 1), 0));
        }
        redraw();
    }

    public void setSectorValue(int index, double value) {
        if (index >= 0 && index < chart.getData().size()) {
            chart.getData().get(index).setValue(value);
            chart.redraw();
        }
    }

    public void setMode(RadarChartMode mode) {
        chart.setMode(mode);
    }

    public void setFillColor(Color color) {
        Paint current = chart.getChartFill();
        if (!color.equals(current)) {
            chart.setChartFill(color);
        }
    }

    public void setGridColor(Color color) {
        Color current = chart.getGridColor();
        if (!color.equals(current)) {
            chart.setGridColor(color);
        }
    }

    public void redraw() {
        chart.redraw();
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        chart.setPrefSize(getWidth(), getHeight());
        chart.resizeRelocate(0, 0, getWidth(), getHeight());
    }
}