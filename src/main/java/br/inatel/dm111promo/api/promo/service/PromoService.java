package br.inatel.dm111promo.api.promo.service;

import br.inatel.dm111promo.api.core.ApiException;
import br.inatel.dm111promo.api.core.AppErrorCode;
import br.inatel.dm111promo.api.promo.PromoRequest;
import br.inatel.dm111promo.persistence.promo.Promo;
import br.inatel.dm111promo.persistence.promo.PromoRepository;
import br.inatel.dm111promo.persistence.promobyuser.PromoByUser;
import br.inatel.dm111promo.persistence.promobyuser.PromoByUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class PromoService {

    private static final Logger log = LoggerFactory.getLogger(PromoService.class);

    private final PromoRepository repository;

    private final PromoByUserRepository promoByUserRepository;

    public PromoService(PromoRepository repository, PromoByUserRepository promoByUserRepository) {
        this.repository = repository;
        this.promoByUserRepository = promoByUserRepository;
    }

    public Promo createPromo(PromoRequest request) {
        var promo = buildPromo(request);
        repository.save(promo);
        return promo;
    }

    public Promo updatePromo(String id, PromoRequest request) throws ApiException {
        var promo = retrievePromo(id);
        promo.setName(request.name());
        promo.setStarting(request.starting());
        promo.setExpiration(request.expiration());
        promo.setProducts(request.products());
        return promo;
    }

    public void removePromo(String id) throws ApiException {
        try {
            repository.delete(id);
        } catch (ExecutionException | InterruptedException e) {
            throw new ApiException(AppErrorCode.PROMO_QUERY_ERROR);
        }
    }

    public Promo searchPromoById(String id) throws ApiException{
        return retrievePromo(id);
    }

    public List<Promo> searchPromos() throws ApiException{
        try {
            return repository.findAll();
        } catch (ExecutionException | InterruptedException e) {
            throw new ApiException(AppErrorCode.PROMO_QUERY_ERROR);
        }
    }

    public PromoByUser searchByUserId(String userId) throws ApiException {
        try {
            return promoByUserRepository.findByUserId(userId)
                    .orElseThrow(() -> new ApiException(AppErrorCode.PROMO_NOT_FOUND));
        } catch (ExecutionException | InterruptedException e) {
            log.error(e.getMessage(), e);
            throw new ApiException(AppErrorCode.PROMO_QUERY_ERROR);
        }
    }

    private Promo retrievePromo(String id) throws ApiException {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new ApiException(AppErrorCode.PROMO_NOT_FOUND));
        } catch (ExecutionException | InterruptedException e) {
            throw new ApiException(AppErrorCode.PROMO_QUERY_ERROR);
        }
    }

    private Promo buildPromo(PromoRequest request) {
        var id = UUID.randomUUID().toString();
        return new Promo(id, request.name(), request.starting(), request.expiration(), request.products());
    }


}
