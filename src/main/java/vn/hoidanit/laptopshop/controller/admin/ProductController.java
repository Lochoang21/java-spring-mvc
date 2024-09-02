package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;


@Controller
public class ProductController {

    private final UploadService uploadService;
    private final ProductService productService;

    public ProductController(UploadService uploadService,  ProductService productService) {
        
        this.uploadService = uploadService;
        this.productService = productService;
    }

    @GetMapping("/admin/product")
    public String getProduct(Model model, @RequestParam("page") Optional<String> pageOptional){
        //client: page =? .limit
        //database: offset limit
        int page =1;
        try {
            if (pageOptional.isPresent()) {
                page = Integer.parseInt(pageOptional.get());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page <Product> prs = this.productService.fetchProducts(pageable);
        List <Product> listProducts = prs.getContent();
        model.addAttribute("products", listProducts);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", prs.getTotalPages());

        return "admin/product/show" ;
    }

    @GetMapping("/admin/product/create")
    public String CreateProduct(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }
    @PostMapping("/admin/product/create")
    public String createProductPage(Model model, 
            @ModelAttribute("newProduct") @Valid Product pr, 
            BindingResult newProductBindingResult,
            @RequestParam("hoidanitFile") MultipartFile file) {

            // List <FieldError> errors = newUserBindingResult.getFieldErrors();
            // for (FieldError fieldError : errors) {
            //     System.out.println(">>>" + fieldError.getField() + " - " + fieldError.getDefaultMessage());
            // }
            if(newProductBindingResult.hasErrors()){
                return "/admin/product/create";
            }
        //validation

        String image = this.uploadService.handleSaveUploadFile(file, "product");

        pr.setImage(image);
        //save 

        this.productService.createProduct(pr);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProductPage(Model model, @PathVariable long id) {
        // User user = new User();
        // user.setId(id);
        model.addAttribute("id", id);
        model.addAttribute("newProduct", new Product());
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String postDeleteProduct(Model model, @ModelAttribute("newProduct") Product pr) {
        this.productService.deleteProduct(pr.getId());
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/{id}")
    public String getProductDetailPage(Model model, @PathVariable long id) {
        Product pr = this.productService.fetchProductById(id).get();
        model.addAttribute("product", pr);
        model.addAttribute("id", id);
        return "admin/product/detail";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(Model model, @PathVariable long id) {
        Optional <Product> currentProduct = this.productService.fetchProductById(id);
        model.addAttribute("newProduct", currentProduct.get());
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String handleUpdateProduct( @ModelAttribute("newproduct") @PathVariable Product pr,
        BindingResult newProductBindingResult, @RequestParam ("hoidanitFile") MultipartFile file) {

        if(newProductBindingResult.hasErrors()){
            return "admin/product/update";
        }
        Product currentproduct = this.productService.fetchProductById(pr.getId()).get();
        // model.addAttribute("newUser", currentUser);
        if (currentproduct != null) {
            // System.out.println("print here");

            if(!file.isEmpty()){
                String img = this.uploadService.handleSaveUploadFile(file, "product");
                currentproduct.setImage(img);
            }
            currentproduct.setName(pr.getName());
            currentproduct.setPrice(pr.getPrice());
            currentproduct.setShortDesc(pr.getShortDesc());
            currentproduct.setDetailDesc(pr.getDetailDesc());
            currentproduct.setQuantity(pr.getQuantity());
            currentproduct.setFactory(pr.getFactory());
            currentproduct.setTarget(pr.getTarget());
            this.productService.createProduct(currentproduct);
        }
        return "redirect:/admin/product";
    }
}
