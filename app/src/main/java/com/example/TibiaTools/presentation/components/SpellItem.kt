package com.example.TibiaTools.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.ShieldMoon
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Healing
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.TibiaTools.presentation.routes.FilterOptionSpells
import com.example.TibiaTools.presentation.routes.GroupSpell
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme

@Composable
fun SpellItem(
    modifier: Modifier = Modifier,
    name: String = "Animate Dead Rune",
    formula: String = "adana mort",
    level: String = "27",
    mana: String = "600",
    type: GroupSpell = GroupSpell.UNKNOW
) {
    val icon = when(type){
        GroupSpell.ATTACK->{
            Icons.Rounded.Bolt
        }
        GroupSpell.HEALING->{
            Icons.Rounded.Healing
        }
        GroupSpell.SUPPORT, GroupSpell.UNKNOW->{
            Icons.Default.ShieldMoon
        }
    }

    val colorBackground =  when(type){
        GroupSpell.ATTACK->{
            MaterialTheme.colorScheme.errorContainer
        }
        GroupSpell.HEALING->{
            MaterialTheme.colorScheme.primaryContainer
        }
        GroupSpell.SUPPORT, GroupSpell.UNKNOW->{
            MaterialTheme.colorScheme.tertiaryContainer
        }
    }

    val colorIcon =  when(type){
        GroupSpell.ATTACK->{
            MaterialTheme.colorScheme.error
        }
        GroupSpell.HEALING->{
            MaterialTheme.colorScheme.primary
        }
        GroupSpell.SUPPORT, GroupSpell.UNKNOW->{
            MaterialTheme.colorScheme.tertiary
        }
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(
                    color = colorBackground,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Spell Type",
                tint = colorIcon,
                modifier = Modifier.size(22.dp)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = formula,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 13.sp,
                    fontFamily = FontFamily.Monospace
                ),
                color = MaterialTheme.colorScheme.primary
            )
        }

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = "Lv $level",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "$mana mana",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 12.sp
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
            )
        }
    }
}

@Preview(name = "SpellItem Light Preview", showBackground = true)
@Composable
fun SpellItemPreview() {
    TibiaToolsTheme(darkTheme = false) {
        SpellItem(
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(name = "SpellItem Dark Preview", showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun SpellItemDarkPreview() {
    TibiaToolsTheme(darkTheme = true) {
        SpellItem(
            modifier = Modifier.fillMaxWidth()
        )
    }
}
