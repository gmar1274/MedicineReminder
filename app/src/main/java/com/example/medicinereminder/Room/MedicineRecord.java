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
    public Date last_taken;
    public float start_range;
    public float end_range;
    public String unit;
    public String note;
    public String quantifier;
    public double dosage_amount;
    public  MedicineRecord(String name){
        this.medicine_name=name;
        this.last_taken = new Date();
        this.start_range=this.end_range=-1;
    }

    public double getDosage_amount() {
        return dosage_amount;
    }

    public void setDosage_amount(double dosage_amount) {
        this.dosage_amount = dosage_amount;
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
    public void setQuantifier(String quantifier){
        this.quantifier=quantifier;
    }
    public String getNote(){return note;}
    public String getMedicine_name(){return medicine_name;}
    public String getUnit(){return unit;}
    public Date getLast_taken(){return last_taken;}

    public float getStart_range() {
        return start_range;
    }

    public float getEnd_range() {
        return end_range;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getQuantifier() {
        return quantifier;
    }
    public String getRange(){
        if(start_range!=-1 && end_range !=-1){
            return String.format("%.2f - %.2f",start_range,end_range);
        }else
            return String.format("%.2f",start_range);
    }
}