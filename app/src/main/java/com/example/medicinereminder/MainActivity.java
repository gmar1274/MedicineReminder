package com.example.medicinereminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
//SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        TimePicker timePicker = findViewById(R.id.timePicker);
        timePicker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(c.get(Calendar.MINUTE));
        final RangeSeekBar<Float> seekBar = findViewById(R.id.seekBar);
//setNotifyWhileDragging is important method to achive this functionality
        seekBar.setNotifyWhileDragging(true);
        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Float>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Float minValue, Float maxValue) {
                       // seekBar.setSelectedMinValue(minValue);
                       // seekBar.setSelectedMaxValue(maxValue);
            }
        });
    }
}
