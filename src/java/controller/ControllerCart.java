package controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Bill;
import model.BillDetails;
import model.BillDetailsId;
import model.Cart;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.BillDetailsService;
import service.BillService;
import service.CategoryService;
import service.ProducerService;
import service.ProductService;

/**
 *
 * @author TVD
 */
@Controller
@RequestMapping(value = "cart")
public class ControllerCart {
     @Autowired
    private ProductService productService;
     @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ProducerService producerService ;
    
       @Autowired
    private BillService billService;
   
        @Autowired
    private BillDetailsService billDetailsService;
        
        
     @RequestMapping(value = "add/{id}.html", method = RequestMethod.GET)
    public String viewAdd(ModelMap mm, HttpSession session, @PathVariable("id") int productId) {
        
        HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        Product product = productService.findById(productId);
        if (product != null) {
            if (cartItems.containsKey(productId)) {
                Cart item = cartItems.get(productId);
                item.setProduct(product);
                item.setQuantity(item.getQuantity() + 1);
                cartItems.put(productId, item);
            } else {
                Cart item = new Cart();
                item.setProduct(product);
                item.setQuantity(1);
                cartItems.put(productId, item);
            }
        }
        mm.put("listCategory", categoryService.getAll());
         mm.put("listCategory", categoryService.getAll());
        mm.put("userForm", new Bill());
        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("myCartTotal", totalPrice(cartItems));
        session.setAttribute("myCartNum", cartItems.size());
        return "pages/Cart";
    }
     public int totalPrice(HashMap<Integer, Cart> cartItems) {
        int count = 0;
        count = cartItems.entrySet().stream().map((list) -> list.getValue().getProduct().getPrice() * list.getValue().getQuantity()).reduce(count, Integer::sum);
        return count;
    }
    @RequestMapping(value = "viewcart.html", method = RequestMethod.GET)
    public String viewcart(ModelMap mm) {
        mm.put("listCategory", categoryService.getAll());
        mm.put("listProducer", producerService.getAll());
        mm.put("userForm", new Bill());
        return "pages/Cart";
    }
    @RequestMapping(value = "deleteallcart.html", method = RequestMethod.GET)
    public String deleteallcart( HttpSession session) {
        
        session.setAttribute("myCartItems", null);
        session.setAttribute("myCartNum", 0);
      
        return "redirect:/cart/viewcart.html";
    }
    @RequestMapping(value = "remove/{id}.html", method = RequestMethod.GET)
    public String viewRemove(HttpSession session, @PathVariable("id") int productId) {
         HashMap<Integer, Cart> cartItems = ( HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        if (cartItems.containsKey(productId)) {
            cartItems.remove(productId);
        }
       ;
        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("myCartTotal", totalPrice(cartItems));
        session.setAttribute("myCartNum", cartItems.size());
        return "redirect:/cart/viewcart.html";
    }
    
    @RequestMapping(value = "checkout.html", method = RequestMethod.POST)
    public String viewCheckoutNoAccount(ModelMap mm, HttpSession session, @ModelAttribute("userForm") Bill bill) {
        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        bill.setDate(new Timestamp(new Date().getTime()));
        bill.setStatus("Chờ xử lý");
        billService.create(bill);
        for (Map.Entry<Long, Cart> entry : cartItems.entrySet()) {
            BillDetails receiptItem = new BillDetails();
            BillDetailsId id = new BillDetailsId(entry.getValue().getProduct().getId(),bill.getId());
            receiptItem.setId(id);
            receiptItem.setBill(bill);
            receiptItem.setProduct(entry.getValue().getProduct());
            receiptItem.setPrice(entry.getValue().getProduct().getPrice());

            receiptItem.setAmount(entry.getValue().getQuantity());
          
            billDetailsService.create(receiptItem);
        }
        cartItems = new HashMap<>();
        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("myCartTotal", 0);
        session.setAttribute("myCartNum", 0);
        return "redirect:/home.html";
    }
    @RequestMapping(value = "checkout1.html", method = RequestMethod.GET)
    public String viewCheckout(ModelMap mm, HttpSession session) {
        Account account = (Account)session.getAttribute("account");
        Bill bill = new Bill();
        HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        bill.setDate(new Timestamp(new Date().getTime()));
        bill.setStatus("Chờ xử lý");
        bill.setAccount(account);
        bill.setEmail(account.getEmail());
        bill.setFullname(account.getFullname());
        bill.setPhonenumber(account.getPhoneNumber());
        bill.setAddress(account.getAddress());
        int totalPrice = totalPrice(cartItems);
        String totalPriString = Integer.toString(totalPrice);
        
        
        bill.setTotalPrice(totalPriString);
        billService.create(bill);
        for (Map.Entry<Integer, Cart> entry : cartItems.entrySet()) {
            BillDetails receiptItem = new BillDetails();
            BillDetailsId id = new BillDetailsId(entry.getValue().getProduct().getId(),bill.getId());
            receiptItem.setId(id);
            receiptItem.setBill(bill);
            receiptItem.setProduct(entry.getValue().getProduct());
            receiptItem.setPrice(entry.getValue().getProduct().getPrice());

            receiptItem.setAmount(entry.getValue().getQuantity());
          
            billDetailsService.create(receiptItem);
        }
        cartItems = new HashMap<>();
        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("myCartTotal", 0);
        session.setAttribute("myCartNum", 0);
        return "redirect:/home.html";
    }
}
