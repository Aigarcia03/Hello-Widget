package com.ceos.widgetph;

import java.util.Map;
import static java.util.Map.entry;
import org.csstudio.display.builder.model.WidgetDescriptor;
import org.csstudio.display.builder.representation.WidgetRepresentation;
import org.csstudio.display.builder.representation.WidgetRepresentationFactory;
import org.csstudio.display.builder.representation.spi.WidgetRepresentationsService;
import com.ceos.widgetph.areachart.AreaChartTileDescriptor;
import com.ceos.widgetph.areachart.AreaChartTileRepresentation;
import com.ceos.widgetph.barchart.BarChartTileDescriptor;
import com.ceos.widgetph.barchart.BarChartTileRepresentation;
import com.ceos.widgetph.leaderboard.LeaderBoardTileDescriptor;
import com.ceos.widgetph.leaderboard.LeaderBoardTileRepresentation;
import com.ceos.widgetph.clock.ClockTileDescriptor;
import com.ceos.widgetph.clock.ClockTileRepresentation;
import com.ceos.widgetph.medusadigital.MedusaDigitalDescriptor;
import com.ceos.widgetph.medusadigital.MedusaDigitalRepresentation;
import com.ceos.widgetph.medusabulletchart.MedusaBulletChartDescriptor;
import com.ceos.widgetph.medusabulletchart.MedusaBulletChartRepresentation;
import com.ceos.widgetph.medusaspacex.MedusaSpaceXDescriptor;
import com.ceos.widgetph.medusaspacex.MedusaSpaceXRepresentation;
import com.ceos.widgetph.medusaslim.MedusaSlimDescriptor;
import com.ceos.widgetph.medusaslim.MedusaSlimRepresentation;
import com.ceos.widgetph.regulator.RegulatorTileDescriptor;
import com.ceos.widgetph.regulator.RegulatorTileRepresentation;
import com.ceos.widgetph.regulator.FeedbackRegulatorTileDescriptor;
import com.ceos.widgetph.regulator.FeedbackRegulatorTileRepresentation;
import com.ceos.widgetph.highlow.HighLowTileDescriptor;
import com.ceos.widgetph.highlow.HighLowTileRepresentation;
import com.ceos.widgetph.circularprogress.CircularProgressTileDescriptor;
import com.ceos.widgetph.stock.StockTileDescriptor;
import com.ceos.widgetph.stock.StockTileRepresentation;
import com.ceos.widgetph.circularprogress.CircularProgressTileRepresentation;
import com.ceos.widgetph.donutchart.DonutChartTileDescriptor;
import com.ceos.widgetph.donutchart.DonutChartTileRepresentation;
import com.ceos.widgetph.gauge.GaugeTileDescriptor;
import com.ceos.widgetph.gauge.GaugeTileRepresentation;
import com.ceos.widgetph.ikonli.IkonliIconDescriptor;
import com.ceos.widgetph.ikonli.IkonliIconRepresentation;
import com.ceos.widgetph.linechart.LineChartTileDescriptor;
import com.ceos.widgetph.linechart.LineChartTileRepresentation;
import com.ceos.widgetph.plusminus.PlusMinusTileDescriptor;
import com.ceos.widgetph.plusminus.PlusMinusTileRepresentation;
import com.ceos.widgetph.radialchart.RadialChartTileDescriptor;
import com.ceos.widgetph.radialchart.RadialChartTileRepresentation;
import com.ceos.widgetph.sparkline.SparklineTileDescriptor;
import com.ceos.widgetph.timercontrol.TimerControlTileDescriptor;
import com.ceos.widgetph.timercontrol.TimerControlTileRepresentation;
import com.ceos.widgetph.sparkline.SparklineTileRepresentation;
import com.ceos.widgetph.radarchart.RadarChartTileDescriptor;
import com.ceos.widgetph.radarchart.RadarChartTileRepresentation;

public class GaugeTileRepresentationService implements WidgetRepresentationsService {

    @Override
    public <TWP, TW> Map<WidgetDescriptor, WidgetRepresentationFactory<TWP, TW>> getWidgetRepresentationFactories() {
        return Map.ofEntries(
                entry(new GaugeTileDescriptor(), () -> (WidgetRepresentation) new GaugeTileRepresentation()),
                entry(new SparklineTileDescriptor(), () -> (WidgetRepresentation) new SparklineTileRepresentation()),
                entry(new RadialChartTileDescriptor(), () -> (WidgetRepresentation) new RadialChartTileRepresentation()),
                entry(new CircularProgressTileDescriptor(), () -> (WidgetRepresentation) new CircularProgressTileRepresentation()),
                entry(new LineChartTileDescriptor(), () -> (WidgetRepresentation) new LineChartTileRepresentation()),
                entry(new AreaChartTileDescriptor(), () -> (WidgetRepresentation) new AreaChartTileRepresentation()),
                entry(new ClockTileDescriptor(), () -> (WidgetRepresentation) new ClockTileRepresentation()),
                entry(new PlusMinusTileDescriptor(), () -> (WidgetRepresentation) new PlusMinusTileRepresentation()),
                entry(new IkonliIconDescriptor(), () -> (WidgetRepresentation) new IkonliIconRepresentation()),
                entry(new TimerControlTileDescriptor(), () -> (WidgetRepresentation) new TimerControlTileRepresentation()),
                entry(new HighLowTileDescriptor(), () -> (WidgetRepresentation) new HighLowTileRepresentation()),
                entry(new StockTileDescriptor(), () -> (WidgetRepresentation) new StockTileRepresentation()),
                entry(new DonutChartTileDescriptor(), () -> (WidgetRepresentation) new DonutChartTileRepresentation()),
                entry(new MedusaDigitalDescriptor(), () -> (WidgetRepresentation) new MedusaDigitalRepresentation()),
                entry(new MedusaBulletChartDescriptor(), () -> (WidgetRepresentation) new MedusaBulletChartRepresentation()),
                entry(new MedusaSpaceXDescriptor(), () -> (WidgetRepresentation) new MedusaSpaceXRepresentation()),
                entry(new MedusaSlimDescriptor(), () -> (WidgetRepresentation) new MedusaSlimRepresentation()),
                entry(new RegulatorTileDescriptor(), () -> (WidgetRepresentation) new RegulatorTileRepresentation()),
                entry(new FeedbackRegulatorTileDescriptor(), () -> (WidgetRepresentation) new FeedbackRegulatorTileRepresentation()),
                entry(new RadarChartTileDescriptor(), () -> (WidgetRepresentation) new RadarChartTileRepresentation()),
                entry(new BarChartTileDescriptor(), () -> (WidgetRepresentation) new BarChartTileRepresentation()),
                entry(new LeaderBoardTileDescriptor(), () -> (WidgetRepresentation) new LeaderBoardTileRepresentation()));
    }
}
