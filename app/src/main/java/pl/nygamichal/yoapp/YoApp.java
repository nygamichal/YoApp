package pl.nygamichal.yoapp;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mnyga on 31.08.2017.
 */

public class YoApp extends Application {

    FirebaseDatabase database;
    public static DatabaseReference databaseReference;


    @Override
    public void onCreate() {
        super.onCreate();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("user");
        databaseReference.setValue("Test","Hello World");

    }
}
