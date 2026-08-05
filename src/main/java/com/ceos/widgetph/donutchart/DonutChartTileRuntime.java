package com.ceos.widgetph.donutchart;

import com.ceos.widgetph.runtime.MultiPVWidgetRuntime;

public class DonutChartTileRuntime extends MultiPVWidgetRuntime<DonutChartTileWidget> {

    @Override
    public void start() {
        super.start();
        final DonutChartTileWidget w = (DonutChartTileWidget) widget;
        connectPV(w.propPV1(), w.runtimeValue1());
        connectPV(w.propPV2(), w.runtimeValue2());
        connectPV(w.propPV3(), w.runtimeValue3());
        connectPV(w.propPV4(), w.runtimeValue4());
    }
}
