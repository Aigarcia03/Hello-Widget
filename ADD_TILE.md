# Guía para agregar un nuevo Tile

## Estructura del proyecto

```
src/main/java/com/ceos/widgetfx/          ← Nodos JavaFX
├── TileNode.java                          ← Interfaz (NO TOCAR)
└── GenericTile.java                       ← Nodo genérico (NO TOCAR)

src/main/java/com/ceos/widgetph/           ← Clases Phoebus
├── base/
│   ├── BaseTileWidget.java                ← Propiedades comunes (NO TOCAR)
│   ├── BaseTileRepresentation.java        ← Listeners comunes (NO TOCAR)
│   └── TileHelper.java                    ← Aplica propiedades comunes (NO TOCAR)
├── gauge/
│   ├── GaugeTileWidget.java               ← Ejemplo con props extra
│   ├── GaugeTileRepresentation.java
│   └── GaugeTileDescriptor.java
├── sparkline/
│   ├── SparklineTileWidget.java           ← Ejemplo sin props extra
│   ├── SparklineTileRepresentation.java
│   └── SparklineTileDescriptor.java
├── GaugeTileService.java                  ← Registro de widgets (EDITAR)
└── GaugeTileRepresentationService.java    ← Registro de renders (EDITAR)

src/main/resources/META-INF/services/      ← SPI (NO TOCAR)
├── org.csstudio.display.builder.model.spi.WidgetsService
└── org.csstudio.display.builder.representation.spi.WidgetRepresentationsService
```

## Paso a paso para agregar un nuevo tile

### 1. Crear el paquete

Crea un subpaquete dentro de `com.ceos.widgetph` con el nombre del tile:
```
src/main/java/com/ceos/widgetph/mitile/
```

### 2. Crear el modelo (`*Widget.java`)

Si el tile solo necesita las propiedades comunes (Sparkline), hereda de `BaseTileWidget` y define un identificador único:

```java
package com.ceos.widgetph.mitile;

import com.ceos.widgetph.base.BaseTileWidget;

public class MiTileWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "mitile";

    public MiTileWidget() {
        super(WIDGET_TYPE);
    }
}
```

Si el tile necesita propiedades adicionales (como Gauge), extiende `BaseTileWidget` y agrega sus propias propiedades en `defineProperties()`:

```java
package com.ceos.widgetph.mitile;

import java.util.List;
import org.csstudio.display.builder.model.WidgetProperty;
import org.csstudio.display.builder.model.WidgetPropertyCategory;
import org.csstudio.display.builder.model.WidgetPropertyDescriptor;
import org.csstudio.display.builder.model.properties.CommonWidgetProperties;
import org.phoebus.ui.color.WidgetColor;
import com.ceos.widgetph.base.BaseTileWidget;

public class MiTileWidget extends BaseTileWidget {
    public static final String WIDGET_TYPE = "mitile";

    private WidgetProperty<WidgetColor> lineaColor;

    public MiTileWidget() {
        super(WIDGET_TYPE);
    }

    @Override
    protected void defineProperties(final List<WidgetProperty<?>> properties) {
        super.defineProperties(properties);
        WidgetPropertyDescriptor<WidgetColor> desc = CommonWidgetProperties.newColorPropertyDescriptor(
                WidgetPropertyCategory.DISPLAY, "lineaColor", "Linea Color");
        lineaColor = desc.createProperty(this, new WidgetColor(255, 255, 255));
        properties.add(lineaColor);
    }

    public WidgetProperty<WidgetColor> propLineaColor() { return lineaColor; }
}
```

### 3. Crear la representación (`*Representation.java`)

Hereda de `BaseTileRepresentation`, indica el `SkinType` y llama al helper:

```java
package com.ceos.widgetph.mitile;

import eu.hansolo.tilesfx.Tile.SkinType;
import com.ceos.widgetfx.GenericTile;
import com.ceos.widgetph.base.BaseTileRepresentation;
import com.ceos.widgetph.base.TileHelper;

public class MiTileRepresentation extends BaseTileRepresentation<GenericTile, MiTileWidget> {

    @Override
    protected GenericTile createJFXNode() throws Exception {
        return new GenericTile(SkinType.METER); // cambia el SkinType
    }

    @Override
    public void updateChanges() {
        super.updateChanges();
        if (dirty_look.checkAndClear()) {
            TileHelper.applyProperties(jfx_node, model_widget);
        }
    }
}
```

Si el tile tiene propiedades extra, también debes:
- Registrar sus listeners en `registerListeners()`
- Aplicarlas en `updateChanges()`
- Agregar los métodos necesarios en `TileNode` y `GenericTile` (ver sección avanzada)

```java
@Override
protected void registerListeners() {
    super.registerListeners();
    model_widget.propLineaColor().addUntypedPropertyListener(listener);
}

@Override
public void updateChanges() {
    super.updateChanges();
    if (dirty_look.checkAndClear()) {
        TileHelper.applyProperties(jfx_node, model_widget);
        jfx_node.setBarColor(JFXUtil.convert(model_widget.propLineaColor().getValue()));
    }
}
```

Nota: `JFXUtil.convert()` convierte `WidgetColor` a `javafx.scene.paint.Color`.

### 4. Crear el descriptor (`*Descriptor.java`)

Define cómo aparece el widget en la paleta de Phoebus. Debes sobreescribir `getCategory()` para controlar la categoría:

```java
package com.ceos.widgetph.mitile;

import org.csstudio.display.builder.model.Widget;
import org.csstudio.display.builder.model.WidgetCategory;
import org.csstudio.display.builder.model.WidgetDescriptor;

public class MiTileDescriptor extends WidgetDescriptor {

    public MiTileDescriptor() {
        super(MiTileWidget.WIDGET_TYPE,
              WidgetCategory.MONITOR,        // valor por defecto, getCategory() manda
              "Mi Tile",                      // nombre en la paleta
              "path/to/icon_16x16.png",       // ícono
              "Descripción del widget");       // descripción
    }

    @Override
    public Widget createWidget() { return new MiTileWidget(); }

    @Override
    public String getType()      { return MiTileWidget.WIDGET_TYPE; }

    @Override
    public String getName()      { return "Mi Tile"; }

    @Override
    public WidgetCategory getCategory() { return WidgetCategory.MONITOR; }

    @Override
    public String getDescription() { return "Descripción del widget."; }
}
```

**Categorías disponibles**: `GRAPHIC`, `MONITOR`, `CONTROL`, `PLOT`, `STRUCTURE`, `EXTENSIONS`, `MISC`.
Usa `PLOT` para gráficos (sparkline, etc.) y `MONITOR` para medidores (gauge, meter, etc.).

### 5. Registrar en los servicios SPI

Editar `GaugeTileService.java`:

```java
import com.ceos.widgetph.mitile.MiTileDescriptor;

public class GaugeTileService implements WidgetsService {
    @Override
    public Collection<WidgetDescriptor> getWidgetDescriptors() {
        return List.of(
            new GaugeTileDescriptor(),
            new SparklineTileDescriptor(),
            new MiTileDescriptor()
        );
    }
}
```

Editar `GaugeTileRepresentationService.java`:

```java
import com.ceos.widgetph.mitile.MiTileDescriptor;
import com.ceos.widgetph.mitile.MiTileRepresentation;

public class GaugeTileRepresentationService implements WidgetRepresentationsService {
    @Override
    public <TWP, TW> Map<WidgetDescriptor, WidgetRepresentationFactory<TWP, TW>> getWidgetRepresentationFactories() {
        return Map.ofEntries(
            entry(new GaugeTileDescriptor(), () -> (WidgetRepresentation) new GaugeTileRepresentation()),
            entry(new SparklineTileDescriptor(), () -> (WidgetRepresentation) new SparklineTileRepresentation()),
            entry(new MiTileDescriptor(), () -> (WidgetRepresentation) new MiTileRepresentation())
        );
    }
}
```

### 6. Compilar y probar

```bash
mvn clean package
```

Copia el `.jar` generado en `target/` a la carpeta de plugins de Phoebus y reinicia.

## Resumen

Por cada tile nuevo necesitas:

| Archivo | Líneas aprox | Contenido |
|---|---|---|
| `mitile/*Widget.java` | 5-30 | `WIDGET_TYPE` + propiedades extra (opcional) |
| `mitile/*Representation.java` | 12-30 | `SkinType` + `TileHelper.applyProperties()` |
| `mitile/*Descriptor.java` | 25 | Tipo, categoría, nombre, ícono |
| `GaugeTileService.java` | +1 línea | Agregar descriptor al `List.of()` |
| `GaugeTileRepresentationService.java` | +1 línea | Agregar fábrica al `Map.ofEntries()` |

**No tocas** `TileNode`, `GenericTile`, `BaseTileWidget`, `BaseTileRepresentation` ni `TileHelper`
a menos que necesites agregar una propiedad común a TODOS los tiles.

## Propiedades comunes vs específicas

- **`BaseTileWidget`**: backgroundColor, titleColor, valueColor, unitColor, title, unit, font,
  decimals, roundedCorners, shadowsEnabled, valueVisible, minValue, maxValue, animated.
- **`GaugeTileWidget`** (ejemplo con extras): agrega needleColor, barColor, barBackgroundColor,
  tickMarkColor, tickLabelColor, threshold, thresholdVisible.
- **Sparkline** usa solo las propiedades comunes de `BaseTileWidget`, sin extras.

## SkinTypes disponibles (TilesFX)

| SkinType | Descripción |
|---|---|
| `GAUGE` | Medidor con aguja |
| `METER` | Medidor tipo reloj |
| `SPARK_LINE` | Gráfico de línea temporal |
| `BAR` | Barra de progreso |
| `DASHBOARD` | Dashboard con aguja y valores |
| `PERCENTAGE` | Porcentaje circular |
| `CUSTOM` | Personalizado |
| ... | Ver `Tile.SkinType` para todos |

## Propiedades extra: agregar soporte en GenericTile

Si tu tile necesita una propiedad que no está soportada por `GenericTile`/`TileNode`:

1. Agrega el método en la interfaz `TileNode`
2. Implementa el método en `GenericTile` (delega al `Tile` de TilesFX)
3. Agrega la línea correspondiente en `TileHelper.applyProperties()` (si es común)
   o aplícala directamente en la representación del tile (si es específica)
