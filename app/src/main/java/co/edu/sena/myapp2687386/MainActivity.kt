package co.edu.sena.myapp2687386

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.edu.sena.myapp2687386.ui.theme.MyApp2687386Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp2687386Theme {
                Box(
                    content = {
                        Image(
                            painter = painterResource(R.drawable.background),
                            contentDescription = "Fondo de aplicación",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = Color.Transparent
                        ) {
                            Conversation(SampleData.conversationSample)
                        }
                    }
                )
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {

    Row(modifier = Modifier.padding(all = 10.dp)) {
        Image(
            painter = painterResource(R.drawable.van),
            contentDescription = "El chapulin colorao",
            modifier = Modifier
                //Set image size to 40 dp
                .size(60.dp)
                //Clip image to be shapped as a circle
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary)
        )

        Spacer(modifier = Modifier.width(10.dp))

        //We keep track if the message is expanded ot nor in this Varibale
        //Variable
        var isExpanded by remember { mutableStateOf(false) }
        // SuperColor will be ipdate gradually from one color to the other

        // We toggle the isExpanded variable when we click on the Column
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge
            )
            //Add vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))

            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp) {

                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    //If the message id expanded, we display all its contect
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyLarge
                )

            }
        }
    }
}


@Composable
fun Conversation(messages: List<Message>) {

    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }

}

@Preview
@Composable
fun PreviewConversation() {

    MyApp2687386Theme {
        Conversation(SampleData.conversationSample)
    }

}


/*
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
*/


@Preview(showBackground = true)
@Composable
fun MessageCardPreview() {
    MyApp2687386Theme {
        Surface {

            MessageCard(msg = Message("El chapulin", "colorao"))

        }
    }
}




