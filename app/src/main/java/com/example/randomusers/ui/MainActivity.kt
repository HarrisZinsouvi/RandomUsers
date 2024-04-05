package com.example.randomusers.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.randomusers.config.RetrofitInstance
import com.example.randomusers.models.ProfilePicture
import com.example.randomusers.models.User
import com.example.randomusers.models.UserName
import com.example.randomusers.repositories.UserRepository
import com.example.randomusers.ui.lib.loadImage
import com.example.randomusers.ui.theme.RandomUsersTheme


class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel = viewModel(
                factory = ViewModelFactory(UserRepository(RetrofitInstance.getRetrofitInstance()))
            )
            viewModel.fetchUsers()

            val users by viewModel.users.collectAsState()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                if (users.isEmpty()) {
                    CircularProgressIndicator() // Afficher une indication de chargement si la liste d'utilisateurs est nulle ou vide
                } else {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(text = "Utilisateurs",  fontWeight = FontWeight.Bold, fontSize = 18.sp )
                        UserList(users) { user ->
                            // Gérer le clic sur l'utilisateur
                            val intent = Intent(this@MainActivity, UserDetailsActivity::class.java)
                            intent.putExtra("user", user)
                            startActivity(intent)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun UserList(users: List<User>, onUserClick: (User) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(users) { index, user ->
            UserCard(user = user, onClick = { onUserClick(user) })
        }

    }
}

@Composable
fun UserCard(user: User, onClick: () -> Unit) {
    Card(

        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        onClick = onClick
    ) {
        var painter by remember { mutableStateOf<Painter?>(null) }

        LaunchedEffect(user.picture?.medium) {
            val loadedPainter = user.picture?.medium?.let { loadImage(it) }
            painter = loadedPainter?.let { BitmapPainter(it) }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            // Avatar rond
            painter?.let {
                Image(
                    painter = it,
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "Mail: ${user.email}", fontWeight = FontWeight.Bold, fontSize = 12.sp )
                Text(text = "Nom: ${user.name.last}")
                Text(text = "Prénom: ${user.name.first}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestUser() {
    RandomUsersTheme {
        UserCard(
            user = User(
                name = UserName("Me", "John","Doe"),
                email = "john.doe@example.com",
                picture = ProfilePicture("https://randomuser.me/api/portraits/thumb/men/1.jpg")
            ), { }
        )
    }
}

