package com.untiy.store.controller;

import com.untiy.store.entity.Order;
import com.untiy.store.service.IOrderService;
import com.untiy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/create")
    public JsonResult<Order> create(Integer[] cids, HttpSession session) {
        // 从Session中取出uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行业务
        Order data = orderService.create(cids, uid, username);
        // 返回成功与数据
        return new JsonResult<Order>(OK, data);
    }

    @RequestMapping("/createsingle")
    public JsonResult<Order> create(Integer cid, HttpSession session) {
        // 从Session中取出uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行业务
        Order data = orderService.createSingle(cid, uid, username);
        // 返回成功与数据
        return new JsonResult<Order>(OK, data);
    }
}
