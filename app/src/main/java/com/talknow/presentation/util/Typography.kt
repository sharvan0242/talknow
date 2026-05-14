package com.talknow.presentation.util

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun HeadlineText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = TalkNowColors.TextWhite
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = TalkNowColors.TextWhite
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun SubtitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = TalkNowColors.TextGray
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = TalkNowColors.TextWhite
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    )
}

@Composable
fun CaptionText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = TalkNowColors.TextGray
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    )
}
