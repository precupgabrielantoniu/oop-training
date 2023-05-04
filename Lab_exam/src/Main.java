import model.Offer;
import model.Product;
import repository.ProductRepository;
import service.ProductService;

public class Main {
    public static void main(String[] args) {
        Product p1 = new Product();
        p1.setName("lApTe");
        p1.setPrice(5.5);
        p1.setId(5648577L);
        Product p2 = new Product();
        p2.setName("Oua");
        p2.setPrice(9.);
        p2.setId(5648578L);
        Product p3 = new Product();
        p3.setName("carne de vita");
        p3.setPrice(55.);
        p3.setId(5645577L);
        Product p4 = new Product();
        p4.setPrice(6.8);
        p4.setId(5448578L);
        Product p5 = new Product();
        p5.setName("Doctor Marius");
        p5.setPrice(6.8);
        p5.setId(5648578L);
        Offer o1 = new Offer();
        o1.setCustomer("Angela Dumitru");
        ProductRepository productRepository = new ProductRepository();
        ProductService ps = new ProductService(productRepository);
        ps.create(p1);
        ps.create(p2);
        System.out.println(productRepository.getProducts());
        ps.delete(p1);
        System.out.println(productRepository.getProducts());
        ProductRepository productRepository1 = new ProductRepository();
        ProductService ps2 = new ProductService(productRepository1);
        ps2.create(p3);
        ps2.create(p5);
        System.out.println(productRepository1.getProducts());
        System.out.println(ps.search("oU"));
        System.out.println("Hello World!");
        System.out.println("Print #2");
    }
}