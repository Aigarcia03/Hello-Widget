package com.ceos.widgetph.base;

import org.csstudio.display.builder.representation.javafx.JFXUtil;
import org.epics.vtype.VNumber;
import org.epics.vtype.VType;
import com.ceos.widgetfx.TileNode;

public class TileHelper {

    private TileHelper() {}

    public static void applyProperties(TileNode node, BaseTileWidget model) {
        node.setTitle(model.propTitle().getValue());
        node.setUnit(model.propUnit().getValue());

        node.setBackgroundColor(JFXUtil.convert(model.propBackgroundColor().getValue()));
        node.setTitleColor(JFXUtil.convert(model.propTitleColor().getValue()));
        node.setValueColor(JFXUtil.convert(model.propValueColor().getValue()));
        node.setUnitColor(JFXUtil.convert(model.propUnitColor().getValue()));
        node.setCustomFont(JFXUtil.convert(model.propFont().getValue()));
        node.setCustomFontEnabled(true);
        node.setDecimals(model.propDecimals().getValue());

        node.setRoundedCorners(model.propRoundedCorners().getValue());
        node.setShadowsEnabled(model.propShadowsEnabled().getValue());
        node.setValueVisible(model.propValueVisible().getValue());

        node.setMinValue(model.propMinValue().getValue());
        node.setMaxValue(model.propMaxValue().getValue());
        node.setAnimated(model.propAnimated().getValue());

        VType vtype = model.runtimePropValue().getValue();
        if (vtype instanceof VNumber) {
            node.setValue(((VNumber) vtype).getValue().doubleValue());
        }
    }
}
