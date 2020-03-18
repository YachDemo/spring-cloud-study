package com.example.demo.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * MybatisPlus的数据源和插件配置
 * @author YanCh
 * Create by 2020-02-26 12:21
 **/
@Configuration
@Slf4j
public class MybatisPlusConfig {
    @Resource
    MybatisProperties mybatisProperties;

    /**
     * 数据库主库
     * @return
     */
    @Bean
    @ConfigurationProperties("spring.datasource.dynamic.datasource.master")
    public DataSource masterDataSource(){
        log.info("======加载主数据源========");
        return DataSourceBuilder.create().build();
    }

    /**
     * 数据库从库
     * @return
     */
    @Bean
    @ConfigurationProperties("spring.datasource.dynamic.datasource.slave1")
    public DataSource slave1DataSource(){
         log.info("======加载从数据源slave1==============");
         return DataSourceBuilder.create().build();
    }

    /**
     * 动态数据源切换
     * @param masterDataSource
     * @param slave1DataSource
     * @return
     */
    @Bean
    @Primary
    public DataSource myRoutingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                          @Qualifier("slave1DataSource") DataSource slave1DataSource){
        log.info("加载[masterDataSource-slave1DataSource]设置为动态数据源DynamicDataSource.");
        Map<Object,Object> targetDataSource = new HashMap<>(2);
        targetDataSource.put(DBTypeEnum.MASTER, masterDataSource);
        targetDataSource.put(DBTypeEnum.SLAVE1, slave1DataSource);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        dynamicDataSource.setTargetDataSources(targetDataSource);
        return dynamicDataSource;
    }

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("masterDataSource") DataSource masterDataSource,
                                                              @Qualifier("slave1DataSource") DataSource slave1DataSource) throws IOException {
        log.info("自定义配置mybatis-plus的SqlSessionFactory.");
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        mybatisPlus.setDataSource(myRoutingDataSource(masterDataSource,slave1DataSource));

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        mybatisPlus.setConfiguration(configuration);
        if (StringUtils.hasLength(this.mybatisProperties.getTypeAliasesPackage())) {
            mybatisPlus.setTypeAliasesPackage(this.mybatisProperties.getTypeAliasesPackage());
        }
        if (StringUtils.hasLength(this.mybatisProperties.getTypeHandlersPackage())) {
            mybatisPlus.setTypeHandlersPackage(this.mybatisProperties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(this.mybatisProperties.resolveMapperLocations())) {
            mybatisPlus.setMapperLocations(this.mybatisProperties.resolveMapperLocations());
        }
        //// 设置mapper.xml文件的路径
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        org.springframework.core.io.Resource[] resource = resolver.getResources("classpath:mapper/*.xml");
        mybatisPlus.setMapperLocations(resource);
        return mybatisPlus;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("myRoutingDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
