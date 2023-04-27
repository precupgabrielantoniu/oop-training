package service;

import exceptions.OfferNotFoundException;
import exceptions.ValidationFailedException;
import model.Offer;
import service.OfferService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class OfferServiceImpl implements OfferService {
    private List<Offer> offers = new ArrayList<>();
    @Override
    public Offer create(Offer offer) {
        if(offer.getCustomer() == null ||
        offer.getProducts().isEmpty()){
            throw new ValidationFailedException("Invalid offer");
        }
        offers.add(offer);
        return offer;
    }

    @Override
    public List<Offer> search(String s) {
        return offers.stream()
                .filter(o->o.getCustomer().startsWith(s))
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(Offer offer) {
        boolean found = offers.remove(offer);
        if(!found) throw new OfferNotFoundException("Offer not found");
        return true;
    }

    @Override
    public List<Offer> findAllByProductName(String name) {
        return offers.stream()
                .filter(o->o.getProducts()
                        .stream()
                        .anyMatch(l->l.getName() == name))
                .collect(Collectors.toList());
    }
}
