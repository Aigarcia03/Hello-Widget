package com.ceos.widgetfx;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GenericTile extends Pane implements TileNode {

    private Tile tile;

    public GenericTile(SkinType skinType) {
        tile = TileBuilder.create()
                          .prefSize(150, 150)
                          .skinType(skinType)
                          .title("Tile")
                          .unit("")
                          .build();
        this.getChildren().add(tile);
    }

    @Override public void setTitle(String title) { tile.setTitle(title); }
    @Override public void setUnit(String unit) { tile.setUnit(unit); }
    @Override public void setBackgroundColor(Color color) { tile.setBackgroundColor(color); }
    @Override public void setTitleColor(Color color) { tile.setTitleColor(color); }
    @Override public void setValueColor(Color color) { tile.setValueColor(color); }
    @Override public void setUnitColor(Color color) { tile.setUnitColor(color); }
    @Override public void setNeedleColor(Color color) { tile.setNeedleColor(color); }
    @Override public void setBarColor(Color color) { tile.setBarColor(color); }
    @Override public void setBarBackgroundColor(Color color) { tile.setBarBackgroundColor(color); }
    @Override public void setTickMarkColor(Color color) { tile.setTickMarkColor(color); }
    @Override public void setTickLabelColor(Color color) { tile.setTickLabelColor(color); }
    @Override public void setHourColor(Color color) { tile.setHourColor(color); }
    @Override public void setMinuteColor(Color color) { tile.setMinuteColor(color); }
    @Override public void setSecondColor(Color color) { tile.setSecondColor(color); }
    @Override public void setKnobColor(Color color) { tile.setKnobColor(color); }
    @Override public void setForegroundColor(Color color) { tile.setForegroundColor(color); }
    @Override public void setCustomFont(Font font) { tile.setCustomFont(font); tile.setCustomFontEnabled(true); }
    @Override public void setCustomFontEnabled(boolean enabled) { tile.setCustomFontEnabled(enabled); }
    @Override public void setDecimals(int decimals) { tile.setDecimals(decimals); }
    @Override public void setRoundedCorners(boolean rounded) { tile.setRoundedCorners(rounded); }
    @Override public void setShadowsEnabled(boolean shadows) { tile.setShadowsEnabled(shadows); }
    @Override public void setValueVisible(boolean visible) { tile.setValueVisible(visible); }
    @Override public void setMinValue(double value) { tile.setMinValue(value); }
    @Override public void setMaxValue(double value) { tile.setMaxValue(value); }
    @Override public void setThreshold(double threshold) { tile.setThreshold(threshold); }
    @Override public void setThresholdVisible(boolean visible) { tile.setThresholdVisible(visible); }
    @Override public void setAnimated(boolean animated) { tile.setAnimated(animated); }
    @Override public void setValue(double value) { tile.setValue(value); }
    @Override public void setChartData(String name, double value) { tile.setChartData(new ChartData(name, value)); }
    @Override public void addChartData(String name, double value) { tile.addChartData(new ChartData(name, value)); }
    @Override public void clearChartData() { tile.clearChartData(); }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        double w = getWidth();
        double h = getHeight();
        if (w > 0 && h > 0) {
            tile.setPrefSize(w, h);
            tile.resize(w, h);
        }
    }
}
