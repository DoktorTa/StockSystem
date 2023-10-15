package stenograffia.app.ui.screens.stock.paint

import android.content.ClipboardManager
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test
import stenograffia.app.domain.model.PaintType
import stenograffia.app.models.PaintModelUi
import stenograffia.app.ui.theme.STENOGRAFFIAAPPTheme

class PaintScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun copyButtonsTest() {
        // Start the app
        composeTestRule.setContent {
            STENOGRAFFIAAPPTheme {
                PaintScreen(
                    navController = rememberNavController(),
                    copyPaintInfo = {},
                    paintModelUi = PaintModelUi(
                        id = 1,
                        type = PaintType.CANS_DEFAULT,
                        timeLabel = 10,
                        nameCreator = "MONTANA CANS",
                        nameLine = "BLK - 400ml",
                        codePaint = "BLK - 1242",
                        nameColor = "POTATO",
                        descriptionColor = "",
                        color = 0xff11f3,
                        quantityInStorage = 1,
                        similarColors = listOf(listOf()),
                        possibleToBuy = true,
                    )
                )
            }
        }

        composeTestRule.onNodeWithTag("CopyIconButton").performClick()
//        composeTestRule.
//        val clipboardManager =
//            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager


//        composeTestRule.onNodeWithText("Continue").performClick()
//
//        composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()
    }

}