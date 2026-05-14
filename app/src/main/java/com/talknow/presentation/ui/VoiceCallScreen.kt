package com.talknow.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.talknow.presentation.util.TalkNowColors

/**
 * Voice Call Screen - Active call with user, timer, and controls
 */
@Composable
fun VoiceCallScreen(
    remoteUserName: String = "Sarah",
    callDurationSeconds: Int = 125,
    isMuted: Boolean = false,
    onMuteClick: (Boolean) -> Unit = {},
    onSpeakerClick: () -> Unit = {},
    onEndCallClick: () -> Unit = {},
    onNextUserClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(TalkNowColors.BackgroundDark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null, tint = TalkNowColors.TextWhite)
                }
                Text("In Call", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TalkNowColors.TextWhite)
                Box(modifier = Modifier.size(48.dp))
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Remote User Avatar
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(TalkNowColors.Purple, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("👤", fontSize = 60.sp)
            }

            // User Name
            Text(
                remoteUserName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TalkNowColors.TextWhite,
                modifier = Modifier.padding(top = 16.dp)
            )

            // Call Timer
            val minutes = callDurationSeconds / 60
            val seconds = callDurationSeconds % 60
            Text(
                String.format("%02d:%02d", minutes, seconds),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = TalkNowColors.Purple,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Controls Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Mute Button
                CircleButton(
                    icon = if (isMuted) "🔇" else "🔊",
                    label = "Mute",
                    isActive = isMuted,
                    onClick = { onMuteClick(!isMuted) },
                    modifier = Modifier.weight(1f)
                )

                // Speaker Button
                CircleButton(
                    icon = "📢",
                    label = "Speaker",
                    onClick = onSpeakerClick,
                    modifier = Modifier.weight(1f)
                )

                // End Call Button
                CircleButton(
                    icon = "📵",
                    label = "End",
                    isActive = true,
                    backgroundColor = Color.Red,
                    onClick = onEndCallClick,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Next User Button
            Button(
                onClick = onNextUserClick,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = TalkNowColors.Pink)
            ) {
                Text("Next User ⏭️", fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}

@Composable
fun CircleButton(
    icon: String,
    label: String,
    onClick: () -> Unit,
    isActive: Boolean = false,
    backgroundColor: Color = TalkNowColors.CardBg,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .size(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isActive) backgroundColor.copy(alpha = 0.8f) else backgroundColor
            )
        ) {
            Text(icon, fontSize = 24.sp)
        }
        Text(label, fontSize = 11.sp, color = TalkNowColors.TextGray, modifier = Modifier.padding(top = 4.dp))
    }
}
