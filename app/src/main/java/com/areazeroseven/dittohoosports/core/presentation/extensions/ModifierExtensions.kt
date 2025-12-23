import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.horizontalGradientBorder(): Modifier = this.then(
    Modifier.border(
        width = 2.dp,
        brush = Brush.horizontalGradient(
            listOf(
                colorScheme.primaryContainer,
                colorScheme.secondaryContainer
            )
        ),
        shape = RoundedCornerShape(12.dp)
    )
)

@Composable
fun Modifier.clipRoundedCornerShape_25_dp(): Modifier = this.then(
    Modifier.clip(RoundedCornerShape(25.dp))
)

// Used for UI visualization/testing
@Composable
fun Modifier.borderRed(): Modifier = this.then(
    Modifier.border(1.dp, Color.Red)
)
