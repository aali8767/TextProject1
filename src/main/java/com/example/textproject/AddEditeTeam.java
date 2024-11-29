package com.example.textproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.textproject.databinding.ActivityAddEditeTeamBinding;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;


public class AddEditeTeam extends AppCompatActivity {
    public static final String TEAM_KEY = "teamKey";
    public static final String ID_KEY = "idKey";
    public static final String RESULT_KEY = "resultKey";
    public static final String SAVE_KEY = "saveKey";
    public static final String EDIT_KEY = "editKey";
    ActivityAddEditeTeamBinding binding;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditeTeamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent currentIntent = getIntent();
        if (currentIntent != null) {
            Team currentTeam = (Team) currentIntent.getSerializableExtra(TEAM_KEY);
            if (currentTeam != null) {
                binding.editTextTeamName.setText(currentTeam.getName());
                binding.editTextTeamDate.setText(String.valueOf(currentTeam.getDate()));
                binding.buttonSaveTeam.setText("Edit");

                binding.buttonSaveTeam.setOnClickListener(v -> {
                    finishAndPutKey(EDIT_KEY, currentTeam.getId());
                });
            } else {
                binding.buttonSaveTeam.setOnClickListener(v -> {
                    finishAndPutKey(SAVE_KEY, null);
                });
            }
        }

        binding.editTextTeamDate.setOnClickListener(v -> {
            DatePickerDialog dialog = DatePickerDialog.newInstance((view, year, monthOfYear, dayOfMonth) -> {
                binding.editTextTeamDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year + "/");
                calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }, Calendar.getInstance());
            dialog.show(getSupportFragmentManager(), null);
        });
    }

    private void finishAndPutKey(String key, Long id) {
        String name = binding.editTextTeamName.getText().toString();
        String number = binding.editTextTeamDate.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(number) || calendar == null) {
            Toast.makeText(AddEditeTeam.this, "please enter avalid data", Toast.LENGTH_LONG).show();
            return;
        }
        Team team = new Team(name, calendar.getTime());
        Intent intent = new Intent();
        intent.putExtra(TEAM_KEY, team);
        System.out.println("ID_KEY "+ id);
        intent.putExtra(ID_KEY, id);
        intent.putExtra(RESULT_KEY, key);
        setResult(RESULT_OK, intent);
        finish();
    }
}