package br.inatel.dm111promo.persistence.promo;

import br.inatel.dm111promo.persistence.promo.Promo;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface PromoRepository {
    void save(Promo promo);

    void update(Promo promo);

    void delete(String id) throws ExecutionException, InterruptedException;

    Optional<Promo> findById(String id) throws ExecutionException, InterruptedException;

    List<Promo> findAll() throws ExecutionException, InterruptedException;

    List<Promo> findPromosBySupermarketList(List<String> products) throws ExecutionException, InterruptedException;
}
