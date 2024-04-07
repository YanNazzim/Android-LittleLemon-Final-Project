@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.littlelemon

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter



@Composable
fun LowerPanel(navController: NavHostController, dishes: List<Dish> = listOf()) {
    Column {
        WeeklySpecialCard()
        LazyColumn {
            itemsIndexed(dishes) { _, dish ->
                MenuDish(navController, dish)
            }
        }
    }
}

@Composable
fun WeeklySpecialCard() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.weekly_special),
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuDish(navController: NavHostController? = null, dish: Dish) {
    Card(onClick = {
        Log.d("AAA", "Click ${dish.id}")
        navController?.navigate(DishDetails.route + "/${dish.id}")
    }) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Column {
                Text(text = dish.name, style = MaterialTheme.typography.h2)
                Text(text = dish.description,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxWidth(.75f).padding(top = 5.dp, bottom = 5.dp))
                Text(text = "$ ${dish.price}", style = MaterialTheme.typography.body2)
            }
            CoilImage(
                data = dish.imageResource,
                contentDescription = null,
                modifier = Modifier.size(100.dp).clip(RoundedCornerShape(8.dp))
            )
        }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        thickness = 1.dp,
        color = LittleLemonColor.yellow
    )
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CoilImage(
    data: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    val painter: Painter = rememberImagePainter(data = data)
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
    )
}
