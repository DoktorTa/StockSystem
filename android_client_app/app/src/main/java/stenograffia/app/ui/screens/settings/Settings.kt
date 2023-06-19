package stenograffia.app.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import stenograffia.app.R
import javax.inject.Singleton


@Composable
fun Settings(
    settingsViewModel: SettingsViewModel,
){
    val dataStoreSettings = DataStoreSettings(LocalContext.current)

    Column {
        Text(
            text = settingsViewModel.getUserName(),
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.settings_text_padding_start)
            )
        )
        Text(
            text = stringResource(id = R.string.setting_group_name, settingsViewModel.getUserStatus()!!.name),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.settings_text_padding_start)
            )
        )
        ThemeSelector(dataStoreSettings, settingsViewModel)
        LangSelector(dataStoreSettings, settingsViewModel)
        
    }
}

@Composable
fun ThemeSelector(
    dataStoreSettings: DataStoreSettings,
    settingsViewModel: SettingsViewModel
){
    val checkedState = remember { settingsViewModel.isDarkThemeEnabled }
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.settings_block_padding_start_end),
                end = dimensionResource(id = R.dimen.settings_block_padding_start_end),
                top = dimensionResource(id = R.dimen.settings_block_padding_top)
            )
            .background(color = MaterialTheme.colors.primary)
    ) {
        Text(
            text = stringResource(id = R.string.setting_name_theme),
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.settings_text_in_block_padding_start)
                )
                .align(Alignment.CenterVertically),
        )
        Spacer(Modifier.weight(1f, true))
        Switch(
            modifier = Modifier.padding(
                end = dimensionResource(id = R.dimen.settings_block_selector_padding_end)
            ),
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                coroutineScope.launch {
                    dataStoreSettings.saveTheme(it)
                }
            },
            colors = SwitchDefaults.colors(
                uncheckedTrackColor = Color(0xFFFFEBEE)
            )
        )
    }
}


@Composable
fun LangSelector(
    dataStoreSettings: DataStoreSettings,
    settingsViewModel: SettingsViewModel
) {
    val coroutineScope = rememberCoroutineScope()

    var expanded by remember { mutableStateOf(false) }
    val items = settingsViewModel.localeList.values
    val disabledValue = remember { settingsViewModel.localeActive }

    Row(
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.settings_block_padding_start_end),
                end = dimensionResource(id = R.dimen.settings_block_padding_start_end),
                top = dimensionResource(id = R.dimen.settings_block_padding_top)
            )
            .background(MaterialTheme.colors.primary)
    ) {
        Text(
            text = stringResource(id = R.string.setting_name_locale),
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.settings_text_in_block_padding_start)
                )
                .align(Alignment.CenterVertically),
        )
        Spacer(Modifier.weight(1f, true))
        IconButton(
            onClick = { expanded = true },
            modifier = Modifier.padding(
                end = dimensionResource(id = R.dimen.settings_block_selector_padding_end)
            )
        ) {
            Row(){
                Text(
                    text = disabledValue.value.language,
                    color = MaterialTheme.colors.secondary
                )
                Icon(
                    Icons.Outlined.ExpandMore,
                    contentDescription = "Выбор языка",
                    tint = MaterialTheme.colors.secondary
                    )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(x = Dp.Infinity, 0.dp)
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    disabledValue.value = s
                    expanded = false
                    coroutineScope.launch {
                        dataStoreSettings.saveLocale(s.language)
                    }
                }) {
                    Text(text = s.language)
                }
            }
        }
    }
}

