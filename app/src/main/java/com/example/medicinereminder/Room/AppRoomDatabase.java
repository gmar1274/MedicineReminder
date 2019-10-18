package com.example.medicinereminder.Room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.medicinereminder.Converters;

/***
 * AppDatabase DAO (data access object). Needs to be an interface or abstract class.
 * @see https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#4
 *
 * By default, all queries must be executed on a separate thread.
 *
 * Room uses the DAO to create a clean API for your code.
 **/

@Database(entities = {MedicineRecord.class},version = 2)
@TypeConverters({Converters.class})
public abstract class AppRoomDatabase extends RoomDatabase {
    public abstract  MedicineRecordDao medicineRecordDao();
    private static volatile AppRoomDatabase mSql;// indicate to the java compiler
    // and thread to not cache value of this var and always
    // always read it from main memory.
    public static AppRoomDatabase getDatabase(final Context context){
        if(mSql==null){
            synchronized (AppRoomDatabase.class){
                if(mSql==null){
                    mSql = Room.databaseBuilder(context.getApplicationContext(), AppRoomDatabase.class, "MedicineRecord").addCallback(sRoomDatabaseCallback).addMigrations(MIGRATION_1_2).build();
                }
            }
        }
        return mSql;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(mSql).execute();
                }
            };
    public static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final MedicineRecordDao mMedicineRecordDao;
        PopulateDbAsync(AppRoomDatabase db) {
            mMedicineRecordDao = db.medicineRecordDao();
        }
        @Override
        protected Void doInBackground(final Void... params) {
            //mMedicineRecordDao.deleteAll();
            //MedicineRecord medicineRecord = new MedicineRecord("Hello");
            //mMedicineRecordDao.insert(medicineRecord);
            //MedicineRecord medicineRecord1 = new MedicineRecord("World");
            //mMedicineRecordDao.insert(medicineRecord1);
            return null;
        }
    }
   protected static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE 'MedicineRecord'");
            database.execSQL("CREATE TABLE 'MedicineRecord' (" +
                    "uid INTEGER PRIMARY KEY NOT NULL," +
                    "medicine_name TEXT NOT NULL," +
                    "last_taken INTEGER ," +
                    "unit TEXT, note TEXT, quantifier TEXT, dosage_amount REAL NOT NULL, color INTEGER NOT NULL, icon TEXT, dosage_frequency TEXT)");
        }
    };
    public static AppRoomDatabase getAppDatabase (){return mSql;}
}
