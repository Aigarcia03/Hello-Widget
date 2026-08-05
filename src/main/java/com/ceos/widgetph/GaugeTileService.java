package com.ceos.widgetph;

import java.util.Collection;
import java.util.List;
import org.csstudio.display.builder.model.WidgetDescriptor;
import org.csstudio.display.builder.model.spi.WidgetsService;
import com.ceos.widgetph.areachart.AreaChartTileDescriptor;
import com.ceos.widgetph.barchart.BarChartTileDescriptor;
import com.ceos.widgetph.leaderboard.LeaderBoardTileDescriptor;
import com.ceos.widgetph.clock.ClockTileDescriptor;
import com.ceos.widgetph.medusadigital.MedusaDigitalDescriptor;
import com.ceos.widgetph.medusabulletchart.MedusaBulletChartDescriptor;
import com.ceos.widgetph.medusaspacex.MedusaSpaceXDescriptor;
import com.ceos.widgetph.medusaslim.MedusaSlimDescriptor;
import com.ceos.widgetph.regulator.RegulatorTileDescriptor;
import com.ceos.widgetph.regulator.FeedbackRegulatorTileDescriptor;
import com.ceos.widgetph.highlow.HighLowTileDescriptor;
import com.ceos.widgetph.circularprogress.CircularProgressTileDescriptor;
import com.ceos.widgetph.donutchart.DonutChartTileDescriptor;
import com.ceos.widgetph.gauge.GaugeTileDescriptor;
import com.ceos.widgetph.ikonli.IkonliIconDescriptor;
import com.ceos.widgetph.linechart.LineChartTileDescriptor;
import com.ceos.widgetph.plusminus.PlusMinusTileDescriptor;
import com.ceos.widgetph.radialchart.RadialChartTileDescriptor;
import com.ceos.widgetph.sparkline.SparklineTileDescriptor;
import com.ceos.widgetph.timercontrol.TimerControlTileDescriptor;
import com.ceos.widgetph.stock.StockTileDescriptor;
import com.ceos.widgetph.radarchart.RadarChartTileDescriptor;

public class GaugeTileService implements WidgetsService {

    @Override
    public Collection<WidgetDescriptor> getWidgetDescriptors() {
        return List.of(new GaugeTileDescriptor(), new SparklineTileDescriptor(), new RadialChartTileDescriptor(),
                new CircularProgressTileDescriptor(), new LineChartTileDescriptor(), new AreaChartTileDescriptor(),
                new ClockTileDescriptor(), new PlusMinusTileDescriptor(), new IkonliIconDescriptor(),
                new TimerControlTileDescriptor(), new HighLowTileDescriptor(), new StockTileDescriptor(),
                new DonutChartTileDescriptor(), new MedusaDigitalDescriptor(),
                new MedusaBulletChartDescriptor(), new MedusaSpaceXDescriptor(),
                new MedusaSlimDescriptor(),
                new RegulatorTileDescriptor(),
                new FeedbackRegulatorTileDescriptor(),
                new RadarChartTileDescriptor(),
                new BarChartTileDescriptor(),
                new LeaderBoardTileDescriptor());
    }
}
