package com.commerce.controller;

import com.commerce.JpaAuditConfig;
import com.commerce.entity.Cart;
import com.commerce.entity.CartProduct;
import com.commerce.entity.Product;
import com.commerce.entity.User;
import com.commerce.repository.CartProductRepository;
import com.commerce.repository.CartRepository;
import com.commerce.repository.ProductRepository;
import com.commerce.repository.UserRepository;
import com.commerce.vo.ResponseVo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by jinwoo on 2017. 7. 8..
 */
@Controller
public class CartController {

  @Autowired
  JpaAuditConfig jpaAuditConfig;

  @Autowired
  UserRepository userRepository;

  @Autowired
  ProductRepository productRepository;

  @Autowired
  CartProductRepository cartProductRepository;

  @Autowired
  CartRepository cartRepository;

  @RequestMapping(value="/api/cart/product/{id}/delete", method= RequestMethod.POST)
  @ResponseBody
  public ResponseVo delete(@PathVariable("id") Long id, Model model) {

    ResponseVo responseVo = new ResponseVo();

    try {

      AuditorAware<Long> auditorProvider = jpaAuditConfig.auditorProvider();
      User user = userRepository.getOne(auditorProvider.getCurrentAuditor());

      Cart cart = user.getCart();
      Set<CartProduct> cartProducts = cart.getCartProducts();

      Iterator<CartProduct> cartProductIterator = cartProducts.iterator();

      for (Iterator<CartProduct> it = cartProductIterator; it.hasNext(); ) {

        CartProduct cartProduct = it.next();

        Long productId = cartProduct.getProduct().getId();

        if (productId == id) {
          System.out.println("-------"+id);
          cartProducts.remove(cartProduct);
          break;
          //CartProduct deleteCartP = cartProductRepository.getOne(cartProduct.getId());
          //System.out.println("JINWOO"+deleteCartP.getProduct().getId());
          //cartProductRepository.delete(deleteCartP);
        }
      }
      //cartProductRepository.delete(deleteCartProduct);

      cartRepository.save(cart);

      responseVo.setResultcode("200");
    }
    catch (Exception e) {
      responseVo.setResultcode("400");
      responseVo.setMessage(e.getMessage());
    }

    return responseVo;
  }


  @RequestMapping(value="/api/cart", method= RequestMethod.GET)
  @ResponseBody
  public CartListVo list(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pagesize , Model model) {
    CartListVo cartListVo = new CartListVo();

    try {

      AuditorAware<Long> auditorProvider = jpaAuditConfig.auditorProvider();
      User user = userRepository.getOne(auditorProvider.getCurrentAuditor());
      Cart cart = user.getCart();

      Pageable pageable = new PageRequest(page - 1, pagesize, Sort.Direction.DESC, "id");

      Page<CartProduct> cartProducts = cartProductRepository.findByCart(cart, pageable);
      cartListVo.setResultcode("200");
      cartListVo.setFullListSize(cartProducts.getTotalPages());
      cartListVo.setPage(page);

      List<CartVo> cartVos = new ArrayList<>();
      for (CartProduct cartProduct: cartProducts) {
        CartVo cartVo = new CartVo();
        Product product = cartProduct.getProduct();

        cartVo.setId(product.getId());
        cartVo.setColor(product.getColor());
        cartVo.setDescription(product.getDescription());
        cartVo.setImageUrl(product.getImageFileName());
        cartVo.setName(product.getName());
        cartVo.setPrice(product.getPrice());
        cartVo.setBuyCount(cartProduct.getBuyCount());
        cartVos.add(cartVo);
      }
      cartListVo.setList(cartVos);

    }
    catch (Exception e) {
      cartListVo.setResultcode("400");
      cartListVo.setMessage(e.getMessage());
    }


    return cartListVo;

  }

  @RequestMapping(value="/api/cart", method= RequestMethod.POST)
  @ResponseBody
  public ResponseVo cartSave(@RequestBody HashMap<String, Long> requestBody , Model model) {

    ResponseVo responseVo = new ResponseVo();

    try {

      Long id = requestBody.get("id");
      Long buyCount = requestBody.get("buyCount");

      AuditorAware<Long> auditorProvider = jpaAuditConfig.auditorProvider();
      User user = userRepository.getOne(auditorProvider.getCurrentAuditor());
      Cart cart = user.getCart();

      if (cart == null) {
        cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);
      }

      Set<CartProduct> cartProducts =  cart.getCartProducts();
      if (cartProducts == null) {
        cartProducts = new HashSet<CartProduct>();
        cart.setCartProducts(cartProducts);
      }

      boolean addcartProductsChk = true;
      addcartProductsChk = cartProductChkAndUpdate(cartProducts, id, buyCount);

      if (addcartProductsChk) {
        CartProduct cartProduct = new CartProduct();
        Product product = productRepository.getOne(id);

        cartProduct.setCart(cart);
        cartProduct.setProduct(product);
        cartProduct.setBuyCount(buyCount);
        cartProducts.add(cartProduct);
      }
      userRepository.save(user);
      responseVo.setResultcode("200");
    }
    catch (Exception e) {
      responseVo.setResultcode("400");
      responseVo.setMessage(e.getMessage());
    }

    return responseVo;
  }

  public Boolean cartProductChkAndUpdate(Set<CartProduct> cartProducts, Long id, Long buyCount) {

    Boolean addcartProductsChk = true;
    if (cartProducts.size()>0) {

      Iterator<CartProduct> cartIterator = cartProducts.iterator();
      for (Iterator<CartProduct> it = cartIterator; it.hasNext(); ) {
        CartProduct cartProduct = it.next();

        Product product = cartProduct.getProduct();

        Long productId = product.getId();
        Long origBuyCount = cartProduct.getBuyCount();

        if (productId == id) {
          addcartProductsChk = false;
          cartProduct.setBuyCount(origBuyCount+buyCount);
        }
      }
    }
    return addcartProductsChk;

  }

  @Getter
  @Setter
  public static class CartListVo extends ResponseVo {
    private Integer page;
    private Integer pageSize;
    private Integer fullListSize;
    private List<CartVo> list;
  }

  @Getter
  @Setter
  public static class CartVo {
    private String message;
    private Long id;
    private String name;
    private Double price;
    private String imageUrl;
    private String description;
    private String color;
    private Long buyCount;
  }

}
