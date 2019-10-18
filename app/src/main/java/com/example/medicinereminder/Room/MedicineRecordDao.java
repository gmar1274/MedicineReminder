package com.example.medicinereminder.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

/**
 * Data access object
 * Needs to use the lifecycle library class LiveData for data
 * observation.
 * @see https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#5
 *
 * Use MutableLiveData to update the stored data
 **/
@Dao
public interface MedicineRecordDao {
    @Query("SELECT * FROM MedicineRecord ORDER BY last_taken DESC")
    LiveData<List<MedicineRecord>> getAll();
    @Query("SELECT  * FROM MedicineRecord ORDER BY last_taken ASC LIMIT 1")
    LiveData<List<MedicineRecord>> getLastDosage();
    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(MedicineRecord medicineRecord);
    @Delete
    void delete(MedicineRecord medicineRecord);
    @Query("DELETE FROM 'MedicineRecord'")
    void deleteAll();
}
