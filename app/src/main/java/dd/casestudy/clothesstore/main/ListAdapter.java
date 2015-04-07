package dd.casestudy.clothesstore.main;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import dd.casestudy.clothesstore.R;
import dd.casestudy.clothesstore.api.StoreItem;

public class ListAdapter extends BaseAdapter {

    private ArrayList<StoreItem> ItemsList = new ArrayList<StoreItem>();

    private final Context mContext;
    private View mView;
    public ArrayList<StoreItem> cartItems = new ArrayList<StoreItem>();

    public ListAdapter(Context mContext, ArrayList<StoreItem> ItemsList) {
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

    public void addCartItem(StoreItem item) {
        cartItems.add(item);
    }

    public void removeCartItem(StoreItem item) {
        cartItems.remove(item);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = convertView;
        if (mView == null) {
            mView = inflater.inflate(R.layout.list_item_layout, null);
        }
        //Text and image updates
        TextView nameTextView = (TextView) mView.findViewById(R.id.name_textview);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.category_textview);
        TextView stockTextView = (TextView) mView.findViewById(R.id.stock_textview);
        TextView oldPriceTextView = (TextView) mView.findViewById(R.id.old_price_textview);
        TextView priceTextView = (TextView) mView.findViewById(R.id.price_textview);
        ImageView mImageView = (ImageView) mView.findViewById(R.id.image_imageview);
        final ToggleButton wishListToggleButton = (ToggleButton) mView.findViewById(R.id.wishlist_button);
        final ToggleButton cartToggleButton = (ToggleButton) mView.findViewById(R.id.cart_button);

        try {
            nameTextView.setText(ItemsList.get(position).getName() + ItemsList.get(position).getProductId());
            categoryTextView.setText(ItemsList.get(position).getCategory());
            stockTextView.setText(mContext.getString(R.string.stock) + String.valueOf(ItemsList.get(position).getStock()));
            if (ItemsList.get(position).getOldPrice() != null) {
                oldPriceTextView.setText(mContext.getString(R.string.old_price) + String.valueOf(ItemsList.get(position).getOldPrice()));
                oldPriceTextView.setPaintFlags(oldPriceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            priceTextView.setText(mContext.getString(R.string.price) + String.valueOf(ItemsList.get(position).getPrice()));
            mImageView.setImageResource(android.R.drawable.ic_menu_gallery);
            wishListToggleButton.setVisibility(View.VISIBLE);
            wishListToggleButton.setChecked(false);
            cartToggleButton.setVisibility(View.VISIBLE);
            if (ItemsList.get(position).getStock() == 0) {
                cartToggleButton.setEnabled(false);
            }
            cartToggleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(cartToggleButton.isChecked()) {
                        //Log.i("test","add");
                        addCartItem(ItemsList.get(position));
                        Toast.makeText(
                                mContext,
                                mContext.getString(R.string.add_to_cart) + ItemsList.get(position).getName(),
                                Toast.LENGTH_LONG).show();
                    }
                    else {
                        removeCartItem(ItemsList.get(position));
                        Toast.makeText(
                                mContext,
                                mContext.getString(R.string.remove_from_cart) + ItemsList.get(position).getName(),
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        catch (Exception e) {

        }
        return mView;
    }
}
