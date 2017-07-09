package com.commerce.controller;

import com.commerce.JpaAuditConfig;
import com.commerce.entity.Order;
import com.commerce.entity.OrderProduct;
import com.commerce.entity.Product;
import com.commerce.entity.User;
import com.commerce.enumeration.PayMethod;
import com.commerce.repository.OrderRepository;
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
public class OrderController {
  @Autowired
  JpaAuditConfig jpaAuditConfig;

  @Autowired
  UserRepository userRepository;

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  ProductRepository productRepository;

  @RequestMapping(value="/api/order", method= RequestMethod.GET)
  @ResponseBody
  public OrderListVo orderList(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pagesize , Model model) {

    OrderListVo orderListVo = new OrderListVo();

    try {

      AuditorAware<Long> auditorProvider = jpaAuditConfig.auditorProvider();
      User user = userRepository.getOne(auditorProvider.getCurrentAuditor());

      Pageable pageable = new PageRequest(page - 1, pagesize, Sort.Direction.DESC, "id");

      Page<Order> orderPage = orderRepository.findByUser(user, pageable);

      orderListVo.setFullListSize(orderPage.getTotalPages());
      orderListVo.setPage(page);
      orderListVo.setOrderList(orderPage.getContent());
      orderListVo.setPageSize(pagesize);
      orderListVo.setResultcode("200");

    }
    catch (Exception e) {
      orderListVo.setResultcode("400");
      orderListVo.setMessage(e.getMessage());
    }

    return orderListVo;

  }


  @RequestMapping(value="/api/order/{id}/cancel", method= RequestMethod.POST)
  @ResponseBody
  public ResponseVo delete(@PathVariable("id") Long id, Model model) {


    ResponseVo responseVo = new ResponseVo();

    try {
      responseVo.setResultcode("200");
      orderRepository.delete(id);
    }
    catch (Exception e) {
      responseVo.setResultcode("400");
      responseVo.setMessage(e.getMessage());
    }

    return responseVo;
  }

  @RequestMapping(value="/api/order", method= RequestMethod.POST)
  @ResponseBody
  public ResponseVo orderSave(@RequestBody SaveOrderVo saveOrderVo , Model model) {
    ResponseVo responseVo = new ResponseVo();


    AuditorAware<Long> auditorProvider = jpaAuditConfig.auditorProvider();
    User user = userRepository.getOne(auditorProvider.getCurrentAuditor());

    try {

      // 배송받을 사람
      Order order = new Order();

      order.setRecipientName(saveOrderVo.recipientName);
      order.setDeliveryAddress(saveOrderVo.deliveryAddress);
      order.setRecipientTel(saveOrderVo.recipientTel);
      order.setPayMethod(saveOrderVo.payMethod);
      order.setOrderPrice(saveOrderVo.orderPrice);

      List<HashMap<String, Long>> orderProductsList = saveOrderVo.orderList;

      Set<OrderProduct> orderProducts = new HashSet<>();

      for (HashMap<String, Long> orderProductInfo :orderProductsList) {

        OrderProduct orderProduct = new OrderProduct();

        Long productId = orderProductInfo.get("product");
        Integer orderCount = Math.toIntExact(orderProductInfo.get("orderCount"));

        Product product = productRepository.getOne(productId);
        orderProduct.setProduct(product);
        orderProduct.setOrder(order);
        orderProduct.setOrderCount(orderCount);
        orderProducts.add(orderProduct);
      }

      order.setOrderProducts(orderProducts);
      order.setUser(user);
      orderRepository.save(order);
      responseVo.setResultcode("200");

    }
    catch (Exception e) {
      responseVo.setResultcode("400");
      responseVo.setMessage(e.getMessage());
    }

    return responseVo;

    /*
    {
      "recipientName": "jinwookim",
        "deliveryAddress": "seoulsi",
        "recipientTel": "01092838674",
        "payMethod": 1,
        "orderPrice": 3000.0,
        "orderList": [
      {
        "product": 1,
          "orderCount": 5
      },
      {
        "product": 2,
          "orderCount": 4
      }
    ]
    }
    */

  }

  @Getter
  @Setter
  public static class OrderListVo extends ResponseVo {

    private Integer page;
    private Integer pageSize;
    private Integer fullListSize;
    private List<Order> orderList;

  }

  @Getter
  @Setter
  public static class SaveOrderVo {

    private String recipientName;
    private String deliveryAddress;
    private String recipientTel;
    private PayMethod payMethod;
    private Double orderPrice;
    private List<HashMap<String,Long>> orderList;
  }
}
