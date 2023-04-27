package service;

import model.Offer;
import service.AppService;

import java.util.List;

public interface OfferService extends AppService<Offer> {
    List<Offer> findAllByProductName(String name);
}
