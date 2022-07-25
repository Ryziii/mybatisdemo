package com.rysiw.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rysiw.demo.dao.UserTestMapper;
import com.rysiw.demo.entity.MongoTestEntity;
import com.rysiw.demo.entity.UserTestEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author WuRuiZhi
 * @date 2022/3/31 16:18
 */
@Service
public class MongoTestService {

    @Resource
    private MongoTemplate mongoTemplate;

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            // 此处判断可根据需求修改
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public void add(MongoTestEntity mongoTest){
        Query query2 = Query.query(Criteria.where("idNum").is(mongoTest.getIdNum()).and("associatedRecordId").is(mongoTest.getAssociatedRecordId()));
        MongoTestEntity mongoTestEntity = mongoTemplate.findOne(query2, MongoTestEntity.class, "recordResult");
        if(Objects.isNull(mongoTestEntity))
            mongoTestEntity = new MongoTestEntity();
        BeanUtils.copyProperties(mongoTest, mongoTestEntity, getNullPropertyNames(mongoTest));
        mongoTemplate.save(mongoTestEntity,"recordResult");
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
