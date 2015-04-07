package dd.casestudy.clothesstore.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dd.casestudy.clothesstore.R;
import dd.casestudy.clothesstore.api.StoreItem;

/**
 * Created by KITKAT on 2015.04.07..
 */
public class CartListAdapter extends BaseAdapter {

        private ArrayList<StoreItem> ItemsList = new ArrayList<StoreItem>();

        private final Context mContext;
        private View mView;
        public ArrayList<StoreItem> cartItems = new ArrayList<StoreItem>();

        public CartListAdapter(Context mContext, ArrayList<StoreItem> ItemsList) {
            this.mContext = mContext;
            this.ItemsList = ItemsList;
        }

        @Override
        public int getCount() {
            return ItemsList.size();
        }

        @Override
        public Object getItem(int position) {
            return ItemsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public ArrayList<StoreItem> getArrayList() {
            return ItemsList;
        }

        public void setArrayList(ArrayList<StoreItem> ItemsList) {
            this.ItemsList = ItemsList;
        }

        public ArrayList<StoreItem> getCartItems() {
            return cartItems;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = convertView;
            if (mView == null) {
                mView = inflater.inflate(R.layout.cart_list_item, null);
            }
            //Text and image updates
            TextView nameTextView = (TextView) mView.findViewById(R.id.cart_name_textview);
            TextView priceTextView = (TextView) mView.findViewById(R.id.cart_price_textview);

            try {
                nameTextView.setText(ItemsList.get(position).getName() + ItemsList.get(position).getProductId());
                priceTextView.setText(mContext.getString(R.string.price) + String.valueOf(ItemsList.get(position).getPrice()));
            }
            catch (Exception e) {
            }
            return mView;
        }
    }
