package com.ceos.widgetfx;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.chart.TilesFXSeries;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GenericTile extends Pane implements TileNode {

    private Tile tile;

    public GenericTile(SkinType skinType, TilesFXSeries<String, Number>... series) {
        this(false, false, skinType, series);
    }

    public GenericTile(boolean smoothing, SkinType skinType, TilesFXSeries<String, Number>... series) {
        this(smoothing, false, skinType, series);
    }

    public GenericTile(boolean smoothing, boolean area, SkinType skinType, TilesFXSeries<String, Number>... series) {
        TileBuilder builder = TileBuilder.create()
                                         .prefSize(200, 200)
                                         .skinType(skinType)
                                         .title("Tile")
                                         .unit("")
                                         .smoothing(smoothing);
        if (area) {
            builder.chartType(eu.hansolo.tilesfx.Tile.ChartType.AREA);
        }
        if (series != null && series.length > 0) {
            builder.tilesFxSeries(series);
        }
        tile = builder.build();
        tile.setMouseTransparent(true);
        this.getChildren().add(tile);
    }

    public void setTileMouseTransparent(boolean mouseTransparent) {
        tile.setMouseTransparent(mouseTransparent);
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
    public void setActiveColor(Color color) { tile.setActiveColor(color); }
    @Override public void setCustomFont(Font font) { tile.setCustomFont(font); tile.setCustomFontEnabled(true); }
    @Override public void setCustomFontEnabled(boolean enabled) { tile.setCustomFontEnabled(enabled); }
    @Override public void setDecimals(int decimals) { tile.setDecimals(decimals); }
    @Override public void setRoundedCorners(boolean rounded) { tile.setRoundedCorners(rounded); }
    @Override public void setShadowsEnabled(boolean shadows) { tile.setShadowsEnabled(shadows); }
    @Override public void setDateColor(Color color) { tile.setDateColor(color); }
    @Override public void setTextColor(Color color) { tile.setTextColor(color); }
    @Override public void setChartGridColor(Color color) { tile.setChartGridColor(color); }
    @Override public void setChartTypeArea() { tile.setChartType(eu.hansolo.tilesfx.Tile.ChartType.AREA); }
    @Override public void setSmoothing(boolean smooth) { tile.setSmoothing(smooth); }
    @Override public void setAxisTextColor(Color color) {
        tile.getXAxis().setTickLabelFill(color);
        tile.getYAxis().setTickLabelFill(color);
    }
    @Override public void setTilesFXSeries(TilesFXSeries<String, Number>... series) { tile.setTilesFXSeries(series); }
    @Override public void setValueVisible(boolean visible) { tile.setValueVisible(visible); }
    @Override public void setMinValue(double value) { tile.setMinValue(value); }
    @Override public void setMaxValue(double value) { tile.setMaxValue(value); }
    @Override public void setThreshold(double threshold) { tile.setThreshold(threshold); }
    @Override public void setThresholdVisible(boolean visible) { tile.setThresholdVisible(visible); }
    @Override public void setThresholdColor(Color color) { tile.setThresholdColor(color); }
    @Override public void setAnimated(boolean animated) { tile.setAnimated(animated); }
    @Override public void setAlarmColor(Color color) { tile.setAlarmColor(color); }
    @Override public void setValue(double value) { tile.setValue(value); }
    public double getValue() { return tile.getValue(); }
    public void addValueListener(javafx.beans.InvalidationListener listener) { tile.valueProperty().addListener(listener); }
    public void removeValueListener(javafx.beans.InvalidationListener listener) { tile.valueProperty().removeListener(listener); }
    public void setRunning(boolean running) { tile.setRunning(running); }
    public void setTime(long millis) { tile.setTime(millis); }
    public void setTime(java.time.ZonedDateTime time) { tile.setTime(time); }
    @Override public void setChartData(String name, double value) { tile.setChartData(new ChartData(name, value)); }
    public void setChartData(ChartData... data) { tile.setChartData(data); }
    @Override public void addChartData(String name, double value) { tile.addChartData(new ChartData(name, value)); }
    @Override public void addChartData(String name, double value, javafx.scene.paint.Color color) {
        tile.addChartData(new ChartData(name, value, color));
    }
    @Override public void addChartData(String name, double value, javafx.scene.paint.Color color, javafx.scene.paint.Color textColor) {
        ChartData chartData = new ChartData(name, value, color);
        chartData.setTextColor(textColor);
        tile.addChartData(chartData);
    }
    @Override public void clearChartData() { tile.clearChartData(); }

    @Override public void setBarChartItems(java.util.List<eu.hansolo.tilesfx.skins.BarChartItem> items) {
        tile.setBarChartItems(items);
    }
    @Override public void addBarChartItem(eu.hansolo.tilesfx.skins.BarChartItem item) {
        tile.addBarChartItem(item);
    }
    @Override public void removeBarChartItem(eu.hansolo.tilesfx.skins.BarChartItem item) {
        tile.removeBarChartItem(item);
    }
    @Override public void clearBarChartItems() {
        tile.clearBarChartItems();
    }
    @Override public void setLeaderBoardItems(java.util.List<eu.hansolo.tilesfx.skins.LeaderBoardItem> items) {
        tile.setLeaderBoardItems(items);
    }
    @Override public void addLeaderBoardItem(eu.hansolo.tilesfx.skins.LeaderBoardItem item) {
        tile.addLeaderBoardItem(item);
    }
    @Override public void removeLeaderBoardItem(eu.hansolo.tilesfx.skins.LeaderBoardItem item) {
        tile.removeLeaderBoardItem(item);
    }
    @Override public void clearLeaderBoardItems() {
        tile.clearLeaderBoardItems();
    }
    @Override public void setItemSorting(eu.hansolo.tilesfx.Tile.ItemSorting sorting) { tile.setItemSorting(sorting); }
    @Override public void setItemSortingTopic(eu.hansolo.tilesfx.Tile.ItemSortingTopic topic) { tile.setItemSortingTopic(topic); }

    public void requestTileLayout() {
        tile.requestLayout();
    }

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
