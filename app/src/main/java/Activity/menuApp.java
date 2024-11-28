package Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dmp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import adapters.ChatAdapter;
import models.User;
import models.Chat;

public class menuApp extends AppCompatActivity implements ChatAdapter.OnChatClickListener {

    private RecyclerView recyclerViewChats;
    private ChatAdapter chatAdapter;
    private TextInputEditText searchInput;
    private FloatingActionButton fabNewChat;
    private ChatViewModel chatViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_app);

        recyclerViewChats = findViewById(R.id.recyclerViewChats);
        searchInput = findViewById(R.id.searchInput);
        fabNewChat = findViewById(R.id.fabNewChat);

        recyclerViewChats.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(new ArrayList<>(), this);
        recyclerViewChats.setAdapter(chatAdapter);

        String currentUser = getIntent().getStringExtra("username");

        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);

        chatViewModel.getUsers().observe(this, users -> {
            List<User> filteredList = new ArrayList<>();
            for (User user : users) {
                if (!user.getNickname().equals(currentUser)) {
                    filteredList.add(user);
                }
            }

            List<Chat> chats = convertUsersToChats(filteredList);
            chatAdapter.updateChats(chats);
        });

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                chatViewModel.searchUsers(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        fabNewChat.setOnClickListener(view -> {
            Intent intent = new Intent(menuApp.this, NuevoChat.class);
            startActivity(intent);
            Toast.makeText(menuApp.this, "Nuevo chat", Toast.LENGTH_SHORT).show();
        });

        chatViewModel.loadUsers();
    }

    private List<Chat> convertUsersToChats(List<User> users) {
        List<Chat> chats = new ArrayList<>();
        for (User user : users) {
            Chat chat = new Chat();
            chat.setNickName(user.getNickname());
            chats.add(chat);
        }
        return chats;
    }

    @Override
    public void onEditChat(Chat chat) {
        Toast.makeText(this, "Editar chat: " + chat.getNickName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteChat(Chat chat) {
        Toast.makeText(this, "Eliminar chat: " + chat.getNickName(), Toast.LENGTH_SHORT).show();
    }
}