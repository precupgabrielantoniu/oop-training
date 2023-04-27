package model;

import model.AbstractEntity;

import java.util.List;

public class Offer extends AbstractEntity {
    private String customer;
    private List<Product> products;

    public Offer(){

    }

    public List<Product> getProducts(){
        return products;
    }

    public void setProducts(List<Product> products){
        this.products = products;
    }

    public Double getTotalPrice(){
        Double result = (double) 0;
        for (Product p : products){
            result += p.getPrice();
        }
        return result;
    }

    public String getCustomer(){
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

}
