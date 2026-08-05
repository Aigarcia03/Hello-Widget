package com.ceos.widgetfx;

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.Gauge.SkinType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MedusaGaugeNode extends Pane implements TileNode {

    private Gauge gauge;

    public MedusaGaugeNode(SkinType skinType) {
        gauge = new Gauge(skinType);
        gauge.setValue(0);
        gauge.setAnimated(false);
        gauge.setKeepAspect(false);
        gauge.setBackgroundPaint(Color.BLACK);
        gauge.setValueColor(Color.WHITE);
        gauge.setUnitColor(Color.GRAY);
        gauge.setTitleColor(Color.WHITE);
        gauge.setMouseTransparent(true);
        this.getChildren().add(gauge);
    }

    @Override public void setTitle(String title) { gauge.setTitle(title); }
    @Override public void setUnit(String unit) { gauge.setUnit(unit); }
    @Override public void setBackgroundColor(Color color) { gauge.setBackgroundPaint(color); }
    @Override public void setTitleColor(Color color) { gauge.setTitleColor(color); }
    @Override public void setValueColor(Color color) { gauge.setValueColor(color); }
    @Override public void setUnitColor(Color color) { gauge.setUnitColor(color); }
    @Override public void setNeedleColor(Color color) { gauge.setNeedleColor(color); }
    @Override public void setBarColor(Color color) { gauge.setBarColor(color); }
    @Override public void setBarBackgroundColor(Color color) { gauge.setBarBackgroundColor(color); }
    @Override public void setTickMarkColor(Color color) { gauge.setTickMarkColor(color); }
    @Override public void setTickLabelColor(Color color) { gauge.setTickLabelColor(color); }
    @Override public void setHourColor(Color color) { }
    @Override public void setMinuteColor(Color color) { }
    @Override public void setSecondColor(Color color) { }
    @Override public void setKnobColor(Color color) { gauge.setKnobColor(color); }
    @Override public void setForegroundColor(Color color) { gauge.setForegroundPaint(color); }
    @Override public void setCustomFont(Font font) { gauge.setCustomFont(font); gauge.setCustomFontEnabled(true); }
    @Override public void setCustomFontEnabled(boolean enabled) { gauge.setCustomFontEnabled(enabled); }
    @Override public void setDecimals(int decimals) { gauge.setDecimals(decimals); }
    @Override public void setRoundedCorners(boolean rounded) { }
    @Override public void setShadowsEnabled(boolean shadows) { }
    @Override public void setValueVisible(boolean visible) { gauge.setValueVisible(visible); }
    @Override public void setMinValue(double value) { gauge.setMinValue(value); }
    @Override public void setMaxValue(double value) { gauge.setMaxValue(value); }
    @Override public void setThreshold(double threshold) { gauge.setThreshold(threshold); }
    @Override public void setThresholdColor(Color color) { gauge.setThresholdColor(color); }
    @Override public void setThresholdVisible(boolean visible) { gauge.setThresholdVisible(visible); }
    @Override public void setAnimated(boolean animated) { gauge.setAnimated(animated); }
    @Override public void setValue(double value) { gauge.setValue(value); }
    @Override public void setChartData(String name, double value) { }
    @Override public void addChartData(String name, double value) { }
    @Override public void clearChartData() { }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        double w = getWidth();
        double h = getHeight();
        if (w > 0 && h > 0) {
            gauge.setPrefSize(w, h);
            gauge.resize(w, h);
            gauge.relocate(0, 0);
        }
    }
}
