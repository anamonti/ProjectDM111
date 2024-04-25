package br.inatel.dm111promo.persistence.product;

public class Product {
    private String id;
    private String discount;

    public Product(String id, String discount) {
        this.id = id;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
