package dd.casestudy.clothesstore.api;

import javax.persistence.Entity;

/**
 * Created by KITKAT on 2015.04.06..
 */
@Entity
public class CartItem {

    long cartId;
    long productId;

    public CartItem(long cartId, long productId){
        super();
        this.cartId = cartId;
        this.productId = productId;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
