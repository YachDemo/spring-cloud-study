//package com.example.shardjdbcdemo;
//
//import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
//import com.baomidou.mybatisplus.core.MybatisConfiguration;
//import com.baomidou.mybatisplus.core.config.GlobalConfig;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.type.JdbcType;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//import org.springframework.util.ObjectUtils;
//import org.springframework.util.StringUtils;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//
///**
// *
// * @author YanCh
// * @version v1.0
// * Create by 2020-03-31 15:10
// **/
//@Configuration
//public class MybatisPlusConfig {
//    @Resource
//    MybatisPlusProperties mybatisProperties;
//
//    @Bean
//    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
//        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
//        MybatisConfiguration configuration = new MybatisConfiguration();
//        mybatisPlus.setDataSource(myRoutingDataSource(masterDataSource,slave1DataSource));
//        configuration.setJdbcTypeForNull(JdbcType.NULL);
//        configuration.setMapUnderscoreToCamelCase(false);
//        configuration.setCacheEnabled(false);
//        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
//        GlobalConfig globalConfig = new GlobalConfig();
//        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
//        dbConfig.setTableUnderline(false);
////        dbConfig.setTableUnderline(false);
////        dbConfig.setCapitalMode(false);
//        globalConfig.setDbConfig(dbConfig);
//        mybatisPlus.setGlobalConfig(globalConfig);
//        mybatisPlus.setConfiguration(configuration);
//
//        Interceptor[] plugins = new Interceptor[1];
//        mybatisPlus.setPlugins(plugins);
//
//        if (StringUtils.hasLength(this.mybatisProperties.getTypeAliasesPackage())) {
//            mybatisPlus.setTypeAliasesPackage(this.mybatisProperties.getTypeAliasesPackage());
//        }
//        if (StringUtils.hasLength(this.mybatisProperties.getTypeHandlersPackage())) {
//            mybatisPlus.setTypeHandlersPackage(this.mybatisProperties.getTypeHandlersPackage());
//        }
//        if (!ObjectUtils.isEmpty(this.mybatisProperties.resolveMapperLocations())) {
//            mybatisPlus.setMapperLocations(this.mybatisProperties.resolveMapperLocations());
//        }
//        //// 设置mapper.xml文件的路径
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        org.springframework.core.io.Resource[] resource = resolver.getResources("classpath:mapper/*.xml");
//        mybatisPlus.setMapperLocations(resource);
//        return mybatisPlus;
//    }
//}
