package com.example.textproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.textproject.databinding.ActivityAddEditePlayerBinding;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;


public class AddEditePlayer extends AppCompatActivity {
    ActivityAddEditePlayerBinding binding;
    private AddEditPlayerViewModel viewModel;
    Calendar calendar;
    public static final String PLAYER_KEY = "playerKey";
    public static final String ID_KEY = "idKey";
    public static final String RESULT_KEY = "resultKey";
    public static final String SAVE_KEY = "saveKey";
    public static final String EDIT_KEY = "editKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditePlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(AddEditPlayerViewModel.class);

        viewModel.getAllTeam(teams -> {
            ArrayList<String> teamsId = new ArrayList<>();
            for (Team team : teams) {
                teamsId.add(String.valueOf(team.getId()));
            }

            ArrayAdapter<String> adapter =
                    new ArrayAdapter<>(getBaseContext(), R.layout.item_teams_id_spinner, teamsId);
            binding.spin.setAdapter(adapter);
            // كيفية اعطاء السبينر قيمة افتراضية

        });

        Intent currentIntent = getIntent();
        if (currentIntent != null) {
            Player currentPlayer = (Player) currentIntent.getSerializableExtra(PLAYER_KEY);
            if (currentPlayer != null) {
                binding.editTextPlayerName.setText(currentPlayer.getName());
                binding.editTextPlayerShirtNo.setText(String.valueOf(currentPlayer.getShirtNumber()));
                binding.editTextPlayerDate.setText(String.valueOf(currentPlayer.getDate()));
                binding.teamId.setText(String.valueOf(currentPlayer.getTeamId()));
                binding.buttonSavePlayer.setText("Edit");

                binding.buttonSavePlayer.setOnClickListener(v -> {
                    finishAndPutKey(EDIT_KEY, currentPlayer.getId());
                });
            } else {
                binding.buttonSavePlayer.setOnClickListener(v -> {
                    finishAndPutKey(SAVE_KEY, null);
                });
            }
        }

        binding.editTextPlayerDate.setOnClickListener(v -> {
            DatePickerDialog dialog = DatePickerDialog.newInstance((view, year, monthOfYear, dayOfMonth) -> {
                binding.editTextPlayerDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }, Calendar.getInstance());
            dialog.show(getSupportFragmentManager(), null);
        });
    }

    private void finishAndPutKey(String key, Long id) {
        String name = binding.editTextPlayerName.getText().toString();
        String ShirtNumber = binding.editTextPlayerShirtNo.getText().toString();
        String teamId = binding.teamId.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(ShirtNumber) || TextUtils.isEmpty(teamId) || calendar == null) {
            Toast.makeText(AddEditePlayer.this, "please enter a valid data", Toast.LENGTH_SHORT).show();
            return;
        }
        int shirtNumber = Integer.parseInt(ShirtNumber);
        int TeamId = Integer.parseInt(teamId);
        Player player = new Player(name, shirtNumber, calendar.getTime(), TeamId);
        Intent intent = new Intent();
        intent.putExtra(PLAYER_KEY, player);
        intent.putExtra(ID_KEY, id);
        intent.putExtra(RESULT_KEY, key);
        setResult(RESULT_OK, intent);
        finish();
    }
}