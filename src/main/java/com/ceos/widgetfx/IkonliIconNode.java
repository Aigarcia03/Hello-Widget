package com.ceos.widgetfx;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

public class IkonliIconNode extends Pane implements TileNode {

    private FontIcon icon;

    public IkonliIconNode() {
        icon = new FontIcon(FontAwesomeSolid.HOME);
        icon.setIconSize(48);
        icon.setIconColor(Color.BLACK);
        this.getChildren().add(icon);
    }

    public void setIcon(Ikon ikon) {
        icon.setIconCode(ikon);
    }

    public void setIconLiteral(String literal) {
        icon.setIconLiteral(literal);
    }

    public void setIconSize(int size) {
        icon.setIconSize(size);
    }

    public void setIconColor(Color color) {
        icon.setIconColor(color);
    }

    @Override public void setTitle(String title) { }
    @Override public void setUnit(String unit) { }
    @Override public void setBackgroundColor(Color color) { this.setStyle("-fx-background-color: #" + color.toString().substring(2, 8) + ";"); }
    @Override public void setTitleColor(Color color) { }
    @Override public void setValueColor(Color color) { }
    @Override public void setUnitColor(Color color) { }
    @Override public void setNeedleColor(Color color) { }
    @Override public void setBarColor(Color color) { }
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
    @Override public void setDecimals(int decimals) { }
    @Override public void setRoundedCorners(boolean rounded) { this.setStyle((rounded ? "-fx-background-radius: 10;" : "")); }
    @Override public void setShadowsEnabled(boolean shadows) { }
    @Override public void setValueVisible(boolean visible) { }
    @Override public void setMinValue(double value) { }
    @Override public void setMaxValue(double value) { }
    @Override public void setThreshold(double threshold) { }
    @Override public void setThresholdVisible(boolean visible) { }
    @Override public void setAnimated(boolean animated) { }
    @Override public void setValue(double value) { }
    @Override public void setChartData(String name, double value) { }
    @Override public void addChartData(String name, double value) { }
    @Override public void clearChartData() { }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        double w = getWidth();
        double h = getHeight();
        if (w > 0 && h > 0) {
            double size = Math.min(w, h);
            icon.setIconSize((int) (size * 0.6));
            icon.setX((w - icon.getLayoutBounds().getWidth()) / 2);
            icon.setY((h - icon.getLayoutBounds().getHeight()) / 2);
        }
    }
}
