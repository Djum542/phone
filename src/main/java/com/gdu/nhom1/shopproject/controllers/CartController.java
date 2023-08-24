package com.gdu.nhom1.shopproject.controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.gdu.nhom1.shopproject.models.CartItem;
import com.gdu.nhom1.shopproject.repository.CartItemReponsitory;
import com.gdu.nhom1.shopproject.services.CartItemService;
import com.google.gson.Gson;
import io.swagger.annotations.ResponseHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.gdu.nhom1.shopproject.dto.BillDTO;
import com.gdu.nhom1.shopproject.models.Bill;
import com.gdu.nhom1.shopproject.models.Product;
import com.gdu.nhom1.shopproject.services.BillService;
import com.gdu.nhom1.shopproject.services.ProductService;
import com.gdu.nhom1.shopproject.services.UserService;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@SessionAttributes("cart")
public class CartController {
    @Autowired
    ProductService productService;

    @Autowired
    BillService billServece;
    CartItemService itemService;
    @Autowired
    UserService userService;
    @Autowired
    CartItemReponsitory reponsitory;
    private static List<Product> cart;

    public static void clearCart() {
        cart = null;
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("productId") int productId,
            @RequestParam("quantity") int quantity, HttpSession session,
            Model model) {
        Product product = productService.getProductById(productId).get();
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        int current = cart.size();
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getId() == productId) {
                cart.get(i).setQuantity(quantity + cart.get(i).getQuantity());
                cart.get(i).setPrice(cart.get(i).getQuantity() * product.getPrice());
                current++;
            }
        }
        if (current == cart.size()) {
            Product product2 = new Product();
            product2.setId(productId);
            product2.setName(product.getName());
            product2.setCategory(product.getCategory());
            product2.setPrice(product.getPrice() * quantity);
            product2.setDescription(product.getDescription());
            product2.setQuantity(quantity);
            product2.setImageName(product.getImageName());
            cart.add(product2);


        }
//
        session.setAttribute("cartCount", cart.size());
        // model.addAttribute("total",
        // cart.stream().mapToDouble(Product::getPrice).sum());
        session.setAttribute("total", cart.stream().mapToDouble(Product::getPrice).sum());
        return "redirect:/shop";
    }
    @PostMapping("/addjsoncart")
    public ResponseEntity<List> addjson(@RequestParam("productId") int productId,
                            @RequestParam("quantity") int quantity, HttpSession session,
                            Model model) {
        Product product = productService.getProductById(productId).get();
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        int current = cart.size();
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getId() == productId) {
                cart.get(i).setQuantity(quantity + cart.get(i).getQuantity());
                cart.get(i).setPrice(cart.get(i).getQuantity() * product.getPrice());
                current++;
            }
        }
        if (current == cart.size()) {
            Product product2 = new Product();
            product2.setId(productId);
            product2.setName(product.getName());
            //product2.setCategory(product.getCategory());
            product2.setPrice(product.getPrice() * quantity);
            //product2.setDescription(product.getDescription());
            product2.setQuantity(quantity);
            product2.setImageName(product.getImageName());
            cart.add(product2);


        }
//
        session.setAttribute("cartCount", cart.size());
        // model.addAttribute("total",
        // cart.stream().mapToDouble(Product::getPrice).sum());
        session.setAttribute("total", cart.stream().mapToDouble(Product::getPrice).sum());
        return ResponseEntity.ok().body(cart);
    }
    @GetMapping("/jsoncart")
    public ResponseEntity<Model> jsoncart(@RequestHeader(name = "Authorization") Model model, HttpSession session){
            model.addAttribute("cartitem", reponsitory.findAll());
            session.setAttribute("session", session);
            return ResponseEntity.ok().body(model);
        //return ResponseEntity.ok(model);
    }


    @PostMapping("/updateCart")
    public String updateCart(@RequestParam("productId") int productId,
            @RequestParam("quantity") int quantity, HttpSession session,
            Model model) {
        Product product = productService.getProductById(productId).get();

        int totalQuantity = 0;

        for (Product p : productService.getAllProduct()) {
            if (p.getId() == productId) {
                totalQuantity = p.getQuantity();
            }
        }

        model.addAttribute("totalQuantity", totalQuantity);

        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getId() == productId) {
                if (quantity <= 0) { // quantity == 0
                    cart.remove(i);
                } else if (quantity > 0 && product.getQuantity() > quantity) {
                    cart.get(i).setQuantity(quantity);
                    cart.get(i).setPrice(quantity * product.getPrice());
                } else {
                    System.out.println("số lượng vượt quá số lượng sản phẩm có trong kho"); // cần 1 thông báo
                }

            }
        }

        session.setAttribute("cartCount", cart.size());
        model.addAttribute("total", cart.stream().mapToDouble(Product::getPrice).sum());
        return "/cart";
    }

    @GetMapping("/cart")
    public String cartGet(Model model, HttpSession session) {
        cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        int totalQuantity = 0;
        for (Product p : productService.getAllProduct()) {
            for (Product c : cart) {
                if (p.getId() == c.getId()) {
                    totalQuantity = p.getQuantity();
                }
            }
        }
        model.addAttribute("totalQuantity", totalQuantity);
        model.addAttribute("cartCount", cart.size());
        model.addAttribute("total", cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", cart);
        return "cart";
    }
    @GetMapping("/gscart")
    public ResponseEntity<Model> gscartGet(Model model, HttpSession session) {
        cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        int totalQuantity = 0;
        for (Product p : productService.getAllProduct()) {
            for (Product c : cart) {
                if (p.getId() == c.getId()) {
                    totalQuantity = p.getQuantity();
                }
            }
        }
        model.addAttribute("totalQuantity", totalQuantity);
        model.addAttribute("cartCount", cart.size());
        model.addAttribute("total", cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", cart);
        return ResponseEntity.ok().body(model);
    }
    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index, HttpSession session, Model model) {
        cart = (List<Product>) session.getAttribute("cart");
        if (cart != null && index >= 0 && index < cart.size()) {
            cart.remove(index);
            session.setAttribute("cartCount", cart.size());
        }
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session) {
        session.getAttribute("userId");

        cart = (List<Product>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }

        model.addAttribute("billDTO", new BillDTO());
        model.addAttribute("total", cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("users", userService.findALL());
        return "/checkout";
    }

    @PostMapping("/payNow")
    public String payNow(@ModelAttribute("billDTO") BillDTO billDTO, Model model, HttpSession session) {
        Bill bill = new Bill();
        bill.setId(billDTO.getId());
        // bill.setUser(userService.getUserById(billDTO.getUserId()).get());
        bill.setFirstName(billDTO.getFirstName());
        bill.setLastName(billDTO.getLastName());
        bill.setAddress(billDTO.getAddress());
        bill.setTown_city(billDTO.getTown_city());
        bill.setPhoneNumber(billDTO.getPhoneNumber());
        bill.setEmail(billDTO.getEmail());
        bill.setUser(userService.getUserById(billDTO.getUserId()).get());
        System.out.println("\n" + billDTO.getUserId() + "\n");

        bill.setAddInformation(billDTO.getAddInformation());

        double totalMoney = 0;

        List<Product> productList = new ArrayList<Product>();

        List<String> productName = new ArrayList<String>();
        for (int i = 0; i < cart.size(); i++) {
            long id = cart.get(i).getId();
            Product product = productService.getProductById(id).get();
            product.setQuantity(product.getQuantity() - cart.get(i).getQuantity());

            Product product2 = new Product();
            product2.setName(cart.get(i).getName());
            product2.setQuantity(cart.get(i).getQuantity());
            product2.setPrice(cart.get(i).getPrice());
            productList.add(product2);

            productName.add(product.getName() + " x " + cart.get(i).getQuantity());

            totalMoney += cart.get(i).getPrice();
        }

        bill.setProductName(productName);

        bill.setPrice(totalMoney);
        billServece.addBill(bill);

        model.addAttribute("bill", bill);
        model.addAttribute("result", bill.getId());
        model.addAttribute("productList", productList);
        model.addAttribute("productName", productName);
        model.addAttribute("total", cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("products", productService.getAllProduct());

        cart.clear();
        session.setAttribute("cartCount", cart.size());
        return "/orderPlaced";
    }

}
//@PostMapping("/jsoncart")
//    public ResponseEntity<FileReader> jsoncart() throws FileNotFoundException {
//        Gson gson = new Gson();
//        String json = gson.toJson(cart);
//        try (FileWriter writer = new FileWriter("cart.json")) {
//            writer.write(json);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        FileReader red = new FileReader("cart.json");
////        try (FileReader reader = new FileReader("person.json")) {
////            // Đọc nội dung từ tệp và chuyển đổi thành đối tượng
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        return ResponseEntity.ok(red);
//    }