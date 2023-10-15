package stenograffia.app.ui.composables.dialogs

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import org.junit.Rule
import org.junit.Test
import stenograffia.app.ui.theme.STENOGRAFFIAAPPTheme


class SelectionButtonsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun selectionButtonTest() {

        composeTestRule.setContent {

            STENOGRAFFIAAPPTheme {
                SelectionButtons(
                    onClickOk = {},
                    onClickCancel = {},
                    minWidthScreen = 50.dp
                )
            }
        }

        composeTestRule.onNodeWithTag("OkClick")
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithTag("CancelClick")
            .assertIsDisplayed()
            .performClick()

    }
}