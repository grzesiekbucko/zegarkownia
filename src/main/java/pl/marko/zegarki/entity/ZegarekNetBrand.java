package pl.marko.zegarki.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity(name="ZegarekNetBrand")
@Table(name = "zegarek_net_brand")
public class ZegarekNetBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="brand")
    private String brand;

    @Column(name="link")
    private String link;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    public ZegarekNetBrand(Long id, String brand, String link, Date created) {
        this.id = id;
        this.brand = brand;
        this.link = link;
        this.createDate = created;
    }

    public ZegarekNetBrand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreated() {
        return createDate;
    }

    public void setCreated(Date created) {
        this.createDate = created;
    }
}
