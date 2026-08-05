package com.ceos.widgetph.circularprogress;

import org.csstudio.display.builder.representation.javafx.JFXUtil;
import org.epics.vtype.VNumber;
import org.epics.vtype.VType;
import eu.hansolo.tilesfx.Tile.SkinType;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class CircularProgressTileRepresentation extends BaseTileRepresentation<GenericTile, CircularProgressTileWidget> {

    @Override
    protected GenericTile createJFXNode() throws Exception {
        return new GenericTile(SkinType.CIRCULAR_PROGRESS);
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        model_widget.propBarColor().addUntypedPropertyListener(listener);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
            jfx_node.setBarColor(JFXUtil.convert(model_widget.propBarColor().getValue()));
        }

        VType vtype = model_widget.runtimePropValue().getValue();
        if (vtype instanceof VNumber) {
            jfx_node.setValue(((VNumber) vtype).getValue().doubleValue());
        }
    }
}
