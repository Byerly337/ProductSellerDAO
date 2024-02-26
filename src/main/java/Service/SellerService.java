package Service;

import DAO.SellerDAO;
import Exceptions.SellerNotFoundException;
import Model.Seller;
import org.example.Main;

import java.util.List;

public class SellerService {
    SellerDAO sellerDAO;
    public SellerService(SellerDAO sellerDAO){
        this.sellerDAO = sellerDAO;
    }

    public List<Seller> getAllSellers(){
        List<Seller> sellerList = sellerDAO.getAllSellers();
        return sellerList;
    }

    public void saveSeller(Seller s){
        Main.log.info("log, added seller");
        sellerDAO.insertSeller(s);
    }

    public Seller getSellerById(int id) throws SellerNotFoundException {
        Seller s = sellerDAO.getSellerById(id);
        if(s == null){
            throw new SellerNotFoundException("no artist found by that id");
        }else{
            return s;
        }
    }


}
