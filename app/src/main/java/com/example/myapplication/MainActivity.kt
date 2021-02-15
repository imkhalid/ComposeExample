package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.model.Feed
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.shapes
import com.example.myapplication.ui.wiget.Header
import com.example.myapplication.utils.DEFAULT_IMAGE
import com.example.myapplication.utils.ViewMod
import com.example.myapplication.utils.loadImage
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.activities.DetailActivity


lateinit var mContext: Activity


class MainActivity : AppCompatActivity() {
    val viewModel by viewModels<ViewMod>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                mContext = this

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    FeedCard()
                }

            }
        }
    }

    @Preview
    @Composable
    fun FeedCard() {
        MyApplicationTheme {
            Column(Modifier.fillMaxSize()) {
                Header("Home") {
                    Toast.makeText(mContext, "Back Button Pressed", Toast.LENGTH_SHORT).show()
                }
                CreateItems()
            }

        }
    }


    @Composable
    fun CreateItems() {
        val mList by viewModel.todoItems.observeAsState()
        viewModel.getList()
        LazyColumn {
            itemsIndexed(items = mList!!) { index, item: Feed ->
                SingleItem(item = item, onClick = {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            DetailActivity::class.java
                        ).putExtra("item", item)
                    )
                })
            }
        }
    }

    @Composable
    fun SingleItem(item: Feed, onClick: (Feed) -> Unit) {
        val name = item.name ?: "N/A"
        val speices = item.species ?: "N/A"
        val url = item.image ?: "N/A"
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { onClick(item) })
                .padding(20.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(vectorResource(id = R.drawable.ic_person_24))
                Column {
                    Text(text = name)
                    Text(
                        text = speices,
                        fontSize = TextUnit.Companion.Sp(10),
                        color = Color.Gray
                    )
                }
            }

            val image = loadImage(url = url, dr = DEFAULT_IMAGE).value
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    Modifier
                        .clip(shape = shapes.medium)
                        .fillMaxWidth()
                )
            }
        }
    }
}



