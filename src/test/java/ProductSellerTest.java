import DAO.ProductDAO;
import DAO.SellerDAO;
import Exceptions.ProductException;
import Exceptions.SellerException;
import Model.Product;
import Model.Seller;
import Service.ProductService;
import Service.SellerService;
import Util.ConnectionSingleton;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.sql.Connection;
import java.util.List;

import static Util.ConnectionSingleton.resetTestDatabase;

public class ProductSellerTest {
    ProductService productService;
    SellerService sellerService;

    ProductDAO productDAO;
    SellerDAO sellerDAO;


    @Before
    public void setUp(){
        Connection conn = ConnectionSingleton.getConnection();
        SellerDAO sellerDAO = new SellerDAO(conn);
        ProductDAO productDAO = new ProductDAO(conn);
        SellerService sellerService = new SellerService(sellerDAO);
        ProductService productService = new ProductService(productDAO);
        resetTestDatabase();
    }

     @Test
    public void testAddProduct() throws ProductException, SellerException {
        String testProductName = "item1";
        double testProductPrice = 2.99;
        String testSellerName = "shop";
        String testSellerName2 ="shop";

        Product product = new Product ();
        product.setProductName(testProductName);
        product.setProductPrice(testProductPrice);
        product.setSellerName(testSellerName);

        Seller seller = new Seller();
        seller.setName(testSellerName2);

        sellerService.saveSeller(seller);
        productService.saveProduct(product);

        assertTrue(productService.getAllProducts().contains(product));
    }

    @Test
    public void testSellerExistsException()throws SellerException {
        String testProductName = "thing";
        double testProductPrice = 10.99;
        String testSellerName = "store";
        String testSellerName2 ="place";

        Product product = new Product ();
        product.setProductName(testProductName);
        product.setProductPrice(testProductPrice);
        product.setSellerName(testSellerName);

        Seller seller = new Seller();
        seller.setName(testSellerName2);

        sellerService.saveSeller(seller);

        try {
            productService.saveProduct(product);
            Assert.fail("Seller's name does not exist in seller list");
        }catch (Exception e){

        }

    }

    @Test
    public void testDeleteProduct()throws Exception {
        List<Product> productList = productService.getAllProducts();
        String testProductName = "this item";
        double testProductPrice = 5.00;
        String testSellerName = "store2";
        String testSellerName2 ="place2";

        Product product = new Product ();
        product.setProductName(testProductName);
        product.setProductPrice(testProductPrice);
        product.setSellerName(testSellerName);

        Seller seller = new Seller();
        seller.setName(testSellerName2);

        sellerService.saveSeller(seller);

        productService.saveProduct(product);
        int productId = product.productId;

        productService.deleteProductByID(product.productId);

        assertEquals(0, productList.size());
    }





}
