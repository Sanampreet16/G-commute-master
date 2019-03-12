package com.example.g_commute.home;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.g_commute.R;
import com.example.g_commute.data.Post;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

public class PostActivity extends AppCompatActivity {

    TextView txtViewPostCreator, txtViewPostTitle, txtViewPostDesc;
    RecyclerView recyclerViewPosts;
    FirestoreRecyclerAdapter postAdapter;
    DividerItemDecoration mDividerDecoration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        recyclerViewPosts = (RecyclerView) findViewById(R.id.recyclerViewPosts);

        com.google.firebase.firestore.Query q = FirebaseFirestore.getInstance().collection("post");
        FirestoreRecyclerOptions<Post> options = new FirestoreRecyclerOptions.Builder<Post>().setQuery(q, Post.class).build();
        postAdapter = new FirestoreRecyclerAdapter<Post, PostActivity.ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PostActivity.ViewHolder holder, int position, @NonNull Post model) {
                holder.onBind(model);
            }

            @NonNull
            @Override
            public PostActivity.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View postView = LayoutInflater.from(PostActivity.this).inflate(R.layout.layout_post, viewGroup, false);
                return new PostActivity.ViewHolder(postView);
            }
        };

        LinearLayoutManager layoutManager = new LinearLayoutManager(PostActivity.this);
        recyclerViewPosts.setLayoutManager(layoutManager);
        recyclerViewPosts.setAdapter(postAdapter);
        mDividerDecoration = new DividerItemDecoration(recyclerViewPosts.getContext(), layoutManager.getOrientation());
        recyclerViewPosts.addItemDecoration(mDividerDecoration);



    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        TextView creator;

        ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txtViewPostTitle);
            desc = itemView.findViewById(R.id.txtViewPostDesc);
            creator = itemView.findViewById(R.id.txtViewPostCreator);
        }

        void onBind(Post post) {
            title.setText(post.getTitle());
            desc.setText(post.getDesc());
            creator.setText(post.getCreator() + ": ");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        postAdapter.startListening();
    }

    public void onStop() {
        super.onStop();
        postAdapter.stopListening();
    }
}
