@file:Suppress("ConstPropertyName")

package io.github.xfy9326.atools.compose.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, widthDp = 50, heightDp = 50)
@Composable
private fun PreviewDivider() {
    MaterialTheme {
        Alignment
        Column(verticalArrangement = Arrangement.Center) {
            Divider(
                direction = DividerDirection.Horizontal
            )
        }
        Row(horizontalArrangement = Arrangement.Center) {
            Divider(
                direction = DividerDirection.Vertical
            )
        }
    }
}

private const val DividerAlpha = 0.12f

enum class DividerDirection {
    Vertical,
    Horizontal
}

@Composable
fun Divider(
    direction: DividerDirection,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface.copy(alpha = DividerAlpha),
    thickness: Dp = 1.dp,
    padding: PaddingValues = PaddingValues()
) {
    val targetThickness = if (thickness == Dp.Hairline) {
        (1f / LocalDensity.current.density).dp
    } else {
        thickness
    }
    Box(
        modifier
            .padding(padding)
            .let {
                when (direction) {
                    DividerDirection.Horizontal -> it
                        .fillMaxWidth()
                        .height(targetThickness)

                    DividerDirection.Vertical -> it
                        .fillMaxHeight()
                        .width(targetThickness)
                }
            }
            .background(color = color)
    )
}
