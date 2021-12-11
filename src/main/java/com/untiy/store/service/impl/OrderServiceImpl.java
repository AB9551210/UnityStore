package com.untiy.store.service.impl;

import com.untiy.store.entity.Order;
import com.untiy.store.entity.OrderItem;
import com.untiy.store.entity.Product;
import com.untiy.store.entity.User;
import com.untiy.store.mapper.CartMapper;
import com.untiy.store.mapper.OrderMapper;
import com.untiy.store.mapper.ProductMapper;
import com.untiy.store.mapper.UserMapper;
import com.untiy.store.service.ICartService;
import com.untiy.store.service.IOrderService;
import com.untiy.store.service.ex.InsertException;
import com.untiy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/** 处理订单和订单数据的业务层实现类 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ICartService cartService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    ProductMapper productMapper;

    @Transactional
    @Override
    public Order create(Integer[] cids, Integer uid, String username) {
        // 当前用户
        User user = userMapper.findByUid(uid);

        // 创建当前时间对象
        Date now = new Date();

        // 根据cids查询所勾选的购物车列表中的数据
        List<CartVO> carts = cartService.getVOByCids(uid, cids);

        Double total_money = Double.valueOf(0);

        for (CartVO cart : carts)
            total_money+=cart.getPrice();

        // 创建订单数据对象
        Order order = new Order();
        // 补全数据：uid
        order.setUid(uid);
        order.setRecvName(username);
        order.setRecvEmail(user.getEmail());
        order.setTotalPrice(total_money);
        // 补全数据：status
        order.setStatus(0);
        // 补全数据：下单时间
        order.setOrderTime(now);
        // 补全数据：日志
        order.setCreatedUser(username);
        order.setCreatedTime(now);
        order.setModifiedUser(username);
        order.setModifiedTime(now);
        // 插入订单数据
        Integer rows1 = orderMapper.insertOrder(order);
        if (rows1 != 1) {
            throw new InsertException("插入订单数据时出现未知错误，请联系系统管理员");
        }


        // 遍历carts，循环插入订单商品数据
        for (CartVO cart : carts) {
            // 创建订单商品数据
            total_money += cart.getPrice();
            OrderItem item = new OrderItem();
            // 补全数据：setOid(order.getOid())
            item.setOid(order.getOid());
            // 补全数据：pid, title, image, price, num
            item.setPid(cart.getPid());
            item.setTitle(cart.getTitle());
            item.setImage(cart.getImage());
            order.setTotalPrice(cart.getPrice());
            // 补全数据：4项日志
            item.setCreatedUser(username);
            item.setCreatedTime(now);
            item.setModifiedUser(username);
            item.setModifiedTime(now);
            // 插入订单商品数据
            Integer rows2 = orderMapper.insertOrderItem(item);
            if (rows2 != 1) {
                throw new InsertException("插入订单商品数据时出现未知错误，请联系系统管理员");
            }
        }

        cartService.deleteByCids(cids);

        // 返回
        return order;
    }

    @Override
    public Order createSingle(Integer cid, Integer uid, String username) {
        // 当前用户
        User user = userMapper.findByUid(uid);

        // 创建当前时间对象
        Date now = new Date();

        // 根据cids查询所勾选的购物车列表中的数据
        Product product = productMapper.findById(cid);

        // 创建订单数据对象
        Order order = new Order();
        // 补全数据：uid
        order.setUid(uid);
        order.setRecvName(username);
        order.setRecvEmail(user.getEmail());
        order.setTotalPrice(product.getAfterprice());
        // 补全数据：status
        order.setStatus(0);
        // 补全数据：下单时间
        order.setOrderTime(now);
        // 补全数据：日志
        order.setCreatedUser(username);
        order.setCreatedTime(now);
        order.setModifiedUser(username);
        order.setModifiedTime(now);
        // 插入订单数据
        Integer rows1 = orderMapper.insertOrder(order);
        if (rows1 != 1) {
            throw new InsertException("插入订单数据时出现未知错误，请联系系统管理员");
        }
        // 返回
        return order;
    }
}
