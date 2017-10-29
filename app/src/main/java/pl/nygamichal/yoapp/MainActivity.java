package pl.nygamichal.yoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ChildEventListener {
    public static final String TAG = LoginActivity.class.getCanonicalName();
    @BindView(R.id.editTextUser)
    EditText editTextUser;
    private String userLocal;
    AdapterHej adapterHej;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapterHej = new AdapterHej(new ArrayList<Hej>());

        //pobiera dane usera
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            //String name = user.getDisplayName();
            String email = user.getEmail();
            if (email!= null && email.contains("@"))
            {
                userLocal = email.split("@")[0];
                if (userLocal != null)
                {
                    YoApp.databaseReference.child(userLocal).child("hejs").addChildEventListener(this);
                }
            }
            //Uri photoUrl = user.getPhotoUrl();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.

            //String uid = user.getUid();
        }
        else
        {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            //by user nie mogl znalezc sie bez zalogowania
            MainActivity.this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        if (userLocal != null){
            YoApp.databaseReference.child(userLocal).child("hejs").removeEventListener(this);
        }
        super.onDestroy();
    }

    @OnClick(R.id.fabSend)
    public void onClickSend(){
        String userSendTo = editTextUser.getText().toString();

        if (userSendTo.contains(".") || userSendTo.contains("@") || userSendTo.contains("#") || userSendTo.contains("$") || userSendTo.contains("[") || userSendTo.contains("]")  )
        {
            Toast.makeText(this,"Wrong characher",Toast.LENGTH_SHORT).show();
            return;
        }
        Hej hej = new Hej();
        hej.fromUser = userLocal;
        hej.timestamp = System.currentTimeMillis();
        //User user = new User();
        YoApp.databaseReference.child(userSendTo).child("yojs").push().setValue(hej).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d(TAG, "onComplete: ");
            }
        });
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Hej hej = dataSnapshot.getValue(Hej.class);
        Log.d(TAG, "onChildAdded: ");
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        Log.d(TAG, "onChildChanged: ");
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {
        Log.d(TAG, "onChildRemoved: ");
    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
        Log.d(TAG, "onChildMoved: ");
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.d(TAG, "onCancelled: ");
    }
}
