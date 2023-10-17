package stenograffia.app.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = white,
    onPrimary = black,
    primaryVariant = white,
    secondary = black,
    onSecondary = white,
    background = darkBlue,
    onBackground = paleRed
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = black,
    onPrimary = white,
    primaryVariant = black,
    secondary = white,
    onSecondary = black,
    background = white,
    onBackground = paleRed


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun STENOGRAFFIAAPPTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}