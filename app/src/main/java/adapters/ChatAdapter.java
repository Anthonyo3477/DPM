package adapters;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dmp.R;
import Activity.Chats;
import models.Chat;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private final List<Chat> chatList;
    private final OnChatClickListener listener;

    public ChatAdapter(List<Chat> chatList, OnChatClickListener listener) {
        this.chatList = chatList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Chat chat = chatList.get(position);

        String nickName = chat.getNickName();
        if (nickName != null) {
            holder.nickNameTextView.setText(nickName);
        } else {
            holder.nickNameTextView.setText("");
        }


        String lastMessage = chat.getLastMessage();
        if (lastMessage != null) {
            holder.lastMessageTextView.setText(lastMessage);
        } else {
            holder.lastMessageTextView.setText("");
        }

        if (chat.isUnread()) {
            holder.nickNameTextView.setTypeface(null, Typeface.BOLD);
            holder.lastMessageTextView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.unread_message));
        } else {
            holder.nickNameTextView.setTypeface(null, Typeface.NORMAL);
            holder.lastMessageTextView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.read_message));
        }

        holder.editButton.setOnClickListener(v -> listener.onEditChat(chat));
        holder.deleteButton.setOnClickListener(v -> listener.onDeleteChat(chat));

        // Action to open chat
        holder.messageButton.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), Chats.class);
            intent.putExtra("chat_id", chat.getId());
            intent.putExtra("nickname", chat.getNickName());
            holder.itemView.getContext().startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public void updateChats(List<Chat> newChats) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ChatDiffCallback(this.chatList, newChats));
        this.chatList.clear();
        this.chatList.addAll(newChats);
        diffResult.dispatchUpdatesTo(this);
    }

    public interface OnChatClickListener {
        void onEditChat(Chat chat);
        void onDeleteChat(Chat chat);
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView nickNameTextView, lastMessageTextView;
        Button editButton, deleteButton, messageButton;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            nickNameTextView = itemView.findViewById(R.id.Nickname);
            lastMessageTextView = itemView.findViewById(R.id.lastMessage);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            messageButton = itemView.findViewById(R.id.messageButton);
        }
    }

    public static class ChatDiffCallback extends DiffUtil.Callback {

        private final List<Chat> oldList;
        private final List<Chat> newList;

        public ChatDiffCallback(List<Chat> oldList, List<Chat> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            // Se considera que los elementos son iguales si tienen el mismo ID
            return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            // Compara el contenido de los elementos
            return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
        }
    }
}