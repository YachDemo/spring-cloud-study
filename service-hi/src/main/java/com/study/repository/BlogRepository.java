package com.study.repository;

import com.study.model.BlogModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.util.List;

/**
 * @author YanCh
 * Create by 2020-02-12 17:30
 **/
public interface BlogRepository extends ElasticsearchCrudRepository<BlogModel,String> {

    List<BlogModel> findByTitleLike(String keyword);

    List<BlogModel> findByTimeLike(String keyword);



}
