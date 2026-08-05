package com.ceos.widgetfx;

import eu.hansolo.regulators.Regulator;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class RegulatorNode extends Pane implements TileNode {

    private Regulator regulator;

    public RegulatorNode() {
        setPrefSize(200, 200);
        regulator = new Regulator();
        regulator.setPrefSize(200, 200);
        regulator.setMinValue(0);
        regulator.setMaxValue(100);
        regulator.setTargetValue(0);
        regulator.setColor(Color.web("#c0c0c0"));
        regulator.setTextColor(Color.web("#333333"));
        this.getChildren().add(regulator);
    }

    public Regulator getRegulator() { return regulator; }

    @Override public void setTitle(String title) { }
    @Override public void setUnit(String unit) { regulator.setUnit(unit); }
    @Override public void setBackgroundColor(Color color) { }
    @Override public void setTitleColor(Color color) { }
    @Override public void setValueColor(Color color) { regulator.setTextColor(color); }
    @Override public void setUnitColor(Color color) { regulator.setTextColor(color); }
    @Override public void setNeedleColor(Color color) { }
    @Override public void setBarColor(Color color) { regulator.setBarColor(color); }
    @Override public void setBarBackgroundColor(Color color) { }
    @Override public void setTickMarkColor(Color color) { }
    @Override public void setTickLabelColor(Color color) { }
    @Override public void setHourColor(Color color) { }
    @Override public void setMinuteColor(Color color) { }
    @Override public void setSecondColor(Color color) { }
    @Override public void setKnobColor(Color color) { }
    @Override public void setForegroundColor(Color color) { }
    @Override public void setCustomFont(Font font) { }
    @Override public void setCustomFontEnabled(boolean enabled) { }
    @Override public void setDecimals(int decimals) { regulator.setDecimals(decimals); }
    @Override public void setRoundedCorners(boolean rounded) { }
    @Override public void setShadowsEnabled(boolean shadows) { }
    @Override public void setValueVisible(boolean visible) { }
    @Override public void setMinValue(double value) { regulator.setMinValue(value); }
    @Override public void setMaxValue(double value) { regulator.setMaxValue(value); }
    @Override public void setThreshold(double threshold) { }
    @Override public void setThresholdVisible(boolean visible) { }
    @Override public void setAnimated(boolean animated) { }
    @Override public void setValue(double value) { regulator.setTargetValue(value); }
    @Override public void setChartData(String name, double value) { }
    @Override public void addChartData(String name, double value) { }
    @Override public void clearChartData() { }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        double w = getWidth();
        double h = getHeight();
        if (w > 0 && h > 0) {
            regulator.setPrefSize(w, h);
            regulator.resize(w, h);
            regulator.relocate(0, 0);
        }
    }
}
