package dd.casestudy.clothesstore.api;

import java.util.Collection;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * This interface defines an API for a Clothes Store API
 */
public interface ClothesStoreApi {

    public static final String GET_PRODUCTS = "/products";

    public static final String PRODUCT_ID = "/{productId}";

    public static final String PRODUCT_ID_PARAMETER = "productId";

    public static final String CART = "/cart";

    public static final String REMOVE_FROM_CART = "/{cartId}";

    public static final String CART_ID_PARAMETER = "cartId";

    @GET(GET_PRODUCTS)
    public Collection<StoreItem> getProducts();

    @GET(GET_PRODUCTS + PRODUCT_ID)
    public StoreItem getProductDetails(@Path(PRODUCT_ID_PARAMETER)long productId);

    @POST(CART)
    public CartItem addToCart(@Body ProductId productId);

    @DELETE(CART + REMOVE_FROM_CART)
    public Void removeFromCart(@Path(CART_ID_PARAMETER) long CartId);
}