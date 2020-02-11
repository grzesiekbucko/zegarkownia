package pl.marko.zegarki.entity;

public class ProductJoin implements java.io.Serializable {

    private String productBrand;
    private String productKod;
    private String newPrice;
    private String percentSale;
    private String shiping;
    private String zegarekNetNewPrice;
    private String zegarekNetPercentSale;
    private String markoLink;
    private String zegarekNetLink;

    public ProductJoin(String productBrand, String productKod, String newPrice, String percentSale, String shiping, String zegarekNetNewPrice, String zegarekNetPercentSale, String markoLink, String zegarekNetLink) {
        this.productBrand = productBrand;
        this.productKod = productKod;
        this.newPrice = newPrice;
        this.percentSale = percentSale;
        this.shiping = shiping;
        this.zegarekNetNewPrice = zegarekNetNewPrice;
        this.zegarekNetPercentSale = zegarekNetPercentSale;
        this.markoLink = markoLink;
        this.zegarekNetLink = zegarekNetLink;
    }

    public ProductJoin() {
    }

    public void setZegarekNetPercentSale(String zegarekNetPercentSale) {
        this.zegarekNetPercentSale = zegarekNetPercentSale;
    }

    public String getMarkoLink() {
        return markoLink;
    }

    public void setMarkoLink(String markoLink) {
        this.markoLink = markoLink;
    }

    public String getZegarekNetLink() {
        return zegarekNetLink;
    }

    public void setZegarekNetLink(String zegarekNetLink) {
        this.zegarekNetLink = zegarekNetLink;
    }

    public String getPercentSale() {
        return percentSale;
    }

    public void setPercentSale(String percentSale) {
        this.percentSale = percentSale;
    }

    public String getZegarekNetPercentSale() {
        return zegarekNetPercentSale;
    }

    public void setZegarekNetpercentSale(String zegarekNetPercentSale) {
        this.zegarekNetPercentSale = zegarekNetPercentSale;
    }

    public String getShiping() {
        return shiping;
    }

    public void setShiping(String shiping) {
        this.shiping = shiping;
    }

    public String getZegarekNetNewPrice() {
        return zegarekNetNewPrice;
    }

    public void setZegarekNetNewPrice(String zegarekNetNewPrice) {
        this.zegarekNetNewPrice = zegarekNetNewPrice;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductKod() {
        return productKod;
    }

    public void setProductKod(String productKod) {
        this.productKod = productKod;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    @Override
    public String toString() {
        return "ProductJoin{" +
                "productKod='" + productKod + '\'' +
                ", newPrice=" + newPrice +
                '}';
    }

}


