package pl.marko.zegarki.entity.ZegarekNet;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name="ZegarekNetBrand")
@Table(name = "zegarek_net_brand")
public class ZegarekNetBrand {

    @Id
    @Column(name="brand", unique=true)
    private String brand;

    @Column(name="link")
    private String link;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_Date")
    private Date updateDate;

    @OneToMany(mappedBy = "productBrand", fetch = FetchType.LAZY)
    private List<ZegarekNetProduct> products;

    public ZegarekNetBrand(String brand, String link) {
        this.brand = brand;
        this.link = link;
    }

    public ZegarekNetBrand() {
    }

    public List<ZegarekNetProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ZegarekNetProduct> products) {
        this.products = products;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


}
