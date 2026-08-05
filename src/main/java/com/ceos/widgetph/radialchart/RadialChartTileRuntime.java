package com.ceos.widgetph.radialchart;

import com.ceos.widgetph.runtime.MultiPVWidgetRuntime;

public class RadialChartTileRuntime extends MultiPVWidgetRuntime<RadialChartTileWidget> {

    @Override
    public void start() {
        super.start();
        final RadialChartTileWidget w = (RadialChartTileWidget) widget;
        connectPV(w.propPV1(), w.runtimeValue1());
        connectPV(w.propPV2(), w.runtimeValue2());
        connectPV(w.propPV3(), w.runtimeValue3());
        connectPV(w.propPV4(), w.runtimeValue4());
        connectPV(w.propPV5(), w.runtimeValue5());
        connectPV(w.propPV6(), w.runtimeValue6());
    }
}
