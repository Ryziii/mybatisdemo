package com.rysiw.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rysiw.demo.dao.UserTestMapper;
import com.rysiw.demo.entity.MongoTestEntity;
import com.rysiw.demo.entity.UserTestEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author WuRuiZhi
 * @date 2022/3/31 16:18
 */
@Service
public class MongoTestService {

    @Resource
    private MongoTemplate mongoTemplate;

    public void add(MongoTestEntity mongoTest){
        mongoTemplate.save(mongoTest);
    }

    public List<MongoTestEntity> findAll(){
        return mongoTemplate.findAll(MongoTestEntity.class);
    }

    public List<MongoTestEntity> findStoryById(String id){
        Query query = new Query();
        // 多个条件则重复类似以下语句
        query.addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.find(query,MongoTestEntity.class,"test");
    }

    public List<MongoTestEntity> findContent(String content){
        Pattern pattern = Pattern.compile(".*"+content+".*");
        Query query = new Query(Criteria.where("content").regex(pattern));
        return mongoTemplate.find(query,MongoTestEntity.class,"story");
    }

}
