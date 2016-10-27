package com.example.user.myapplication;

import android.annotation.TargetApi;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
        int price = calculatePrice();
        EditText simpleEditText = (EditText) findViewById(R.id.name_feild);
        String name = simpleEditText.getText().toString();
        CheckBox chekBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream= chekBox.isChecked();
        Log.v("MainActivity","haswhipped" +hasWhippedCream);
        CheckBox chacoCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean chacolateCheckbox= chacoCheckbox.isChecked();
        Log.v("MainActivity","haswhipped" +chacolateCheckbox);
        String priceMessage = createOrderSummary(name,price,hasWhippedCream,chacolateCheckbox);
        displayMessage(priceMessage);
    }

    public int calculatePrice(){
        int price= quantity * 2;
        return price;
    }
    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view){
        quantity =quantity+1;
        displayQuantity(quantity);
    }
    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view){
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
    /**
     * This method displays the price for the given quantity value on the screen.
     */
    @TargetApi(Build.VERSION_CODES.N)
    private  void displayPrice(int number){
        TextView priceTextview = (TextView) findViewById(R.id.Order_summary_text_view);
        priceTextview.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private void displayMessage(String message){
        TextView messagetextView = (TextView) findViewById(R.id.Order_summary_text_view);
        messagetextView.setText(message);
    }

}
