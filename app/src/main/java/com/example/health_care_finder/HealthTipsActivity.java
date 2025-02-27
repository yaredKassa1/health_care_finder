package com.example.health_care_finder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HealthTipsActivity extends AppCompatActivity {
    private ListView tipsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);

        tipsListView = findViewById(R.id.tipsListView);

        // Load health tips and emergency numbers
        String[] tips = {
                "Emergency Numbers:\nRed Cross: 911",
                "Ambulance: 999",
                "Health Tips:",
                "1. Stay hydrated!",
                "2. Regular exercise is important.",
                "3. Eat a balanced diet rich in fruits and vegetables.",
                "4. Get enough sleep (7-9 hours per night).",
                "5. Manage stress through mindfulness or relaxation techniques.",
                "6. Avoid smoking and limit alcohol consumption.",
                "7. Schedule regular health check-ups with your doctor."
        };

        // Set the adapter for the ListView
        HealthTipsAdapter adapter = new HealthTipsAdapter(this, tips);
        tipsListView.setAdapter(adapter);
    }

    private class HealthTipsAdapter extends ArrayAdapter<String> {
        private Context context;
        private String[] tips;

        public HealthTipsAdapter(Context context, String[] tips) {
            super(context, android.R.layout.simple_list_item_1, tips);
            this.context = context;
            this.tips = tips;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            }

            TextView textView = convertView.findViewById(android.R.id.text1);
            String tip = tips[position];

            // Make emergency numbers clickable
            if (tip.contains("Red Cross: 911")) {
                makeClickable(textView, tip, "911");
            } else if (tip.contains("Ambulance: 999")) {
                makeClickable(textView, tip, "999");
            } else {
                textView.setText(tip);
            }

            return convertView;
        }

        private void makeClickable(TextView textView, String text, final String number) {
            SpannableString spannableString = new SpannableString(text);
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    dialNumber(number);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(true); // Underline for clickable effect
                }
            };

            int start = text.indexOf(number);
            int end = start + number.length();
            spannableString.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(spannableString);
            textView.setMovementMethod(LinkMovementMethod.getInstance()); // Enable link clicking
        }

        private void dialNumber(String number) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + number));
            context.startActivity(intent);
        }
    }
}