package com.ceos.widgetph.regulator;

import org.csstudio.display.builder.representation.javafx.JFXUtil;
import com.ceos.widgetfx.RegulatorNode;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class RegulatorTileRepresentation extends BaseTileRepresentation<RegulatorNode, RegulatorTileWidget> {

    @Override
    protected RegulatorNode createJFXNode() throws Exception {
        return new RegulatorNode();
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        model_widget.propKnobColor().addUntypedPropertyListener(listener);
        model_widget.propIndicatorColor().addUntypedPropertyListener(listener);
        model_widget.propBarColor().addUntypedPropertyListener(listener);
        jfx_node.getRegulator().setOnTargetSet(evt ->
            toolkit.fireWrite(model_widget, jfx_node.getRegulator().getTargetValue()));
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
            jfx_node.getRegulator().setColor(JFXUtil.convert(model_widget.propKnobColor().getValue()));
            jfx_node.getRegulator().setIndicatorColor(JFXUtil.convert(model_widget.propIndicatorColor().getValue()));
            jfx_node.getRegulator().setBarColor(JFXUtil.convert(model_widget.propBarColor().getValue()));
        }
    }
}
