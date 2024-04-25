package br.inatel.dm111promo.api.promo.controller;

import br.inatel.dm111promo.api.core.ApiException;
import br.inatel.dm111promo.api.promo.PromoRequest;
import br.inatel.dm111promo.api.promo.service.PromoService;
import br.inatel.dm111promo.persistence.promo.Promo;
import br.inatel.dm111promo.persistence.promobyuser.PromoByUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dm111")
public class PromoController {
    private final PromoService service;

    public PromoController(PromoService service) {
        this.service = service;
    }

    @PostMapping("/promo")
    public ResponseEntity<Promo> postPromo(@RequestBody PromoRequest request) throws ApiException {
        var list = service.createPromo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    @PutMapping("/promo/{id}")
    public ResponseEntity<Promo> putPromo(@PathVariable("id") String id,
                                          @RequestBody PromoRequest request)
            throws ApiException {
        var list = service.updatePromo(id, request);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/promo/{id}")
    public ResponseEntity<?> deletePromo(@PathVariable("id")String id) throws ApiException {
        service.removePromo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/promo/{id}")
    public ResponseEntity<Promo> getPromo(@PathVariable("id") String id) throws ApiException {
        var list = service.searchPromoById(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/promo")
    public ResponseEntity<List<Promo>> getPromos() throws ApiException {
        var list = service.searchPromos();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/promo/users/{userId}")
    public ResponseEntity<PromoByUser> getPromos(@PathVariable("userId") String userId) throws ApiException {
        var promo = service.searchByUserId(userId);
        return ResponseEntity.ok(promo);
    }
}
