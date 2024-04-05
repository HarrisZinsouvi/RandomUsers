package com.example.randomusers.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.IntentCompat
import com.example.randomusers.models.User
import com.example.randomusers.models.UserName
import com.example.randomusers.ui.lib.loadImage
import com.example.randomusers.ui.theme.RandomUsersTheme

class UserDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentUser = IntentCompat.getParcelableExtra(intent, "user", User::class.java)

        setContent {
            RandomUsersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserDetails(currentUser)
                }
            }
        }
    }
}

@Composable
fun UserDetails(user: User?) {
    if (user != null) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var painter by remember { mutableStateOf<Painter?>(null) }

            LaunchedEffect(user.picture?.large) {
                val loadedPainter = user.picture?.large?.let { loadImage(it) }
                painter = loadedPainter?.let { BitmapPainter(it) }
            }
            /*Image(
                painter = rememberImagePainter(user.picture?.large),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colors.primary, CircleShape)
            )*/
            painter?.let {
                Image(
                    painter = it,
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Nom: ${user.name.first} ${user.name.last}", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Email: ${user.email}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Téléphone: ${user.phone}")
        }
    } else {

    }
}


/*@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    RandomUsersTheme {
        UserDetails(User("TEST","Test"))
    }
}*/
