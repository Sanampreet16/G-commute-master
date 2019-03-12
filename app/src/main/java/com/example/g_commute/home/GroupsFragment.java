package com.example.g_commute.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.g_commute.data.Group;
import com.example.g_commute.R;
import com.example.g_commute.group.ChatFragment;
import com.example.g_commute.group.GroupActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsFragment extends Fragment {

    TextView txtViewGroupId, txtViewGroupName;
    RecyclerView recyclerViewGroups;
    FirestoreRecyclerAdapter groupAdapter;
    DividerItemDecoration mdividerItemDecoration;
    FragmentManager fragmentManager;


    public GroupsFragment() {
        // Required empty public constructor
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.txtViewGroupId);
            name = itemView.findViewById(R.id.txtViewGroupName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), GroupActivity.class);
                    startActivity(intent);
                }
            });
        }

        void onBind(Group group) {
            id.setText(group.getUser());
            name.setText(group.getName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_groups, container, false);
        txtViewGroupId = (TextView) view.findViewById(R.id.txtViewGroupId);
        txtViewGroupName = (TextView) view.findViewById(R.id.txtViewGroupName);
        recyclerViewGroups = (RecyclerView) view.findViewById(R.id.recyclerViewGroups);

        ///////////////////////////////////////////////
        // query to retrieve id and name of the group//
        ///////////////////////////////////////////////

        com.google.firebase.firestore.Query q = FirebaseFirestore.getInstance().collection("group");
        FirestoreRecyclerOptions<Group> options = new FirestoreRecyclerOptions.Builder<Group>().setQuery(q, Group.class).build();

        groupAdapter = new FirestoreRecyclerAdapter<Group, GroupsFragment.ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull GroupsFragment.ViewHolder holder, int position, @NonNull Group model) {
                holder.onBind(model);
            }

            @NonNull
            @Override
            public GroupsFragment.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View groupView = LayoutInflater.from(getContext()).inflate(R.layout.layout_group, viewGroup, false);
                return new ViewHolder(groupView);
            }
        };

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewGroups.setLayoutManager(layoutManager);
        recyclerViewGroups.setAdapter(groupAdapter);
        mdividerItemDecoration=new DividerItemDecoration(recyclerViewGroups.getContext(),layoutManager.getOrientation());
        recyclerViewGroups.addItemDecoration(mdividerItemDecoration);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        groupAdapter.startListening();
    }

    public void onStop() {
        super.onStop();
        groupAdapter.stopListening();
    }


}
