package dd.casestudy.clothesstore.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;

import dd.casestudy.clothesstore.R;
import dd.casestudy.clothesstore.api.ClothesStoreApi;
import dd.casestudy.clothesstore.api.StoreItem;
import dd.casestudy.clothesstore.support.CallableTask;
import dd.casestudy.clothesstore.support.Constants;
import dd.casestudy.clothesstore.support.RestClient;
import dd.casestudy.clothesstore.support.TaskCallback;


public class StoreListActivity extends Activity {

    protected ListView storeList;
    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storelist);

        storeList = (ListView) findViewById(R.id.storeList);
        refreshStoreList();
    }

    private void refreshStoreList() {
        final ClothesStoreApi mRestClient = RestClient.get();
        if (mRestClient != null) {
            CallableTask.invoke(new Callable<Collection<StoreItem>>() {

                @Override
                public Collection<StoreItem> call() throws Exception {
                    return mRestClient.getProducts();
                }
            }, new TaskCallback<Collection<StoreItem>>() {

                @Override
                public void success(Collection<StoreItem> result) {
                    ArrayList<StoreItem> mStoreItems = new ArrayList<StoreItem>(result);
                    mListAdapter = new ListAdapter(StoreListActivity.this, mStoreItems);
                    storeList.setAdapter(mListAdapter);
                }

                @Override
                public void error(Exception e) {
                    Toast.makeText(
                            StoreListActivity.this,
                            getString(R.string.error_list_refresh) + e.toString(),
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_storelist_activity, menu);
        MenuItem cartdActionMenuItem = menu.findItem(R.id.cart_menu);
        MenuItem wishlitActionMenuItem = menu.findItem(R.id.wishlist_menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
        case R.id.action_settings:
                return true;

            case R.id.cart_menu:
                ArrayList<StoreItem> cartItems = mListAdapter.getCartItems();
                Intent mIntent = new Intent(this, CartListActivity.class);
                mIntent.putExtra(Constants.PRODUCTID_ARRAY_EXTRA, (Serializable) cartItems);
                startActivity(mIntent);
                return true;

            case R.id.wishlist_menu:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
