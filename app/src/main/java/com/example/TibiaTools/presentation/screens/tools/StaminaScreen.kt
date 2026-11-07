package com.example.TibiaTools.presentation.screens.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.TibiaTools.operations.StaminaCalculator
import com.example.TibiaTools.operations.StaminaTime
import com.example.TibiaTools.presentation.components.StaminaTextField
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme
import com.example.TibiaTools.utils.ex.StaminaLevel
import com.example.TibiaTools.utils.ex.level

@Composable
fun StaminaScreen(
    modifier: Modifier = Modifier
){
    var timeText by rememberSaveable { mutableStateOf("") }

    val staminaTime = remember(timeText) {
        if(timeText.isNotEmpty()) StaminaCalculator.parse(input = timeText) else null
    }

    val isError = timeText.isNotEmpty() && staminaTime == null

    val greenTimeState = remember(staminaTime) {
        StaminaCalculator.timeToGreenStamina(current = staminaTime?: StaminaTime(0, 0))
    }
    val fullTimeState = remember(staminaTime) {
        StaminaCalculator.timeToFullStamina(current = staminaTime?: StaminaTime(0, 0))
    }

    Column(
        modifier = modifier
    ) {
        StaminaTextField(
            label = "Current stamina (hh:mm)",
            level = timeText,
            onValueChange = {
                timeText = it
            },
            isError = isError,
            errorMessage = "Invalid format. Use hh:mm (0:00 - 42:00)"
        )
        Spacer(modifier = Modifier.size(20.dp))
        StaminaCard(modifier = Modifier.fillMaxWidth(), time = staminaTime)
        Spacer(modifier = Modifier.size(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
        ){
            CardHoursStamina(
                modifier = Modifier.weight(1f),
                color = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                ),
                subtitle = if (timeText.isNotEmpty() && staminaTime != null) greenTimeState.format() else "--:--",
                staminaTime = greenTimeState
            )
            Spacer(modifier = Modifier.size(10.dp))
            CardHoursStamina(
                modifier = Modifier.weight(1f),
                title = "To full (42:00)",
                subtitle = if (timeText.isNotEmpty() && staminaTime != null) fullTimeState.format() else "--:--",
                color = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                staminaTime = fullTimeState
            )
        }
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = "Offline regeneration starts 10 minutes after logout: 1 min per 3 min offline up to 39:00, then 1 min per 10 min.",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                ),
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    trim = LineHeightStyle.Trim.Both
                )
            )
        )
    }
}


@Composable
fun StaminaCard(modifier: Modifier = Modifier, time: StaminaTime?){
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLargeIncreased.copy(all = CornerSize(35.dp))
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "0:00",
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 12.sp)
                )
                Text(
                    text = "39:00",
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 12.sp)
                )
                Text(
                    text = "42:00",
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 12.sp)
                )
            }

            StaminaSlider(
                modifier = Modifier.fillMaxWidth(),
                time = time
            )
        }
    }
}

@Composable
fun CardHoursStamina(
    modifier: Modifier = Modifier,
    color: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ),
    title: String = "To green (39:00)",
    subtitle: String = "5h 00m",
    staminaTime: StaminaTime
){
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLargeIncreased.copy(all = CornerSize(35.dp)),
        colors = color
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 12.sp, fontWeight = FontWeight.Bold,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.Both
                    )
                ),
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f),
                text = title
            )
            Text(
                style = MaterialTheme.typography.displayMedium.copy(
                    fontSize = 26.sp,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.Both
                    )
                ),
                fontWeight = FontWeight.ExtraBold,
                text = subtitle
            )
        }
    }
}


/****
 * - Desde 39:00: verde #3FB872
 * - De 14:00 a 38:59: primario var(--p), #006684 en claro y #67D3FF en oscuro
 * - Menos de 14:00: rojo #E5484D
 *
 * Alrededor del relleno:
 * - Fondo de la barra, var(--c4): #DCE4E9 en claro, #363B3E en oscuro
 * - Marca de 39:00, var(--bg): #FBFCFE en claro, #191C1E en oscuro
 *
 * Ojo: el verde y el rojo están fijos en el código, así que no cambian con el tema, a diferencia del resto de la paleta.
 *
 * ****/

@Composable
fun StaminaSlider(
    modifier: Modifier = Modifier,
    time: StaminaTime? = StaminaTime(0,0)
){
    val staminaRed = Color(0xFFE5484D)
    val successColor = Color(0xFF3FB872)
    val staminaOrange = Color(0xFFF59E0B)

    val finalColor = when(time?.level()) {
        StaminaLevel.RED -> staminaRed
        StaminaLevel.ORANGE -> staminaOrange
        StaminaLevel.GREEN ->successColor
        StaminaLevel.EMPTY -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.primary
    }

    BoxWithConstraints(
        modifier = modifier
    ) {
        // 1. Calculamos la posición del indicador basado en el ancho total (maxWidth)
        // La marca de 39h sobre 42h es aproximadamente el 92.8% (39f / 42f)
        val greenMarkRatio = 39f / 42f

        // Multiplicamos el ancho total por la proporción y restamos la mitad del ancho del indicador (1.5.dp)
        val indicatorOffset = (maxWidth * greenMarkRatio) - 1.5.dp

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(14.dp)
                .clip(RoundedCornerShape(7.dp))
                .background(color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(time?.percent()?.coerceIn(0f, 1f) ?: 0f)
                    .height(14.dp)
                    .clip(RoundedCornerShape(7.dp))
                    .background(color = finalColor)
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(3.dp)
                    .offset(x = indicatorOffset) // Offset calculado en Dp usando maxWidth
                    .background(color = MaterialTheme.colorScheme.onSurface)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StaminaCardPreview(){
    TibiaToolsTheme(darkTheme = true) {
        StaminaCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            time = StaminaTime(39, 0)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun StaminaCardOrangePreview(){
    TibiaToolsTheme(darkTheme = true) {
        StaminaCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            time = StaminaTime(14, 0)
        )
    }
}

@Preview()
@Composable
fun StaminaCardRedPreview(){
    TibiaToolsTheme(darkTheme = true) {
        StaminaCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            time = StaminaTime(1, 0)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StaminaScreenPreview(){
    TibiaToolsTheme {
        StaminaScreen(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }
}


