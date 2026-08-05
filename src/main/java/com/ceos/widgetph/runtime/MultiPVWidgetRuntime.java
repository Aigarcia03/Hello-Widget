package com.ceos.widgetph.runtime;

import java.util.ArrayList;
import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.runtime.PVNameToValueBinding;
import org.csstudio.display.builder.runtime.WidgetRuntime;
import org.epics.vtype.VType;
import com.ceos.widgetph.base.BaseTileWidget;

public abstract class MultiPVWidgetRuntime<MW extends BaseTileWidget> extends WidgetRuntime<MW> {

    private final List<PVNameToValueBinding> bindings = new ArrayList<>();

    protected void connectPV(final WidgetProperty<String> name, final WidgetProperty<VType> value) {
        bindings.add(new PVNameToValueBinding(this, name, value));
    }

    @Override
    public void stop() {
        for (PVNameToValueBinding binding : bindings) {
            binding.dispose();
        }
        bindings.clear();
        super.stop();
    }
}
