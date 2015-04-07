package dd.casestudy.clothesstore.api;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;

import dd.casestudy.clothesstore.R;
import dd.casestudy.clothesstore.main.ListAdapter;
import dd.casestudy.clothesstore.support.CallableTask;
import dd.casestudy.clothesstore.support.RestClient;
import dd.casestudy.clothesstore.support.TaskCallback;

/**
 * Created by KITKAT on 2015.04.06..
 */
public class HttpCommands {

   static ArrayList<StoreItem> mStoreItems = null;
   static StoreItem mStoreItem = null;

    public static void refreshStoreList(final Context mContext, final ListAdapter mListAdapter) {
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
                    ArrayList<StoreItem> mArrayList = new ArrayList<StoreItem>(result);
                    mListAdapter.setArrayList(mArrayList);
                    mListAdapter.notifyDataSetChanged();
                }

                @Override
                public void error(Exception e) {
                    Toast.makeText(
                            mContext,
                            mContext.getString(R.string.error_list_refresh) + e.toString(),
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public static StoreItem getStoreItem(final Context mContext, final long productId, final ListAdapter mListAdapter) {
        final ClothesStoreApi mRestClient = RestClient.get();
        mStoreItem = null;
        if (mRestClient != null) {
            CallableTask.invoke(new Callable<StoreItem>() {

                @Override
                public StoreItem call() throws Exception {
                    return mRestClient.getProductDetails(productId);
                }
            }, new TaskCallback<StoreItem>() {

                @Override
                public void success(StoreItem result) {
                    ArrayList<StoreItem> mArrayList = mListAdapter.getArrayList();
                    mArrayList.add(result);
                    mListAdapter.setArrayList(mArrayList);
                    mListAdapter.notifyDataSetChanged();

                    //CartListActivity.updateTotalPrice(mArrayList,mContext);
                }

                @Override
                public void error(Exception e) {
                    Toast.makeText(
                            mContext,
                            mContext.getString(R.string.error_list_refresh) + e.toString(),
                            Toast.LENGTH_LONG).show();
                }
            });
        }
        return mStoreItem;
    }
}
