package com.example.g_commute.group;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.g_commute.R;
import com.example.g_commute.data.ChatData;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    FirestoreRecyclerAdapter chatAdapter;
    RecyclerView recyclerViewChat;
    TextView txtViewChatMessage;
    TextView txtViewChatUser;
    EditText editTxtChatBox;
    ImageButton btnSend;
    DividerItemDecoration mdividerItemDecoration;
    FirebaseFirestore chatMsgSend;
    int id = 2;
    int group = 102;

    public ChatFragment() {
        // Required empty public constructor
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView message;

        ViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.txtViewChatUser);
            message = itemView.findViewById(R.id.txtViewChatMessage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new GroupDetailFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.groupDetailActivityFrameContainer, fragment).commit();
                }
            });
        }

        void onBind(ChatData data) {
            id.setText(Integer.toString(data.getId()));

            message.setText(data.getMessage());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        txtViewChatMessage = (TextView) view.findViewById(R.id.txtViewChatMessage);
        txtViewChatUser = (TextView) view.findViewById(R.id.txtViewChatUser);
        recyclerViewChat = (RecyclerView) view.findViewById(R.id.recyclerViewChat);
        editTxtChatBox = (EditText) view.findViewById(R.id.editTxtChatbox);
        btnSend = (ImageButton) view.findViewById(R.id.btnSend);

        chatMsgSend = FirebaseFirestore.getInstance();


        //to write
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curMsg = editTxtChatBox.getText().toString();
               CollectionReference reference = chatMsgSend.collection("chat");
                ChatData chat = new ChatData(id, group, curMsg,Timestamp.now());
                reference.add(chat).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Toast.makeText(ChatFragment.this, "added", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Toast.makeText(CreatePostActivity.this, "not added "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });




        com.google.firebase.firestore.Query q = FirebaseFirestore.getInstance().collection("chat").orderBy("time", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<ChatData> options = new FirestoreRecyclerOptions.Builder<ChatData>().setQuery(q, ChatData.class).build();

        chatAdapter = new FirestoreRecyclerAdapter<ChatData, ChatFragment.ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ChatFragment.ViewHolder holder, int position, @NonNull ChatData model) {
                holder.onBind(model);
            }

            @NonNull
            @Override
            public ChatFragment.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View chatView = LayoutInflater.from(getContext()).inflate(R.layout.layout_chat, viewGroup, false);
                return new ChatFragment.ViewHolder(chatView);
            }
        };

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewChat.setLayoutManager(layoutManager);
        recyclerViewChat.setAdapter(chatAdapter);
       // mdividerItemDecoration = new DividerItemDecoration(recyclerViewChat.getContext(), layoutManager.getOrientation());
        //recyclerViewChat.addItemDecoration(mdividerItemDecoration);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        chatAdapter.startListening();
    }

    public void onStop() {
        super.onStop();
        chatAdapter.stopListening();
    }


}
