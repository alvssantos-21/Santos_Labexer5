package com.example.santos_labexer4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CompanyAdaptor extends ArrayAdapter<CompanyDetails> {
    private Context context;
    private int resource;

    public CompanyAdaptor(@NonNull Context context, int resource, @NonNull List<CompanyDetails> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;

    }
    @NonNull
    @Override
    public View getView(int i, @Nullable View convertView, @NonNull ViewGroup parent){
        int logo = getItem(i).getLogo();
        String name = getItem(i).getName();
        String ceo = getItem(i).getCeo();
        String industry = getItem(i).getIndustry();
        String country = getItem(i).getCountry();
        String descrip = getItem(i).getDes();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        ImageView img = convertView.findViewById(R.id.ivLogo);
        TextView cname = convertView.findViewById(R.id.tvName);
        TextView ccountry = convertView.findViewById(R.id.tvCountry);
        TextView cind = convertView.findViewById(R.id.tvInd);
        TextView cceo = convertView.findViewById(R.id.tvCeo);

        img.setImageResource(logo);
        cname.setText(name);
        cceo.setText(ceo);
        cind.setText(industry);
        ccountry.setText(country);
        return convertView;
    }
}
