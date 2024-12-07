package com.example.chatapp.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.databinding.ItemContainerRecievedMessageBinding;
import com.example.chatapp.databinding.ItemContainerSentMessageBinding;
import com.example.chatapp.models.ChatMessage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Bitmap recieverProfileImage;
    private final List<ChatMessage> chatMessages;
    private final String sendId;
    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECIEVED = 2;

    /**
     * Constructor of Chat Adapter
     * @param bitmap profile image bitmap
     * @param chatMessages messages sent between members
     * @param sendId sender id
     */
    public ChatAdapter(Bitmap bitmap, List<ChatMessage> chatMessages, String sendId){
        recieverProfileImage = bitmap;
        this.chatMessages = chatMessages;
        this.sendId = sendId;
    }

    /**
     * Determines if member is the sender or receiver
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return a ViewHolder based on if they are the sender or receiver
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_SENT){
            return  new SentMessageViewHolder(ItemContainerSentMessageBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }else {
            return  new RecieverMessageViewHolder(ItemContainerRecievedMessageBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
    }

    /**
     * Sets data based on whether the member is a sender or receiver
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == VIEW_TYPE_SENT){
            ((SentMessageViewHolder)holder).setData(chatMessages.get(position));
        }else {
            ((RecieverMessageViewHolder)holder).setData(chatMessages.get(position), recieverProfileImage);
        }
    }

    /**
     * Gets the number of messages in the chat
     * @return number of messages in the chat
     */
    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    /**
     * Determines if message is being sent or received
     * @param position position to query
     * @return if message is being sent or received
     */
    @Override
    public int getItemViewType(int position){
        if(chatMessages.get(position).senderId.equals(sendId)){
            return VIEW_TYPE_SENT;
        }else {
            return VIEW_TYPE_RECIEVED;
        }
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder{

        private final ItemContainerSentMessageBinding binding;

        /**
         * Constructor of SentMessageViewHolder
         * @param itemContainerSentMessageBinding container of sent messages
         */
        public SentMessageViewHolder(ItemContainerSentMessageBinding itemContainerSentMessageBinding) {
            super(itemContainerSentMessageBinding.getRoot());
            binding = itemContainerSentMessageBinding;
        }

        /**
         * Sets up data of Member being messaged
         * @param chatMessage Message that was sent
         */
        void setData(ChatMessage chatMessage){
            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.dateTime);
        }
    }

    static class RecieverMessageViewHolder extends RecyclerView.ViewHolder{

        private final ItemContainerRecievedMessageBinding binding;

        /**
         * Constructor of RecieverMessageViewHolder
         * @param itemContainerRecievedMessageBinding container of received messages
         */
        public RecieverMessageViewHolder(ItemContainerRecievedMessageBinding itemContainerRecievedMessageBinding) {
            super(itemContainerRecievedMessageBinding.getRoot());
            binding = itemContainerRecievedMessageBinding;
        }

        /**
         * Sets up data of Member being messaged
         * @param chatMessage Message that was received
         * @param recieverProfileImage profile image of Member getting messages
         */
        void setData(ChatMessage chatMessage, Bitmap recieverProfileImage){
            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.dateTime);
            binding.imageProfile.setImageBitmap(recieverProfileImage);
        }
    }
}
