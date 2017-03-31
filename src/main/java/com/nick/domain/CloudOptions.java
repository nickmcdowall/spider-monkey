package com.nick.domain;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.bg.Background;
import com.kennycason.kumo.font.scale.FontScalar;
import com.kennycason.kumo.palette.ColorPalette;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(
        builder = "cloudOptions",
        init = "with*"
)
public interface CloudOptions {

    int width();
    int height();
    int padding();
    String fileFormat();
    CollisionMode collisionMode();
    Background background();
    ColorPalette colorPalette();
    FontScalar fontScalar();

}
