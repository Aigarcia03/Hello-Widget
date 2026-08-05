package com.ceos.widgetph.medusadigital;

import eu.hansolo.medusa.Gauge.SkinType;
import org.csstudio.display.builder.representation.javafx.JFXUtil;
import com.ceos.widgetfx.MedusaGaugeNode;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class MedusaDigitalRepresentation extends BaseTileRepresentation<MedusaGaugeNode, MedusaDigitalWidget> {

    @Override
    protected MedusaGaugeNode createJFXNode() throws Exception {
        return new MedusaGaugeNode(SkinType.DIGITAL);
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        model_widget.propForegroundColor().addUntypedPropertyListener(listener);
        model_widget.propBarColor().addUntypedPropertyListener(listener);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
            jfx_node.setForegroundColor(JFXUtil.convert(model_widget.propForegroundColor().getValue()));
            jfx_node.setBarColor(JFXUtil.convert(model_widget.propBarColor().getValue()));
        }
    }
}
