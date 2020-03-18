package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static com.example.demo.entity.BaseElasticEntity.*;

/**
 * 
 * @author YanCh
 * Create by 2020-03-05 9:09
 **/
@Configuration
@Slf4j
public class ElasticsearchConfig implements InitializingBean, DisposableBean, FactoryBean<RestHighLevelClient> {

    @Value("${es.hosts}")
    private String[] hosts;
    @Value("${es.username}")
    private String usename;
    @Value("${es.password}")
    private String password;

    private RestHighLevelClient restHighLevelClient;

    @Override
    public RestHighLevelClient getObject() throws Exception {
        return this.restHighLevelClient;
    }

    @Override
    public Class<?> getObjectType() {
        return RestHighLevelClient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void destroy() throws Exception {
        if (restHighLevelClient != null){
            log.info("==========销毁restHighLevelClient连接=============");
            restHighLevelClient.close();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        restHighLevelClient = buildClient();
        createIndex(restHighLevelClient);
    }

    private RestHighLevelClient buildClient() {
        log.info("=========创建Elasticsearch连接开始========");
//        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials(usename,password));
        HttpHost[] httpHosts = new HttpHost[hosts.length];
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < hosts.length; i++) {
            log.info("===========连接{}开始========",hosts[i]);
            httpHosts[i] = HttpHost.create(hosts[i]);
            stringBuilder.append(hosts[i]).append(",");
        }
        RestClientBuilder builder = RestClient.builder(httpHosts)/*
                .setHttpClientConfigCallback(httpClientBuilder -> {
                    httpClientBuilder.disableAuthCaching();
                    return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                })*/;
        restHighLevelClient = new RestHighLevelClient(builder);
        log.info("============连接{}成功==========",stringBuilder.toString());
        return restHighLevelClient;
    }

    /**
     * 创建es索引
     * @param restHighLevelClient
     */
    private void createIndex(RestHighLevelClient restHighLevelClient){
        try {
            // 遍历需要创建的索引
            for (int i = 0; i < INDEX_NAMES.length; i++) {
                if (this.indexExist(INDEX_NAMES[i],restHighLevelClient)) {
                    log.warn(" idxName={} 已经存在,idxSql={}",INDEX_NAMES[i],INDEX_MAPPING_PATHS[i]);
                    continue;
                }
                String mappingPath = ElasticsearchConfig.class.getResource(INDEX_MAPPING_PATHS[i]).getFile();
                String settingPath =  ElasticsearchConfig.class.getResource(INDEX_SETTING_PATHS[i]).getFile();
                CreateIndexRequest request = new CreateIndexRequest(INDEX_NAMES[i]);
                // 读取json内容
                String idxSQL = json2String(mappingPath);
                String setting = json2String(settingPath);
                request.mapping(idxSQL, XContentType.JSON);
                request.settings(setting,XContentType.JSON); // 手工指定Setting
                CreateIndexResponse res = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
                if (!res.isAcknowledged()) {
                    throw new RuntimeException("初始化失败");
                }
            }
        } catch (Exception e) {
            log.error("================初始化es index 异常========================",e);
            System.exit(0);
        }
    }
    
    /** 断某个index是否存在
     * @param idxName index名
     * @return boolean
     */
    public boolean indexExist(String idxName,RestHighLevelClient restHighLevelClient) throws Exception {
        return restHighLevelClient.indices().exists(new GetIndexRequest(idxName),RequestOptions.DEFAULT);
    }

    /**
     * 读取json内容
     * @param path
     * @return
     * @throws IOException
     */
    public static String json2String(String path) throws IOException {
        StringBuilder result = new StringBuilder();
        InputStream in = new FileInputStream(path);
        //读取文件上的数据。
        // 将字节流向字符流的转换。
        InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);//读取
        //创建字符流缓冲区
        BufferedReader bufr = new BufferedReader(isr);//缓冲

        String line;
        while ((line = bufr.readLine()) != null) {
            result.append(System.lineSeparator()).append(line);
        }
        isr.close();
        return result.toString();
    }

}
