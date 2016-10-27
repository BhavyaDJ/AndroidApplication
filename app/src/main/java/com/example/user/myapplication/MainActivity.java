package com.example.user.myapplication;

import android.annotation.TargetApi;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quantity= 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText simpleEditText = (EditText) findViewById(R.id.name_feild);
        String name = simpleEditText.getText().toString();

        CheckBox chekBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream= chekBox.isChecked();
        Log.v("MainActivity","haswhipped" +hasWhippedCream);

        CheckBox chacoCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChacolate= chacoCheckbox.isChecked();
        Log.v("MainActivity","haswhipped" +hasChacolate);

        int price = calculatePrice(hasWhippedCream,hasChacolate);
        String priceMessage = createOrderSummary(name,price,hasWhippedCream,hasChacolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order for " +name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
//        displayMessage(priceMessage);
        }


    public int calculatePrice(boolean addWhippedCream,boolean addChocolate){
        int baseprice = 5;
        if (addWhippedCream){
            baseprice = baseprice +1;
        }
        if (addChocolate){
            baseprice = baseprice +2;
        }
        return quantity * baseprice;
    }
    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view){
        if (quantity == 100){
            Toast.makeText(this,"You cannot have more than 100 Coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity =quantity+1;
        displayQuantity(quantity);
    }
    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view){
        if (quantity == 1){
            Toast.makeText(this,"Minimum order of Coffees is 1",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity =quantity-1;
        displayQuantity(quantity);
    }
    private String createOrderSummary(String name,int price,boolean addWhippedCream,boolean addChocolate){
        String priceMessage = "Name : " +name ;
        priceMessage = priceMessage +"\nQuantity :" +quantity;
        priceMessage = priceMessage +"\nAdding Whipped cream : " +addWhippedCream;
        priceMessage = priceMessage +"\nAdding Chocolate : " +addChocolate;
        priceMessage = priceMessage + "\nTotal : $" +price;
        priceMessage = priceMessage + "\nThank you!";
        return priceMessage;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.textView2);
        quantityTextView.setText("" + quantity);
    }

//    private void displayMessage(String message){
//        TextView messagetextView = (TextView) findViewById(R.id.Order_summary_text_view);
//        messagetextView.setText(message);
//    }

}
