package com.example.demo.controller;

import com.example.demo.entity.BaseElasticEntity;
import com.example.demo.entity.Item;
import com.example.demo.service.BaseElasticService;
import com.example.demo.utils.ChineseToPinYinUtil;
import com.example.demo.utils.Result;
import org.elasticsearch.index.query.DisMaxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author YanCh
 * Create by 2020-03-05 10:24
 **/
@RestController
public class EsDemoController {
    @Autowired
    BaseElasticService<Item> baseElasticService;

    @RequestMapping("/addIndex")
    public String addIndex(){
        baseElasticService.createIndex("item","{\n" +
                "  \"properties\": {\n" +
                "    \"id\": {\n" +
                "      \"type\": \"integer\"\n" +
                "    },\n" +
                "    \"title\": {\n" +
                "      \"type\": \"text\",\n" +
                "      \"analyzer\": \"ikIndexAnalyzer\",\n" +
                "      \"fields\": {\n" +
                "        \"ngram\": {\n" +
                "          \"type\": \"text\",\n" +
                "          \"analyzer\": \"ngramIndexAnalyzer\"\n" +
                "        },\n" +
                "        \"SPY\": {\n" +
                "          \"type\": \"text\",\n" +
                "          \"analyzer\": \"pinyiSimpleIndexAnalyzer\"\n" +
                "        },\n" +
                "        \"FPY\": {\n" +
                "          \"type\": \"text\",\n" +
                "          \"analyzer\": \"pinyiFullIndexAnalyzer\"\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"category\": {\n" +
                "      \"type\": \"text\"\n" +
                "    },\n" +
                "    \"brand\": {\n" +
                "      \"type\": \"text\"\n" +
                "    },\n" +
                "    \"price\": {\n" +
                "      \"type\": \"double\"\n" +
                "    },\n" +
                "    \"images\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    }\n" +
                "  }\n" +
                "}","{\n" +
                "  \"number_of_shards\": \"5\",\n" +
                "  \"number_of_replicas\": \"2\",\n" +
                "  \"analysis\": {\n" +
                "    \"filter\": {\n" +
                "      \"edge_ngram_filter\": {\n" +
                "        \"type\": \"edge_ngram\",\n" +
                "        \"min_gram\": 1,\n" +
                "        \"max_gram\": 50\n" +
                "      },\n" +
                "      \"pinyin_simple_filter\": {\n" +
                "        \"type\": \"pinyin\",\n" +
                "        \"keep_first_letter\": true,\n" +
                "        \"keep_separate_first_letter\": false,\n" +
                "        \"keep_full_pinyin\": false,\n" +
                "        \"keep_original\": false,\n" +
                "        \"limit_first_letter_length\": 50,\n" +
                "        \"lowercase\": true\n" +
                "      },\n" +
                "      \"pinyin_full_filter\": {\n" +
                "        \"type\": \"pinyin\",\n" +
                "        \"keep_first_letter\": false,\n" +
                "        \"keep_separate_first_letter\": false,\n" +
                "        \"keep_full_pinyin\": true,\n" +
                "        \"none_chinese_pinyin_tokenize\": true,\n" +
                "        \"keep_original\": false,\n" +
                "        \"limit_first_letter_length\": 50,\n" +
                "        \"lowercase\": true\n" +
                "      }\n" +
                "    },\n" +
                "    \"char_filter\": {\n" +
                "      \"charconvert\": {\n" +
                "        \"type\": \"mapping\",\n" +
                "        \"mappings\": [\n" +
                "          \"à => a\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    \"tokenizer\": {\n" +
                "      \"ik_max_word\": {\n" +
                "        \"type\": \"ik_max_word\",\n" +
                "        \"use_smart\": true\n" +
                "      }\n" +
                "    },\n" +
                "    \"analyzer\": {\n" +
                "      \"ngramIndexAnalyzer\": {\n" +
                "        \"type\": \"custom\",\n" +
                "        \"tokenizer\": \"keyword\",\n" +
                "        \"filter\": [\n" +
                "          \"edge_ngram_filter\",\n" +
                "          \"lowercase\"\n" +
                "        ],\n" +
                "        \"char_filter\": [\n" +
                "          \"charconvert\"\n" +
                "        ]\n" +
                "      },\n" +
                "      \"ngramSearchAnalyzer\": {\n" +
                "        \"type\": \"custom\",\n" +
                "        \"tokenizer\": \"keyword\",\n" +
                "        \"filter\": [\n" +
                "          \"lowercase\"\n" +
                "        ],\n" +
                "        \"char_filter\": [\n" +
                "          \"charconvert\"\n" +
                "        ]\n" +
                "      },\n" +
                "      \"ikIndexAnalyzer\": {\n" +
                "        \"type\": \"custom\",\n" +
                "        \"tokenizer\": \"ik_max_word\",\n" +
                "        \"char_filter\": [\n" +
                "          \"charconvert\"\n" +
                "        ]\n" +
                "      },\n" +
                "      \"ikSearchAnalyzer\": {\n" +
                "        \"type\": \"custom\",\n" +
                "        \"tokenizer\": \"ik_max_word\",\n" +
                "        \"char_filter\": [\n" +
                "          \"charconvert\"\n" +
                "        ]\n" +
                "      },\n" +
                "      \"pinyiSimpleIndexAnalyzer\": {\n" +
                "        \"tokenizer\": \"keyword\",\n" +
                "        \"filter\": [\n" +
                "          \"pinyin_simple_filter\",\n" +
                "          \"edge_ngram_filter\",\n" +
                "          \"lowercase\"\n" +
                "        ]\n" +
                "      },\n" +
                "      \"pinyiSimpleSearchAnalyzer\": {\n" +
                "        \"tokenizer\": \"keyword\",\n" +
                "        \"filter\": [\n" +
                "          \"pinyin_simple_filter\",\n" +
                "          \"lowercase\"\n" +
                "        ]\n" +
                "      },\n" +
                "      \"pinyiFullIndexAnalyzer\": {\n" +
                "        \"tokenizer\": \"keyword\",\n" +
                "        \"filter\": [\n" +
                "          \"pinyin_full_filter\",\n" +
                "          \"lowercase\"\n" +
                "        ]\n" +
                "      },\n" +
                "      \"pinyiFullSearchAnalyzer\": {\n" +
                "        \"tokenizer\": \"keyword\",\n" +
                "        \"filter\": [\n" +
                "          \"pinyin_full_filter\",\n" +
                "          \"lowercase\"\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}");
        return "操作成功！";
    }

    @RequestMapping("/insertBatch")
    public String insertBatch(){
        List<Item> items = new ArrayList<>();
        Item item = new Item("坚果手机R1", "手机", "锤子", 3999.00, "https://img12.360buyimg.com/n1/s450x450_jfs/t1/14081/40/4987/124705/5c371b20E53786645/c1f49cd69e6c7e6a.jpg");
        item.setId("1");
        items.add(item);
        item = new Item("华为META20", "手机", "华为", 4999.00, "https://img12.360buyimg.com/n1/s450x450_jfs/t1/14081/40/4987/124705/5c371b20E53786645/c1f49cd69e6c7e6a.jpg");
        item.setId("2");
        items.add(item);
        item = new Item("iPhone X", "手机", "iPhone", 5100.00, "https://img12.360buyimg.com/n1/s450x450_jfs/t1/14081/40/4987/124705/5c371b20E53786645/c1f49cd69e6c7e6a.jpg");
        item.setId("3");
        items.add(item);
        item = new Item( "iPhone XS", "手机", "iPhone", 5999.00, "https://img12.360buyimg.com/n1/s450x450_jfs/t1/14081/40/4987/124705/5c371b20E53786645/c1f49cd69e6c7e6a.jpg");
        item.setId("4");
        items.add(item);
        baseElasticService.insertBatch("item",items);
        return "操作成功";
    }

    @RequestMapping("searchPy")
    public Result searchPy(@RequestParam String content){

        // 这个sourcebuilder就类似于查询语句中最外层的部分。包括查询分页的起始，
        // 查询语句的核心，查询结果的排序，查询结果截取部分返回等一系列配置
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        // 结果开始处
        sourceBuilder.from((1-1)*10);
//        // 查询结果终止处
        sourceBuilder.size(1);
//        // 查询的等待时间
//        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
//        //执行查询
        sourceBuilder.query(chineseAndPinYinSearch(content));
        List<Item> list = baseElasticService.search("item",sourceBuilder,Item.class);
        try {
            return Result.success(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private QueryBuilder chineseAndPinYinSearch(String words){

        //使用dis_max直接取多个query中，分数最高的那一个query的分数即可
        DisMaxQueryBuilder disMaxQueryBuilder = QueryBuilders.disMaxQuery();

        /**
         * 纯中文搜索，不做拼音转换,采用edge_ngram分词(优先级最高)
         * 权重* 5
         */
        QueryBuilder normSearchBuilder = QueryBuilders.matchQuery("title.ngram",words).analyzer("ngramSearchAnalyzer").boost(5f);

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
