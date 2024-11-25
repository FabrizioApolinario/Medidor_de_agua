
MainActivity.java
package com.example.firebaseprueba;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase ;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button on;
    Button off;
    TextView distance;

    TextView MQ;
    DatabaseReference dref;

    DatabaseReference uff;
    String status;
    String xdxd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        on = (Button) findViewById(R.id.on);
        off = (Button) findViewById(R.id.off);
        distance = (TextView) findViewById(R.id.textView2);
        MQ = (TextView) findViewById(R.id.textView1) ;
        uff = FirebaseDatabase.getInstance().getReference();
        uff .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                xdxd = dataSnapshot.child("MQ7").getValue().toString();
                MQ.setText(xdxd);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        dref = FirebaseDatabase.getInstance().getReference();
        dref .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                status = dataSnapshot.child("distance").getValue().toString();
                distance.setText(status);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        on.setOnClickListener((view)->{
                 FirebaseDatabase database = FirebaseDatabase.getInstance();
                 DatabaseReference myRef = database.getReference("PRESS_STATUS");
                 myRef.setValue(1);
                });
        off.setOnClickListener((view)->{
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("PRESS_STATUS");
            myRef.setValue(0);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
