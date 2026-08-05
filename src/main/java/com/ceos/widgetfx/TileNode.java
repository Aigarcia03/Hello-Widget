package com.ceos.widgetfx;

import eu.hansolo.tilesfx.chart.TilesFXSeries;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public interface TileNode {
    void setTitle(String title);
    void setUnit(String unit);
    void setBackgroundColor(Color color);
    void setTitleColor(Color color);
    void setValueColor(Color color);
    void setUnitColor(Color color);
    void setNeedleColor(Color color);
    void setBarColor(Color color);
    void setBarBackgroundColor(Color color);
    void setTickMarkColor(Color color);
    void setTickLabelColor(Color color);
    void setHourColor(Color color);
    void setMinuteColor(Color color);
    void setSecondColor(Color color);
    void setKnobColor(Color color);
    void setForegroundColor(Color color);
    void setCustomFont(Font font);
    void setCustomFontEnabled(boolean enabled);
    void setDecimals(int decimals);
    void setRoundedCorners(boolean rounded);
    void setShadowsEnabled(boolean shadows);
    default void setDateColor(Color color) {}
    default void setTextColor(Color color) {}
    default void setChartGridColor(Color color) {}
    default void setChartTypeArea() {}
    default void setSmoothing(boolean smooth) {}
    default void setAxisTextColor(Color color) {}
    @SuppressWarnings("unchecked")
    default void setTilesFXSeries(TilesFXSeries<String, Number>... series) {}
    void setValueVisible(boolean visible);
    void setMinValue(double value);
    void setMaxValue(double value);
    void setThreshold(double threshold);
    void setThresholdVisible(boolean visible);
    default void setThresholdColor(Color color) {}
    void setAnimated(boolean animated);
    default void setAlarmColor(Color color) {}
    void setValue(double value);
    void setChartData(String name, double value);
    void addChartData(String name, double value);
    default void addChartData(String name, double value, javafx.scene.paint.Color color) {
        addChartData(name, value);
    }
    default void addChartData(String name, double value, javafx.scene.paint.Color color, javafx.scene.paint.Color textColor) {
        addChartData(name, value);
    }
    void clearChartData();
    default void setBarChartItems(java.util.List<eu.hansolo.tilesfx.skins.BarChartItem> items) {}
    default void addBarChartItem(eu.hansolo.tilesfx.skins.BarChartItem item) {}
    default void removeBarChartItem(eu.hansolo.tilesfx.skins.BarChartItem item) {}
    default void clearBarChartItems() {}
    default void setLeaderBoardItems(java.util.List<eu.hansolo.tilesfx.skins.LeaderBoardItem> items) {}
    default void addLeaderBoardItem(eu.hansolo.tilesfx.skins.LeaderBoardItem item) {}
    default void removeLeaderBoardItem(eu.hansolo.tilesfx.skins.LeaderBoardItem item) {}
    default void clearLeaderBoardItems() {}
    default void setItemSorting(eu.hansolo.tilesfx.Tile.ItemSorting sorting) {}
    default void setItemSortingTopic(eu.hansolo.tilesfx.Tile.ItemSortingTopic topic) {}
}
