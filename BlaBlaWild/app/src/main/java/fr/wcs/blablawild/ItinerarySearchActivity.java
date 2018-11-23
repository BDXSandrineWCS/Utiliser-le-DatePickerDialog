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

    private EditText editTextDeparture;
    private EditText editTextDestination;
    private EditText editTextDate;
    private Calendar dateCalendar;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_search);
        editTextDeparture = (EditText) findViewById(R.id.editTextDeparture);
        editTextDestination = (EditText) findViewById(R.id.editTextDestination);
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        dateCalendar = Calendar.getInstance();
        initialiseListeners();
    }


    //getters and setters


    public EditText getEditTextDeparture(){
        return editTextDeparture;
    }

    public void setEditTextDeparture(EditText departure){
        editTextDeparture=departure;
    }

    public EditText getEditTextDestination(){
        return editTextDestination;
    }

    public void setEditTextDestination(EditText destination){
        editTextDestination=destination;
    }

    public EditText getEditTextDate(){
        return editTextDate;
    }

    public void setEditTextDate(EditText date){
        editTextDate=date;
    }

    public Calendar getDateCalendar(){
        return dateCalendar;
    }

    public void setDateCalendar(Calendar calendar){
        dateCalendar=calendar;
    }

    public DatePickerDialog.OnDateSetListener getDateSetListener(){
        return dateSetListener;
    }

    public void setDateSetListener(DatePickerDialog.OnDateSetListener listener){
        dateSetListener=listener;
    }


    //methodes


    protected void initialiseListeners(){

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), dateSetListener, dateCalendar
                        .get(Calendar.YEAR), dateCalendar.get(Calendar.MONTH),
                        dateCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                dateCalendar.set(Calendar.YEAR, year);
                dateCalendar.set(Calendar.MONTH, monthOfYear);
                dateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

    }


    private void updateLabel() {
        String expressionFormat = "yyyy/MM/dd"; //In which you need put here
        SimpleDateFormat dateFormat = new SimpleDateFormat(expressionFormat, Locale.US);
        editTextDate.setText(dateFormat.format(dateCalendar.getTime()));
    }


    public void sendDataToItineraryList(View view) {
         /**
         * when click on search button in the activityIterarySearchLayout verify the edited text in the same layout and
          * if departure or destination are empty create an error toast else sent to ItineraryListActivity with the text of the edits text of the layout as extra
          * @parameter view which call the method (buttonSearch)
          * @return void
          */

            String departure = editTextDeparture.getText().toString();
            String destination = editTextDestination.getText().toString();
            String date = editTextDate.getText().toString();
            if (departure == null || departure.isEmpty() || destination == null || destination.isEmpty() ){
                Context context = getApplicationContext();
                String text = getString(R.string.no_departure_or_destination_text);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                editTextDeparture.setText(departure);
                editTextDestination.setText(destination);
            }else {
                Intent intent = new Intent(this, ItineraryListActivity.class);
                intent.putExtra("EXTRA_DEPARTURE", departure);
                intent.putExtra("EXTRA_DESTINATION", destination);
                intent.putExtra("EXTRA_DATE", date);
                startActivity(intent);
            }
    }

}
