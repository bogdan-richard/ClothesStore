package dd.casestudy.clothesstore.api;

import javax.persistence.Entity;

/**
 * Created by KITKAT on 2015.04.06..
 */
@Entity
public class ProductId {

    int productId;

    public ProductId(int productId) {
        super();
        this.productId = productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }
}
