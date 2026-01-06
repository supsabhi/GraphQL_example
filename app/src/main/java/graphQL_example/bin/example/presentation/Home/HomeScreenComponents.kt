package graphQL_example.bin.example.graphQL_example.bin.graphQL_example.presentation.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import graphQL_example.bin.example.graphQL_example.bin.graphQL_example.domain.model.CountryDataModel
import test.bin.jetpackcompose_template.R

@Composable
fun ComposeAlertDialog(
    msg: String?,
    title: String,
    onButtonClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissClick,
        confirmButton = {
            TextButton(
                onClick = onButtonClick
            ) {
                Text(stringResource(id = R.string.ok_txt))
            }
        },
        title = { Text(title) },
        text = {
            Text(
                text = msg ?: "",
                style = MaterialTheme.typography.bodyMedium,
                softWrap = true,
                maxLines = Int.MAX_VALUE
            )
        }
    )
}

@Composable
fun CountryItem(country: CountryDataModel) {
    Card(
        shape = RoundedCornerShape(16.dp),
        // elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(horizontal = 12.dp)
            .padding(vertical = 12.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(
                text = country.name,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    lineHeight = 28.sp,
                    letterSpacing = 0.sp,
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = country.capital.toString(),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    lineHeight = 28.sp,
                    letterSpacing = 0.sp,
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = country.code,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    lineHeight = 28.sp,
                    letterSpacing = 0.sp,
                    color = Color.Black
                )
            )
        }
    }
}

@Composable
fun CountryScreen(countryModel: List<CountryDataModel>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.LightGray)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(countryModel) { item ->
                CountryItem(item)
            }
        }
    }
}
