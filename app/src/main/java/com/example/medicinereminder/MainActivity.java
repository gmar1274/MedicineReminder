package com.example.medicinereminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.medicinereminder.Room.AppDatabase;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private AppDatabase mSql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        TextView tv = findViewById(R.id.tvNextDosage);
        tv.setText(getNextDosage());
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
    protected void onStart(){
        mSql = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"MedicineRecord").build();
        super.onStart();
    }
    @Override
    protected void onStop(){
        mSql.close();
        super.onStop();
    }
    private String prettyDisplay(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        return sdf.format(date);
    }
    private String getNextDosage(){
        String noDosage = "You have no upcoming dosage.";
        return noDosage;
    }
}
