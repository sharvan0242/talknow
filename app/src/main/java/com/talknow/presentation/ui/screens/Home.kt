package com.talknow.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.talknow.presentation.util.TalkNowColors

/**
 * Home Screen - Main screen with Call Now button
 * Shows online users count, coins balance, and navigation
 */
@Composable
fun HomeScreen(
    onlineUsersCount: Int = 342,
    coinsBalance: Int = 1250,
    onCallNowClick: () -> Unit = {},
    onNavigateToFriends: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    var selectedNav by remember { mutableStateOf("home") }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(TalkNowColors.BackgroundDark)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)
        ) {
            // Top Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TalkNowColors.CardBg)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(TalkNowColors.Purple.copy(alpha = 0.1f))
                            .padding(8.dp)
                    ) {
                        Text("🟢", fontSize = 12.sp)
                        Column {
                            Text("Online", fontSize = 10.sp, color = TalkNowColors.TextGray)
                            Text("$onlineUsersCount", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TalkNowColors.TextWhite)
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(TalkNowColors.Pink.copy(alpha = 0.1f))
                            .padding(8.dp)
                    ) {
                        Text("🪙", fontSize = 12.sp)
                        Text(coinsBalance.toString(), fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TalkNowColors.TextWhite)
                    }
                }
            }

            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Ready to Connect?", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = TalkNowColors.TextWhite)
                Text("Meet random people instantly", fontSize = 14.sp, color = TalkNowColors.TextGray, modifier = Modifier.padding(top = 8.dp, bottom = 32.dp))

                // Call Button
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .clip(CircleShape)
                        .background(
                            brush = androidx.compose.ui.graphics.Brush.linearGradient(
                                colors = listOf(TalkNowColors.Purple, TalkNowColors.Pink)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = onCallNowClick,
                        modifier = Modifier.size(150.dp).clip(CircleShape),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = CircleShape
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("📞", fontSize = 40.sp)
                            Text("CALL", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                }
            }
        }

        // Bottom Nav
        NavigationBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            containerColor = TalkNowColors.CardBg
        ) {
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                label = { Text("Home") },
                selected = selectedNav == "home",
                onClick = { selectedNav = "home" },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = TalkNowColors.Purple,
                    selectedTextColor = TalkNowColors.Purple,
                    unselectedIconColor = TalkNowColors.TextGray
                )
            )

            NavigationBarItem(
                icon = { Icon(Icons.Filled.Groups, contentDescription = null) },
                label = { Text("Friends") },
                selected = selectedNav == "friends",
                onClick = { selectedNav = "friends"; onNavigateToFriends() },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = TalkNowColors.Purple,
                    unselectedIconColor = TalkNowColors.TextGray
                )
            )

            NavigationBarItem(
                icon = { Icon(Icons.Filled.Person, contentDescription = null) },
                label = { Text("Profile") },
                selected = selectedNav == "profile",
                onClick = { selectedNav = "profile"; onNavigateToProfile() },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = TalkNowColors.Purple,
                    unselectedIconColor = TalkNowColors.TextGray
                )
            )
        }
    }
}
