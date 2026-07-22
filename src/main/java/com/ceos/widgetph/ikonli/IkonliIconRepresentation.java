package com.ceos.widgetph.ikonli;

import org.csstudio.display.builder.representation.javafx.JFXUtil;
import com.ceos.widgetfx.IkonliIconNode;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class IkonliIconRepresentation extends BaseTileRepresentation<IkonliIconNode, IkonliIconWidget> {

    @Override
    protected IkonliIconNode createJFXNode() throws Exception {
        return new IkonliIconNode();
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        final IkonliIconWidget model = model_widget;
        model.propIconName().addUntypedPropertyListener(listener);
        model.propIconSize().addUntypedPropertyListener(listener);
        model.propIconColor().addUntypedPropertyListener(listener);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
            applyIconProperties();
        }
    }

    private void applyIconProperties() {
        jfx_node.setIconLiteral(model_widget.propIconName().getValue());
        jfx_node.setIconSize(model_widget.propIconSize().getValue());
        jfx_node.setIconColor(JFXUtil.convert(model_widget.propIconColor().getValue()));
    }
}
