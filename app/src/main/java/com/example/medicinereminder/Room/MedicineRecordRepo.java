package com.example.medicinereminder.Room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MedicineRecordRepo {
    private MedicineRecordDao mMedicineRecordDao;
    private LiveData<List<MedicineRecord>> mMedicineRecords;
    public MedicineRecordRepo(Application app){
        AppRoomDatabase db = AppRoomDatabase.getDatabase(app);
        mMedicineRecordDao = db.medicineRecordDao();
        mMedicineRecords = mMedicineRecordDao.getAll();
    }
    public LiveData<List<MedicineRecord>> getmMedicineRecords(){
        return mMedicineRecords;
    }

    /**
     * Must be called on a non-UI thread
     * @param medicineRecord
     */
    public void insert(MedicineRecord medicineRecord){

    }
    private static class insertAsyncTask extends AsyncTask<MedicineRecord,Void,Void>{
        private  MedicineRecordDao mAsyncTaskDao;
        public insertAsyncTask(MedicineRecordDao medicineRecordDao){
            mAsyncTaskDao = medicineRecordDao;
        }
        @Override
        protected  Void doInBackground(final MedicineRecord... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
