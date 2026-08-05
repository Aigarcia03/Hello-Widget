package com.ceos.widgetph.medusaslim;

import eu.hansolo.medusa.Gauge.SkinType;
import org.csstudio.display.builder.representation.javafx.JFXUtil;
import com.ceos.widgetfx.MedusaGaugeNode;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class MedusaSlimRepresentation extends BaseTileRepresentation<MedusaGaugeNode, MedusaSlimWidget> {

    @Override
    protected MedusaGaugeNode createJFXNode() throws Exception {
        return new MedusaGaugeNode(SkinType.SLIM);
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        model_widget.propBarColor().addUntypedPropertyListener(listener);
        model_widget.propBarBackgroundColor().addUntypedPropertyListener(listener);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
            jfx_node.setBarColor(JFXUtil.convert(model_widget.propBarColor().getValue()));
            jfx_node.setBarBackgroundColor(JFXUtil.convert(model_widget.propBarBackgroundColor().getValue()));
        }
    }
}
