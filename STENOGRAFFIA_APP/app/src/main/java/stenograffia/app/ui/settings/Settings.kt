package stenograffia.app.ui.settings

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch


@Composable
fun Settings(
    navController: NavController,
    settingsViewModel: SettingsViewModel,
){
    val dataStoreSettings = DataStoreSettings(LocalContext.current)

    Column {
        Text(
            text = "NAME NICK",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(start = 15.dp)
            )
        Text(
            text = "Group: ARTIST",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 15.dp)
        )
        ThemeSelector(dataStoreSettings, settingsViewModel)
        LangSelector()
        
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
            .padding(start = 15.dp, end = 15.dp, top = 5.dp)
            .background(MaterialTheme.colors.primary)
    ) {
        Text(
            text = "Night mod",
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .padding(start = 10.dp)
                .align(Alignment.CenterVertically),
        )
        Spacer(Modifier.weight(1f, true))
        Switch(
            modifier = Modifier.padding(end = 5.dp),
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
//    dataStoreSettings: DataStoreSettings,
//    settingsViewModel: SettingsViewModel
) {
//    val checkedState = remember { settingsViewModel.isDarkThemeEnabled }
//    val coroutineScope = rememberCoroutineScope()

    var expanded by remember { mutableStateOf(false) }
    val items = listOf("EN", "RU")
    var disabledValue = remember { mutableStateOf("EN") }

    Row(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 5.dp)
            .background(MaterialTheme.colors.primary)
    ) {
        Text(
            text = "Language",
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .padding(start = 10.dp)
                .align(Alignment.CenterVertically),
        )
        Spacer(Modifier.weight(1f, true))
        IconButton(
            onClick = { expanded = true },
            modifier = Modifier.padding(end = 5.dp)
        ) {
            Row(){
                Text(
                    text = disabledValue.value,
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
                }) {
                    Text(text = s)
                }
            }
        }
    }
}

