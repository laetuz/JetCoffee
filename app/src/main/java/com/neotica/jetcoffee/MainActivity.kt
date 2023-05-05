package com.neotica.jetcoffee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.*
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neotica.jetcoffee.model.BottomBarItem
import com.neotica.jetcoffee.model.Menu
import com.neotica.jetcoffee.model.dummyCategory
import com.neotica.jetcoffee.model.dummyMenu
import com.neotica.jetcoffee.model.dummyMenuBestSellerMenu
import com.neotica.jetcoffee.ui.components.CategoryItem
import com.neotica.jetcoffee.ui.components.HomeSection
import com.neotica.jetcoffee.ui.components.MenuItem
import com.neotica.jetcoffee.ui.components.SearchBar
import com.neotica.jetcoffee.ui.theme.JetCoffeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetCoffeeTheme {
                // A surface container using the 'background' color from the theme
                JetCoffeeApp()
            }
        }
    }
}

@Composable
fun JetCoffeeApp() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Banner()
        HomeSection(
            title = stringResource(id = R.string.section_category),
            content = { CategoryRow() }
        )
        HomeSection(
            title = stringResource(id = R.string.section_favorite_menu),
            content = { MenuRow(listMenu = dummyMenu) }
        )
        HomeSection(
            title = stringResource(id = R.string.section_best_seller_menu),
            content = { MenuRow(listMenu = dummyMenuBestSellerMenu) }
        )
    }
}

@Composable
fun Banner(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp)
        )
        SearchBar()
    }
}

@Composable
fun CategoryRow() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(dummyCategory, key = { it.textCategory }) {
            CategoryItem(category = it)
        }
    }
}

@Composable
fun MenuRow(
    listMenu: List<Menu>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(listMenu, key = { it.title }) { MenuItem(menu = it) }
    }
}

@Composable
fun SectionText(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.ExtraBold
        ),
        modifier = modifier
            .padding(16.dp, 8.dp)
    )
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier
) {
    NavigationBar() {
        val navigationItems = listOf(
            BottomBarItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Default.Home
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle
            )
        )
        navigationItems.map {
            NavigationBarItem(
                selected = it.title == navigationItems[0].title,
                onClick = { },
                icon = {
                    Icon(imageVector = it.icon, contentDescription = it.title)
                },
                label = { Text(text = it.title)}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    JetCoffeeTheme {
        Column {
            Banner()
            SectionText(title = stringResource(id = R.string.section_category))
            CategoryRow()
            SectionText(title = stringResource(id = R.string.section_favorite_menu))
            MenuRow(listMenu = dummyMenu)
            SectionText(title = stringResource(id = R.string.section_best_seller_menu))
            MenuRow(listMenu = dummyMenuBestSellerMenu)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AppPreviewNew() {
    JetCoffeeTheme {
        Scaffold(
            bottomBar = { BottomBar()}
        ) {padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                Banner()
                HomeSection(
                    title = stringResource(id = R.string.section_category),
                    content = { CategoryRow() }
                )
                HomeSection(
                    title = stringResource(id = R.string.section_favorite_menu),
                    Modifier,
                    content = { MenuRow(listMenu = dummyMenu) }
                )
                HomeSection(
                    title = stringResource(id = R.string.section_best_seller_menu),
                    Modifier,
                    content = { MenuRow(listMenu = dummyMenuBestSellerMenu) }
                )
            }
        }

    }
}