package com.example.dhu_project;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    Map<String, Object> task = new HashMap<String, Object>();

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;

    private ListView listView;
    private ArrayAdapter<String> adapter;
    List<Object> Array = new ArrayList<Object>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        listView = (ListView)findViewById(R.id.timelist);

        initDatabse();


        adapter = new ArrayAdapter<String>(this, R.layout.activity_simpleitem, new ArrayList<String>());
        listView.setAdapter(adapter);

        mReference = mDatabase.getReference("timer/stopwatch");
        mReference.orderByValue().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();
                int count = 1;
                ArrayList<Integer> a = new ArrayList<Integer>();

                for (DataSnapshot messgaData : snapshot.getChildren()) {
                    String msg = messgaData.getValue().toString();
                    int i_msg = Integer.parseInt(msg);
                    a.add(i_msg);
                }
                Collections.sort(a, Collections.reverseOrder());
                for (int b : a) {
                    if(count <= 10) {
                        int c = b;
                        int hour = c / (60 * 60);
                        int min = c % (60 * 60) / 60;
                        int sec = c % 60;

                        String time = hour + "시" + min + "분" + sec + "초";
                        Array.add(count + "등 : " + time);
                        adapter.add(count + "등 : " + time);
                        count++;
                    }
                }
                    adapter.notifyDataSetChanged();
                    listView.setSelection(adapter.getCount() - 1);
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("timer/normal/clear").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TextView text = (TextView)findViewById(R.id.textView2);
                int value = snapshot.getValue(int.class);
                String str_value = Integer.toString(value);
                text.setText("클리어 횟수 : " + str_value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("timer/hard/clear").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TextView text = (TextView)findViewById(R.id.textView3);
                int value = snapshot.getValue(int.class);
                String str_value = Integer.toString(value);
                text.setText("클리어 횟수 : " + str_value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initDatabse() {
        mDatabase = FirebaseDatabase.getInstance();

        mReference = mDatabase.getReference("log");
        mReference.child("log").setValue("check");

        mChild = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mReference.addChildEventListener(mChild);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
