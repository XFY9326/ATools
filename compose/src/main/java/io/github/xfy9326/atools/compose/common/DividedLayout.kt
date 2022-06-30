package io.github.xfy9326.atools.compose.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.TABLET)
@Composable
private fun PreviewDividedLayout() {
    MaterialTheme {
        DividedLayout(
            contentUpStart = {
                Text(
                    text = "UpStart",
                    fontSize = 30.sp,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            },
            contentDownEnd = {
                Text(
                    text = "DownEnd",
                    fontSize = 30.sp,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
            }
        )
    }
}

@Composable
fun DividedLayout(
    modifier: Modifier = Modifier,
    weightUpStart: Float = 0.5f,
    weightDownEnd: Float = 0.5f,
    rowDividerContent: (@Composable RowScope.() -> Unit)? = DefaultRowDividerContent,
    columnDividerContent: (@Composable ColumnScope.() -> Unit)? = DefaultColumnDividerContent,
    modifierUpStart: Modifier = Modifier,
    modifierDownEnd: Modifier = Modifier,
    contentUpStart: @Composable BoxScope.() -> Unit,
    contentDownEnd: @Composable BoxScope.() -> Unit
) {
    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Row(modifier = modifier) {
            Box(
                modifier = Modifier
                    .weight(weightUpStart)
                    .fillMaxSize()
                    .then(modifierUpStart),
                content = contentUpStart
            )
            rowDividerContent?.invoke(this)
            Box(
                modifier = Modifier
                    .weight(weightDownEnd)
                    .fillMaxSize()
                    .then(modifierDownEnd),
                content = contentDownEnd
            )
        }
    } else {
        Column(modifier = modifier) {
            Box(
                modifier = Modifier
                    .weight(weightUpStart)
                    .fillMaxSize()
                    .then(modifierUpStart),
                content = contentUpStart
            )
            columnDividerContent?.invoke(this)
            Box(
                modifier = Modifier
                    .weight(weightDownEnd)
                    .fillMaxSize()
                    .then(modifierDownEnd),
                content = contentDownEnd
            )
        }
    }
}

private val DefaultRowDividerContent: @Composable RowScope.() -> Unit = {
    Divider(
        direction = DividerDirection.Vertical,
        thickness = 2.dp,
    )
}

private val DefaultColumnDividerContent: @Composable ColumnScope.() -> Unit = {
    Divider(
        direction = DividerDirection.Horizontal,
        thickness = 2.dp,
    )
}
