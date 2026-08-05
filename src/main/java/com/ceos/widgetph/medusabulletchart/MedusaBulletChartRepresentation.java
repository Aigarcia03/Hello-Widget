package com.ceos.widgetph.medusabulletchart;

import eu.hansolo.medusa.Gauge.SkinType;
import org.csstudio.display.builder.representation.javafx.JFXUtil;
import com.ceos.widgetfx.MedusaGaugeNode;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class MedusaBulletChartRepresentation extends BaseTileRepresentation<MedusaGaugeNode, MedusaBulletChartWidget> {

    @Override
    protected MedusaGaugeNode createJFXNode() throws Exception {
        return new MedusaGaugeNode(SkinType.BULLET_CHART);
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        model_widget.propBarColor().addUntypedPropertyListener(listener);
        model_widget.propBarBackgroundColor().addUntypedPropertyListener(listener);
        model_widget.propTickMarkColor().addUntypedPropertyListener(listener);
        model_widget.propTickLabelColor().addUntypedPropertyListener(listener);
        model_widget.propForegroundColor().addUntypedPropertyListener(listener);
        model_widget.propThreshold().addUntypedPropertyListener(listener);
        model_widget.propThresholdColor().addUntypedPropertyListener(listener);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
            jfx_node.setBarColor(JFXUtil.convert(model_widget.propBarColor().getValue()));
            jfx_node.setBarBackgroundColor(JFXUtil.convert(model_widget.propBarBackgroundColor().getValue()));
            jfx_node.setTickMarkColor(JFXUtil.convert(model_widget.propTickMarkColor().getValue()));
            jfx_node.setTickLabelColor(JFXUtil.convert(model_widget.propTickLabelColor().getValue()));
            jfx_node.setForegroundColor(JFXUtil.convert(model_widget.propForegroundColor().getValue()));
            jfx_node.setThreshold(model_widget.propThreshold().getValue());
            jfx_node.setThresholdColor(JFXUtil.convert(model_widget.propThresholdColor().getValue()));
        }
    }
}
