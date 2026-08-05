package com.ceos.widgetph.leaderboard;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class LeaderBoardTileDescriptor extends WidgetDescriptor {

    public LeaderBoardTileDescriptor() {
        super(LeaderBoardTileWidget.WIDGET_TYPE, WidgetCategory.PLOT, "Custom", "/chart_bar.png", "Leader board tile widget");
    }

    @Override
    public Widget createWidget() {
        return new LeaderBoardTileWidget();
    }

    @Override
    public String getType(){
        return LeaderBoardTileWidget.WIDGET_TYPE;
    }

    @Override
    public String getName(){
        return "LeaderBoard Tile";
    }

    @Override
    public WidgetCategory getCategory(){
       return WidgetCategory.PLOT;
    }

    @Override
    public String getDescription(){
        return "Leader board tile widget with one PV per row.";
    }
}
