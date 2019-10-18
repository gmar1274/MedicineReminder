package com.example.medicinereminder.Room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.medicinereminder.R;

import java.util.Date;

@Entity(tableName = "MedicineRecord")
public class MedicineRecord {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo
    @NonNull
    public String medicine_name;
    public Date last_taken;
    public String unit;
    public String note;
    public String quantifier;
    public double dosage_amount;
    public int color;
    public String icon;
    public String dosage_frequency;
    public  MedicineRecord(String medicine_name){
        this.medicine_name=medicine_name;
        this.last_taken = new Date();
        this.dosage_amount=-1;
        this.unit="N/A";
        this.quantifier="N/A";
        this.dosage_frequency="N/A";
        this.color= R.color.colorPrimary;
        this.icon="&#xf00d;";
    }
    public double getDosage_amount() {
        return dosage_amount;
    }
    public void setDosage_amount(double dosage_amount) {
        this.dosage_amount = dosage_amount;
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
    public void setNote(String note) {
        this.note = note;
    }
    public String getQuantifier() {
        return quantifier;
    }

    public void setMedicine_name(@NonNull String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public void setLast_taken(Date last_taken) {
        this.last_taken = last_taken;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDosage_frequency() {
        return dosage_frequency;
    }

    public void setDosage_frequency(String dosage_frequency) {
        this.dosage_frequency = dosage_frequency;
    }

    public String getRange(){
       return this.dosage_frequency;
    }
}