package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.HeroesRepository.heroes
import com.example.superheroes.ui.theme.SuperheroesTheme
import kotlin.io.encoding.Base64

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperheroesTheme {
                MainScreen()
            }
        }
    }
}

//@Composable
//fun SuperheroesApp(modifier: Modifier = Modifier){
//    SuperheroList(heroes, paddingValues = it)
//}

@Composable
fun SuperheroCard(
        superhero: Hero, //taking object of data class Hero as an argument of Card displaying function
        modifier: Modifier = Modifier
    ){
    Card (
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(R.dimen.padding_medium))
    ){
        Row(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_small))
                .height(dimensionResource(R.dimen.content_width))
        ) {
            Image(
                painter = painterResource(superhero.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .size(dimensionResource(R.dimen.image_size))
            )
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Column(){
                Text(
                    text = stringResource(superhero.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(superhero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun SuperheroList(
    listOfHeroes: List<Hero>,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
){
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding(), start = 16.dp, end = 16.dp),
        userScrollEnabled = true
    ){
        items(listOfHeroes){
            superhero -> SuperheroCard(superhero = superhero)  //named argument
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuperheroesAppPreview() {
    SuperheroesTheme {
        MainScreen()
    }
}

@Preview
@Composable
fun SuperheroesDarkThemePreview(){
    SuperheroesTheme(darkTheme = true) {
        MainScreen()
    }
}