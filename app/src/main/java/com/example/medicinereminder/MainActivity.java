package com.example.medicinereminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.medicinereminder.Room.AppRoomDatabase;
import com.example.medicinereminder.Room.MedicineRecord;
import com.example.medicinereminder.Room.MedicineRecordDao;
import com.example.medicinereminder.Room.MedicineRecordViewModel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MedicineRecordViewModel mMedicineRecordViewModel;
    public static final int NEW_MEDICINE_RECORD_ACTIVITY_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d("D","LOADED");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.e("ERR",errorCode+"");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });


        //
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        RecyclerView recyclerView = findViewById(R.id.rv);
        final MedicineRecordAdapter adapter = new MedicineRecordAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        mMedicineRecordViewModel =  new ViewModelProviders().of(this).get(MedicineRecordViewModel.class);
        mMedicineRecordViewModel.getmMedicineRecords().observe(this, new Observer<List<MedicineRecord>>() {
            @Override
            public void onChanged(List<MedicineRecord> medicineRecords) {
                // Update the cached copy of the words in the adapter.
                adapter.setmMedicineRecords(medicineRecords);
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewMedicineRecordActivity.class);
                startActivityForResult(intent, NEW_MEDICINE_RECORD_ACTIVITY_REQUEST_CODE);
            }
        });

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_MEDICINE_RECORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {//incoming from new activity and is okay
            String name = data.getStringExtra(NewMedicineRecordActivity.EXTRA_NAME);
            String dosage_amount = data.getStringExtra(NewMedicineRecordActivity.EXTRA_DOSAGE_AMOUNT);
            String dosage_unit = data.getStringExtra(NewMedicineRecordActivity.EXTRA_DOSAGE_UNITS);
            String dosage_freq = data.getStringExtra(NewMedicineRecordActivity.EXTRA_DOSAGE_FREQ);
            String unicodeIcon =data.getStringExtra(NewMedicineRecordActivity.EXTRA_ICON);
            String note = data.getStringExtra(NewMedicineRecordActivity.EXTRA_NOTE);
            int color = data.getIntExtra(NewMedicineRecordActivity.EXTRA_COLOR,this.getResources().getColor(R.color.colorPrimary));
            MedicineRecord medicineRecord = new MedicineRecord(name);
            medicineRecord.setDosage_amount(Double.parseDouble(dosage_amount));
            medicineRecord.setDosage_frequency(dosage_freq);
            medicineRecord.setUnit(dosage_unit);
            medicineRecord.setIcon(unicodeIcon);
            medicineRecord.setColor(color);
            medicineRecord.setNote(note);
            mMedicineRecordViewModel.insert(medicineRecord);
            Toast.makeText(
                    getApplicationContext(),
                    "Saved!",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Not saved",
                    Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
   @Override
   protected void onDestroy(){
        super.onDestroy();
        AppRoomDatabase.getAppDatabase().close();
   }
}
