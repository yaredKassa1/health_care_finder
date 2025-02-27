package com.example.health_care_finder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class HealthcareDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "healthcare.db";
    private static final int DATABASE_VERSION = 1;

    // Appointments Table
    private static final String TABLE_APPOINTMENTS = "appointments";
    private static final String COLUMN_APPOINTMENT_ID = "appointment_id";
    private static final String COLUMN_PATIENT_NAME = "patient_name";
    private static final String COLUMN_APPOINTMENT_DATE = "appointment_date";
    private static final String COLUMN_PATIENT_DISEASE = "patient_disease";
    private static final String COLUMN_PATIENT_CARD_NUMBER = "patient_card_number";
    private static final String COLUMN_PATIENT_AGE = "patient_age";
    private static final String COLUMN_PATIENT_CONTACT = "patient_contact";

    public HealthcareDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Appointments Table
        String createAppointmentsTable = "CREATE TABLE " + TABLE_APPOINTMENTS + " (" +
                COLUMN_APPOINTMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PATIENT_NAME + " TEXT, " +
                COLUMN_APPOINTMENT_DATE + " TEXT, " +
                COLUMN_PATIENT_DISEASE + " TEXT, " +
                COLUMN_PATIENT_CARD_NUMBER + " TEXT, " +
                COLUMN_PATIENT_AGE + " TEXT, " +
                COLUMN_PATIENT_CONTACT + " TEXT)";
        db.execSQL(createAppointmentsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENTS);
        onCreate(db);
    }

    // Method to check user credentials
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE emails=? AND nPassword=?", new String[]{email, password});
        boolean userExists = cursor.getCount() > 0;
        cursor.close();
        return userExists;
    }

    // Method to add an appointment
    public boolean addAppointment(String patientName, String appointmentDate, String patientDisease, String patientCardNumber, String patientAge, String patientContact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PATIENT_NAME, patientName);
        values.put(COLUMN_APPOINTMENT_DATE, appointmentDate);
        values.put(COLUMN_PATIENT_DISEASE, patientDisease);
        values.put(COLUMN_PATIENT_CARD_NUMBER, patientCardNumber);
        values.put(COLUMN_PATIENT_AGE, patientAge);
        values.put(COLUMN_PATIENT_CONTACT, patientContact);

        long result = db.insert(TABLE_APPOINTMENTS, null, values);
        db.close();
        return result != -1; // Returns true if data is inserted successfully
    }

    // Method to get all appointments
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointmentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_APPOINTMENTS, null);
        if (cursor.moveToFirst()) {
            do {
                Appointment appointment = new Appointment(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                );
                appointmentList.add(appointment);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return appointmentList;
    }
}