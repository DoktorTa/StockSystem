package stenograffia.app.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import stenograffia.app.R

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 32.sp
    ),

    h4 = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp
    ),



    body2 = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    ),

    body1 = TextStyle(
        fontFamily = FontFamily(
            Font(
                resId = R.font.ibmplexmono_regular,
                weight = FontWeight.Normal,
                style = FontStyle.Normal)
        ),
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
    ),

    h2 = TextStyle(
        fontFamily = FontFamily(
            Font(
                resId = R.font.ibmplexmono_bold,
                weight = FontWeight.Bold,
                style = FontStyle.Normal)
        ),
    fontWeight = FontWeight.Bold,
    fontSize = 32.sp
    ),

    h3 = TextStyle(
        fontFamily = FontFamily(
            Font(
                resId = R.font.ibmplexmono_regular,
                weight = FontWeight.Normal,
                style = FontStyle.Normal)
        ),
    fontWeight = FontWeight.Normal,
    fontSize = 24.sp
    ),
)