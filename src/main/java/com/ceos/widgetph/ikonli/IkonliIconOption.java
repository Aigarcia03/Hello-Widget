package com.ceos.widgetph.ikonli;

public enum IkonliIconOption {
    HOME("Home", "fas-home"),
    STAR("Star", "fas-star"),
    HEART("Heart", "fas-heart"),
    COG("Cog", "fas-cog"),
    USER("User", "fas-user"),
    PLAY("Play", "fas-play"),
    STOP("Stop", "fas-stop"),
    PAUSE("Pause", "fas-pause"),
    BELL("Bell", "fas-bell"),
    LIGHTBULB("Lightbulb", "fas-lightbulb"),
    THMOMETER("Thermometer", "fas-thermometer-half"),
    TACHOMETER("Tachometer", "fas-tachometer-alt"),
    CHART_BAR("Chart Bar", "fas-chart-bar"),
    CHECK("Check", "fas-check-circle"),
    TIMES("Times", "fas-times-circle"),
    WARNING("Warning", "fas-exclamation-triangle"),
    FAN("Fan", "mdi-fan"),
    PUMP("Pump", "mdi-pump"),
    VALVE("Valve", "mdi-valve"),
    POWER("Power", "mdi-power"),
    ALARM("Alarm", "mdi-alarm"),
    ALERT("Alert", "mdi-alert"),
    PLUS("Plus", "mdi-plus"),
    MINUS("Minus", "mdi-minus"),
    ARROW_UP("Arrow Up", "mdi-arrow-up"),
    ARROW_DOWN("Arrow Down", "mdi-arrow-down"),
    SETTINGS("Settings", "mdi-cog"),
    CHECK_CIRCLE("Check Circle", "mdi-check-circle"),
    LOCK("Lock", "mdi-lock"),
    REFRESH("Refresh", "mdi-refresh");

    private final String displayName;
    private final String iconLiteral;

    IkonliIconOption(String displayName, String iconLiteral) {
        this.displayName = displayName;
        this.iconLiteral = iconLiteral;
    }

    public String getIconLiteral() { return iconLiteral; }

    @Override
    public String toString() { return displayName; }
}
