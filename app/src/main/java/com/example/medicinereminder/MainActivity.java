package com.example.medicinereminder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.medicinereminder.Room.AppRoomDatabase;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Upcoming Medication");
        RecyclerView recyclerView = findViewById(R.id.rv);
        final MedicineRecordAdapter adapter = new MedicineRecordAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*Spinner unitSpinner = findViewById(R.id.spinner);
        final RangeSeekBar<Float> seekBar = findViewById(R.id.seekBar);
        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Float>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Float minValue, Float maxValue) {
                if(minValue<1){
                    //change hours to mins
                    seekBar.setSelectedMinValue(60*minValue);
                }
                if(maxValue<1){
                    seekBar.setSelectedMaxValue(60*maxValue);
                }
            }
        });*/
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private String prettyDisplay(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        return sdf.format(date);
    }

    private String getNextDosage() {
        String noDosage = "You have no upcoming dosage.";
        return noDosage;
    }
}
