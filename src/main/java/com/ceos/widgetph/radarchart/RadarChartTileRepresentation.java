package com.ceos.widgetph.radarchart;

import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetfx.RadarChartNode;
import org.csstudio.display.builder.model.util.VTypeUtil;
import org.epics.vtype.VType;
import javafx.scene.paint.Color;

public class RadarChartTileRepresentation extends BaseTileRepresentation<RadarChartNode, RadarChartTileWidget> {

    @Override
    protected RadarChartNode createJFXNode() throws Exception {
        return new RadarChartNode();
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        model_widget.propMode().addUntypedPropertyListener(listener);
        model_widget.propChartFillColor().addUntypedPropertyListener(listener);
        model_widget.propGridColor().addUntypedPropertyListener(listener);
        model_widget.propNumSectors().addUntypedPropertyListener(listener);
        model_widget.runtimeValue1().addUntypedPropertyListener(listener);
        model_widget.runtimeValue2().addUntypedPropertyListener(listener);
        model_widget.runtimeValue3().addUntypedPropertyListener(listener);
        model_widget.runtimeValue4().addUntypedPropertyListener(listener);
        model_widget.runtimeValue5().addUntypedPropertyListener(listener);
        model_widget.runtimeValue6().addUntypedPropertyListener(listener);
    }

    @Override
    protected void unregisterListeners() {
        super.unregisterListeners();
        model_widget.propMode().removePropertyListener(listener);
        model_widget.propChartFillColor().removePropertyListener(listener);
        model_widget.propGridColor().removePropertyListener(listener);
        model_widget.propNumSectors().removePropertyListener(listener);
        model_widget.runtimeValue1().removePropertyListener(listener);
        model_widget.runtimeValue2().removePropertyListener(listener);
        model_widget.runtimeValue3().removePropertyListener(listener);
        model_widget.runtimeValue4().removePropertyListener(listener);
        model_widget.runtimeValue5().removePropertyListener(listener);
        model_widget.runtimeValue6().removePropertyListener(listener);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            jfx_node.setMode(eu.hansolo.tilesfx.chart.RadarChartMode.valueOf(
                model_widget.propMode().getValue().name()
            ));
            jfx_node.setFillColor(toFXColor(
                model_widget.propChartFillColor().getValue(), Color.rgb(100, 150, 255, 0.5)
            ));
            jfx_node.setGridColor(toFXColor(
                model_widget.propGridColor().getValue(), Color.rgb(200, 200, 200)
            ));
            int n = model_widget.propNumSectors().getValue();
            jfx_node.initSectors(n);
            if (n >= 1) jfx_node.setSectorValue(0, sectorValue(model_widget.runtimeValue1()));
            if (n >= 2) jfx_node.setSectorValue(1, sectorValue(model_widget.runtimeValue2()));
            if (n >= 3) jfx_node.setSectorValue(2, sectorValue(model_widget.runtimeValue3()));
            if (n >= 4) jfx_node.setSectorValue(3, sectorValue(model_widget.runtimeValue4()));
            if (n >= 5) jfx_node.setSectorValue(4, sectorValue(model_widget.runtimeValue5()));
            if (n >= 6) jfx_node.setSectorValue(5, sectorValue(model_widget.runtimeValue6()));
        }
    }

    private static double sectorValue(final org.csstudio.display.builder.model.WidgetProperty<org.epics.vtype.VType> runtime) {
        if (runtime.getValue() != null) {
            final Number num = VTypeUtil.getValueNumber(runtime.getValue());
            if (num != null && !Double.isNaN(num.doubleValue())) {
                return num.doubleValue();
            }
        }
        return 0.0;
    }

    private static Color toFXColor(org.phoebus.ui.color.WidgetColor c, Color fallback) {
        if (c == null) return fallback;
        return Color.rgb(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha() / 255.0);
    }
}