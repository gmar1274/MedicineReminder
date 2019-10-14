package com.example.medicinereminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.medicinereminder.Room.MedicineRecord;

import java.text.SimpleDateFormat;
import java.util.List;

public class MedicineRecordAdapter extends RecyclerView.Adapter<MedicineRecordAdapter.MedicineViewHolder> {
    public class MedicineViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvNote;
        private final TextView tvDosageAmount;
        private final TextView tvUnit;
        private final TextView tvQuantifier;
        private final TextView tvRange;
        private final TextView tvName;
        private final TextView tvLastTaken;

        private MedicineViewHolder(View itemView){
            super(itemView);
            tvNote = itemView.findViewById(R.id.tvNote);
            tvName = itemView.findViewById(R.id.tvMedicineName);
            tvDosageAmount = itemView.findViewById(R.id.tvDosageAmount);
            tvUnit = itemView.findViewById(R.id.tvUnits);
            tvQuantifier = itemView.findViewById(R.id.tvQuantifier);
            tvRange = itemView.findViewById(R.id.tvRange);
        }
    }
    private final LayoutInflater mInflater;
    private List<MedicineRecord> mMedicineRecords;//cached copy
    public MedicineRecordAdapter(Context context){
        mInflater= LayoutInflater.from(context);
    }
    @Override
    public  MedicineViewHolder onCreateViewHolder(ViewGroup parent, int viewType ){
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new MedicineViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MedicineViewHolder holder, int pos){
        if(mMedicineRecords!=null){
            MedicineRecord current = mMedicineRecords.get(pos);
            holder.tvNote.setText(current.getNote());
            holder.tvName.setText(current.getMedicine_name());
            holder.tvDosageAmount.setText(String.format("%.2f",current.getDosage_amount()));
            holder.tvUnit.setText(current.getUnit());
            holder.tvQuantifier.setText(current.getQuantifier());
            holder.tvRange.setText(current.getRange());
            SimpleDateFormat sdf = new SimpleDateFormat("EE MM/dd/yyyy h:mm a");
            holder.tvLastTaken.setText(sdf.format(current.getLast_taken()));
        }else {
            holder.tvName.setText("No upcoming medicines...");

        }
    }
}