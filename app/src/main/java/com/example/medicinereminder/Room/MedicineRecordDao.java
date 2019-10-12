package com.example.medicinereminder.Room;

import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

@Dao
public interface MedicineRecordDao {
    @Query("SELECT * FROM MedicineRecord ORDER BY dosage DESC")
    List<MedicineRecord> getAll();
    @Query("SELECT  * FROM MedicineRecord ORDER BY dosage ASC LIMIT 1")
    List<MedicineRecord> getLastDosage();
}
