# Guía de desarrollo de Tiles para Phoebus (WidgetTest)

Esta guía explica, desde cero, cómo funciona el desarrollo de widgets ("tiles") en este
proyecto y cómo agregar una tile nueva. Está pensada para alguien que no ha trabajado
antes con este código: después de leerla deberías poder crear tiles nuevas y entender
las que ya existen.

Todo widget de Phoebus se desarrolla en **dos fases**:

1. **Crear (o reutilizar) un nodo JavaFX**: el componente visual (un `Pane`, un
   `Canvas`, un control de una librería…).
2. **Envolverlo con las clases de Phoebus**: el modelo (`Widget`), el descriptor
   (`WidgetDescriptor`) y la representación (`WidgetRepresentation`), que conectan el
   nodo con el editor y el runtime de Phoebus.

---

## Estructura del proyecto

```
src/main/java/com/ceos/widgetfx/          ← Nodos JavaFX (Fase 1)
├── TileNode.java                          ← Interfaz común de todos los nodos
├── GenericTile.java                       ← Nodo genérico que envuelve TilesFX
├── MedusaGaugeNode.java                   ← Nodo que envuelve Medusa (Gauge)
├── RadarChartNode.java                    ← Nodo con gráfico radar propio
├── RegulatorNode.java                     ← Nodo con control circular de regulators
└── IkonliIconNode.java                    ← Nodo de iconos

src/main/java/com/ceos/widgetph/           ← Clases Phoebus (Fase 2)
├── base/
│   ├── BaseTileWidget.java                ← Propiedades comunes del modelo
│   ├── BaseTileRepresentation.java        ← Listeners y lógica común
│   └── TileHelper.java                    ← Aplica las propiedades comunes al nodo
├── sparkline/                             ← Ejemplo de tile MÍNIMA (sin props extra)
├── plusminus/                             ← Ejemplo de tile INTERACTIVA (escribe PV)
├── radialchart/                           ← Ejemplo de tile con VARIOS PVs (6)
├── regulator/                             ← Ejemplo con control externo
├── GaugeTileService.java                  ← Registro de widgets en la paleta (EDITAR)
├── GaugeTileRepresentationService.java    ← Registro de representaciones (EDITAR)
└── runtime/
    └── TileWidgetRuntimes.java            ← Registro de runtimes multi-PV (EDITAR)

src/main/resources/META-INF/services/      ← SPI de Phoebus (NO TOCAR, ya configurado)
├── org.csstudio.display.builder.model.spi.WidgetsService
├── org.csstudio.display.builder.representation.spi.WidgetRepresentationsService
└── org.csstudio.display.builder.runtime.spi.WidgetRuntimesService
```

---

# Fase 1: el nodo JavaFX

Cualquier clase que herede de `javafx.scene.Node` puede ser un widget de Phoebus:
`Pane`, `StackPane`, `Canvas`, `Group`, `Image`, etc.

En este proyecto no siempre hace falta crear un nodo nuevo. Lo más habitual es
**reutilizar uno existente** según la librería visual que quieras:

| Necesitas | Usa | Ejemplo real |
|---|---|---|
| Una tile de TilesFX (reloj, sparkline, chart, gauge…) | `GenericTile(SkinType.X)` | `SparklineTileRepresentation` |
| Un gauge de Medusa | `MedusaGaugeNode(SkinType.X)` | `MedusaDigitalRepresentation` |
| Un control circular de regulators | `RegulatorNode` | `RegulatorTileRepresentation` |
| Algo hecho a medida | Crear tu propio nodo que implemente `TileNode` | `RadarChartNode` |

## 1.1 Reutilizar `GenericTile`

Para una tile de TilesFX solo hay que indicar el `SkinType`:

```java
GenericTile node = new GenericTile(SkinType.SPARK_LINE);
```

`GenericTile` ya expone métodos para cambiar colores, título, min/max, threshold, etc.
(delega al `Tile` de TilesFX). Ejemplo de métodos útiles: `setTitle`, `setBarColor`,
`setThreshold`, `setChartData`, `setTilesFXSeries`, `setTextColor`, `setValue`.

## 1.2 Crear un nodo propio

Un nodo nuevo implementa la interfaz `TileNode`, que define todos los `setXxx` que
aplican las propiedades comunes (título, colores, fuente, min/max…). Si tu nodo no
soporta alguna propiedad, la implementas como método vacío `{ }`.

```java
public class MyNode extends Pane implements TileNode {
    private final MyControl control = new MyControl();

    public MyNode() {
        this.getChildren().add(control);
    }

    @Override public void setTitle(String title) { control.setTitle(title); }
    @Override public void setBarColor(Color color) { control.setLineColor(color); }
    // ... el resto de métodos de TileNode, vacíos si no aplican

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

`layoutChildren()` es obligatorio cuando el nodo es un `Pane`: redimensiona el control
interno al tamaño que Phoebus le asigne al widget.

## 1.3 ⚠️ El ratón: `setMouseTransparent` (importante)

En Phoebus, si el nodo es **transparente al ratón**, un clic en el editor selecciona la
tile. Si el nodo **consume el ratón** (porque tiene botones, un knob, etc.), el clic lo
captura el propio widget y entonces hay que **sombreadarlo** (arrastrar una caja) para
seleccionarlo.

Todos los nodos se crean con el ratón transparente por defecto:

```java
// GenericTile.java
tile.setMouseTransparent(true);
```

Solo se desactiva cuando el widget es **interactivo en runtime** (botones +/-,
perilla del regulator…):

```java
// PlusMinusTileRepresentation.java
node.setTileMouseTransparent(false);
```

**Regla práctica**: si tu tile es de solo lectura (un medidor, un gráfico), déjalo
transparente y se seleccionará con un clic. Si debe ser operable por el usuario,
desactiva el ratón y acepta que en el editor se seleccione sombreando.

---

# Fase 2: envolver con las clases de Phoebus

## 2.1 El modelo: `*TileWidget.java`

Es el modelo de datos del widget. Extiende `BaseTileWidget`, que a su vez extiende
`PVWidget` (por eso la tile puede leer/escribir PVs). La clase base ya define muchas
propiedades comunes:

`backgroundColor`, `titleColor`, `valueColor`, `unitColor`, `title`, `unit`, `font`,
`decimals`, `roundedCorners`, `shadowsEnabled`, `valueVisible`, `minValue`, `maxValue`,
`animated`, `alarmColor` y el PV principal (`runtimePropValue`).

Lo mínimo para una tile sin propiedades extra (caso `SparklineTileWidget`):

```java
package com.ceos.widgetph.mitile;

import com.ceos.widgetph.base.BaseTileWidget;

public class MiTileWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "mitile";   // id único, va en los .bob

    public MiTileWidget() {
        super(WIDGET_TYPE);
    }
}
```

### Propiedades propias

Se crean como campos `WidgetProperty<?>` y se agregan en `defineProperties()`. Cada
propiedad tiene una **categoría** que controla dónde aparece en el panel de propiedades:

- `WidgetPropertyCategory.DISPLAY` → colores, textos, apariencia.
- `WidgetPropertyCategory.BEHAVIOR` → PVs y comportamiento.
- `WidgetPropertyCategory.CONTROL` → propiedades de interacción.

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

Otros descriptores útiles de `CommonWidgetProperties`:

| Tipo | Descriptor |
|---|---|
| Color | `newColorPropertyDescriptor(cat, "name", "Label")` |
| Número (double) | `newDoublePropertyDescriptor(...)` |
| Entero | `newIntegerPropertyDescriptor(...)` |
| Booleano | `newBooleanPropertyDescriptor(...)` |
| Texto | `newStringPropertyDescriptor(...)` |
| Nombre de PV | `newPVNamePropertyDescriptor(cat, "pv1", "Label")` |
| Valor de runtime de un PV | `newRuntimeValue("runtimeValue1", "Label")` |

El valor por defecto se pasa en `createProperty(this, default)`.

### 1 PV vs varios PVs en la misma tile

**Un solo PV**: no hace falta nada extra; el `PVWidget` base ya conecta el PV y lo deja
en `runtimePropValue`. Lo lees en la representación con
`model_widget.runtimePropValue().getValue()` (ver `TileHelper`).

**Varios PVs (hasta N)**: se declara un `pvX` por cada PV y un `runtimeValueX` por cada
valor que recibirá:

```java
private WidgetProperty<String> pv1, pv2, pv3;            // BEHAVIOR
private WidgetProperty<VType> runtimeValue1, runtimeValue2, runtimeValue3;

// en defineProperties():
pv1 = CommonWidgetProperties.newPVNamePropertyDescriptor(
        WidgetPropertyCategory.BEHAVIOR, "pv1", "Value 1 PV").createProperty(this, "");
properties.add(pv1);
// ... pv2, pv3 ...

runtimeValue1 = CommonWidgetProperties.newRuntimeValue("runtimeValue1", "Value 1")
        .createProperty(this, null);
properties.add(runtimeValue1);
// ... runtimeValue2, runtimeValue3 ...
```

Este es el patrón de `RadarChartTileWidget` y `RadialChartTileWidget` (que usan 6 PVs).
Los `runtimeValueN` **no los llena Phoebus solo**: hay que conectarlos con un runtime
(ver sección 2.4).

## 2.2 El descriptor: `*TileDescriptor.java`

Define cómo aparece la tile en la paleta del editor. Aquí se elige la **sección**
(categoría) donde aparecerá:

```java
package com.ceos.widgetph.mitile;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class MiTileDescriptor extends WidgetDescriptor {

    public MiTileDescriptor() {
        super(MiTileWidget.WIDGET_TYPE,
              WidgetCategory.MONITOR,        // sección de la paleta
              "Mi Tile",                      // nombre en la paleta
              "/icono_16x16.png",             // ícono
              "Descripción corta");
    }

    @Override public Widget createWidget()        { return new MiTileWidget(); }
    @Override public String getType()            { return MiTileWidget.WIDGET_TYPE; }
    @Override public String getName()            { return "Mi Tile"; }
    @Override public WidgetCategory getCategory() { return WidgetCategory.MONITOR; }
    @Override public String getDescription()      { return "Descripción corta."; }
}
```

### Categorías de la paleta (`WidgetCategory`)

| Categoría | Cuándo usarla | Ejemplos del proyecto |
|---|---|---|
| `MONITOR` | Medidores y visualización de lectura | gauge, sparkline, stock, highlow |
| `PLOT` | Gráficas | linechart, areachart, radar, radial, donut, bar |
| `CONTROL` | Widgets interactivos (escriben PV) | plusminus, regulator, feedbackregulator |
| `ARRAY` / `STRUCTURE` | Datos estructurados | — |
| `MISC` | Varios | clock, timer, ikonli |

> El 3.º argumento del `super(...)` es la categoría inicial, pero el editor usa el
> `getCategory()` sobreescrito, que es el que manda.

## 2.3 La representación: `*TileRepresentation.java`

Conecta el modelo con el nodo JavaFX: escucha cambios de propiedades y los aplica al
nodo, y procesa los valores de los PVs. Extiende `BaseTileRepresentation<N, M>`.

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
        return new GenericTile(SkinType.SPARK_LINE);   // el nodo de la Fase 1
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
            TileHelper.applyProperties(jfx_node, model_widget);   // propiedades comunes
            jfx_node.setBarColor(JFXUtil.convert(model_widget.propBarColor().getValue()));
        }

        // (opcional) valor del PV principal
        VType vtype = model_widget.runtimePropValue().getValue();
        if (vtype instanceof VNumber) {
            jfx_node.setValue(((VNumber) vtype).getValue().doubleValue());
        }
    }
}
```

Puntos clave:

- `model_widget` y `jfx_node` son campos protegidos: el modelo y el nodo.
- `registerListeners()`: cada propiedad propia se escucha con
  `addUntypedPropertyListener(listener)`. El `listener` marca el `dirty_look` y agenda
  una actualización.
- `updateChanges()`: dentro de `if (dirty_look.checkAndClear())` se reaplican las
  propiedades al nodo. Fuera del bloque se procesan los valores dinámicos (los PVs).
- `TileHelper.applyProperties(jfx_node, model_widget)` aplica **todas** las propiedades
  comunes (título, colores, fuente, min/max…) con una sola llamada. No la olvides.
- `JFXUtil.convert(WidgetColor)` → `Color` de JavaFX. También convierte `WidgetFont`.

### Escribir un valor al PV (widget interactivo)

Para escribir hay que llamar a `toolkit.fireWrite(model_widget, valor)` (patrón de
`PlusMinusTileRepresentation` y de los reguladores). Un flag `active` evita reescribir
cuando el valor llega desde el propio PV:

```java
node.setTileMouseTransparent(false);   // permite clic en runtime

// al cambiar el valor por interacción del usuario:
if (active && jfx_node.getValue() != valorRecibidoDelPV) {
    toolkit.fireWrite(model_widget, jfx_node.getValue());
}
```

### Varios PVs en la representación

Cuando la tile usa varios PVs, la representación lee cada `runtimeValueN` y lo aplica a
su serie/canal correspondiente (ver `RadialChartTileRepresentation`):

```java
model_widget.runtimeValue1().addUntypedPropertyListener(listener);
// ... runtimeValue2, ... (y también unregisterListeners)

// en updateChanges():
valueData[0].setValue(sectorValue(model_widget.runtimeValue1()));
valueData[1].setValue(sectorValue(model_widget.runtimeValue2()));
```

## 2.4 El runtime para varios PVs: `*TileRuntime.java`

Es lo que realmente conecta cada `pvX` con su `runtimeValueX`. Sin esto, los
`runtimeValueN` nunca se llenan.

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

- Las tiles de **1 PV no necesitan runtime**: usan el runtime base de `PVWidget`.
- Las tiles de **varios PVs** sí: se crea el runtime y se registra en
  `TileWidgetRuntimes.java` (paso 3.3).

---

# Fase 3: registro en el SPI de Phoebus

Phoebus descubre los widgets mediante servicios SPI. En este proyecto las clases de
servicio ya existen y los archivos `META-INF/services/*` ya apuntan a ellas: solo tienes
que **añadir tu tile a las clases de servicio** (3 archivos de Java).

## 3.1 `GaugeTileService.java` (la paleta)

```java
import com.ceos.widgetph.mitile.MiTileDescriptor;

public class GaugeTileService implements WidgetsService {
    @Override
    public Collection<WidgetDescriptor> getWidgetDescriptors() {
        return List.of(
            new GaugeTileDescriptor(),
            new SparklineTileDescriptor(),
            new MiTileDescriptor()          // ← añade aquí
        );
    }
}
```

## 3.2 `GaugeTileRepresentationService.java` (el render)

Asocia cada descriptor con su representación:

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

## 3.3 `TileWidgetRuntimes.java` (solo multi-PV)

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

# Fase 4: compilar y probar

```bash
mvn -q package -DskipTests
```

Si compila sin errores, el jar queda en `target/widget-0.13.0-SNAPSHOT.jar`. Para
probarlo en Phoebus usa el script de arranque del proyecto (`launch_phoebus.bat` en
Windows o `launch_phoebus.sh` en Linux/macOS), que levanta Phoebus con el jar de los
widgets en el classpath. La tile nueva debe aparecer en la paleta, en la sección que le
pusiste en el descriptor.

---

# Checklist para una tile nueva

| Paso | Archivo | Qué haces |
|---|---|---|
| 1 | (opcional) `com/ceos/widgetfx/MyNode.java` | Crear nodo FX si no reutilizas `GenericTile`/`MedusaGaugeNode` |
| 2 | `widgetph/mitile/MiTileWidget.java` | `WIDGET_TYPE`, constructor y propiedades extra (opcional) |
| 3 | `widgetph/mitile/MiTileDescriptor.java` | Tipo, **categoría**, nombre, ícono, descripción |
| 4 | `widgetph/mitile/MiTileRepresentation.java` | `createJFXNode`, listeners, `updateChanges` |
| 5 | (si multi-PV) `widgetph/mitile/MiTileRuntime.java` | `connectPV(pvN, runtimeValueN)` |
| 6 | `GaugeTileService.java` | Añadir descriptor |
| 7 | `GaugeTileRepresentationService.java` | Añadir descriptor → representación |
| 8 | (si multi-PV) `runtime/TileWidgetRuntimes.java` | Añadir runtime |
| 9 | — | `mvn -q package -DskipTests` y probar en Phoebus |

**No toques** `TileNode`, `GenericTile`, `BaseTileWidget`, `BaseTileRepresentation`,
`TileHelper` ni los archivos de `META-INF/services`, salvo que quieras que una
propiedad sea común a todos los tiles.

---

# Anexo A: propiedades que heredas de `BaseTileWidget`

`backgroundColor`, `titleColor`, `valueColor`, `unitColor`, `title`, `unit`, `font`,
`decimals`, `roundedCorners`, `shadowsEnabled`, `valueVisible`, `minValue`, `maxValue`,
`animated`, `alarmColor`. Más el PV principal (`pv_name` + `runtimePropValue`) heredado
de `PVWidget`.

# Anexo B: `SkinType` de TilesFX usados en el proyecto

`GAUGE`, `METER`, `SPARK_LINE`, `BAR`, `DASHBOARD`, `PERCENTAGE`, `SMOOTHED_CHART`,
`RADIAL_CHART`, `STOCK`, `HIGH_LOW`, `DONUT_CHART`, `CHARGE`, `RING_PROGRESS`,
`DIGITAL_CLOCK`, `PLUS_MINUS`, `TIMER_CONTROL`, `LEADER_BOARD`… La lista completa está
en `eu.hansolo.tilesfx.Tile.SkinType`. Para Medusa, los skins van en
`eu.hansolo.medusa.Gauge.SkinType` (p. ej. `DIGITAL`, `SLIM`, `BULLET_CHART`, `SPACE_X`).

# Anexo C: saber qué propiedad del skin pinta cada cosa

Cada skin lee propiedades distintas del `Tile` (una línea usa `getBarColor()`, otra
`getTextColor()`, otra `getThreshold()`…). Para averiguar qué propiedad controla lo que
quieres cambiar, desensambla el skin con `javap`:

```bash
javap -p -c -classpath tilesfx-21.0.9.jar eu.hansolo.tilesfx.skins.NombreDelSkin
```

y busca `getBarColor`, `getTextColor`, `getValueColor`, etc. Ese es el método que tienes
que exponer en `GenericTile` (delegando al `Tile`) y luego añadir como propiedad del
widget. Ejemplo real: el skin `SparkLineTileSkin` pinta la línea con `getBarColor()`.

# Anexo D: ejemplos para copiar según lo que necesites

| Lo que quieres hacer | Archivos de referencia |
|---|---|
| Tile mínima, sin props extra | `sparkline/SparklineTileWidget`, `...Representation`, `...Descriptor` |
| Añadir propiedades de color | `medusabulletchart/MedusaBulletChartWidget` (`threshold`, `thresholdColor`) |
| Tile interactiva que escribe al PV | `plusminus/PlusMinusTileRepresentation`, `regulator/RegulatorTileRepresentation` |
| Tile con varios PVs (hasta 6) y colores | `radialchart/` (Widget, Runtime, Representation) |
| Nodo propio a medida | `widgetfx/RadarChartNode.java` + `radarchart/RadarChartTileRepresentation.java` |
