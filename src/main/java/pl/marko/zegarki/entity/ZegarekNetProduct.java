package pl.marko.zegarki.entity;

import javax.persistence.*;

@Entity(name="ZegarekNetProduct")
@Table(name = "zegarek_net_product")
public class ZegarekNetProduct {

    @Id
    @Column(name="kod", unique=true)
    private String productKod;

    @ManyToOne
    @JoinColumn(name = "productBrand_brand")
    private ZegarekNetBrand productBrand;

    @Column(name="product_link")
    private String productLink;

    public ZegarekNetProduct(String productKod, String productLink, ZegarekNetBrand productBrand) {
        this.productKod = productKod;
        this.productBrand = productBrand;
        this.productLink = productLink;
    }

    public ZegarekNetProduct() {
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
