package vn.hoidanit.laptopshop.controller.client;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;




@Controller
public class ItemController {
    
    private final ProductService productService;

    public ItemController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String getProdcut(Model model, @PathVariable long id) {

        Product pr = this.productService.fetchProductById(id).get();
        model.addAttribute("product", pr);
        model.addAttribute("id", id);
        return "client/product/detail";
    }
    @PostMapping("/add-product-to-cart/{id}")
    public String addProductCart(@PathVariable long id, HttpServletRequest request) {
        //TODO: process POST request
        HttpSession session = request.getSession(false);
        long productId = id;
        String email = (String) session.getAttribute("email");
        this.productService.handleAddProductToCart(email, productId, session);
        return "redirect:/";
    }
    
    @GetMapping("/cart")
    public String getCartPage(Model model,HttpServletRequest request ) {
       User curentUser = new User();
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        curentUser.setId(id);

        Cart cart = this.productService.fetchByUser(curentUser);

        List<CartDetail> cartDetail = cart == null ? new ArrayList<CartDetail>() :  cart.getCartDetails();

        double totalPrice = 0;
        for(CartDetail cd : cartDetail ){
            totalPrice = cd.getPrice() * cd.getQuantity();
        }
        model.addAttribute("cartDetails", cartDetail);
        model.addAttribute("totalPrice", totalPrice);
        return "client/cart/show";
    }

    @PostMapping("/delete-cart-product/{id}")
    public String deleteCartDetail(@PathVariable long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        long cartDetailId = id;
        this.productService.handleRemoveCartDetail(cartDetailId, session);
        return "redirect:/cart";
    }
    


}
