package com.untiy.store.mapper;

import com.untiy.store.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 处理商品数据的持久层接口 */
@Mapper
public interface ProductMapper {
    /**
     * 查询展示商品列表
     * @return 商品列表的集合
     */
    List<Product> findHotList();

    /**
     * 根据商品id查询商品详情
     * @param id 商品id
     * @return 匹配的商品详情，如果没有匹配的数据则返回null
     */
    Product findById(Integer id);
}
