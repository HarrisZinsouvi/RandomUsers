package com.example.randomusers.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.IntentCompat
import com.example.randomusers.models.User
import com.example.randomusers.models.UserName
import com.example.randomusers.ui.theme.RandomUsersTheme

class UserDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentUser=IntentCompat.getParcelableExtra(intent, "user", User::class.java)

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
            Text(text = user.name.first+""+user.name.last, fontWeight = FontWeight.Bold)
            Text(text = "Email: ${user.email}")
            Text(text = "Phone: ${user.phone}")
        }
    } else {
        // Handle null user
    }
}

/*@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    RandomUsersTheme {
        UserDetails(User("TEST","Test"))
    }
}*/
