package com.example.g_commute.group;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.g_commute.R;
import com.example.g_commute.data.Group;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupDetailFragment extends Fragment {

    TextView txtViewShowDetail;
    TextView txtViewGroupTitle;
    TextView txtViewGroupMembers;
    FragmentManager fragmentManager;
    FrameLayout groupDetail;
    DividerItemDecoration mdividerItemDecoration;
    DocumentReference docRef;

    public GroupDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_detail, container, false);
        txtViewGroupTitle = (TextView) view.findViewById(R.id.txtViewGroupTitle);
        txtViewGroupMembers = (TextView) view.findViewById(R.id.txtViewMembers);
        groupDetail = (FrameLayout) view.findViewById(R.id.groupDetailContainer);

        ////////////////////////////////////////////////
        // query to retrieve id and name of the group //
        ////////////////////////////////////////////////

        final Group groupData = new Group();
       docRef.collection("group").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
           @Override
           public void onComplete(@NonNull Task<QuerySnapshot> task) {
               if (task.isSuccessful()) {
                   if (task.isSuccessful()) {
                       for (QueryDocumentSnapshot document : task.getResult()) {
                           Log.d(TAG, document.getId() + " => " + document.getData());
                       }
                   } else {
                       Log.d(TAG, "Error getting documents: ", task.getException());
                   }
               } else {
                   Log.d(TAG, "get failed with ", task.getException());
               }
           }
       });


        return view;

    }
}


