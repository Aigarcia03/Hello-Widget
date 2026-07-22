package com.ceos.widgetfx;

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
    void setValueVisible(boolean visible);
    void setMinValue(double value);
    void setMaxValue(double value);
    void setThreshold(double threshold);
    void setThresholdVisible(boolean visible);
    void setAnimated(boolean animated);
    void setValue(double value);
    void setChartData(String name, double value);
    void addChartData(String name, double value);
    void clearChartData();
}
