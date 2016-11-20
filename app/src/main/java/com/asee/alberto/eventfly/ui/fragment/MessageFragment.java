package com.asee.alberto.eventfly.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.asee.alberto.eventfly.R;
import com.asee.alberto.eventfly.adapter.MessageAdapter;
import com.asee.alberto.eventfly.manager.MessageManager;
import com.asee.alberto.eventfly.model.MessageDB;
import com.asee.alberto.eventfly.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberto on 20/11/16.
 */

public class MessageFragment extends Fragment {

    //TextView of card item
    private TextView msgBody;
    //Button for send new messages to the event
    private Button mButtonSend;
    //Edit Text with the content of the new message
    private EditText mEditTextNewMsg;
    // A list with all the messages of the event
    private List<MessageDB> items;
    // Name of the event which belongs the messages
    private String eventName;


    private RecyclerView messageRecycler;
    private RecyclerView.Adapter messageAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public MessageFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.message_fragment, container, false);

        // Load the bundle for get the arguments passed from the MapFragment
        Bundle bundle = this.getArguments();
        if(bundle != null){
            eventName = bundle.getString("eventName");
            ((MainActivity) getActivity()).setActionBarTitle(eventName);

        }

        // Initialize the components
        items = new ArrayList<MessageDB>();
        mButtonSend = (Button) v.findViewById(R.id.send_button);
        mEditTextNewMsg = (EditText) v.findViewById(R.id.new_msg_body);


        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mEditTextNewMsg.getText().toString().isEmpty()){

                    // Create new MessageDB object with the editText content
                    MessageDB newMsg = new MessageDB();
                    newMsg.setBody(mEditTextNewMsg.getText().toString());

                    //Add this new message to the message list
                    items.add(newMsg);
                    mEditTextNewMsg.setText("");

                    //Hide the keyboard
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    persisNewMessage(newMsg);
                }
            }

        });

        // Items getter
/*
        MessageDB item1 = new MessageDB();
        item1.setBody("Hello world 1");
        items.add(item1);
        MessageDB item2 = new MessageDB();
        item2.setBody("Hello world 2");
        items.add(item2);
        MessageDB item3 = new MessageDB();
        item3.setBody("Hello world 3");
        items.add(item3);*/


        items.addAll(MessageManager.getMessageByEvent(eventName));

        // Instance the RecyclerView
        messageRecycler = (RecyclerView) v.findViewById(R.id.message_recycler);
        messageRecycler.setHasFixedSize(true);

        // Use a layoutManager
        layoutManager = new LinearLayoutManager(getActivity());
        messageRecycler.setLayoutManager(layoutManager);

        // Create a new adaptar
        messageAdapter = new MessageAdapter(items);
        messageRecycler.setAdapter(messageAdapter);
        return v;
    }

    private void persisNewMessage(MessageDB msg){

        msg.setEventName(eventName);
        MessageManager.saveOrUpdateMessage(msg);
    }
}
