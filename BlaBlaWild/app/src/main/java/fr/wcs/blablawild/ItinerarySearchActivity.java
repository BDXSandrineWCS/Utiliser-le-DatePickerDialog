package fr.wcs.blablawild;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ItinerarySearchActivity extends AppCompatActivity {



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
                intent.putExtra("EXTRA_DEPARTURE", departure);
                intent.putExtra("EXTRA_DESTINATION", destination);
                intent.putExtra("EXTRA_DATE", date);
                startActivity(intent);
            }
    }




    Calendar myCalendar = Calendar.getInstance();

    EditText edittext= (EditText) findViewById(R.id.editTextDate);
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

edittext.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            new DatePickerDialog(this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    });


    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }

}
