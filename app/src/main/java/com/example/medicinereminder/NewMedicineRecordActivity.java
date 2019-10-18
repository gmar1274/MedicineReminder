package com.example.medicinereminder;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorListener;

public class NewMedicineRecordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY ="" ;
    private final String ERROR = "Cannot be empty.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine_record);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        final RadioGroup radioGroup = findViewById(R.id.radioGroup);
        final RadioButton radioButtonLiquid = findViewById(R.id.radioButtonLiquid);
        final RadioButton radioButtonTablet = findViewById(R.id.radioButtonTablet);
        final RadioButton radioButtonCapsule = findViewById(R.id.radioButtonCapsule);
        ColorPickerView colorPickerView = findViewById(R.id.colorPickerView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            colorPickerView.setPureColor(getColor(R.color.colorPrimary));
        }
        colorPickerView.setLifecycleOwner(this); // this means activity or fragment.
        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {
                if(color==-258)return;
                radioButtonLiquid.setTextColor(color);
                radioButtonTablet.setTextColor(color);
                radioButtonCapsule.setTextColor(color);
                //Toast.makeText(NewMedicineRecordActivity.this,""+color,Toast.LENGTH_LONG).show();
            }
        });
        final EditText editTextMedName = findViewById(R.id.editTextMedName);
        final EditText editTextDosageAmount = findViewById(R.id.editTextDosageAmount);
        final EditText editTextDosageUnits = findViewById(R.id.editTextDosageUnits);
        final EditText editTextDosageFreq = findViewById(R.id.editTextDosageFreq);
        final EditText multiAutoCompleteTextViewNote = findViewById(R.id.multiAutoCompleteTextViewNote);

        Button button = findViewById(R.id.buttonCreateRecord);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                RadioButton icon = findViewById(radioGroup.getCheckedRadioButtonId());
                Toast.makeText(NewMedicineRecordActivity.this,icon.getText().toString(),Toast.LENGTH_LONG).show();
                Intent replyIntent = new Intent();
                if (isEmpty(editTextMedName)) {
                   // setResult(RESULT_CANCELED, replyIntent);
                    setError(editTextMedName);
                }else if(isEmpty(editTextDosageAmount)){
                    setError(editTextDosageAmount);
                }else if(isEmpty(editTextDosageUnits)){
                    setError(editTextDosageUnits);
                }else if(isEmpty(editTextDosageFreq)){
                    setError(editTextDosageFreq);
                } else {
                    String name = editTextMedName.getText().toString();
                    String dosage_amount = editTextDosageAmount.getText().toString();
                    String dosage_unit = editTextDosageUnits.getText().toString();
                    String dosage_freq = editTextDosageFreq.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, name);
                    setResult(RESULT_OK, replyIntent);
                    finish();
                }

            }
        });
    }
    private boolean isEmpty(EditText view){
        return TextUtils.isEmpty(view.getText());
    }
    private void setError(EditText view){
        view.setError(ERROR);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
