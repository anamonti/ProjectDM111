package br.inatel.dm111promo.persistence.promobyuser;

import br.inatel.dm111promo.persistence.product.Product;
import br.inatel.dm111promo.persistence.promo.Promo;

import java.util.List;
import java.util.Map;

public class PromoByUser {
    private String id;
    private String name;
    private String starting;
    private String expiration;
    private String user;
    private List<Product> productsForYou;

    public PromoByUser() {
    }

    public PromoByUser(String id, String name, String starting, String expiration, String user, List<Product> productsForYou) {
        this.id = id;
        this.name = name;
        this.starting = starting;
        this.expiration = expiration;
        this.user = user;
        this.productsForYou = productsForYou;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarting() {
        return starting;
    }

    public void setStarting(String starting) {
        this.starting = starting;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Product> getProductsForYou() {
        return productsForYou;
    }

    public void setProductsForYou(List<Product> productsForYou) {
        this.productsForYou = productsForYou;
    }
}
