package com.example.g_commute.group;

import android.content.Intent;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.g_commute.R;
import com.example.g_commute.home.CreatePostActivity;
import com.example.g_commute.home.GroupsFragment;
import com.example.g_commute.home.HomeActivity;
import com.example.g_commute.home.PostActivity;

public class GroupActivity extends AppCompatActivity {

    Fragment fragment;
    FragmentManager fragmentManager;
    FloatingActionButton fabCreatePost;
    BottomAppBar bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

       /* fabCreatePost = (FloatingActionButton) findViewById(R.id.fabCreatePost);
        bottomAppBar = (BottomAppBar) findViewById(R.id.bottomAppBar);
        bottomAppBar.replaceMenu(R.menu.navigation);
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.settings:
                        Toast.makeText(GroupActivity.this, "", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.calendar:
                        Toast.makeText(GroupActivity.this, "", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.post:
                        Toast.makeText(GroupActivity.this, "", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(GroupActivity.this, PostActivity.class);
                        startActivity(intent);
                        break;
                }

                return false;
            }
        });*/

        fragment = new ChatFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.groupDetailActivityFrameContainer, fragment).commit();

      /*  fabCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupActivity.this, CreatePostActivity.class);
                startActivity(intent);
            }
        });*/

    }
}
