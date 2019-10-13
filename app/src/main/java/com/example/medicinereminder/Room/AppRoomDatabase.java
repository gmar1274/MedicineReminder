package com.example.medicinereminder.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
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
public abstract class AppRoomDatabase extends RoomDatabase {
    public abstract  MedicineRecordDao medicineRecordDao();
    private static volatile AppRoomDatabase mSql;// indicate to the java compiler
    // and thread to not cache value of this var and always
    // always read it from main memory.
    public static AppRoomDatabase getDatabase(final Context context){
        if(mSql==null){
            synchronized (AppRoomDatabase.class){
                if(mSql==null){
                    mSql = Room.databaseBuilder(context.getApplicationContext(), AppRoomDatabase.class, "MedicineRecord").build();
                }
            }
        }
        return mSql;
    }
}
