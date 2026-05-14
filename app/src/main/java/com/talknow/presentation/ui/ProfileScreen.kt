package com.talknow.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.talknow.presentation.util.*

@Composable
fun ProfileScreen(
    username: String = "John Doe",
    email: String = "john@example.com",
    gender: String = "Male",
    interests: List<String> = listOf("Gaming", "Friendship"),
    coinsBalance: Int = 1250,
    totalCalls: Int = 42,
    friendsCount: Int = 15,
    onEditClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    var isEditMode by remember { mutableStateOf(false) }

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
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TalkNowColors.CardBg)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = TalkNowColors.TextWhite
                    )
                }

                Text(
                    text = "Profile",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TalkNowColors.TextWhite,
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = { isEditMode = !isEditMode }) {
                    Icon(
                        imageVector = if (isEditMode) Icons.Filled.Done else Icons.Filled.Edit,
                        contentDescription = null,
                        tint = TalkNowColors.Purple
                    )
                }
            }

            // Scrollable Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Avatar
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(TalkNowColors.Purple),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(50.dp)
                    )
                }

                // Username
                ProfileField(label = "Username", value = username, enabled = isEditMode)

                // Email
                ProfileField(label = "Email", value = email, enabled = false)

                // Gender
                ProfileField(label = "Gender", value = gender, enabled = false)

                // Interests
                Column {
                    Text(
                        text = "Interests",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TalkNowColors.TextGray
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        items(interests) { interest ->
                            AssistChip(
                                onClick = { },
                                label = { Text(interest, fontSize = 12.sp) },
                                modifier = Modifier.height(32.dp),
                                colors = AssistChipDefaults.assistChipColors(
                                    containerColor = TalkNowColors.CardBg
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Statistics Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = TalkNowColors.CardBg)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Coins
                        StatColumn(
                            label = "Coins",
                            value = coinsBalance.toString(),
                            icon = "🪙",
                            modifier = Modifier.weight(1f)
                        )

                        Divider(
                            modifier = Modifier
                                .width(1.dp)
                                .height(40.dp),
                            color = TalkNowColors.BackgroundDark
                        )

                        // Calls
                        StatColumn(
                            label = "Calls",
                            value = totalCalls.toString(),
                            icon = "📞",
                            modifier = Modifier.weight(1f)
                        )

                        Divider(
                            modifier = Modifier
                                .width(1.dp)
                                .height(40.dp),
                            color = TalkNowColors.BackgroundDark
                        )

                        // Friends
                        StatColumn(
                            label = "Friends",
                            value = friendsCount.toString(),
                            icon = "👥",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // Logout Button
                Button(
                    onClick = onLogoutClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red.copy(alpha = 0.1f)
                    )
                ) {
                    Text(
                        text = "Logout",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        // Bottom Navigation
        NavigationBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            containerColor = TalkNowColors.CardBg
        ) {
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                label = { Text("Home") },
                selected = false,
                onClick = { },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = TalkNowColors.TextGray
                )
            )

            NavigationBarItem(
                icon = { Icon(Icons.Filled.Groups, contentDescription = null) },
                label = { Text("Friends") },
                selected = false,
                onClick = { },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = TalkNowColors.TextGray
                )
            )

            NavigationBarItem(
                icon = { Icon(Icons.Filled.Person, contentDescription = null) },
                label = { Text("Profile") },
                selected = true,
                onClick = { },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = TalkNowColors.Purple,
                    selectedTextColor = TalkNowColors.Purple,
                    unselectedIconColor = TalkNowColors.TextGray
                )
            )
        }
    }
}

@Composable
fun ProfileField(
    label: String,
    value: String,
    enabled: Boolean = false
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = TalkNowColors.TextGray,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = { },
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                disabledBorderColor = TalkNowColors.CardBg,
                disabledTextColor = TalkNowColors.TextWhite
            )
        )
    }
}

@Composable
fun StatColumn(
    label: String,
    value: String,
    icon: String = "",
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = icon,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = TalkNowColors.TextWhite
        )
        Text(
            text = label,
            fontSize = 10.sp,
            color = TalkNowColors.TextGray
        )
    }
}
