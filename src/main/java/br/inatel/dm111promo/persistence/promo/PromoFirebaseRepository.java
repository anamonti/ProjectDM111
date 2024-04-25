package br.inatel.dm111promo.persistence.promo;

import br.inatel.dm111promo.persistence.product.Product;
import br.inatel.dm111promo.persistence.promobyuser.PromoByUser;
import br.inatel.dm111promo.persistence.promobyuser.PromoByUserRepository;
import com.google.cloud.firestore.Firestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class PromoFirebaseRepository implements PromoRepository {

    private static final String COLLECTION_NAME = "promo";

    private final Firestore firestore;

    private final PromoByUserRepository promoByUserRepository;

    public PromoFirebaseRepository(Firestore firestore, PromoByUserRepository promoByUserRepository) {
        this.firestore = firestore;
        this.promoByUserRepository = promoByUserRepository;
    }

    @Override
    public void save(Promo promo) {
        firestore.collection(COLLECTION_NAME)
                .document(promo.getId())
                .set(promo);
    }

    @Override
    public void update(Promo promo) {
        save(promo);
    }

    @Override
    public void delete(String id) throws ExecutionException, InterruptedException {
        firestore.collection(COLLECTION_NAME).document(id).delete().get();
    }

    @Override
    public Optional<Promo> findById(String id) throws ExecutionException, InterruptedException {
        var promo = firestore.collection(COLLECTION_NAME)
                .document(id)
                .get()
                .get()
                .toObject(Promo.class);
        return Optional.ofNullable(promo);
    }

    @Override
    public List<Promo> findAll() throws ExecutionException, InterruptedException {
        return firestore.collection(COLLECTION_NAME)
                .get()
                .get()
                .getDocuments()
                .parallelStream()
                .map(promo -> promo.toObject(Promo.class))
                .toList();
    }

    @Override
    public List<Promo> findPromosBySupermarketList(List<String> products) throws ExecutionException, InterruptedException {
        List<Promo> promos = new ArrayList<>();
        for (String product : products) {
            promos.addAll(findPromosByProduct(product));
        }
        return promos;
    }

    private List<Promo> findPromosByProduct(String product) throws ExecutionException, InterruptedException {
        return firestore.collection(COLLECTION_NAME)
                .get()
                .get()
                .getDocuments()
                .parallelStream()
                .map(promo -> promo.toObject(Promo.class))
                .filter(promo -> promo.getProducts().contains(product))
                .toList();
    }
}
