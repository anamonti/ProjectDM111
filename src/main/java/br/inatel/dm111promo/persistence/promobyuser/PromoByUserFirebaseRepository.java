package br.inatel.dm111promo.persistence.promobyuser;

import br.inatel.dm111promo.persistence.product.Product;
import br.inatel.dm111promo.persistence.promo.Promo;
import com.google.cloud.firestore.Firestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class PromoByUserFirebaseRepository implements PromoByUserRepository {
    private static final String COLLECTION_NAME = "promo_by_user";

    private final Firestore firestore;

    public PromoByUserFirebaseRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public void save(PromoByUser promo) {
        firestore.collection(COLLECTION_NAME)
                .document(promo.getId())
                .set(promo);
    }

    @Override
    public void update(PromoByUser promo) {
        save(promo);
    }

    @Override
    public void delete(String id) throws ExecutionException, InterruptedException {
        firestore.collection(COLLECTION_NAME).document(id).delete().get();
    }

    @Override
    public Optional<PromoByUser> findById(String id) throws ExecutionException, InterruptedException {
        var promo = firestore.collection(COLLECTION_NAME)
                .document(id)
                .get()
                .get()
                .toObject(PromoByUser.class);
        return Optional.ofNullable(promo);
    }

    @Override
    public Optional<PromoByUser> findByUserId(String id) throws ExecutionException, InterruptedException {
        return firestore.collection(COLLECTION_NAME)
                .get()
                .get()
                .getDocuments()
                .stream()
                .map(promo -> promo.toObject(PromoByUser.class))
                .filter(promo -> promo.getUser().equalsIgnoreCase(id))
                .findFirst();
    }

    @Override
    public List<PromoByUser> findAllByUserId(String userId) throws ExecutionException, InterruptedException {
        return firestore.collection(COLLECTION_NAME)
                .get()
                .get()
                .getDocuments()
                .parallelStream()
                .map(promo -> promo.toObject(PromoByUser.class))
                .filter(promo -> promo.getUser().equals(userId))
                .toList();
    }

    private List<Product> findProductsFromList(List<String> productsFromList, List<Promo> promos) {
        List<Product> products = new ArrayList<>();
        for (Promo promo : promos) {
            for (Product product : promo.getProducts()) {
                if (productsFromList.contains(product.getId())) {
                    products.add(product);
                }
            }
        }
        return products;
    }

}
