package pl.marko.zegarki.entity.marko;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name="MarkoProduct")
@Table(name = "marko_product")
public class MarkoProduct {

    @Id
    @Column(name="kod", unique=true)
    private String productKod;

    @ManyToOne
    @JoinColumn(name = "productBrand")
    private MarkoBrand productBrand;

    @Column(name="product_link")
    private String productLink;

    @Column(name="new_price")
    private BigDecimal newPrice;

    @Column(name="old_price")
    private BigDecimal oldPrice;

    @Column(name="percent_sale",columnDefinition="Decimal(6,2) default '0.00'")
    private BigDecimal percentSale;

    @Column(name="shiping")
    private String shiping;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;

    public MarkoProduct(String productKod, MarkoBrand productBrand, String productLink, BigDecimal newPrice, BigDecimal oldPrice, String shiping, BigDecimal percentSale) {
        this.productKod = productKod;
        this.productBrand = productBrand;
        this.productLink = productLink;
        this.newPrice = newPrice;
        this.oldPrice = oldPrice;
        this.shiping = shiping;
        this.percentSale = percentSale;
    }

    public MarkoProduct(String productKod, MarkoBrand productBrand, String productLink, BigDecimal newPrice, String shiping) {
        this.productKod = productKod;
        this.productBrand = productBrand;
        this.productLink = productLink;
        this.newPrice = newPrice;
        this.shiping = shiping;
    }

    public MarkoProduct() {
    }

    public String getShiping() {
        return shiping;
    }

    public void setShiping(String shiping) {
        this.shiping = shiping;
    }

    public String getProductKod() {
        return productKod;
    }

    public void setProductKod(String productKod) {
        this.productKod = productKod;
    }

    public MarkoBrand getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(MarkoBrand productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public BigDecimal getPercentSale() {
        return percentSale;
    }

    public void setPercentSale(BigDecimal percentSale) {
        this.percentSale = percentSale;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MarkoProduct{");
        sb.append("productKod='").append(productKod).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
