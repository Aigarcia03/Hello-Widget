package com.ceos.widgetph.runtime;

import java.util.Map;
import java.util.function.Supplier;
import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.runtime.WidgetRuntime;
import org.csstudio.display.builder.runtime.spi.WidgetRuntimesService;
import com.ceos.widgetph.barchart.BarChartTileRuntime;
import com.ceos.widgetph.barchart.BarChartTileWidget;
import com.ceos.widgetph.donutchart.DonutChartTileRuntime;
import com.ceos.widgetph.donutchart.DonutChartTileWidget;
import com.ceos.widgetph.leaderboard.LeaderBoardTileRuntime;
import com.ceos.widgetph.leaderboard.LeaderBoardTileWidget;
import com.ceos.widgetph.radarchart.RadarChartTileRuntime;
import com.ceos.widgetph.radarchart.RadarChartTileWidget;
import com.ceos.widgetph.radialchart.RadialChartTileRuntime;
import com.ceos.widgetph.radialchart.RadialChartTileWidget;

public class TileWidgetRuntimes implements WidgetRuntimesService {

    @Override
    public Map<String, Supplier<WidgetRuntime<? extends Widget>>> getWidgetRuntimeFactories() {
        return Map.of(
                RadarChartTileWidget.WIDGET_TYPE, () -> new RadarChartTileRuntime(),
                RadialChartTileWidget.WIDGET_TYPE, () -> new RadialChartTileRuntime(),
                DonutChartTileWidget.WIDGET_TYPE, () -> new DonutChartTileRuntime(),
                BarChartTileWidget.WIDGET_TYPE, () -> new BarChartTileRuntime(),
                LeaderBoardTileWidget.WIDGET_TYPE, () -> new LeaderBoardTileRuntime());
    }
}
