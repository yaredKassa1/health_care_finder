package com.example.health_care_finder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class ProviderAdapter extends ArrayAdapter<HealthCareProvider> {
    public ProviderAdapter(Context context, List<HealthCareProvider> providers) {
        super(context, 0, providers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HealthCareProvider provider = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_provider, parent, false);
        }
        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView addressTextView = convertView.findViewById(R.id.addressTextView);

        nameTextView.setText(provider.getName());
        addressTextView.setText(provider.getAddress());

        return convertView;
    }
}
