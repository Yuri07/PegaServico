package com.compsol.appsol.pegaservico.servico.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;

import java.text.NumberFormat;
import java.util.Calendar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.compsol.appsol.pegaservico.PegaServicoApp;
import com.compsol.appsol.pegaservico.R;
import com.compsol.appsol.pegaservico.databinding.ActivityMainBinding;
import com.compsol.appsol.pegaservico.databinding.ActivityServicoBinding;
import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.entities.ServiceItemBuilder;
import com.compsol.appsol.pegaservico.entities.User;
import com.compsol.appsol.pegaservico.servico.ServicoPresenter;
import com.cottacush.android.currencyedittext.CurrencyEditText;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import javax.inject.Inject;

public class ServicoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener, ServicoView {

    DatePickerDialog datePickerDialog ;
    TimePickerDialog timePickerDialog ;
    int Year, Month, Day, Hour, Minute;
    Calendar calendar ;

    private ActivityServicoBinding binding;

    private TextView textview_datepicker;
    private TextView textview_timepicker;

    private Button button_minus1hr, button_minus6hr, button_plus1hr, button_plus6hr;
    private Button button_plus10rs, button_plus50rs, button_plus100rs;

    private EditText editText_period;
    //private CurrencyEditText currentEditText_Value;
    private EditText currentEditText_Value;

    private TextView textViewDate, textViewHora;

    @Inject
    ServicoPresenter presenter;


    private User myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_servico);

        binding = ActivityServicoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textview_datepicker = binding.actServiTextviewData;
        textview_timepicker = binding.actServiTextviewHora;

        calendar = Calendar.getInstance();

        Year = calendar.get(Calendar.YEAR) ;
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);
        Hour = calendar.get(Calendar.HOUR_OF_DAY);
        Minute = calendar.get(Calendar.MINUTE);

        String date = Day+"/"+(Month+1)+"/"+Year;
        textview_datepicker.setText(date);

        String time = Hour+":"+Minute;
        textview_timepicker.setText(time);

        textview_datepicker.setOnClickListener(new View.OnClickListener() {
            //https://www.freakyjolly.com/android-material-datepicker-and-timepicker-by-wdullaer-tutorial-by-example/
            @Override
            public void onClick(View v) {
                datePickerDialog = DatePickerDialog.newInstance(ServicoActivity.this, Year, Month, Day);
                datePickerDialog.setThemeDark(false);
                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setTitle("Data");

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

        textview_timepicker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timePickerDialog = TimePickerDialog.newInstance(ServicoActivity.this, Hour, Minute,false );
                timePickerDialog.setThemeDark(false);
                //timePickerDialog.showYearPickerFirst(false);
                timePickerDialog.setTitle("Horário");

                timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialogInterface) {

                        Toast.makeText(ServicoActivity.this, "Timepicker Canceled", Toast.LENGTH_SHORT).show();
                    }
                });

                timePickerDialog.show(getSupportFragmentManager(), "TimePickerDialog");
            }
        });

        button_minus1hr = binding.buttonminus1hr;
        button_minus6hr = binding.buttonminus6hr;
        button_plus1hr = binding.buttonplus1hr;
        button_plus6hr = binding.buttonplus6hr;

        editText_period = binding.editTextPeriod;
        currentEditText_Value = binding.editTextCurrencyValue;

        textViewDate = binding.actServiTextviewData;
        textViewHora = binding.actServiTextviewHora;



        button_minus1hr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String[] period = String.valueOf(editText_period.getText()).split(" ");

                int newQuant = Integer.parseInt(period[0])-1;

                if(newQuant>-1)
                    editText_period.setText(newQuant+" horas");
                else
                    editText_period.setText(0 +" horas");

            }
        });

        button_minus6hr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String[] period = String.valueOf(editText_period.getText()).split(" ");

                int newQuant = Integer.parseInt(period[0])-6;

                if(newQuant>-1)
                    editText_period.setText(newQuant+" horas");
                else
                    editText_period.setText(0 +" horas");

            }
        });

        button_plus1hr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String[] period = String.valueOf(editText_period.getText()).split(" ");

                int newQuant = Integer.parseInt(period[0])+1;

                if(newQuant<25)
                    editText_period.setText(newQuant+" horas");
                else
                    editText_period.setText(24 +" horas");

            }
        });

        button_plus6hr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String[] period = String.valueOf(editText_period.getText()).split(" ");

                int newQuant = Integer.parseInt(period[0])+6;

                if(newQuant<25)
                    editText_period.setText(newQuant+" horas");
                else
                    editText_period.setText(24 +" horas");

            }
        });

        currentEditText_Value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            private String current = "";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(current)){
                    if(current.length()>=11 && s.length()>11)  {
                        currentEditText_Value.setText(current);
                        currentEditText_Value.setSelection(current.length());
                    }else {
                        currentEditText_Value.removeTextChangedListener(this);

                        String cleanString = s.toString().replaceAll("[R$,.\\s]", "");

                        //if(cleanString.equals(" ")||cleanString.equals(""))
                        //cleanString = "0.00";

                        double parsed = Double.parseDouble(cleanString);
                        String formatted = NumberFormat.getCurrencyInstance().format((parsed / 100));
                        current = formatted;

                        if(current.charAt(2)!=' '){
                            current = addChar(formatted, ' ', 2);
                        }

                        /*char[] currentVectorChar = new char[] { ' ', ' ', ' '};
                        current.getChars(0,2,currentVectorChar,0);*/
                        //current = addChar(formatted, ' ', 2);
                        //current = formatted.replaceAll("[R$]", "");

                        /*if(currentVectorChar[2]!=' '){
                            current = addChar(formatted, ' ', 2);
                        }*/

                        currentEditText_Value.setText(current);
                        currentEditText_Value.setSelection(current.length());

                        currentEditText_Value.addTextChangedListener(this);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button_plus10rs = binding.button10rs;
        button_plus50rs = binding.button50rs;
        button_plus100rs = binding.button100rs;

        button_plus10rs.setOnClickListener(new View.OnClickListener() {
            private String current = "";
            public void onClick(View v) {
                if(currentEditText_Value.getText()==null || currentEditText_Value.
                                                            getText().toString().equals("")) {
                    currentEditText_Value.setText("R$ 10,00");
                }else {
                    String cleanString = currentEditText_Value.
                            getText().toString().replaceAll("[R$,.\\s]", "");

                    double parsed = Double.parseDouble(cleanString);

                    double newValue = parsed + 1000.0;

                    String formatted = NumberFormat.getCurrencyInstance().format((newValue / 100));

                    //current = addChar(formatted, ' ', 2);
                    //current = formatted.replaceAll("[R$]", "");

                    if(formatted.length()<12) {
                        currentEditText_Value.setText(formatted);
                        currentEditText_Value.setSelection(formatted.length());
                    }
                }

            }
        });

        button_plus50rs.setOnClickListener(new View.OnClickListener() {
            private String current = "";
            public void onClick(View v) {
                if(currentEditText_Value.getText()==null || currentEditText_Value.getText()
                                                                        .toString().equals("")) {
                    currentEditText_Value.setText("R$ 50,00");
                }else {

                    String cleanString = currentEditText_Value.
                            getText().toString().replaceAll("[R$,.\\s]", "");

                    double parsed = Double.parseDouble(cleanString);

                    double newValue = parsed + 5000.0;

                    String formatted = NumberFormat.getCurrencyInstance().format((newValue / 100));

                    //current = addChar(formatted, ' ', 2);//usaddo com editText
                    //current = formatted.replaceAll("[R$]", "");//usado com Cottacush.currencyEditText

                    if(formatted.length()<12) {
                        currentEditText_Value.setText(formatted);
                        currentEditText_Value.setSelection(formatted.length());
                    }
                }

            }
        });

        button_plus100rs.setOnClickListener(new View.OnClickListener() {
            private String current = "";
            public void onClick(View v) {
                if(currentEditText_Value.getText()==null || currentEditText_Value.getText()
                                                                        .toString().equals("")) {
                    currentEditText_Value.setText("R$ 100,00");
                }else {

                    String cleanString = currentEditText_Value.
                            getText().toString().replaceAll("[R$,.\\s]", "");

                    double parsed = Double.parseDouble(cleanString);

                    double newValue = parsed + 10000.0;

                    String formatted = NumberFormat.getCurrencyInstance().format((newValue / 100));

                    //current = addChar(formatted, ' ', 2);
                    //current = formatted.replaceAll("[R$]", "");
                    if(formatted.length()<12) {
                        currentEditText_Value.setText(formatted);
                        currentEditText_Value.setSelection(formatted.length());
                    }
                }

            }
        });

        Button confirmServiceButton = binding.confirmServiceButton;
        TextView cancelTextView = binding.cancelTextview;

        confirmServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(myUser!=null) {

                    String valueString = currentEditText_Value.
                            getText().toString().replaceAll("[R$,.\\s]", "");

                    double valueService = Double.parseDouble(valueString) / 100;

                    String dataString = textViewDate.getText().toString();
                    String horaString = textViewHora.getText().toString();

                    String periodString = editText_period.getText().toString();

                    ServiceItem servico = new ServiceItemBuilder()
                            .email(myUser.getEmail())
                            .nome(myUser.getNome())
                            .data(dataString)
                            .entrada(horaString)
                            .periodo(Integer.parseInt(periodString))
                            .valor(valueService)
                            .status(ServiceItem.waitingAcceptStatus)
                            .urlPhotoUser(myUser.getUrlPhotoUser())
                            .build();

                    presenter.addService(servico);


                }
            }
        });

        setupInjection();

        presenter.onCreate();

    }

    private void setupInjection() {
        PegaServicoApp app = (PegaServicoApp) getApplication();
        app.getChatComponent(this, this).inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int Year, int Month, int Day) {
        String date = Day+"/"+(Month+1)+"/"+Year;

        Toast.makeText(ServicoActivity.this, date, Toast.LENGTH_LONG).show();

        textview_datepicker.setText(date);
        this.Year = Year;
        this.Month = Month;
        this.Day = Day;
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String time = hourOfDay+":"+minute;

        Toast.makeText(ServicoActivity.this, time, Toast.LENGTH_LONG).show();

        textview_timepicker.setText(time);
        this.Hour = hourOfDay;
        this.Minute = minute;
    }

    private String addChar(String str, char ch, int position) {
        int len = str.length();
        char[] updatedArr = new char[len + 1];
        str.getChars(0, position, updatedArr, 0);
        updatedArr[position] = ch;
        str.getChars(position, len, updatedArr, position + 1);
        return new String(updatedArr);
    }

    @Override
    public void onSuccessToGetDataUser(User currentUser) {
        myUser = currentUser;
    }

    @Override
    public void onFailedToGetDateUser() {
        myUser = null;
    }

    @Override
    public void onServiceConfirmedError() {

    }

    @Override
    public void onServiceConfirmed() {

    }
}