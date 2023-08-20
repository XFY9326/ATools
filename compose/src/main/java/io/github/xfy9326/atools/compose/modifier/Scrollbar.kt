@file:Suppress("ConstPropertyName")

package io.github.xfy9326.atools.compose.modifier

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, widthDp = 200, heightDp = 200)
@Composable
private fun PreviewScrollbar() {
    val verticalScrollState = rememberScrollState()
    val horizontalScrollState = rememberScrollState()
    MaterialTheme {
        Text(
            text = buildString {
                repeat(20) {
                    append("Test long long long long long text in line $it")
                    if (it < 20 - 1) append("\n")
                }
            },
            modifier = Modifier
                .size(200.dp)
                .horizontalScroll(horizontalScrollState)
                .verticalScroll(verticalScrollState)
                .verticalScrollbar(
                    verticalScrollState,
                    horizontalScrollState,
                    scrollBarStyle()
                )
                .horizontalScrollbar(
                    horizontalScrollState,
                    verticalScrollState,
                    scrollBarStyle()
                )
        )
    }
}

private const val DefaultScrollBarWidthDp = 4
private const val DefaultMinScrollBarHeightDp = 5
private const val DefaultScrollBarCornerRadiusDp = 2
private const val DefaultScrollBarPaddingEdgeDp = 1
private const val DefaultScrollBarShowAnimationMills = 150
private const val DefaultScrollBarHideAnimationMills = 500
private const val DefaultScrollBarAlpha = 0.7f

@Composable
fun scrollBarStyle(
    alwaysVisible: Boolean = false,
    shape: Shape = RoundedCornerShape(DefaultScrollBarCornerRadiusDp.dp),
    color: Color = MaterialTheme.colors.primary,
    alpha: Float = DefaultScrollBarAlpha,
    thickness: Dp = DefaultScrollBarWidthDp.dp,
    minLength: Dp = DefaultMinScrollBarHeightDp.dp,
    paddingEdge: Dp = DefaultScrollBarPaddingEdgeDp.dp,
    animationShowMills: Int = DefaultScrollBarShowAnimationMills,
    animationHideMills: Int = DefaultScrollBarHideAnimationMills,
): ScrollBarStyle =
    ScrollBarStyle(
        alwaysVisible = alwaysVisible,
        shape = shape,
        color = color,
        alpha = alpha,
        thickness = thickness,
        minLength = minLength,
        paddingEdge = paddingEdge,
        animationShowMills = animationShowMills,
        animationHideMills = animationHideMills
    )

data class ScrollBarStyle(
    val alwaysVisible: Boolean,
    val shape: Shape,
    val color: Color,
    val alpha: Float,
    val thickness: Dp,
    val minLength: Dp,
    val paddingEdge: Dp,
    val animationShowMills: Int,
    val animationHideMills: Int,
)

/**
 * Vertical Scrollbar
 * This methods must be used after verticalScroll() and horizontalScroll().
 * @param scrollState Scroll state from verticalScroll()
 * @param horizontalScrollState Scroll state from horizontalScroll()
 * @param style Scrollbar style
 */
fun Modifier.verticalScrollbar(
    scrollState: ScrollState,
    horizontalScrollState: ScrollState? = null,
    style: ScrollBarStyle
): Modifier = composed {
    val scrollBarAlpha = if (style.alwaysVisible) {
        style.alpha
    } else {
        val targetAlpha = if (scrollState.isScrollInProgress) style.alpha else 0f
        val animationMills = if (scrollState.isScrollInProgress) style.animationShowMills else style.animationHideMills
        val animatedAlpha by animateFloatAsState(
            targetValue = targetAlpha,
            animationSpec = tween(durationMillis = animationMills),
            label = "verticalScrollbarAnimatedAlpha"
        )
        animatedAlpha
    }
    drawWithContent {
        drawContent()

        val needDrawScrollbar = scrollState.isScrollInProgress || scrollBarAlpha > 0.0f

        if (needDrawScrollbar && scrollState.maxValue > 0) {
            val scrollPercent = scrollState.value.toFloat() / scrollState.maxValue
            val contentVisibleHeight = size.height - scrollState.maxValue
            val contentVisibleEnd = if (horizontalScrollState == null) {
                when (layoutDirection) {
                    LayoutDirection.Ltr -> size.width
                    LayoutDirection.Rtl -> 0f
                }
            } else {
                when (layoutDirection) {
                    LayoutDirection.Ltr -> size.width - horizontalScrollState.maxValue + horizontalScrollState.value
                    LayoutDirection.Rtl -> horizontalScrollState.value.toFloat()
                }
            }
            val scrollBarSize = Size(
                width = style.thickness.toPx(),
                height = (contentVisibleHeight * (contentVisibleHeight / size.height)).coerceAtLeast(style.minLength.toPx())
            )
            val scrollBarOffset = Offset(
                x = when (layoutDirection) {
                    LayoutDirection.Ltr -> contentVisibleEnd - scrollBarSize.width - style.paddingEdge.toPx()
                    LayoutDirection.Rtl -> contentVisibleEnd + style.paddingEdge.toPx()
                },
                y = scrollState.value + (contentVisibleHeight - scrollBarSize.height) * scrollPercent
            )
            val scrollBarOutline = style.shape.createOutline(scrollBarSize, layoutDirection, this)
            withTransform({
                translate(
                    left = scrollBarOffset.x,
                    top = scrollBarOffset.y
                )
            }) {
                drawOutline(
                    outline = scrollBarOutline,
                    color = style.color,
                    alpha = scrollBarAlpha
                )
            }
        }
    }
}

/**
 * Horizontal Scrollbar
 * This methods must be used after horizontalScroll() and verticalScroll().
 * @param scrollState Scroll state from horizontalScroll()
 * @param verticalScrollState Scroll state from verticalScroll()
 * @param style Scrollbar style
 */
fun Modifier.horizontalScrollbar(
    scrollState: ScrollState,
    verticalScrollState: ScrollState? = null,
    style: ScrollBarStyle
): Modifier = composed {
    val scrollBarAlpha = if (style.alwaysVisible) {
        style.alpha
    } else {
        val targetAlpha = if (scrollState.isScrollInProgress) style.alpha else 0f
        val animationMills = if (scrollState.isScrollInProgress) style.animationShowMills else style.animationHideMills
        val animatedAlpha by animateFloatAsState(
            targetValue = targetAlpha,
            animationSpec = tween(durationMillis = animationMills),
            label = "horizontalScrollbarAnimatedAlpha"
        )
        animatedAlpha
    }
    drawWithContent {
        drawContent()

        val needDrawScrollbar = scrollState.isScrollInProgress || scrollBarAlpha > 0.0f

        if (needDrawScrollbar && scrollState.maxValue > 0) {
            val scrollPercent = scrollState.value.toFloat() / scrollState.maxValue
            val contentVisibleWidth = size.width - scrollState.maxValue
            val contentVisibleBottom = if (verticalScrollState == null) {
                size.height
            } else {
                size.height - verticalScrollState.maxValue + verticalScrollState.value
            }
            val scrollBarSize = Size(
                width = (contentVisibleWidth * (contentVisibleWidth / size.width)).coerceAtLeast(style.minLength.toPx()),
                height = style.thickness.toPx()
            )
            val scrollBarOffset = Offset(
                x = scrollState.value + (contentVisibleWidth - scrollBarSize.width) * scrollPercent,
                y = contentVisibleBottom - scrollBarSize.height - style.paddingEdge.toPx()
            )
            val scrollBarOutline = style.shape.createOutline(scrollBarSize, layoutDirection, this)
            withTransform({
                translate(
                    left = scrollBarOffset.x,
                    top = scrollBarOffset.y
                )
            }) {
                drawOutline(
                    outline = scrollBarOutline,
                    color = style.color,
                    alpha = scrollBarAlpha
                )
            }
        }
    }
}
