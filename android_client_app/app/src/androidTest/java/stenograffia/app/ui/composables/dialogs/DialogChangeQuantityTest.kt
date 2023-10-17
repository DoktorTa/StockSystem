package stenograffia.app.ui.composables.dialogs

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test
import stenograffia.app.domain.model.PaintType
import stenograffia.app.models.PaintModelUi
import stenograffia.app.ui.screens.stock.paint.PaintScreen
import stenograffia.app.ui.theme.STENOGRAFFIAAPPTheme


class DialogChangeQuantityTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun dialogChangeQuantityBoxTest() {

        composeTestRule.setContent {
            STENOGRAFFIAAPPTheme {
                val differenceQuantity = remember { mutableIntStateOf(0) }
                val quantityOnStock = remember { mutableIntStateOf(6) }
                val showDialogChangeQuantity = remember { mutableStateOf(true) }

                DialogChangeQuantityBox(
                    showDialogChangeQuantity = showDialogChangeQuantity,
                    differenceQuantity = differenceQuantity,
                    quantityOnStock = quantityOnStock,
                    onClickOk = { /* TODO */ }
                )
            }
        }

        composeTestRule.onNodeWithTag("ButtonMinus6")
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithTag("ButtonMinus1")
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithTag("ButtonPlus1")
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithTag("ButtonPlus6")
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithTag("TextDifferenceQuantity")
            .assertIsDisplayed()
    }

    @Test
    fun dialogChangeQuantityBoxCheckButtonsTest() {

        composeTestRule.setContent {
            STENOGRAFFIAAPPTheme {
                val differenceQuantity = remember { mutableIntStateOf(0) }
                val quantityOnStock = remember { mutableIntStateOf(5) }
                val showDialogChangeQuantity = remember { mutableStateOf(true) }

                DialogChangeQuantityBox(
                    showDialogChangeQuantity = showDialogChangeQuantity,
                    differenceQuantity = differenceQuantity,
                    quantityOnStock = quantityOnStock,
                    onClickOk = { /* TODO */ }
                )
            }
        }

        composeTestRule.onNodeWithTag("ButtonMinus6")
            .assertIsDisplayed()
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag("ButtonMinus1")
            .assertIsDisplayed()
            .assertIsEnabled()

        composeTestRule.onNodeWithTag("ButtonPlus1")
            .assertIsDisplayed()
            .assertIsEnabled()

        composeTestRule.onNodeWithTag("ButtonPlus6")
            .assertIsDisplayed()
            .assertIsEnabled()
    }
}
