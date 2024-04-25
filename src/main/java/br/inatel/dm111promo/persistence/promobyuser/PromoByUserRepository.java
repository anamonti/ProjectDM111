package br.inatel.dm111promo.persistence.promobyuser;

import br.inatel.dm111promo.consumer.SuperMarketListMessage;
import br.inatel.dm111promo.persistence.promo.Promo;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface PromoByUserRepository {
    void save(PromoByUser promo);

    void update(PromoByUser promo);

    void delete(String id) throws ExecutionException, InterruptedException;

    Optional<PromoByUser> findById(String id) throws ExecutionException, InterruptedException;

    Optional<PromoByUser> findByUserId(String id) throws ExecutionException, InterruptedException;
}
