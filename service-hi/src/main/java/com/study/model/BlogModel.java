package com.study.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author YanCh
 * Create by 2020-02-12 17:25
 **/
@Data
@Accessors(chain = true)
@Document(indexName = "blog",type = "java")
public class BlogModel implements Serializable {

    @Id
    private String id;

    private String title;

    private String time;
}
