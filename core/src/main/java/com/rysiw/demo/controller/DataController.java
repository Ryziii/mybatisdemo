package com.rysiw.demo.controller;

import cn.hutool.db.Db;
import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.simple.SimpleDataSource;
import cn.hutool.db.handler.EntityListHandler;
import cn.hutool.db.sql.SqlExecutor;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.jdbc.Driver;
import com.rysiw.demo.common.utils.DBUtils;
import com.rysiw.demo.common.vo.ResultVO;
import com.rysiw.demo.exception.DefineException;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Rysiw
 * @date 2022/1/18 22:38
 */
@RestController
@RequestMapping("/data")
public class DataController {

    private static final String driverName = "com.mysql.cj.jdbc.Driver";

    @RequestMapping(value = "/migrate", method = RequestMethod.POST)
    public ResultVO<Object> migrate(@RequestBody String query){
        JSONObject jsonObject = JSONObject.parseObject(query);
        JSONObject source = (JSONObject) jsonObject.get("source");
        JSONObject target = (JSONObject) jsonObject.get("target");

        String sourceUsername = String.valueOf(source.get("username"));
        String sourcePassword = String.valueOf(source.get("password"));
        String sourceUrl = String.valueOf(source.get("url"));

        String targetUsername = String.valueOf(target.get("username"));
        String targetPassword = String.valueOf(target.get("password"));
        String targetUrl = String.valueOf(target.get("url"));

        String table = String.valueOf(jsonObject.get("table"));

        //TODO 支持不同的数据库类型迁移，oracle 等
        DataSource sourceDb = new SimpleDataSource(sourceUrl, sourceUsername, sourcePassword);
        DataSource targetDb = new SimpleDataSource(targetUrl, targetUsername, targetPassword);


        List<Entity> list = new ArrayList<>();
        try {
            Db sourceD = Db.use(sourceDb);
            Db targetD = Db.use(targetDb);
            list = sourceD.findAll(table);
            targetD.execute("truncate table " + table);
            targetD.insert(list);

        } catch (Exception e) {
            e.printStackTrace();
            throw new DefineException(e);
        }finally {
            DbUtil.close();
        }

        return ResultVO.getSuccessVO();
    }
}
