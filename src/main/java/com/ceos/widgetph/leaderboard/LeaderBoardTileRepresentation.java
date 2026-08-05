package com.ceos.widgetph.leaderboard;

import java.util.ArrayList;
import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.util.VTypeUtil;
import org.csstudio.display.builder.representation.javafx.JFXUtil;
import org.epics.vtype.VType;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.skins.LeaderBoardItem;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class LeaderBoardTileRepresentation extends BaseTileRepresentation<GenericTile, LeaderBoardTileWidget> {

    private final List<LeaderBoardItem> items = new ArrayList<>();

    @Override
    protected GenericTile createJFXNode() throws Exception {
        return new GenericTile(SkinType.LEADER_BOARD);
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        final LeaderBoardTileWidget model = model_widget;
        model.propNumItems().addUntypedPropertyListener(listener);
        model.propSorting().addUntypedPropertyListener(listener);
        model.propName1().addUntypedPropertyListener(listener);
        model.propName2().addUntypedPropertyListener(listener);
        model.propName3().addUntypedPropertyListener(listener);
        model.propName4().addUntypedPropertyListener(listener);
        model.propName5().addUntypedPropertyListener(listener);
        model.propName6().addUntypedPropertyListener(listener);
        model.propNameColor1().addUntypedPropertyListener(listener);
        model.propNameColor2().addUntypedPropertyListener(listener);
        model.propNameColor3().addUntypedPropertyListener(listener);
        model.propNameColor4().addUntypedPropertyListener(listener);
        model.propNameColor5().addUntypedPropertyListener(listener);
        model.propNameColor6().addUntypedPropertyListener(listener);
        model.runtimeValue1().addUntypedPropertyListener(listener);
        model.runtimeValue2().addUntypedPropertyListener(listener);
        model.runtimeValue3().addUntypedPropertyListener(listener);
        model.runtimeValue4().addUntypedPropertyListener(listener);
        model.runtimeValue5().addUntypedPropertyListener(listener);
        model.runtimeValue6().addUntypedPropertyListener(listener);
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
            jfx_node.setItemSorting(model_widget.propSorting().getValue());
            jfx_node.setItemSortingTopic(eu.hansolo.tilesfx.Tile.ItemSortingTopic.VALUE);
        }

        final LeaderBoardTileWidget model = model_widget;
        final int n = Math.max(0, Math.min(6, model.propNumItems().getValue()));

        while (items.size() > n) {
            final LeaderBoardItem item = items.remove(items.size() - 1);
            jfx_node.removeLeaderBoardItem(item);
        }
        while (items.size() < n) {
            final int idx = items.size();
            final LeaderBoardItem item = new LeaderBoardItem(name(model, idx), 0.0);
            items.add(item);
            jfx_node.addLeaderBoardItem(item);
        }

        for (int i = 0; i < n; i++) {
            final LeaderBoardItem item = items.get(i);
            item.setName(name(model, i));
            item.setNameColor(JFXUtil.convert(nameColor(model, i)));
            item.setValue(value(model, i));
        }
    }

    private static String name(final LeaderBoardTileWidget model, final int idx) {
        return nameProp(model, idx).getValue();
    }

    private static double value(final LeaderBoardTileWidget model, final int idx) {
        final WidgetProperty<VType> runtime = runtimeProp(model, idx);
        if (runtime.getValue() != null) {
            final Number num = VTypeUtil.getValueNumber(runtime.getValue());
            if (num != null && !Double.isNaN(num.doubleValue())) {
                return num.doubleValue();
            }
        }
        return 0.0;
    }

    private static org.phoebus.ui.color.WidgetColor nameColor(final LeaderBoardTileWidget model, final int idx) {
        return nameColorProp(model, idx).getValue();
    }

    private static WidgetProperty<String> nameProp(final LeaderBoardTileWidget model, final int idx) {
        switch (idx) {
        case 0: return model.propName1();
        case 1: return model.propName2();
        case 2: return model.propName3();
        case 3: return model.propName4();
        case 4: return model.propName5();
        default: return model.propName6();
        }
    }

    private static WidgetProperty<org.phoebus.ui.color.WidgetColor> nameColorProp(final LeaderBoardTileWidget model, final int idx) {
        switch (idx) {
        case 0: return model.propNameColor1();
        case 1: return model.propNameColor2();
        case 2: return model.propNameColor3();
        case 3: return model.propNameColor4();
        case 4: return model.propNameColor5();
        default: return model.propNameColor6();
        }
    }

    private static WidgetProperty<VType> runtimeProp(final LeaderBoardTileWidget model, final int idx) {
        switch (idx) {
        case 0: return model.runtimeValue1();
        case 1: return model.runtimeValue2();
        case 2: return model.runtimeValue3();
        case 3: return model.runtimeValue4();
        case 4: return model.runtimeValue5();
        default: return model.runtimeValue6();
        }
    }
}
