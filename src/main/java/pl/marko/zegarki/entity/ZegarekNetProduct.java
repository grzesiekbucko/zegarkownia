package pl.marko.zegarki.entity;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity(name="ZegarekNetProduct")
@Table(name = "zegarek_net_product")
public class ZegarekNetProduct {

    @Id
    @Column(name="kod", unique=true)
    private String productKod;

    @ManyToOne
    @JoinColumn(name = "productBrand")
    private ZegarekNetBrand productBrand;

    @Column(name="product_link")
    private String productLink;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;

    public ZegarekNetProduct(String productKod, String productLink, ZegarekNetBrand productBrand) {
        this.productKod = productKod;
        this.productBrand = productBrand;
        this.productLink = productLink;
    }

    public ZegarekNetProduct() {
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
}
