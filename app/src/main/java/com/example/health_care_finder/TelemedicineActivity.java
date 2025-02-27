package com.example.health_care_finder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TelemedicineActivity extends AppCompatActivity {
    private EditText patientNameEditText, appointmentDateEditText, patientDiseaseEditText, patientCardNumberEditText, patientAgeEditText, patientContactEditText;
    private Button scheduleButton;
    private ListView appointmentsListView;
    private HealthcareDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telemedicine);

        patientNameEditText = findViewById(R.id.patientNameEditText);
        appointmentDateEditText = findViewById(R.id.appointmentDateEditText);
        patientDiseaseEditText = findViewById(R.id.patientDiseaseEditText);
        patientCardNumberEditText = findViewById(R.id.patientCardNumberEditText);
        patientAgeEditText = findViewById(R.id.patientAgeEditText);
        patientContactEditText = findViewById(R.id.patientContactEditText);
        appointmentsListView = findViewById(R.id.appointmentsListView);
        scheduleButton = findViewById(R.id.scheduleButton);

        databaseHelper = new HealthcareDatabaseHelper(this);

        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });


        loadAppointments();
    }

    private void showConfirmationDialog() {
        final String patientName = patientNameEditText.getText().toString();
        final String appointmentDate = appointmentDateEditText.getText().toString();
        final String patientDisease = patientDiseaseEditText.getText().toString();
        final String patientCardNumber = patientCardNumberEditText.getText().toString();
        final String patientAge = patientAgeEditText.getText().toString();
        final String patientContact = patientContactEditText.getText().toString();

        if (!isValidDate(appointmentDate)) {
            showMessage("Invalid Date", "The appointment date cannot be in the past. Please correct it.");
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Appointment");
        builder.setMessage("Do you want to schedule an appointment for " +
                patientName + " (Disease: " + patientDisease + ", Card No: " + patientCardNumber + ", Age: " + patientAge + ", Contact: " + patientContact + ") on " + appointmentDate + "?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                boolean isInserted = databaseHelper.addAppointment(patientName, appointmentDate, patientDisease, patientCardNumber, patientAge, patientContact);
                if (isInserted) {
                    showMessage("Success", "Appointment scheduled successfully.");
                    loadAppointments(); // Refresh the appointment list
                } else {
                    showMessage("Error", "Failed to schedule appointment.");
                }
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean isValidDate(String appointmentDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); // Adjust format as needed
        try {
            Date date = sdf.parse(appointmentDate);
            return date != null && !date.before(new Date()); // Checking if the date is not before today
        } catch (ParseException e) {
            return false;
        }
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    private void loadAppointments() {
        List<Appointment> appointments = databaseHelper.getAllAppointments();
        ArrayAdapter<Appointment> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, appointments);
        appointmentsListView.setAdapter(adapter);
    }
}