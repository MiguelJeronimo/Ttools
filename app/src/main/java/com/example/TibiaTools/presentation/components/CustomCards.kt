package com.example.TibiaTools.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLargeIncreased.copy(all = CornerSize(35.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 12.sp,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.Both
                    )
                )
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )
            )
        }
    }
}

data class ListDataCardModel(
    val key: String,
    val value: String
)

@Composable
fun ListDataCard(
    modifier: Modifier = Modifier,
    data: List<ListDataCardModel> = emptyList()
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLargeIncreased.copy(all = CornerSize(35.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            data.forEachIndexed { index, model ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = model.key,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = model.value,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                if (data.lastIndex != index) {
                    Spacer(modifier = Modifier.height(25.dp))
                }
            }
        }
    }
}

data class CharacterCardModel(
    val name: String,
    val world: String,
    val isOnline: Boolean = false,
    val isMain: Boolean = false,
    val isViewing: Boolean = false,
    val isTraded: Boolean = false,
    val isDeleted: Boolean = false,
    val showChevron: Boolean = false,
    val onClick: (() -> Unit)? = null
)

@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    character: CharacterCardModel
) {
    CharacterCard(
        modifier = modifier,
        name = character.name,
        world = character.world,
        isOnline = character.isOnline,
        isMain = character.isMain,
        isViewing = character.isViewing,
        isTraded = character.isTraded,
        isDeleted = character.isDeleted,
        showChevron = character.showChevron,
        onClick = character.onClick
    )
}

@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    name: String,
    world: String,
    isOnline: Boolean = false,
    isMain: Boolean = false,
    isViewing: Boolean = false,
    isTraded: Boolean = false,
    isDeleted: Boolean = false,
    showChevron: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (onClick != null) Modifier.clickable { onClick() } else Modifier
            ),
        shape = MaterialTheme.shapes.extraLargeIncreased.copy(all = CornerSize(35.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Avatar Circle con Indicador de Estado (Online / Offline)
            Box(
                modifier = Modifier.size(52.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            color = Color(0xFF004D64).copy(alpha = 0.5f),
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = name.firstOrNull()?.uppercase() ?: "",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                // Indicador de Estado (Punto verde o gris)
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .align(Alignment.BottomEnd)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceContainerLow,
                            shape = CircleShape
                        )
                        .padding(2.dp)
                        .background(
                            color = if (isOnline) Color(0xFF3FB872) else Color(0xFF7A838A),
                            shape = CircleShape
                        )
                )
            }

            // Info principal del personaje
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                // Nombre (Tachado si está eliminado)
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        textDecoration = if (isDeleted) TextDecoration.LineThrough else TextDecoration.None
                    ),
                    color = if (isDeleted) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f) else MaterialTheme.colorScheme.onSurface
                )

                // Mundo y Estado (Online / Offline)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Public,
                        contentDescription = "World Icon",
                        modifier = Modifier.size(14.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "$world · ",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = if (isOnline) "Online" else "Offline",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = if (isOnline) Color(0xFF3FB872) else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                    )
                }

                // Badges / Tags (Main, Viewing, Traded, Deleted)
                val hasTags = isMain || isViewing || isTraded || isDeleted
                if (hasTags) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        if (isMain) {
                            CharacterBadge(
                                icon = Icons.Default.Star,
                                text = "Main",
                                containerColor = Color(0xFF004D64),
                                contentColor = Color(0xFF67D3FF)
                            )
                        }
                        if (isViewing) {
                            CharacterBadge(
                                icon = Icons.Default.RemoveRedEye,
                                text = "Viewing",
                                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        if (isTraded) {
                            CharacterBadge(
                                icon = Icons.Default.SwapHoriz,
                                text = "Traded",
                                containerColor = Color(0xFF3B3858),
                                contentColor = Color(0xFFC6C2EA)
                            )
                        }
                        if (isDeleted) {
                            CharacterBadge(
                                icon = Icons.Default.Delete,
                                text = "Deleted",
                                containerColor = Color(0xFF601410),
                                contentColor = Color(0xFFF2B8B5)
                            )
                        }
                    }
                }
            }

            // Flecha a la derecha opcional
            if (showChevron) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Navigate",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
private fun CharacterBadge(
    icon: ImageVector,
    text: String,
    containerColor: Color,
    contentColor: Color
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(containerColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(12.dp),
                tint = contentColor
            )
            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = contentColor
            )
        }
    }
}

@Preview(name = "Custom Card", showBackground = true)
@Composable
fun CustomCardPreview() {
    TibiaToolsTheme(darkTheme = true) {
        CustomCard(
            title = "Total Online",
            subtitle = "17,445",
            modifier = Modifier
        )
    }
}

@Preview(name = "List Data Card", showBackground = true)
@Composable
fun ListDataCardPreview() {
    TibiaToolsTheme(darkTheme = true) {
        ListDataCard(
            data = listOf(
                ListDataCardModel(key = "Total Online", value = "17,445"),
                ListDataCardModel(key = "Total Online", value = "17,445"),
                ListDataCardModel(key = "Total Online", value = "17,445"),
                ListDataCardModel(key = "Total Online", value = "17,445")
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}

@Preview(name = "Character Cards", showBackground = true)
@Composable
fun CharacterCardsPreview() {
    TibiaToolsTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CharacterCard(
                name = "Fenna Lightfoot",
                world = "Pacera",
                isOnline = true,
                isMain = true,
                isViewing = true
            )
            CharacterCard(
                name = "Edric Hollowmere",
                world = "Bona",
                isOnline = false,
                showChevron = true
            )
            CharacterCard(
                name = "Isolde Emberfall",
                world = "Menera",
                isOnline = false,
                isTraded = true,
                showChevron = true
            )
            CharacterCard(
                name = "Nyra Starfall",
                world = "Monza",
                isOnline = false,
                isDeleted = true
            )
        }
    }
}
