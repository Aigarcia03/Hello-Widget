package com.ceos.widgetph.clock;

import eu.hansolo.tilesfx.Tile.SkinType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.csstudio.display.builder.representation.javafx.JFXUtil;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class ClockTileRepresentation extends BaseTileRepresentation<GenericTile, ClockTileWidget> {

    @Override
    protected GenericTile createJFXNode() throws Exception {
        GenericTile node = new GenericTile(SkinType.CLOCK);
        node.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                node.setRunning(true);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                    node.setTime(java.time.ZonedDateTime.now());
                }));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
            }
        });
        return node;
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        final ClockTileWidget model = model_widget;
        model.propDateColor().addUntypedPropertyListener(listener);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
            jfx_node.setDateColor(JFXUtil.convert(model_widget.propDateColor().getValue()));
        }
    }
}
