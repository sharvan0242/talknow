package com.talknow.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.talknow.presentation.util.TalkNowColors

@Composable
fun Modifier.gradientBackground(
    colors: List<Color> = listOf(TalkNowColors.Purple, TalkNowColors.Pink),
    angle: Float = 45f
): Modifier = composed {
    val brush = remember {
        Brush.linearGradient(
            colors = colors,
            angle = angle
        )
    }
    this.then(
        Modifier.background(brush)
    )
}

@Composable
fun Modifier.purplePinkGradient(): Modifier = composed {
    gradientBackground(
        colors = listOf(TalkNowColors.Purple, TalkNowColors.Pink)
    )
}

@Composable
fun Modifier.bluePurpleGradient(): Modifier = composed {
    gradientBackground(
        colors = listOf(TalkNowColors.Blue, TalkNowColors.Purple)
    )
}

@Composable
fun Modifier.pinkBlueGradient(): Modifier = composed {
    gradientBackground(
        colors = listOf(TalkNowColors.Pink, TalkNowColors.Blue)
    )
}
