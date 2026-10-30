package com.example.TibiaTools.presentation.screens.tools

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.TibiaTools.operations.BlessingCalculator
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme
import com.example.TibiaTools.utils.GoldFormatter


@Composable
fun BlessingScreen(
    modifier: Modifier = Modifier
) {
    var levelText by rememberSaveable { mutableStateOf("") }

    // Independent state for each of the 8 blessings
    var isSolitudeChecked by rememberSaveable { mutableStateOf(true) }
    var isPhoenixChecked by rememberSaveable { mutableStateOf(true) }
    var isSunsChecked by rememberSaveable { mutableStateOf(true) }
    var isShieldingChecked by rememberSaveable { mutableStateOf(true) }
    var isEmbraceChecked by rememberSaveable { mutableStateOf(true) }
    var isHeartChecked by rememberSaveable { mutableStateOf(false) }
    var isBloodChecked by rememberSaveable { mutableStateOf(false) }
    var isTwistChecked by rememberSaveable { mutableStateOf(true) }

    val level = levelText.toIntOrNull()?.takeIf { it >= 1 }
    val isError = levelText.isNotEmpty() && level == null

    val regularUnitCost = if (level != null) BlessingCalculator.regularBlessingCost(level) else 0L
    val twistUnitCost = if (level != null) BlessingCalculator.twistOfFateCost(level) else 0L
    val enhancedUnitCost = if (level != null) BlessingCalculator.enhancedBlessingCost(level) else 0L

    val solitudeCost = if (isSolitudeChecked) regularUnitCost else 0L
    val phoenixCost = if (isPhoenixChecked) regularUnitCost else 0L
    val sunsCost = if (isSunsChecked) regularUnitCost else 0L
    val shieldingCost = if (isShieldingChecked) regularUnitCost else 0L
    val embraceCost = if (isEmbraceChecked) regularUnitCost else 0L
    val heartCost = if (isHeartChecked) enhancedUnitCost else 0L
    val bloodCost = if (isBloodChecked) enhancedUnitCost else 0L
    val twistCost = if (isTwistChecked) twistUnitCost else 0L

    val grandTotal = solitudeCost + phoenixCost + sunsCost + shieldingCost + embraceCost + heartCost + bloodCost + twistCost

    val selectedCount = listOf(
        isSolitudeChecked,
        isPhoenixChecked,
        isSunsChecked,
        isShieldingChecked,
        isEmbraceChecked,
        isHeartChecked,
        isBloodChecked,
        isTwistChecked
    ).count { it }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = levelText,
            onValueChange = { levelText = it },
            label = { Text(text = "Character level") },
            shape = MaterialTheme.shapes.large,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = isError,
            singleLine = true,
            supportingText = {
                if (isError) {
                    Text(text = "Invalid level (must be >= 1)")
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        TotalCard(
            modifier = Modifier.fillMaxWidth(),
            total = GoldFormatter.format(grandTotal),
            subtitle = "$selectedCount of 8 selected"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLargeIncreased.copy(all = CornerSize(35.dp)),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    BlessCost(
                        title = "Wisdom of Solitude",
                        subtitle = "Regular",
                        amount = GoldFormatter.format(regularUnitCost),
                        checked = isSolitudeChecked,
                        onCheckedChange = { isSolitudeChecked = it }
                    )
                    HorizontalDivider()
                }
                item {
                    BlessCost(
                        title = "Spark of the Phoenix",
                        subtitle = "Regular",
                        amount = GoldFormatter.format(regularUnitCost),
                        checked = isPhoenixChecked,
                        onCheckedChange = { isPhoenixChecked = it }
                    )
                    HorizontalDivider()
                }
                item {
                    BlessCost(
                        title = "Fire of the Suns",
                        subtitle = "Regular",
                        amount = GoldFormatter.format(regularUnitCost),
                        checked = isSunsChecked,
                        onCheckedChange = { isSunsChecked = it }
                    )
                    HorizontalDivider()
                }
                item {
                    BlessCost(
                        title = "Spiritual Shielding",
                        subtitle = "Regular",
                        amount = GoldFormatter.format(regularUnitCost),
                        checked = isShieldingChecked,
                        onCheckedChange = { isShieldingChecked = it }
                    )
                    HorizontalDivider()
                }
                item {
                    BlessCost(
                        title = "Embrace of Tibia",
                        subtitle = "Regular",
                        amount = GoldFormatter.format(regularUnitCost),
                        checked = isEmbraceChecked,
                        onCheckedChange = { isEmbraceChecked = it }
                    )
                    HorizontalDivider()
                }
                item {
                    BlessCost(
                        title = "Heart of the Mountain",
                        subtitle = "Enhanced",
                        amount = GoldFormatter.format(enhancedUnitCost),
                        checked = isHeartChecked,
                        onCheckedChange = { isHeartChecked = it }
                    )
                    HorizontalDivider()
                }
                item {
                    BlessCost(
                        title = "Blood of the Mountain",
                        subtitle = "Enhanced",
                        amount = GoldFormatter.format(enhancedUnitCost),
                        checked = isBloodChecked,
                        onCheckedChange = { isBloodChecked = it }
                    )
                    HorizontalDivider()
                }
                item {
                    BlessCost(
                        title = "Twist of Fate",
                        subtitle = "PvP",
                        amount = GoldFormatter.format(twistUnitCost),
                        checked = isTwistChecked,
                        onCheckedChange = { isTwistChecked = it }
                    )
                }
            }
        }
    }
}

@Composable
fun TotalCard(
    modifier: Modifier = Modifier,
    total: String,
    subtitle: String = "8 of 8 selected"
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLargeIncreased.copy(all = CornerSize(35.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "TOTAL",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                )
                Text(
                    text = total,
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
fun BlessCost(
    modifier: Modifier = Modifier,
    title: String = "Blessing",
    subtitle: String = "Regular",
    amount: String = "10,000",
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!checked) }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
            )
        }
        Text(
            modifier = Modifier.weight(1f),
            text = "$amount gp",
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BlessingScreenPreview() {
    TibiaToolsTheme {
        BlessingScreen(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TotalCardPreview() {
    TibiaToolsTheme {
        TotalCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            total = "198,000"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BlessCostPreview() {
    TibiaToolsTheme {
        BlessCost(
            title = "Regular Blessings (5x)",
            amount = "100,000",
            checked = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }
}