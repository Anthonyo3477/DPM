package Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dmp.R;
import java.util.ArrayList;
import java.util.List;
import adapters.MensajeAdapter;
import models.Message;

public class Chats extends AppCompatActivity {

    private RecyclerView recyclerViewMessages;
    private EditText inputMessage;
    private Button buttonSend, btnBack;
    private MensajeAdapter messageAdapter;
    private List<Message> messageList;
    private TextView tvUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensajeria);

        String nickname = getIntent().getStringExtra("nickname");
        int chatId = getIntent().getIntExtra("chat_id", -1);

        if (nickname != null) {
            tvUserName = findViewById(R.id.tvUserName);
            tvUserName.setText(nickname);
        }

        recyclerViewMessages = findViewById(R.id.recyclerViewChat);
        inputMessage = findViewById(R.id.editMsg);
        buttonSend = findViewById(R.id.btnEnviar);
        btnBack = findViewById(R.id.btnBack);

        messageList = new ArrayList<>();
        messageAdapter = new MensajeAdapter(messageList);
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMessages.setAdapter(messageAdapter);

        buttonSend.setOnClickListener(v -> sendMessage());

        btnBack.setOnClickListener(v -> onBackPressed());
    }

    // Método para enviar un mensaje
    private void sendMessage() {
        String messageText = inputMessage.getText().toString().trim();

        if (!messageText.isEmpty()) {
            Message newMessage = new Message(messageText, true);
            messageList.add(newMessage);
            messageAdapter.notifyItemInserted(messageList.size() - 1);

            inputMessage.setText("");
            recyclerViewMessages.scrollToPosition(messageList.size() - 1);

        } else {
            Toast.makeText(this, "Por favor, escribe un mensaje", Toast.LENGTH_SHORT).show();
        }
    }

    private void simulateAutoReply(String messageText) {
        String reply = "Respuesta automática: " + messageText;
        Message receivedMessage = new Message(reply, false);
        messageList.add(receivedMessage);
        messageAdapter.notifyItemInserted(messageList.size() - 1);
        recyclerViewMessages.scrollToPosition(messageList.size() - 1);
    }
}