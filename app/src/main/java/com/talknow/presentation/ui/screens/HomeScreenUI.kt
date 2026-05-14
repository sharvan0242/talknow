package com.talknow.presentation.ui

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

// Home Screen Composable
@Composable
fun HomeScreenUI(
    onlineUsersCount: Int = 342,
    coinsBalance: Int = 1250,
    onCallNowClick: () -> Unit = {},
    onNavigateToFriends: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    var selectedNavItem by remember { mutableStateOf("home") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(TalkNowColors.BackgroundDark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)
        ) {
            // Top Status Bar
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
                            Text("$onlineUsersCount Users", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TalkNowColors.TextWhite)
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
                        Column(horizontalAlignment = Alignment.End) {
                            Text("Coins", fontSize = 10.sp, color = TalkNowColors.TextGray)
                            Text(coinsBalance.toString(), fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TalkNowColors.TextWhite)
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Ready to Connect?", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = TalkNowColors.TextWhite, modifier = Modifier.padding(bottom = 12.dp))
                Text("Tap below to meet random people instantly", fontSize = 14.sp, color = TalkNowColors.TextGray, modifier = Modifier.padding(bottom = 48.dp))

                Box(
                    modifier = Modifier
                        .size(200.dp)
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
                        modifier = Modifier
                            .size(180.dp)
                            .clip(CircleShape),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = CircleShape
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text("📞", fontSize = 48.sp, modifier = Modifier.padding(bottom = 8.dp))
                            Text("CALL NOW", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White, modifier = Modifier.padding(horizontal = 12.dp), maxLines = 2)
                        }
                    }
                }
            }
        }

        NavigationBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            containerColor = TalkNowColors.CardBg
        ) {
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                label = { Text("Home") },
                selected = selectedNavItem == "home",
                onClick = { selectedNavItem = "home" },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = TalkNowColors.Purple,
                    selectedTextColor = TalkNowColors.Purple,
                    unselectedIconColor = TalkNowColors.TextGray
                )
            )

            NavigationBarItem(
                icon = { Icon(Icons.Filled.Groups, contentDescription = null) },
                label = { Text("Friends") },
                selected = selectedNavItem == "friends",
                onClick = { selectedNavItem = "friends"; onNavigateToFriends() },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = TalkNowColors.Purple,
                    selectedTextColor = TalkNowColors.Purple,
                    unselectedIconColor = TalkNowColors.TextGray
                )
            )

            NavigationBarItem(
                icon = { Icon(Icons.Filled.Person, contentDescription = null) },
                label = { Text("Profile") },
                selected = selectedNavItem == "profile",
                onClick = { selectedNavItem = "profile"; onNavigateToProfile() },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = TalkNowColors.Purple,
                    selectedTextColor = TalkNowColors.Purple,
                    unselectedIconColor = TalkNowColors.TextGray
                )
            )
        }
    }
}
