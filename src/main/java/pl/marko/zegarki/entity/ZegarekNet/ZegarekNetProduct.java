package pl.marko.zegarki.entity.ZegarekNet;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name="ZegarekNetProduct")
@Table(name = "zegarek_net_product")
public class ZegarekNetProduct implements java.io.Serializable {

    @Id
    @Column(name="kod", unique=true)
    private String productKod;

    @ManyToOne
    @JoinColumn(name = "productBrand")
    private ZegarekNetBrand productBrand;

    @Column(name="product_link")
    private String productLink;

    @Column(name="zegarek_net_new_price")
    private BigDecimal zegarek_net_new_price;

    @Column(name="old_price")
    private BigDecimal oldPrice;

    @Column(name="percent_sale",columnDefinition="Decimal(6,2) default '0.00'")
    private BigDecimal percentSale;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;

    public ZegarekNetProduct(String productKod, ZegarekNetBrand productBrand, String productLink, BigDecimal zegarek_net_new_price, BigDecimal oldPrice, BigDecimal percentSale) {
        this.productKod = productKod;
        this.productBrand = productBrand;
        this.productLink = productLink;
        this.zegarek_net_new_price = zegarek_net_new_price;
        this.oldPrice = oldPrice;
        this.percentSale = percentSale;
    }

    public ZegarekNetProduct(String productKod, ZegarekNetBrand productBrand, String productLink, BigDecimal zegarek_net_new_price) {
        this.productKod = productKod;
        this.productBrand = productBrand;
        this.productLink = productLink;
        this.zegarek_net_new_price = zegarek_net_new_price;
    }

    public ZegarekNetProduct() {
    }

    public BigDecimal getPercentSale() {
        return percentSale;
    }

    public void setPercentSale(BigDecimal percentSale) {
        this.percentSale = percentSale;
    }

    public BigDecimal getZegarek_net_new_price() {
        return zegarek_net_new_price;
    }

    public void setZegarek_net_new_price(BigDecimal zegarek_net_new_price) {
        this.zegarek_net_new_price = zegarek_net_new_price;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getProductKod() {
        return productKod;
    }

    public void setProductKod(String productKod) {
        this.productKod = productKod;
    }

    public ZegarekNetBrand getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(ZegarekNetBrand productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ZegarekNetProduct{");
        sb.append("productKod='").append(productKod).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
