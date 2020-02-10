package pl.marko.zegarki.entity;

import pl.marko.zegarki.entity.marko.MarkoBrand;

import javax.persistence.Entity;
import java.math.BigDecimal;

public class ProductJoin implements java.io.Serializable {

    private String productKod;
    private BigDecimal newPrice;
    private BigDecimal newPrice2;

    public ProductJoin(String productKod, BigDecimal newPrice, BigDecimal newPrice2) {
        this.productKod = productKod;
        this.newPrice = newPrice;
        this.newPrice2 = newPrice2;
    }

    public ProductJoin() {
    }

    public String getProductKod() {
        return productKod;
    }

    public void setProductKod(String productKod) {
        this.productKod = productKod;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    public BigDecimal getNewPrice2() {
        return newPrice2;
    }

    public void setNewPrice2(BigDecimal newPrice2) {
        this.newPrice2 = newPrice2;
    }

}
