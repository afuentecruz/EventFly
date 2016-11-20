package com.asee.alberto.eventfly.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.asee.alberto.eventfly.R;
import com.asee.alberto.eventfly.model.MessageDB;

import java.util.List;

/**
 * Created by alberto on 20/11/16.
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    // Message list
    private List<MessageDB> messageList;

    public MessageAdapter(List<MessageDB> items) {
        this.messageList = items;
    }

    @Override
    public int getItemCount(){
        return messageList.size();
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_message_item, parent, false);
        return new MessageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        MessageDB msg = messageList.get(position);
        holder.msgBody.setText(msg.getBody());
    }


    public class MessageViewHolder extends RecyclerView.ViewHolder {

        // Item attributes
        public TextView msgBody;

        public MessageViewHolder(View itemView) {
            super(itemView);
            msgBody = (TextView) itemView.findViewById(R.id.message_body);

        }
    }
}
