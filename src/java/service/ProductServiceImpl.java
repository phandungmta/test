package service;

import dao.ProductDAO;
import model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TVD
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

     @Override
    public boolean create(Product object) {
        return productDAO.create(object);
    }

    @Override
    public List<Product> find(String Name) {
        return productDAO.find(Name);
    }

    @Override
    public List<Product> getListByCategory(int categoryId) {
        return productDAO.getListByCategory(categoryId);
    }
    @Override
    public List<Product> getListByProducer(int producerId) {
        return productDAO.getListByProducer(producerId);
    }
    @Override
    public List<Product> getListHot() {

        return productDAO.getListHot();
    }

    @Override
    public List<Product> getListNew() {
        return productDAO.getListNew();
    }

    @Override
    public List<Product> getListNav(int start, int limit) {
        return productDAO.getListNav(start, limit);
    }

    @Override
    public int totalItem() {
        return productDAO.totalItem();
    }

    @Override
    public List<Product> getListByCategoryIDAndProducer(int categoryId, int producerId) {
        return  productDAO.getListByCategoryIDAndProducer(categoryId, producerId);
    }

    @Override
    public Product findById(int productId) {
        return productDAO.findById(productId);
    }

   
}
