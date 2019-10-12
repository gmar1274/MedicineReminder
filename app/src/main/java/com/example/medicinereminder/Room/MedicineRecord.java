package com.example.medicinereminder.Room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "MedicineRecord")
public class MedicineRecord {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo
    @NonNull
    public String medicine_name;
    public Date dosage;
    public float start_range;
    public float end_range;
    public String unit;
    public  MedicineRecord(String name){
        this.medicine_name=name;
    }
    public void setDosage(Date dosage) {
        this.dosage = dosage;
    }
    public void setStart_range(float start_range) {
        this.start_range = start_range;
    }
    public void setEnd_range(float end_range) {
        this.end_range = end_range;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
}