package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun DishDetails(id: Int) {
    val dish = requireNotNull(DishRepository.getDish(id))
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Center everything horizontally
        modifier = Modifier.fillMaxHeight() // Fill the maximum height available
    ) {
        TopAppBar()
        Image(
            painter = painterResource(id = dish.imageResource),
            contentDescription = "Dish Image",
            modifier = Modifier
                .fillMaxWidth(.6f)
                .padding(vertical = 16.dp), // Add vertical padding to center the image
            alignment = Alignment.Center,
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally, // Center everything horizontally
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        ) {
            Text(
                text = dish.name,
                style = MaterialTheme.typography.h1
            )
            Text(
                text = dish.description,
                style = MaterialTheme.typography.body1
            )


            Counter()
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LittleLemonColor.yellow,
                    contentColor = Color.Black
                ),
                modifier = Modifier.clip(RoundedCornerShape(50.dp))
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.add_for) + " $${dish.price}",
                )
            }
        }
    }
}

@Composable
fun Counter() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        var counter by remember {
            mutableStateOf(1)
        }
        TextButton(
            onClick = {
                counter--
            }
        ) {
            Text(
                text = "-",
                style = MaterialTheme.typography.h2
            )
        }
        Text(
            text = counter.toString(),
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(16.dp)
        )
        TextButton(
            onClick = {
                counter++
            }
        ) {
            Text(
                text = "+",
                style = MaterialTheme.typography.h2
            )
        }
    }
}

@Preview
@Composable
fun DishDetailsPreview() {
    DishDetails(id = 1)
}
