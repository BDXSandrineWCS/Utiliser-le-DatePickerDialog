package fr.wcs.blablawild;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ItineraryListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_list);

        // Get the Intent that started this activity (from ItinerarySearchActivity) and extract the extra (departure destination and date)
        Intent intent = getIntent();
        String departure = intent.getStringExtra("EXTRA_DEPARTURE");
        String destination = intent.getStringExtra("EXTRA_DESTINATION");
        String date = intent.getStringExtra("EXTRA_DATE");
        String title="";
        if ((departure != null) && (destination!= null)) title = (departure + " >> " + destination);
        if (date!= null) title += " at the " + date;
        this.setTitle(title);
    }
}
