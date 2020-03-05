package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

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
    }

    private RestHighLevelClient buildClient() {
        log.info("=========创建Elasticsearch连接开始========");
//        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials("user","password"));
        HttpHost[] httpHosts = new HttpHost[hosts.length];
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < hosts.length; i++) {
            log.info("===========连接{}开始========",hosts[i]);
            httpHosts[i] = HttpHost.create(hosts[i]);
            stringBuilder.append(hosts[i]+",");
        }
        RestClientBuilder builder = RestClient.builder(httpHosts);
//                .setHttpClientConfigCallback(httpClientBuilder -> {
//                    httpClientBuilder.disableAuthCaching();
//                    return httpClientBuilder
//                            .setDefaultCredentialsProvider(credentialsProvider);
//                });
//        RestClient restClient = RestClient.builder(HttpHost.create(hosts[0])
//                ,HttpHost.create(hosts[1]),HttpHost.create(hosts[2]))
        restHighLevelClient = new RestHighLevelClient(builder);
        log.info("============连接{}成功==========",stringBuilder.toString());
        return restHighLevelClient;
    }
}
