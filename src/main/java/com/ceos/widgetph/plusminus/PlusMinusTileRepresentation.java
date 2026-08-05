package com.ceos.widgetph.plusminus;

import eu.hansolo.tilesfx.Tile.SkinType;
import org.csstudio.display.builder.representation.javafx.JFXUtil;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class PlusMinusTileRepresentation extends BaseTileRepresentation<GenericTile, PlusMinusTileWidget> {

    private boolean active = false;

    @Override
    protected GenericTile createJFXNode() throws Exception {
        GenericTile node = new GenericTile(SkinType.PLUS_MINUS);
        node.setTileMouseTransparent(false);
        return node;
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        model_widget.propButtonColor().addUntypedPropertyListener(listener);
        jfx_node.addValueListener(obs -> {
            if (!active) {
                toolkit.fireWrite(model_widget, jfx_node.getValue());
            }
        });
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            active = true;
            try {
                TileHelper.applyProperties(jfx_node, model_widget);
                javafx.scene.paint.Color buttonColor = JFXUtil.convert(model_widget.propButtonColor().getValue());
                jfx_node.setForegroundColor(buttonColor);
                jfx_node.setActiveColor(buttonColor);
            } finally {
                active = false;
            }
        }
    }
}
