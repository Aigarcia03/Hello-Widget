package com.ceos.widgetph.barchart;

import java.util.ArrayList;
import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.util.VTypeUtil;
import org.csstudio.display.builder.representation.javafx.JFXUtil;
import org.epics.vtype.VType;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.skins.BarChartItem;
import javafx.scene.paint.Color;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class BarChartTileRepresentation extends BaseTileRepresentation<GenericTile, BarChartTileWidget> {

    private final List<BarChartItem> items = new ArrayList<>();

    @Override
    protected GenericTile createJFXNode() throws Exception {
        return new GenericTile(SkinType.BAR_CHART);
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        final BarChartTileWidget model = model_widget;
        model.propNumItems().addUntypedPropertyListener(listener);
        model.propSorting().addUntypedPropertyListener(listener);
        model.propName1().addUntypedPropertyListener(listener);
        model.propName2().addUntypedPropertyListener(listener);
        model.propName3().addUntypedPropertyListener(listener);
        model.propName4().addUntypedPropertyListener(listener);
        model.propName5().addUntypedPropertyListener(listener);
        model.propName6().addUntypedPropertyListener(listener);
        model.propColor1().addUntypedPropertyListener(listener);
        model.propColor2().addUntypedPropertyListener(listener);
        model.propColor3().addUntypedPropertyListener(listener);
        model.propColor4().addUntypedPropertyListener(listener);
        model.propColor5().addUntypedPropertyListener(listener);
        model.propColor6().addUntypedPropertyListener(listener);
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

        final BarChartTileWidget model = model_widget;
        final int n = Math.max(0, Math.min(6, model.propNumItems().getValue()));

        while (items.size() > n) {
            final BarChartItem item = items.remove(items.size() - 1);
            jfx_node.removeBarChartItem(item);
        }
        while (items.size() < n) {
            final int idx = items.size();
            final BarChartItem item = new BarChartItem(name(model, idx), 0.0, color(model, idx));
            items.add(item);
            jfx_node.addBarChartItem(item);
        }

        for (int i = 0; i < n; i++) {
            final BarChartItem item = items.get(i);
            item.setName(name(model, i));
            item.setBarColor(color(model, i));
            item.setValue(value(model, i));
        }
    }

    private static String name(final BarChartTileWidget model, final int idx) {
        return nameProp(model, idx).getValue();
    }

    private static double value(final BarChartTileWidget model, final int idx) {
        final WidgetProperty<VType> runtime = runtimeProp(model, idx);
        if (runtime.getValue() != null) {
            final Number num = VTypeUtil.getValueNumber(runtime.getValue());
            if (num != null && !Double.isNaN(num.doubleValue())) {
                return num.doubleValue();
            }
        }
        return 0.0;
    }

    private static Color color(final BarChartTileWidget model, final int idx) {
        return JFXUtil.convert(colorProp(model, idx).getValue());
    }

    private static WidgetProperty<String> nameProp(final BarChartTileWidget model, final int idx) {
        switch (idx) {
        case 0: return model.propName1();
        case 1: return model.propName2();
        case 2: return model.propName3();
        case 3: return model.propName4();
        case 4: return model.propName5();
        default: return model.propName6();
        }
    }

    private static WidgetProperty<org.phoebus.ui.color.WidgetColor> colorProp(final BarChartTileWidget model, final int idx) {
        switch (idx) {
        case 0: return model.propColor1();
        case 1: return model.propColor2();
        case 2: return model.propColor3();
        case 3: return model.propColor4();
        case 4: return model.propColor5();
        default: return model.propColor6();
        }
    }

    private static WidgetProperty<VType> runtimeProp(final BarChartTileWidget model, final int idx) {
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
