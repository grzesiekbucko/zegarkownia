package pl.marko.zegarki.entity.marko;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "MarkoBrand")
@Table(name = "marko_brand")
public class MarkoBrand {

    @Id
    @Column(name = "brand", unique = true)
    private String brand;

    @Column(name = "link")
    private String link;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_Date")
    private Date updateDate;

    @OneToMany(mappedBy = "productBrand", fetch = FetchType.LAZY)
    private List<MarkoProduct> products;

    public MarkoBrand(String brand, String link) {
        this.brand = brand;
        this.link = link;
    }

    public MarkoBrand() {
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<MarkoProduct> getProducts() {
        return products;
    }

    public void setProducts(List<MarkoProduct> products) {
        this.products = products;
    }


}
