package com.example.medicinereminder.Room;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MedicineRecordViewModel extends AndroidViewModel {
    private MedicineRecordRepo mMedicineRecordRepo;
    private LiveData<List<MedicineRecord>> mMedicineRecords;
    public MedicineRecordViewModel(Application app){
        super(app);
        mMedicineRecordRepo = new MedicineRecordRepo(app);
        mMedicineRecords = mMedicineRecordRepo.getmMedicineRecords();
    }
    public LiveData<List<MedicineRecord>> getmMedicineRecords(){
        return mMedicineRecords;
    }
    public void insert(MedicineRecord medicineRecord){
        mMedicineRecordRepo.insert(medicineRecord);
    }
    public MedicineRecordRepo getAppRepo(){return this.mMedicineRecordRepo;}
}
