package com.study.repository;

import com.study.model.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 
 * @author YanCh
 * Create by 2020-02-17 14:59
 **/
public interface ItemRepository extends ElasticsearchRepository<Item,Integer> {

    List<Item> findByPriceBetween(double price1, double price2);

}
