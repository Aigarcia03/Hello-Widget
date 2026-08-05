package com.ceos.widgetph.regulator;

import org.csstudio.display.builder.representation.javafx.JFXUtil;
import com.ceos.widgetfx.FeedbackRegulatorNode;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class FeedbackRegulatorTileRepresentation extends BaseTileRepresentation<FeedbackRegulatorNode, FeedbackRegulatorTileWidget> {

    @Override
    protected FeedbackRegulatorNode createJFXNode() throws Exception {
        return new FeedbackRegulatorNode();
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        model_widget.propKnobColor().addUntypedPropertyListener(listener);
        model_widget.propIndicatorColor().addUntypedPropertyListener(listener);
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
        }
    }
}
