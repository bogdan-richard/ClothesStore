package dd.casestudy.clothesstore.api;

import java.io.Serializable;

import javax.persistence.Entity;
/**
 * Created by KITKAT on 2015.04.06..
 */
@Entity
public class StoreItem implements Serializable {

    long productId;
    String name;
    String category;
    double price;
    Double oldPrice;
    long stock;


    public StoreItem(long productId,
                     String name,
                     String category,
                     double price,
                     Double oldPrice,
                     long stock) {
        super();
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.oldPrice = oldPrice;
        this.stock = stock;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }
}
