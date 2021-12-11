package com.untiy.store.mapper;

import com.untiy.store.entity.Cart;
import com.untiy.store.vo.CartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 处理购物车数据的持久层接口 */
@Mapper
public interface CartMapper {
    /**
     * 插入购物车数据
     * @param cart 购物车数据
     * @return 受影响的行数
     */
    Integer insert(Cart cart);

    /**
     * 删除购物车数据
     * @param cid 购物车id
     * @return
     */
    Integer delete(Integer cid);

    /**
     * 删除购物车数据
     * @param cids 购物车id列表
     * @return
     */
    Integer deleteByAll(Integer [] cids);

    /**
     * 根据用户id和商品id查询购物车中的数据
     * @param uid 用户id
     * @param pid 商品id
     * @return 匹配的购物车数据，如果该用户的购物车中并没有该商品，则返回null
     */
    Cart findByUidAndPid(
            @Param("uid") Integer uid,
            @Param("pid") Integer pid);

    /**
     * 查询某用户的购物车数据
     * @param uid 用户id
     * @return 该用户的购物车数据的列表
     */
    List<CartVO> findVOByUid(Integer uid);

    /**
     * 根据购物车数据id查询购物车数据详情
     * @param cid 购物车数据id
     * @return 匹配的购物车数据详情，如果没有匹配的数据则返回null
     */
    Cart findByCid(Integer cid);

    /**
     * 根据若干个购物车数据id查询详情的列表
     * @param cids 若干个购物车数据id
     * @return 匹配的购物车数据详情的列表
     */
    List<CartVO> findVOByCids(Integer[] cids);
}
