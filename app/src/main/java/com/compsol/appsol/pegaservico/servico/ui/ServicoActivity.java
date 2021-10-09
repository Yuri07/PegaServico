package com.compsol.appsol.pegaservico.servico.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import java.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.compsol.appsol.pegaservico.R;
import com.compsol.appsol.pegaservico.databinding.ActivityMainBinding;
import com.compsol.appsol.pegaservico.databinding.ActivityServicoBinding;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

public class ServicoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    DatePickerDialog datePickerDialog ;
    TimePickerDialog timePickerDialog ;
    int Year, Month, Day, Hour, Minute;
    Calendar calendar ;

    private ActivityServicoBinding binding;

    private EditText editText_datepicker;
    private EditText editText_timepicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_servico);

        binding = ActivityServicoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editText_datepicker = binding.editTextDate;
        editText_timepicker = binding.editTextTime;

        calendar = Calendar.getInstance();

        Year = calendar.get(Calendar.YEAR) ;
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);
        Hour = calendar.get(Calendar.HOUR_OF_DAY);
        Minute = calendar.get(Calendar.MINUTE);

        editText_datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = DatePickerDialog.newInstance(ServicoActivity.this, Year, Month, Day);
                datePickerDialog.setThemeDark(false);
                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setTitle("Date Picker");


                // Setting Min Date to today date
                Calendar min_date_c = Calendar.getInstance();
                datePickerDialog.setMinDate(min_date_c);



                // Setting Max Date to next 2 years
                Calendar max_date_c = Calendar.getInstance();
                max_date_c.set(Calendar.YEAR, Year+2);
                datePickerDialog.setMaxDate(max_date_c);

                datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialogInterface) {

                        Toast.makeText(ServicoActivity.this, "Datepicker Canceled", Toast.LENGTH_SHORT).show();
                    }
                });

                // If you're calling this from a support Fragment
                //datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
                datePickerDialog.show(getSupportFragmentManager(), "Datepickerdialog");
            }
        });

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "Date: "+Day+"/"+(Month+1)+"/"+Year;

        Toast.makeText(ServicoActivity.this, date, Toast.LENGTH_LONG).show();

        editText_datepicker.setText(date);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

    }
}