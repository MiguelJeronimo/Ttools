package com.example.TibiaTools.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.TibiaTools.domain.model.SpellDetail
import com.example.TibiaTools.domain.model.SpellInfoData
import com.example.TibiaTools.presentation.components.ErrorMessage
import com.example.TibiaTools.presentation.components.Loader
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme
import com.example.TibiaTools.presentation.viewmodel.ViewModelSpell
import org.koin.androidx.compose.koinViewModel

@Composable
fun SpellProfileScreen(
    modifier: Modifier = Modifier,
    id: String,
    viewModel: ViewModelSpell = koinViewModel()
){
    LaunchedEffect(id) {
        viewModel.setSpell(id = id)
    }
    val state = viewModel.spell.collectAsStateWithLifecycle()
    SpellProfileScreen(
        modifier = modifier,
        state = state.value,
        onRetry = {
            viewModel.setSpell(id = id)
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SpellProfileScreen(
    modifier: Modifier = Modifier,
    state: ViewModelSpell.UIState,
    onRetry: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp)
    ) { innerPadding ->
        when(val data = state){
            is ViewModelSpell.UIState.Idle -> {}
            is ViewModelSpell.UIState.Loader -> {
                Loader()
            }
            is ViewModelSpell.UIState.Error -> {
                ErrorMessage(
                    modifier = Modifier.fillMaxSize().padding(innerPadding),
                    onRetry = onRetry
                )
            }
            is ViewModelSpell.UIState.Success -> {
                val spell = data.data
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    val info = spell?.spellInformation

                    Text(
                        text = spell?.name ?: "Chained Penance",
                        style = MaterialTheme.typography.displayMediumEmphasized.copy(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold
                        ),
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(28.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "WORDS",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    letterSpacing = 1.5.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                            )

                            Text(
                                text = info?.formula.takeIf { !it.isNullOrBlank() } ?: "exori med pug",
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 26.sp
                                ),
                                color = MaterialTheme.colorScheme.primary
                            )

                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                if (info?.isTypeInstant == true || spell == null) {
                                    SpellBadge(text = "Instant")
                                }
                                if (info?.isTypeRune == true) {
                                    SpellBadge(text = "Rune")
                                }
                                if (info?.isPremiumOnly == true || spell == null) {
                                    SpellBadge(text = "Premium")
                                }
                            }
                        }
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            StatCard(
                                modifier = Modifier.weight(1f),
                                label = "LEVEL",
                                value = info?.level.takeIf { !it.isNullOrBlank() } ?: "70"
                            )
                            StatCard(
                                modifier = Modifier.weight(1f),
                                label = "MANA",
                                value = info?.mana.takeIf { !it.isNullOrBlank() } ?: "180"
                            )
                            StatCard(
                                modifier = Modifier.weight(1f),
                                label = "PRICE",
                                value = "0 gp"
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            StatCard(
                                modifier = Modifier.weight(1f),
                                label = "COOLDOWN",
                                value = info?.cooldownAlone?.let { "${it}s" } ?: "4s"
                            )
                            StatCard(
                                modifier = Modifier.weight(1f),
                                label = "GROUP",
                                value = if (info?.isGroupAttack == true) "Attack" else if (info?.isGroupHealing == true) "Healing" else if (info?.isGroupSupport == true) "Support" else "—"
                            )
                            StatCard(
                                modifier = Modifier.weight(1f),
                                label = "SOUL",
                                value = info?.soulPoints?.toString() ?: "0"
                            )
                        }
                    }


                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Vocations",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.onBackground
                        )

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            val vocations = info?.vocation.takeIf { !it.isNullOrEmpty() } ?: listOf("Monk")
                            vocations.forEach { vocation ->
                                Surface(
                                    shape = RoundedCornerShape(16.dp),
                                    color = MaterialTheme.colorScheme.surfaceContainerHighest
                                ) {
                                    Text(
                                        text = vocation,
                                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                        style = MaterialTheme.typography.labelLarge.copy(
                                            fontWeight = FontWeight.SemiBold
                                        ),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
fun SpellBadge(text: String) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(name = "SpellProfile Light Preview", showBackground = true)
@Composable
fun SpellProfileScreenPreview() {
    TibiaToolsTheme(darkTheme = false) {
        SpellProfileScreen(
            modifier = Modifier.fillMaxSize(),
            state = ViewModelSpell.UIState.Success(
                data = SpellDetail(
                    name = "Chained Penance",
                    spellInformation = SpellInfoData(
                        formula = "exori med pug",
                        level = "70",
                        mana = "180",
                        cooldownAlone = 4,
                        soulPoints = 0,
                        isTypeInstant = true,
                        isPremiumOnly = true,
                        isGroupAttack = true,
                        vocation = listOf("Monk")
                    )
                )
            )
        )
    }
}

@Preview(name = "SpellProfile Dark Preview", showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun SpellProfileScreenDarkPreview() {
    TibiaToolsTheme(darkTheme = true) {
        SpellProfileScreen(
            modifier = Modifier.fillMaxSize(),
            state = ViewModelSpell.UIState.Success(
                data = SpellDetail(
                    name = "Chained Penance",
                    spellInformation = SpellInfoData(
                        formula = "exori med pug",
                        level = "70",
                        mana = "180",
                        cooldownAlone = 4,
                        soulPoints = 0,
                        isTypeInstant = true,
                        isPremiumOnly = true,
                        isGroupAttack = true,
                        vocation = listOf("Monk")
                    )
                )
            )
        )
    }
}
