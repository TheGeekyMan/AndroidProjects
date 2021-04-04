package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        /*
        * method call for  whipped cream
        * */
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        /*
         * method call for  chocolate
         * */
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();
        /*
        * method call for edit text
        * nameEditTextView is editable object
        * */
        EditText nameEditTextView = (EditText) findViewById(R.id.name_editText_view);
        String name = nameEditTextView.getText().toString();
        /**
        * passing tow parameters to calculate final price
         * parameters which are passed are boolean
        * */
        int price = calculatePrice(hasWhippedCream,hasChocolate);

        String priceMessage=createOrderSummary(price,name,hasWhippedCream,hasChocolate);
        /**
         * there is another way to print thank you on next line
         * priceMessage = priceMessage + "\nThank you!";    //it will add "thank you" to the priceMessage
         * this is called as updating string variable
         * */

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
/**
 * this method is commented so this will not run
 *
 * displayMessage(priceMessage);
 */


    }

    /**
     * Calculates the price of the order.
     * @param  addChocolate is check whether user wants chocolate or not
     * @param  addWhippedCream is check whether user wants whipped cream or not
     * @return total price
     */
    private int calculatePrice(boolean addWhippedCream,boolean addChocolate) {
        int basePrice =  5;
        if(addWhippedCream){
            basePrice = basePrice + 1;
        }
        if(addChocolate){
            basePrice = basePrice + 2;
        }
        return basePrice * quantity;
    }
    /**
     * @param price of the order
     * @param editName
     * @param addWhippedCream is whether the order is with or without cream
     * @param addChocolate is checks the order is with or with out chocolate
     * @return text summary
     * */
    private String createOrderSummary(int price, String editName, boolean addWhippedCream, boolean addChocolate){
        String priceMessage = "Name : " + editName;
        priceMessage = priceMessage + "\nAdd whipped cream? " + addWhippedCream;
        priceMessage = priceMessage + "\nAdd chocolate? " + addChocolate;
        priceMessage = priceMessage + "\nQuantity : " + quantity;
        priceMessage = priceMessage + "\nTotal $ : " + price + "\nThank you!";
        return priceMessage;
    }

/**
* this method is called when the increment button is clicked
 */
    public void increment(View view){
        if(quantity >= 5){
            //Toast will show error message
            Toast.makeText(this,"Currently we are not accepting more than 5 orders at a time",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }
    /**
     * this method is called when the decrement button is clicked
     */
    public void decrement(View view){
        if(quantity <= 1){
            //Toast will show error message
            Toast.makeText(this,"You cannot have less than 1 order",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen
     * there is no use of this method you can also delete this method
     * 
    private void displayPrice(int number) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
     */
    /**
     * This method displays the given text on the screen.
     * but for this app we are gonna sent this info to the mail directly ,(no need to display on screen as user wants i.e. this is user requirement)
     *
     *
     * private void displayMessage(String message) {
     *         TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
     *         priceTextView.setText(message);
     *     }
     */


}


