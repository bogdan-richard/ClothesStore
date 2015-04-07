package dd.casestudy.clothesstore.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import dd.casestudy.clothesstore.R;
import dd.casestudy.clothesstore.api.StoreItem;
import dd.casestudy.clothesstore.support.Constants;

/**
 * Created by KITKAT on 2015.04.06..
 */
public class CartListActivity extends Activity {
    protected ListView cartList;
    private CartListAdapter mListAdapter;

    public static double totalPrice;
    static TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartlist);

        totalPriceTextView = (TextView) findViewById(R.id.total_price_textview);

        cartList = (ListView) findViewById(R.id.cartList);
        Intent mIntent = getIntent();
        ArrayList<StoreItem> ids = (ArrayList<StoreItem>) mIntent.getSerializableExtra(Constants.PRODUCTID_ARRAY_EXTRA);
        if(ids.size()>0) {
            totalPrice = 0;
            for(StoreItem item : ids) {
                totalPrice = totalPrice + item.getPrice();
            }
            totalPriceTextView.setText(getString(R.string.total_price) + totalPrice);
            mListAdapter = new CartListAdapter(CartListActivity.this, ids);
            cartList.setAdapter(mListAdapter);
        }
    }
}
