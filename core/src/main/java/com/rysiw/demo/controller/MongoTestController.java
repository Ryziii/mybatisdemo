package com.rysiw.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rysiw.demo.entity.MongoTestEntity;
import com.rysiw.demo.service.MongoTestService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author WuRuiZhi
 * @date 2022/4/7 18:43
 */
@RestController
@RequestMapping("mongotest")
public class MongoTestController {

    @Resource
    private MongoTestService mongoTestService;

    @PostMapping("/add")
    public boolean addStory(@RequestParam JSONArray jsonAr){
        List<JSONObject> jsonObjectList = jsonAr.toJavaList(JSONObject.class);
        for (JSONObject json : jsonObjectList){
            String idNum = json.get("idNum").toString();
            String associatedRecordId = json.get("associatedRecordId").toString();
            MongoTestEntity mongoTestEntity = new MongoTestEntity();
            mongoTestEntity.setAssociatedRecordId(associatedRecordId);
            mongoTestEntity.setIdNum(idNum);
            JSONArray jsonArray = new JSONArray();
            mongoTestEntity.setEntCaseInfo(jsonArray);
            mongoTestEntity.setPunishBreak(jsonArray);
            mongoTestEntity.setPunished(jsonArray);
            mongoTestEntity.setRyposfr(jsonArray);
            mongoTestEntity.setRyposper(jsonArray);
            mongoTestEntity.setShareholder(jsonArray);
            mongoTestEntity.setFinalCase(jsonArray);
            mongoTestEntity.setLimitConsum(jsonArray);
            this.mongoTestService.add(mongoTestEntity);
        }
        return true;
    }
    @GetMapping("/get/stories")
    public String getStories(){
        return this.mongoTestService.findAll().toString();
    }
    @GetMapping("/get/story/id")
    public String getStoryById(String id){
        return this.mongoTestService.findStoryById(id).toString();
    }
    @GetMapping("/get/story/content")
    public String getContent(String content){
        return this.mongoTestService.findContent(content).toString();
    }
}
