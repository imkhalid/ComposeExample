package com.example.myapplication.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.model.Feed
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.wiget.Header
import com.example.myapplication.utils.DEFAULT_IMAGE
import com.example.myapplication.utils.loadImage

class DetailActivity : AppCompatActivity() {
    lateinit var feed: Feed
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getParcelableExtra<Feed>("item")?.let {
            feed = it
        }
        setContent {
            MyApplicationTheme {
                DetailView()
            }
        }
    }

    @Preview
    @Composable
    private fun DetailView() {
        Column {
            Header(text = feed.name ?: "N/A", onClickListner = { finish() })
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                loadImage(url = feed.image!!, dr = DEFAULT_IMAGE).value?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        Modifier.fillMaxWidth()
                    )
                }
                ScrollableColumn(modifier = Modifier.weight(1f)) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.5.dp)
                            .background(Color.Gray)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextWidgetHorizontal(
                            text = "Date of Birth:",
                            value = feed.dob,
                            modifier = Modifier
                                .weight(1f)

                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(0.5.dp)
                                .background(Color.Gray)
                        )
                        TextWidgetHorizontal(
                            text = "Date of Death:",
                            value = feed.dod,
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.5.dp)
                            .background(Color.Gray)
                    )
                    TextWidgetVertical(text = "Sex:", value = feed.sex)
                    TextWidgetVertical(text = "Affiliation:", value = feed.affiliation)
                    TextWidgetVertical(text = "Wiki:", value = feed.wikilink, clickable = true)
                    TextWidgetVertical(text = "Note:", value = feed.note)
                }


            }
        }
    }


    @Composable
    fun TextWidgetVertical(text: String, value: String?, clickable: Boolean = false) {
        Column(modifier = Modifier.clickable(enabled = clickable, onClick = {})) {
            Text(
                text = text,
                style = TextStyle(
                    color = Color.White,
                    fontSize = TextUnit.Sp(12),
                ),
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
            )
            Text(
                text = value ?: "N/A",
                style = TextStyle(
                    color = Color.White,
                    fontSize = TextUnit.Sp(16),
                ),
                modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 5.dp)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(Color.Gray)
            )
        }
    }

    @Composable
    fun TextWidgetHorizontal(text: String, value: String?, modifier: Modifier) {
        Column(modifier = modifier) {
            Text(
                text = text,
                style = TextStyle(
                    color = Color.White,
                    fontSize = TextUnit.Sp(12)
                ),
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
            )
            Text(
                text = value ?: "N/A",
                style = TextStyle(
                    color = Color.White,
                    fontSize = TextUnit.Sp(16),
                ),
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
            )
        }
    }
}