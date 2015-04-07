package dd.casestudy.clothesstore.main;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;

import dd.casestudy.clothesstore.R;
import dd.casestudy.clothesstore.api.StoreItem;

public class StoreListActivityTest extends ActivityInstrumentationTestCase2<StoreListActivity> {

    StoreListActivity mStoreLIstActivity;
    StoreItem testStoreItem;
    Solo solo;

    public StoreListActivityTest() {
        super(StoreListActivity.class);
        //TestCase();
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();

        RobotiumTest();
    }

    public void RobotiumTest() {
        // test correct activities are running
        solo = new Solo(getInstrumentation(), getActivity());
        solo.assertCurrentActivity("wrong activity", StoreListActivity.class);
        solo.sleep(5000);
        android.widget.ListView store_listView = (android.widget.ListView)
                solo.getView(android.widget.ListView.class, 0);
        StoreItem store_item0 = (StoreItem) store_listView.getAdapter().getItem(0);
        double totalPrice = store_item0.getPrice();

        //Single item to cart
        solo.clickOnToggleButton("Cart");
        solo.clickOnActionBarItem(R.id.cart_menu);
        solo.waitForActivity(CartListActivity.class);
        solo.assertCurrentActivity("wrong activity", CartListActivity.class);
        solo.sleep(2000);

        //Check if the correct element is added
        android.widget.ListView cart_listView = (android.widget.ListView)
                solo.getView(android.widget.ListView.class, 0);
        StoreItem cart_item0 = (StoreItem) store_listView.getAdapter().getItem(0);
        assertEquals(store_item0.getName(), cart_item0.getName());

        //Check if total price is right
        TextView totalPriceTextView = (TextView) solo.getView(R.id.total_price_textview);
        assertEquals("Total price:" + totalPrice, totalPriceTextView.getText().toString());
        solo.sleep(1000);
        solo.goBack();
        solo.sleep(1000);

        //Remove item from cart
        solo.clickOnToggleButton("Cart");
        solo.sleep(1000);
        solo.clickOnActionBarItem(R.id.cart_menu);
        solo.waitForActivity(CartListActivity.class);
        solo.assertCurrentActivity("wrong activity", CartListActivity.class);
        solo.sleep(1000);
        solo.goBack();
        solo.sleep(1000);

        //Multiple items to cart
        store_item0 = (StoreItem) store_listView.getAdapter().getItem(0);
        StoreItem store_item1 = (StoreItem) store_listView.getAdapter().getItem(1);
        StoreItem store_item2 = (StoreItem) store_listView.getAdapter().getItem(2);
        totalPrice = store_item0.getPrice() + store_item1.getPrice() + store_item2.getPrice();
        View list_child0 = store_listView.getChildAt(0);
        ToggleButton toggle_button0 = (ToggleButton)list_child0.findViewById(R.id.cart_button);
        View list_child1 = store_listView.getChildAt(1);
        ToggleButton toggle_button1 = (ToggleButton)list_child1.findViewById(R.id.cart_button);
        View list_child2 = store_listView.getChildAt(2);
        ToggleButton toggle_button2 = (ToggleButton)list_child2.findViewById(R.id.cart_button);
        solo.clickOnView(toggle_button0);
        solo.clickOnView(toggle_button1);
        solo.clickOnView(toggle_button2);
        solo.sleep(2000);
        solo.clickOnActionBarItem(R.id.cart_menu);
        totalPriceTextView = (TextView) solo.getView(R.id.total_price_textview);
        assertEquals("Total price:" + totalPrice, totalPriceTextView.getText().toString());
        cart_listView = (android.widget.ListView)
                solo.getView(android.widget.ListView.class, 0);
        cart_item0 = (StoreItem) store_listView.getAdapter().getItem(0);
        StoreItem cart_item1 = (StoreItem) store_listView.getAdapter().getItem(1);
        StoreItem cart_item2 = (StoreItem) store_listView.getAdapter().getItem(2);
        assertEquals(store_item0.getName(),cart_item0.getName());
        assertEquals(store_item1.getName(), cart_item1.getName());
        assertEquals(store_item2.getName(), cart_item2.getName());
        solo.sleep(2000);
        solo.goBack();
        solo.sleep(1000);
    }
    //Test StoreItem getters and Setters
    @SmallTest
    public void testStoreItem() {
        testStoreItem = new StoreItem(1, "item", "category", 25.0,15.5,5);
        assertEquals(1, testStoreItem.getProductId());
        assertEquals("item", testStoreItem.getName());
        assertEquals("category", testStoreItem.getCategory());
        assertEquals(25.0 , testStoreItem.getPrice());
        assertEquals(15.5,testStoreItem.getOldPrice());
        assertEquals(5, testStoreItem.getStock());
    }


    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}