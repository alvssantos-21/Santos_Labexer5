package com.example.santos_labexer4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] names, countries ,industries, ceos, des;
    ListView lstcompany;

    int[] logo = {R.drawable.icbc, R.drawable.jpm, R.drawable.ccb, R.drawable.abc, R.drawable.boa, R.drawable.apple, R.drawable.ping, R.drawable.boc, R.drawable.shell, R.drawable.well, R.drawable.ex, R.drawable.at, R.drawable.sam, R.drawable.citi};

    ArrayList<CompanyDetails> details = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("TOP GLOBAL COMPANIES");

        names = getResources().getStringArray(R.array.company);
        countries = getResources().getStringArray(R.array.country);
        industries = getResources().getStringArray(R.array.industry);
        ceos = getResources().getStringArray(R.array.CEO);
        des = getResources().getStringArray(R.array.desc);

        for(int i = 0; i < names.length; i++){
            details.add(new CompanyDetails(names[i], countries[i], industries[i], ceos[i], logo[i], des[i]));
        }
        CompanyAdaptor adapter = new CompanyAdaptor(this, R.layout.item, details);
        lstcompany = findViewById(R.id.lvCompany);
        lstcompany.setAdapter(adapter);
        lstcompany.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        final File folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, "versions.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            String choice = details.get(i).getName() + " " + details.get(i).getCountry() + " " + details.get(i).getIndustry() + " " + details.get(i).getCeo();

            fos.write(choice.getBytes());

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(details.get(i).getName());
            dialog.setIcon(details.get(i).getLogo());
            dialog.setMessage(details.get(i).getDes());

            dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    try {
                        FileInputStream fis = new FileInputStream( new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)+"/versions.txt"));
                        int i;
                        String str = "";
                        while ((i = fis.read()) != -1){
                            str += Character.toString((char) i);
                        }
                        fis.close();
                        Toast.makeText(MainActivity.this, str , Toast.LENGTH_LONG).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });


            //AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            //dialog.setIcon(logo[i]);
            //dialog.setTitle(names[i]);
            //dialog.setMessage(des[i]);
            //dialog.setNeutralButton("Close", new DialogInterface.OnClickListener() {
            //@Override
            //public void onClick(DialogInterface dialog, int which) {
            //dialog.dismiss();
            //Toast.makeText(MainActivity.this, names[i], Toast.LENGTH_LONG).show();

            dialog.create().show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
