package com.mssql;
import java.util.ArrayList;
import java.util.Date;

import com.mssql.mapper.KqInfoMapper;
import com.mssql.model.KqInfo;
import com.xiaoleilu.hutool.date.DateUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@SpringBootApplication(scanBasePackages = {"com"})
@MapperScan(basePackages = {"com.mssql.mapper"})
@RestController
public class MssqlDemoApplication {
    @Autowired
    KqInfoMapper kqInfoMapper;
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public static void main(String[] args) {
        SpringApplication.run(MssqlDemoApplication.class, args);
    }

    @RequestMapping("add")
    public String add(){
//        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
//        KqInfoMapper userMapper = sqlSession.getMapper(KqInfoMapper.class);
        for (int i = 0; i < 70000; i++) {
            KqInfo kqInfo = new KqInfo();
            kqInfo.setKqId(createSalt());
            kqInfo.setKqMoney("50.000");
            kqInfo.setKqPsw("");
            kqInfo.setKqYxq(DateUtil.parse("2028-08-18 18:18:18.000"));
            kqInfo.setState("未核销");
            kqInfo.setFqMallName("新花城總部");
            kqInfo.setFqCkName("默認總部門店");
            kqInfo.setHxMallName("");
            kqInfo.setHxCkName("");
            kqInfo.setHxMoney(null);
            kqInfo.setHxPerson("");
            kqInfo.setHxTime(null);
            kqInfo.setCreatTime(new Date());
            kqInfo.setCreatPerson("曾廣文");
            kqInfo.setChgTime(new Date());
            kqInfo.setChgPerson("曾廣文");
            kqInfo.setBzMemo("50實體的通用券");
            kqInfo.setHxMemo("");
            kqInfo.setKqName("50實體的通用券");
            kqInfo.setHxDhId("");
            kqInfo.setUseTj("全部通用");
            kqInfo.setXfMe(".0000");
            kqInfoMapper.insert(kqInfo);
        }
//        sqlSession.commit();
        return "dsadas";
    }

    public String createSalt() {
        int maxNum = 36;
        int i;
        int count = 0;
        char[] str = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuilder code = new StringBuilder();
        Random r = new Random();
        while (count < 9) {
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                code.append(str[i]);
                count++;
            }
        }
        if (kqInfoMapper.selectByPrimaryKey(code.toString()) != null) {
            createSalt();
        }
        return code.toString();
    }
}
