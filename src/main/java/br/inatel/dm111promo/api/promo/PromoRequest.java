package br.inatel.dm111promo.api.promo;

import br.inatel.dm111promo.persistence.product.Product;

import java.util.List;
import java.util.Map;

public record PromoRequest (String name,
                            String starting,
                            String expiration,
                            List<Product> products) {
}
