package com.example.medicinereminder.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/***
 * AppDatabase DAO (data access object). Needs to be an interface or abstract class.
 * @see https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#4
 *
 * By default, all queries must be executed on a separate thread.
 *
 * Room uses the DAO to create a clean API for your code.
 **/
@Database(entities = {MedicineRecord.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract  MedicineRecordDao medicineRecordDao();
}
