package fr.wcs.blablawild;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ItinerarySearchActivity extends AppCompatActivity {

    public static final String EXTRA_DEPARTURE = "fr.wcs.blablawild.DEPARTURE";
    public static final String EXTRA_DESTINATION = "fr.wcs.blablawild.DESTINATION";
    public static final String EXTRA_DATE = "fr.wcs.blablawild.DATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_search);
    }

    public void sendDataToItineraryList(View view) {
         /**
         * when click on search button in the activityIterarySearchLayout verify the edited text in the same layout and
          * if departure or destination are empty create an error toast else sent to ItineraryListActivity with the text of the edits text of the layout as extra
          * @parameter view which call the method (buttonSearch)
          * @return void
          */
            EditText editTextDeparture = (EditText) findViewById(R.id.editTextDeparture);
            EditText editTextDestination = (EditText) findViewById(R.id.editTextDestination);
            EditText editTextDate = (EditText) findViewById(R.id.editTextDate);
            String departure = editTextDeparture.getText().toString();
            String destination = editTextDestination.getText().toString();
            String date = editTextDate.getText().toString();
            if (departure == null || departure.isEmpty() || destination == null || destination.isEmpty() ){
                Context context = getApplicationContext();
                String text = getString(R.string.no_departure_or_destination_text);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }else {
                Intent intent = new Intent(this, ItineraryListActivity.class);
                intent.putExtra(EXTRA_DEPARTURE, departure);
                intent.putExtra(EXTRA_DESTINATION, destination);
                intent.putExtra(EXTRA_DATE, date);
                startActivity(intent);
            }
    }

}
