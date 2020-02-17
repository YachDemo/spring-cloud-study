package com.study.controller;

import com.study.model.Item;
import com.study.repository.ItemRepository;
import com.study.utils.ChineseToPinYinUtil;
import com.study.utils.Result;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.DisMaxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author YanCh
 * Create by 2020-02-17 14:55
 **/
@RestController
public class TestController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    RestHighLevelClient client;

    //索引库名称
    private static final String INDEX = "item";
//    //文档类型
//    private static final String TYPE = "docs";



    @RequestMapping("insert")
    public String insert(){
        Item item = new Item(1L, "小米手机6", "手机", "小米", 2299.00, "https://img12.360buyimg.com/n1/s450x450_jfs/t1/14081/40/4987/124705/5c371b20E53786645/c1f49cd69e6c7e6a.jpg");
        Item item1 = new Item(7L, "小米手機6", "手机", "小米", 2299.00, "https://img12.360buyimg.com/n1/s450x450_jfs/t1/14081/40/4987/124705/5c371b20E53786645/c1f49cd69e6c7e6a.jpg");
        itemRepository.save(item);
        itemRepository.save(item1);
        return "success";
    }

    @RequestMapping("insertList")
    public Result insertList(){
        List<Item> list = new ArrayList<>();
        list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3999.00, "https://img12.360buyimg.com/n1/s450x450_jfs/t1/14081/40/4987/124705/5c371b20E53786645/c1f49cd69e6c7e6a.jpg"));
        list.add(new Item(3L, "华为META20", "手机", "华为", 4999.00, "https://img12.360buyimg.com/n1/s450x450_jfs/t1/14081/40/4987/124705/5c371b20E53786645/c1f49cd69e6c7e6a.jpg"));
        list.add(new Item(4L, "iPhone X", "手机", "iPhone", 5100.00, "https://img12.360buyimg.com/n1/s450x450_jfs/t1/14081/40/4987/124705/5c371b20E53786645/c1f49cd69e6c7e6a.jpg"));
        list.add(new Item(5L, "iPhone XS", "手机", "iPhone", 5999.00, "https://img12.360buyimg.com/n1/s450x450_jfs/t1/14081/40/4987/124705/5c371b20E53786645/c1f49cd69e6c7e6a.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
        return Result.success(list);
    }

    @RequestMapping("deleteAll")
    public String deleteAll(){
        itemRepository.deleteAll();
        return "success";
    }

    /**
     * 普通查询
     * @return
     */
    @RequestMapping("query")
    public Result query(){
        // 根据价格区间查询
        List<Item> list = itemRepository.findByPriceBetween(5000.00, 6000.00);
        list.forEach(item -> System.out.println("item = " + item));
        return Result.success(list);
    }

    @RequestMapping("queryAll")
    public Result queryAll(){
        Iterable<Item> list = itemRepository.findAll();
        return Result.success(list);
    }

    @RequestMapping("search")
    public Result search(){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "小米"));
        // 搜索，获取结果
        Page<Item> items = itemRepository.search(queryBuilder.build());
        // 总条数
        long total = items.getTotalElements();
        System.out.println("total = " + total);
        items.forEach(item -> System.out.println("item = " + item));
        return Result.success(items.getContent());
    }

    @RequestMapping("searchByPage")
    public Result searchByPage(){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("category", "手机"));
        // 分页：
        int page = 0;
        int size = 2;
        queryBuilder.withPageable(PageRequest.of(page, size));
        // 搜索，获取结果
        Page<Item> items = itemRepository.search(queryBuilder.build());
        long total = items.getTotalElements();
        System.out.println("总条数 = " + total);
        System.out.println("总页数 = " + items.getTotalPages());
        System.out.println("当前页：" + items.getNumber());
        System.out.println("每页大小：" + items.getSize());
        items.forEach(item -> System.out.println("item = " + item));
        return Result.success(items);
    }

    /**
     * 排序
     */
    @RequestMapping("searchAndSort")
    public Result searchAndSort() {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("category", "手机"));
        // 排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        // 搜索，获取结果
        Page<Item> items = this.itemRepository.search(queryBuilder.build());
        // 总条数
        long total = items.getTotalElements();
        System.out.println("总条数 = " + total);
        items.forEach(item -> System.out.println("item = " + item));
        return Result.success(items);
    }

    @RequestMapping("searchPy")
    public Result searchPy(@RequestParam String content){

        // 这个sourcebuilder就类似于查询语句中最外层的部分。包括查询分页的起始，
        // 查询语句的核心，查询结果的排序，查询结果截取部分返回等一系列配置
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 结果开始处
        sourceBuilder.from((1-1)*10);
        // 查询结果终止处
        sourceBuilder.size(10);
        // 查询的等待时间
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //执行查询
        sourceBuilder.query(chineseAndPinYinSearch(content));
        System.out.println(sourceBuilder);
//        指定索引库和类型
        SearchRequest searchRequest = new SearchRequest(INDEX);
//        searchRequest.types(TYPE);
        searchRequest.source(sourceBuilder);
        try {
            return Result.success(client.search(searchRequest));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


        //使用中文拼音混合搜索，取分数最高的，具体评分规则可参照：
        //  https://blog.csdn.net/paditang/article/details/79098830
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(chineseAndPinYinSearch(content))
//                .build();
//        Page<Item> list = itemRepository.search(searchQuery);
//        Pageable pageable = new PageRequest(0,10);
//        List<Item> lists = new ArrayList<>();
//        Iterable<Item> list = itemRepository.search(chineseAndPinYinSearch(content),pageable);
//        list.forEach(lists::add);

//        return Result.success(list.getContent());
    }

    /**
     * 中文、拼音混合搜索
     *
     * @param content the content
     * @return dis max query builder
     */
    public DisMaxQueryBuilder structureQuery(String content) {
        //使用dis_max直接取多个query中，分数最高的那一个query的分数即可
        DisMaxQueryBuilder disMaxQueryBuilder = QueryBuilders.disMaxQuery();
        //boost 设置权重,只搜索匹配name和disrector字段
        QueryBuilder ikNameQuery = QueryBuilders.matchQuery("title", content).boost(2f);
        QueryBuilder pinyinNameQuery = QueryBuilders.matchQuery("title.pinyin", content);
        QueryBuilder multiMatch = QueryBuilders.wildcardQuery("title","*"+content+"*");
//        QueryBuilder ikDirectorQuery = QueryBuilders.matchQuery("director", content).boost(2f);
        disMaxQueryBuilder.add(ikNameQuery);
        disMaxQueryBuilder.add(pinyinNameQuery);
        disMaxQueryBuilder.add(multiMatch);
//        disMaxQueryBuilder.add(ikDirectorQuery);
        return disMaxQueryBuilder;
    }


//    @RequestMapping("searchAdvanced")
    private QueryBuilder chineseAndPinYinSearch(String words){

        //使用dis_max直接取多个query中，分数最高的那一个query的分数即可
        DisMaxQueryBuilder disMaxQueryBuilder=QueryBuilders.disMaxQuery();

        /**
         * 纯中文搜索，不做拼音转换,采用edge_ngram分词(优先级最高)
         * 权重* 5
         */
        QueryBuilder normSearchBuilder=QueryBuilders.matchQuery("title.ngram",words).analyzer("ngramSearchAnalyzer").boost(5f);

        /**
         * 拼音简写搜索
         * 1、分析key，转换为简写  case:  南京东路==>njdl，南京dl==>njdl，njdl==>njdl
         * 2、搜索匹配，必须完整匹配简写词干
         * 3、如果有中文前缀，则排序优先
         * 权重*1
         */
        String firstChar = ChineseToPinYinUtil.ToFirstChar(words);
        TermQueryBuilder pingYinSampleQueryBuilder = QueryBuilders.termQuery("title.SPY", firstChar);

        /**
         * 拼音简写包含匹配，如 njdl可以查出 "城市公牛 南京东路店"，虽然非南京东路开头
         * 权重*0.8
         */
        QueryBuilder  pingYinSampleContainQueryBuilder=null;
        if(firstChar.length()>1){
            pingYinSampleContainQueryBuilder=QueryBuilders.wildcardQuery("title.SPY", "*"+firstChar+"*").boost(0.8f);
        }

        /**
         * 拼音全拼搜索
         * 1、分析key，获取拼音词干   case :  南京东路==>[nan,jing,dong,lu]，南京donglu==>[nan,jing,dong,lu]
         * 2、搜索查询，必须匹配所有拼音词，如南京东路，则nan,jing,dong,lu四个词干必须完全匹配
         * 3、如果有中文前缀，则排序优先
         * 权重*1
         */
        QueryBuilder pingYinFullQueryBuilder=null;
        if(words.length()>1){
            pingYinFullQueryBuilder=QueryBuilders.matchPhraseQuery("title.FPY", words).analyzer("pinyiFullSearchAnalyzer");
        }

        /**
         * 完整包含关键字查询(优先级最低，只有以上四种方式查询无结果时才考虑）
         * 权重*0.8
         */
        QueryBuilder containSearchBuilder=QueryBuilders.matchQuery("title", words).analyzer("ikSearchAnalyzer").minimumShouldMatch("100%");

        disMaxQueryBuilder
                .add(normSearchBuilder)
                .add(pingYinSampleQueryBuilder)
                .add(containSearchBuilder);

        //以下两个对性能有一定的影响，故作此判定，单个字符不执行此类搜索
        if(pingYinFullQueryBuilder!=null){
            disMaxQueryBuilder.add(pingYinFullQueryBuilder);
        }
        if(pingYinSampleContainQueryBuilder!=null){
            disMaxQueryBuilder.add(pingYinSampleContainQueryBuilder);
        }

        return disMaxQueryBuilder;
    }

}
