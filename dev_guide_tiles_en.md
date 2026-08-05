# Phoebus Tiles Development Guide (WidgetTest)

This guide explains, from scratch, how widget ("tiles") development works in this project and how to add a new tile. It is intended for someone who hasn't worked with this code before: after reading it, you should be able to create new tiles and understand the existing ones.

Every Phoebus widget is developed in **two phases**:

1. **Create (or reuse) a JavaFX node**: the visual component (a `Pane`, a `Canvas`, a control from a library…).
2. **Wrap it with Phoebus classes**: the model (`Widget`), the descriptor (`WidgetDescriptor`), and the representation (`WidgetRepresentation`), which connect the node with the editor and the Phoebus runtime.

---

## Project Structure

```text
src/main/java/com/ceos/widgetfx/          ← JavaFX Nodes (Phase 1)
├── TileNode.java                          ← Common interface for all nodes
├── GenericTile.java                       ← Generic node wrapping TilesFX
├── MedusaGaugeNode.java                   ← Node wrapping Medusa (Gauge)
├── RadarChartNode.java                    ← Node with custom radar chart
├── RegulatorNode.java                     ← Node with circular regulators control
└── IkonliIconNode.java                    ← Icon node

src/main/java/com/ceos/widgetph/           ← Phoebus Classes (Phase 2)
├── base/
│   ├── BaseTileWidget.java                ← Common model properties
│   ├── BaseTileRepresentation.java        ← Listeners and common logic
│   └── TileHelper.java                    ← Applies common properties to the node
├── sparkline/                             ← MINIMAL tile example (no extra props)
├── plusminus/                             ← INTERACTIVE tile example (writes PV)
├── radialchart/                           ← Tile example with MULTIPLE PVs (6)
├── regulator/                             ← Example with external control
├── GaugeTileService.java                  ← Widget registration in the palette (EDIT)
├── GaugeTileRepresentationService.java    ← Representation registration (EDIT)
└── runtime/
    └── TileWidgetRuntimes.java            ← Multi-PV runtimes registration (EDIT)

src/main/resources/META-INF/services/      ← Phoebus SPI (DO NOT TOUCH, already configured)
├── org.csstudio.display.builder.model.spi.WidgetsService
├── org.csstudio.display.builder.representation.spi.WidgetRepresentationsService
└── org.csstudio.display.builder.runtime.spi.WidgetRuntimesService
```

---

# Phase 1: The JavaFX Node

Any class that inherits from `javafx.scene.Node` can be a Phoebus widget: `Pane`, `StackPane`, `Canvas`, `Group`, `Image`, etc.

In this project, you don't always need to create a new node. The most common approach is to **reuse an existing one** depending on the visual library you want:

| You Need | Use | Real Example |
|---|---|---|
| A TilesFX tile (clock, sparkline, chart, gauge…) | `GenericTile(SkinType.X)` | `SparklineTileRepresentation` |
| A Medusa gauge | `MedusaGaugeNode(SkinType.X)` | `MedusaDigitalRepresentation` |
| A circular regulators control | `RegulatorNode` | `RegulatorTileRepresentation` |
| Something custom-made | Create your own node implementing `TileNode` | `RadarChartNode` |

## 1.1 Reusing `GenericTile`

For a TilesFX tile, you only need to specify the `SkinType`:

```java
GenericTile node = new GenericTile(SkinType.SPARK_LINE);
```

`GenericTile` already exposes methods to change colors, title, min/max, threshold, etc. (it delegates to the TilesFX `Tile`). Example of useful methods: `setTitle`, `setBarColor`, `setThreshold`, `setChartData`, `setTilesFXSeries`, `setTextColor`, `setValue`.

## 1.2 Creating a Custom Node

A new node implements the `TileNode` interface, which defines all the `setXxx` methods that apply common properties (title, colors, font, min/max…). If your node doesn't support a specific property, you implement it as an empty method `{ }`.

```java
public class MyNode extends Pane implements TileNode {
    private final MyControl control = new MyControl();

    public MyNode() {
        this.getChildren().add(control);
    }

    @Override public void setTitle(String title) { control.setTitle(title); }
    @Override public void setBarColor(Color color) { control.setLineColor(color); }
    // ... the rest of the TileNode methods, empty if they don't apply

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        double w = getWidth(), h = getHeight();
        if (w > 0 && h > 0) {
            control.setPrefSize(w, h);
            control.resize(w, h);
            control.relocate(0, 0);
        }
    }
}
```

`layoutChildren()` is mandatory when the node is a `Pane`: it resizes the internal control to the size that Phoebus assigns to the widget.

## 1.3 ⚠️ The Mouse: `setMouseTransparent` (Important)

In Phoebus, if the node is **mouse transparent**, clicking on it in the editor selects the tile. If the node **consumes the mouse** (because it has buttons, a knob, etc.), the widget itself captures the click, and you must then **marquee select** it (drag a selection box) to select it.

All nodes are created with mouse transparency enabled by default:

```java
// GenericTile.java
tile.setMouseTransparent(true);
```

It is only disabled when the widget is **interactive at runtime** (+/- buttons, regulator knob…):

```java
// PlusMinusTileRepresentation.java
node.setTileMouseTransparent(false);
```

**Rule of thumb**: if your tile is read-only (a meter, a chart), leave it transparent so it can be selected with a single click. If it must be operable by the user, disable mouse transparency and accept that it must be marquee-selected in the editor.

---

# Phase 2: Wrapping with Phoebus Classes

## 2.1 The Model: `*TileWidget.java`

This is the widget's data model. It extends `BaseTileWidget`, which in turn extends `PVWidget` (which is why the tile can read/write PVs). The base class already defines many common properties:

`backgroundColor`, `titleColor`, `valueColor`, `unitColor`, `title`, `unit`, `font`, `decimals`, `roundedCorners`, `shadowsEnabled`, `valueVisible`, `minValue`, `maxValue`, `animated`, `alarmColor`, and the main PV (`runtimePropValue`).

The minimum code for a tile without extra properties (like `SparklineTileWidget`):

```java
package com.ceos.widgetph.mitile;

import com.ceos.widgetph.base.BaseTileWidget;

public class MiTileWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "mitile";   // unique id, used in .bob files

    public MiTileWidget() {
        super(WIDGET_TYPE);
    }
}
```

### Custom Properties

They are created as `WidgetProperty<?>` fields and added in `defineProperties()`. Each property has a **category** that controls where it appears in the properties panel:

- `WidgetPropertyCategory.DISPLAY` → colors, texts, appearance.
- `WidgetPropertyCategory.BEHAVIOR` → PVs and behavior.
- `WidgetPropertyCategory.CONTROL` → interaction properties.

```java
private WidgetProperty<WidgetColor> barColor;

@Override
protected void defineProperties(final List<WidgetProperty<?>> properties) {
    super.defineProperties(properties);

    WidgetPropertyDescriptor<WidgetColor> desc = CommonWidgetProperties.newColorPropertyDescriptor(
            WidgetPropertyCategory.DISPLAY, "barColor", "Bar Color");
    barColor = desc.createProperty(this, new WidgetColor(50, 150, 255));
    properties.add(barColor);
}

public WidgetProperty<WidgetColor> propBarColor() { return barColor; }
```

Other useful descriptors from `CommonWidgetProperties`:

| Type | Descriptor |
|---|---|
| Color | `newColorPropertyDescriptor(cat, "name", "Label")` |
| Number (double) | `newDoublePropertyDescriptor(...)` |
| Integer | `newIntegerPropertyDescriptor(...)` |
| Boolean | `newBooleanPropertyDescriptor(...)` |
| Text | `newStringPropertyDescriptor(...)` |
| PV Name | `newPVNamePropertyDescriptor(cat, "pv1", "Label")` |
| PV runtime value | `newRuntimeValue("runtimeValue1", "Label")` |

The default value is passed in `createProperty(this, default)`.

### 1 PV vs Multiple PVs in the Same Tile

**A single PV**: nothing extra is needed; the base `PVWidget` already connects the PV and places it in `runtimePropValue`. You read it in the representation with `model_widget.runtimePropValue().getValue()` (see `TileHelper`).

**Multiple PVs (up to N)**: declare a `pvX` for each PV and a `runtimeValueX` for each value it will receive:

```java
private WidgetProperty<String> pv1, pv2, pv3;            // BEHAVIOR
private WidgetProperty<VType> runtimeValue1, runtimeValue2, runtimeValue3;

// in defineProperties():
pv1 = CommonWidgetProperties.newPVNamePropertyDescriptor(
        WidgetPropertyCategory.BEHAVIOR, "pv1", "Value 1 PV").createProperty(this, "");
properties.add(pv1);
// ... pv2, pv3 ...

runtimeValue1 = CommonWidgetProperties.newRuntimeValue("runtimeValue1", "Value 1")
        .createProperty(this, null);
properties.add(runtimeValue1);
// ... runtimeValue2, runtimeValue3 ...
```

This is the pattern for `RadarChartTileWidget` and `RadialChartTileWidget` (which use 6 PVs). Phoebus **does not fill out** the `runtimeValueN` automatically: they must be connected to a runtime (see section 2.4).

## 2.2 The Descriptor: `*TileDescriptor.java`

Defines how the tile appears in the editor's palette. Here you choose the **section** (category) where it will be displayed:

```java
package com.ceos.widgetph.mitile;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class MiTileDescriptor extends WidgetDescriptor {

    public MiTileDescriptor() {
        super(MiTileWidget.WIDGET_TYPE,
              WidgetCategory.MONITOR,        // palette section
              "My Tile",                      // name in palette
              "/icon_16x16.png",              // icon
              "Short description");
    }

    @Override public Widget createWidget()        { return new MiTileWidget(); }
    @Override public String getType()            { return MiTileWidget.WIDGET_TYPE; }
    @Override public String getName()            { return "My Tile"; }
    @Override public WidgetCategory getCategory() { return WidgetCategory.MONITOR; }
    @Override public String getDescription()      { return "Short description."; }
}
```

### Palette Categories (`WidgetCategory`)

| Category | When to use it | Project Examples |
|---|---|---|
| `MONITOR` | Meters and read-only displays | gauge, sparkline, stock, highlow |
| `PLOT` | Graphs and Charts | linechart, areachart, radar, radial, donut, bar |
| `CONTROL` | Interactive widgets (write PV) | plusminus, regulator, feedbackregulator |
| `ARRAY` / `STRUCTURE` | Structured data | — |
| `MISC` | Miscellaneous | clock, timer, ikonli |

> The 3rd argument of `super(...)` is the initial category, but the editor uses the overridden `getCategory()`, which takes precedence.

## 2.3 The Representation: `*TileRepresentation.java`

Connects the model with the JavaFX node: listens for property changes and applies them to the node, and processes PV values. It extends `BaseTileRepresentation<N, M>`.

```java
package com.ceos.widgetph.mitile;

import org.csstudio.display.builder.representation.javafx.JFXUtil;
import eu.hansolo.tilesfx.Tile.SkinType;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class MiTileRepresentation extends BaseTileRepresentation<GenericTile, MiTileWidget> {

    @Override
    protected GenericTile createJFXNode() throws Exception {
        return new GenericTile(SkinType.SPARK_LINE);   // the node from Phase 1
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
            TileHelper.applyProperties(jfx_node, model_widget);   // common properties
            jfx_node.setBarColor(JFXUtil.convert(model_widget.propBarColor().getValue()));
        }

        // (optional) main PV value
        VType vtype = model_widget.runtimePropValue().getValue();
        if (vtype instanceof VNumber) {
            jfx_node.setValue(((VNumber) vtype).getValue().doubleValue());
        }
    }
}
```

Key points:

- `model_widget` and `jfx_node` are protected fields: the model and the node.
- `registerListeners()`: each custom property is listened to with `addUntypedPropertyListener(listener)`. The `listener` flags `dirty_look` and schedules an update.
- `updateChanges()`: inside `if (dirty_look.checkAndClear())` properties are reapplied to the node. Dynamic values (PVs) are processed outside the block.
- `TileHelper.applyProperties(jfx_node, model_widget)` applies **all** common properties (title, colors, font, min/max…) in a single call. Do not forget it.
- `JFXUtil.convert(WidgetColor)` → JavaFX `Color`. It also converts `WidgetFont`.

### Writing a Value to the PV (Interactive Widget)

To write, you must call `toolkit.fireWrite(model_widget, value)` (pattern from `PlusMinusTileRepresentation` and the regulators). An `active` flag prevents rewriting when the value comes from the PV itself:

```java
node.setTileMouseTransparent(false);   // allows click in runtime

// upon value change by user interaction:
if (active && jfx_node.getValue() != valueReceivedFromPV) {
    toolkit.fireWrite(model_widget, jfx_node.getValue());
}
```

### Multiple PVs in the Representation

When the tile uses several PVs, the representation reads each `runtimeValueN` and applies it to its corresponding series/channel (see `RadialChartTileRepresentation`):

```java
model_widget.runtimeValue1().addUntypedPropertyListener(listener);
// ... runtimeValue2, ... (and also unregisterListeners)

// in updateChanges():
valueData[0].setValue(sectorValue(model_widget.runtimeValue1()));
valueData[1].setValue(sectorValue(model_widget.runtimeValue2()));
```

## 2.4 The Runtime for Multiple PVs: `*TileRuntime.java`

This is what actually connects each `pvX` with its `runtimeValueX`. Without this, the `runtimeValueN` are never populated.

```java
package com.ceos.widgetph.mitile;

import com.ceos.widgetph.runtime.MultiPVWidgetRuntime;

public class MiTileRuntime extends MultiPVWidgetRuntime<MiTileWidget> {

    @Override
    public void start() {
        super.start();
        final MiTileWidget w = (MiTileWidget) widget;
        connectPV(w.propPV1(), w.runtimeValue1());
        connectPV(w.propPV2(), w.runtimeValue2());
        connectPV(w.propPV3(), w.runtimeValue3());
    }
}
```

- **1-PV tiles do not need a runtime**: they use the base runtime from `PVWidget`.
- **Multiple PV tiles** do: the runtime is created and registered in `TileWidgetRuntimes.java` (step 3.3).

---

# Phase 3: Registration in the Phoebus SPI

Phoebus discovers widgets via SPI services. In this project, the service classes already exist, and the `META-INF/services/*` files already point to them: you just need to **add your tile to the service classes** (3 Java files).

## 3.1 `GaugeTileService.java` (The Palette)

```java
import com.ceos.widgetph.mitile.MiTileDescriptor;

public class GaugeTileService implements WidgetsService {
    @Override
    public Collection<WidgetDescriptor> getWidgetDescriptors() {
        return List.of(
            new GaugeTileDescriptor(),
            new SparklineTileDescriptor(),
            new MiTileDescriptor()          // ← add here
        );
    }
}
```

## 3.2 `GaugeTileRepresentationService.java` (The Render)

Associates each descriptor with its representation:

```java
import com.ceos.widgetph.mitile.MiTileDescriptor;
import com.ceos.widgetph.mitile.MiTileRepresentation;

public class GaugeTileRepresentationService implements WidgetRepresentationsService {
    @Override
    public <TWP, TW> Map<WidgetDescriptor, WidgetRepresentationFactory<TWP, TW>> getWidgetRepresentationFactories() {
        return Map.ofEntries(
            entry(new GaugeTileDescriptor(), () -> (WidgetRepresentation) new GaugeTileRepresentation()),
            entry(new MiTileDescriptor(), () -> (WidgetRepresentation) new MiTileRepresentation())
        );
    }
}
```

## 3.3 `TileWidgetRuntimes.java` (Multi-PV Only)

```java
import com.ceos.widgetph.mitile.MiTileRuntime;
import com.ceos.widgetph.mitile.MiTileWidget;

public class TileWidgetRuntimes implements WidgetRuntimesService {
    @Override
    public Map<String, Supplier<WidgetRuntime<? extends Widget>>> getWidgetRuntimeFactories() {
        return Map.of(
            MiTileWidget.WIDGET_TYPE, () -> new MiTileRuntime(),
            RadarChartTileWidget.WIDGET_TYPE, () -> new RadarChartTileRuntime()
            // ...
        );
    }
}
```

---

# Phase 4: Compile and Test

```bash
mvn -q package -DskipTests
```

If it compiles without errors, the jar will be in `target/widget-0.13.0-SNAPSHOT.jar`. To test it in Phoebus, use the project's startup script (`launch_phoebus.bat` on Windows or `launch_phoebus.sh` on Linux/macOS), which starts Phoebus with the widgets jar in the classpath. The new tile should appear in the palette, in the section you specified in the descriptor.

---

# Checklist for a New Tile

| Step | File | Action |
|---|---|---|
| 1 | (optional) `com/ceos/widgetfx/MyNode.java` | Create FX node if you don't reuse `GenericTile`/`MedusaGaugeNode` |
| 2 | `widgetph/mitile/MiTileWidget.java` | `WIDGET_TYPE`, constructor, and extra properties (optional) |
| 3 | `widgetph/mitile/MiTileDescriptor.java` | Type, **category**, name, icon, description |
| 4 | `widgetph/mitile/MiTileRepresentation.java` | `createJFXNode`, listeners, `updateChanges` |
| 5 | (if multi-PV) `widgetph/mitile/MiTileRuntime.java` | `connectPV(pvN, runtimeValueN)` |
| 6 | `GaugeTileService.java` | Add descriptor |
| 7 | `GaugeTileRepresentationService.java` | Add descriptor → representation |
| 8 | (if multi-PV) `runtime/TileWidgetRuntimes.java` | Add runtime |
| 9 | — | `mvn -q package -DskipTests` and test in Phoebus |

**Do not touch** `TileNode`, `GenericTile`, `BaseTileWidget`, `BaseTileRepresentation`, `TileHelper`, nor the files in `META-INF/services`, unless you want a property to be common across all tiles.

---

# Annex A: Properties Inherited from `BaseTileWidget`

`backgroundColor`, `titleColor`, `valueColor`, `unitColor`, `title`, `unit`, `font`, `decimals`, `roundedCorners`, `shadowsEnabled`, `valueVisible`, `minValue`, `maxValue`, `animated`, `alarmColor`. Plus the main PV (`pv_name` + `runtimePropValue`) inherited from `PVWidget`.

# Annex B: TilesFX `SkinType`s Used in the Project

`GAUGE`, `METER`, `SPARK_LINE`, `BAR`, `DASHBOARD`, `PERCENTAGE`, `SMOOTHED_CHART`, `RADIAL_CHART`, `STOCK`, `HIGH_LOW`, `DONUT_CHART`, `CHARGE`, `RING_PROGRESS`, `DIGITAL_CLOCK`, `PLUS_MINUS`, `TIMER_CONTROL`, `LEADER_BOARD`… The full list is in `eu.hansolo.tilesfx.Tile.SkinType`. For Medusa, skins are in `eu.hansolo.medusa.Gauge.SkinType` (e.g., `DIGITAL`, `SLIM`, `BULLET_CHART`, `SPACE_X`).

# Annex C: Knowing Which Skin Property Paints What

Each skin reads different properties from the `Tile` (one line uses `getBarColor()`, another `getTextColor()`, another `getThreshold()`…). To figure out which property controls what you want to change, disassemble the skin with `javap`:

```bash
javap -p -c -classpath tilesfx-21.0.9.jar eu.hansolo.tilesfx.skins.SkinName
```

and look for `getBarColor`, `getTextColor`, `getValueColor`, etc. That is the method you need to expose in `GenericTile` (delegating to the `Tile`) and then add as a widget property. Real example: the `SparkLineTileSkin` paints the line using `getBarColor()`.

# Annex D: Examples to Copy Based on What You Need

| What you want to do | Reference Files |
|---|---|
| Minimal tile, no extra props | `sparkline/SparklineTileWidget`, `...Representation`, `...Descriptor` |
| Add color properties | `medusabulletchart/MedusaBulletChartWidget` (`threshold`, `thresholdColor`) |
| Interactive tile that writes to PV | `plusminus/PlusMinusTileRepresentation`, `regulator/RegulatorTileRepresentation` |
| Tile with multiple PVs (up to 6) and colors | `radialchart/` (Widget, Runtime, Representation) |
| Custom-made node | `widgetfx/RadarChartNode.java` + `radarchart/RadarChartTileRepresentation.java` |
