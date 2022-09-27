package com.rysiw.demo;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.StopWatch;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rysiw.demo.entity.UserEntity;
import com.rysiw.demo.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SpringBootTest
public class StringTest {

    @Autowired
    private UserService userService;
    private static final byte[] key = {-51, 49, -80, -73, -94, 80, -23, 55, -110, -54, -89, 88, 58, 83, 30, 113};


    @Test
    public void test5(){
        final String INTERACT_PATH_DOWNLOAD = "/Users/rysiw/Downloads/未命名文件夹/";
        final String INTERACT_PATH_UPLOAD = "/Users/rysiw/Downloads/未命名文件夹/";
        try{
            delete(INTERACT_PATH_DOWNLOAD);
            delete(INTERACT_PATH_UPLOAD);
            System.out.println("[定时删除系统交互管理文件]成功结束");
        }catch (Exception e){
            System.out.println("[定时删除系统交互管理文件]异常");
        }
    }

    public void delete(String path) throws Exception{
        try {
            if (FileUtil.isDirectory(path)) {
                for (File file : FileUtil.loopFiles(path)) {
                    if (file.isFile() && FileUtil.extName(file).equals("xlsx")) {
                        long millTime = FileUtil.getAttributes(file.toPath(), false).creationTime().toMillis();
                        Date fileCreateTime = DateUtil.date(millTime);
                        Date nowTime = DateUtil.date();
                        long betweenDays = DateUtil.betweenDay(fileCreateTime, nowTime, false);
                        if (betweenDays > 30) {
                            FileUtil.del(file);
                        }
                    }
                }
            }
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test
    public void deleteOrder(){
        Date nowDate = DateUtil.date();
        Date oneMonthDate = DateUtil.offsetDay(nowDate, -30);
        LambdaQueryWrapper<UserEntity> queryWrapper = new QueryWrapper<UserEntity>().lambda()
                .lt(UserEntity::getCreateTime, oneMonthDate);

        List<UserEntity> list = userService.list(queryWrapper);
        userService.removeByIds(list.stream().map(UserEntity::getId).collect(Collectors.toList()));
        System.out.println(list);
    }

    @Test
    public void test() {

        List<UserEntity1> list = new ArrayList<>();
        UserEntity1 u1 = new UserEntity1();
        u1.setUsername("11");
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("11","a");
        jsonArray.add(jsonObject);
        u1.setJsonArray(jsonArray);
        list.add(u1);
        for (int i = 0; i < 5; i++) {
            UserEntity1 uu = new UserEntity1();
            JSONArray jsonArray2 = new JSONArray();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("11","b");
            jsonArray2.add(jsonObject1);
            uu.setUsername("11");
            uu.setJsonArray(jsonArray2);
            list.add(uu);
        }
        UserEntity1 u2 = new UserEntity1();
        u2.setUsername("22");
        JSONArray jsonArray2 = new JSONArray();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("22","a");
        jsonArray2.add(jsonObject2);
        u2.setJsonArray(jsonArray2);
        list.add(u2);
        for (int i = 0; i < 5; i++) {
            UserEntity1 uu = new UserEntity1();
            JSONArray jsonArray3 = new JSONArray();
            JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("22","b");
            jsonArray3.add(jsonObject3);
            uu.setUsername("22");
            uu.setJsonArray(jsonArray3);
            list.add(uu);
        }

        long time1 = System.currentTimeMillis();

        list.stream().filter(o->o.getUsername().equals("11")).forEach(oo->{
                JSONArray jsona = new JSONArray();
                oo.setJsonArray(jsona);
        });
        list.forEach(o->{
            if(o.getUsername().equals("22")){
                JSONArray jsona = new JSONArray();
                o.setJsonArray(jsona);
            }
        });
//        ArrayList<UserEntity1> list1 = new ArrayList<>(list);
//        for (int i = 0; i < list.size(); i++) {
//            for (int j = 0; j < list1.size(); j++) {
//                if(list.get(i).equals(list1.get(j)))
//                    continue;
//                if (list.get(i).getUsername().equals(list1.get(j).getUsername())) {
//                    JSONArray jsonAr = list.get(i).getJsonArray();
//                    jsonAr.addAll(list1.get(j).getJsonArray());
//                    list.get(i).setJsonArray(jsonAr);
//                    list.remove(list1.get(j));
//                }
//            }
//        }

        System.out.println((System.currentTimeMillis() - time1));
        System.out.println("123");
    }

    @Test
    public void test2() {
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();

        JSONObject jo1 = new JSONObject();
        jo1.put("1","1");
        jsonArray1.add(jo1);

        JSONObject jo2 = new JSONObject();
        jo2.put("1","1");
        jsonArray2.add(jo2);

        jsonArray1.addAll(jsonArray2);

        jsonArray1 = JSONArray.parseArray(JSONObject.toJSONString(jsonArray1.stream().distinct().collect(Collectors.toList())));

        System.out.println(jsonArray1.equals(jsonArray2));
    }

    @Test
    public void test3(){
        //随机生成密钥
        byte[] key = {-51, 49, -80, -73, -94, 80, -23, 55, -110, -54, -89, 88, 58, 83, 30, 113};
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);


        String str = aes.decryptStr("c55585438344bb521e1505c2a62589a8", CharsetUtil.CHARSET_UTF_8);
        System.out.println(str);
    }

    @Test
    public void test4(){
        UserEntity1 user1 = new UserEntity1();
        UserEntity1 user2 = new UserEntity1();
        user1.setUsername("123");
        user1.setId(1L);
        user2.setUsername("123");
        user2.setId(2L);
        UserEntity1 user3 = new UserEntity1();
        user3.setUsername("123");
        user3.setId(3L);
        UserEntity1 user4 = new UserEntity1();
        user4.setUsername("123");
        user4.setId(4L);
        UserEntity1 user5 = new UserEntity1();
        user5.setUsername("123");
        user5.setId(5L);

        List<UserEntity1> list = new ArrayList<>();
        int count = 20000000;
        Long sum = 0L;
        while(count-- != 0){
            UserEntity1 user = new UserEntity1();
            user.setUsername("123");
            user.setId((long) count);
            list.add(user);
            sum += count;
        }
        System.out.println(sum);
        StopWatch sw = new StopWatch();
        sw.start();
        exec2(CollUtil.newArrayList(list));
        sw.stop();
        sw.start();
        exec1(CollUtil.newArrayList(list));
        sw.stop();

        System.out.println(sw.prettyPrint(TimeUnit.MILLISECONDS));
        System.out.println(1);
    }

    public List<UserEntity1> exec1(List<UserEntity1> list){
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
        List<UserEntity1> result = new ArrayList<>();

        for (UserEntity1 u : list) {
            if (!map.containsKey(u.getUsername())) {
                result.add(u);
                map.put(u.getUsername(), true);
            } else {
                result = result.stream().map(o -> {
                    UserEntity1 user = new UserEntity1();
                    user.setUsername(o.getUsername());
                    user.setId(o.getId());
                    if (o.getUsername().equals(u.getUsername())){
                        user.setId(o.getId() + u.getId());
                    }
                    return user;

                }).collect(Collectors.toList());
            }
        }
        return result;
    }

    public List<UserEntity1> exec2(List<UserEntity1> list){
        HashMap<String, Long> map = new HashMap<String, Long>();
        List<UserEntity1> result = new ArrayList<>();

        for (UserEntity1 u : list) {
            if (!map.containsKey(u.getUsername())) {
                result.add(u);
                map.put(u.getUsername(), u.getId());
            } else {
                map.put(u.getUsername(), map.get(u.getUsername()) + u.getId());
            }
        }
        result = result.stream().filter(new Predicate<UserEntity1>() {
            final Set<UserEntity1> set = new HashSet<>();
            public boolean test(UserEntity1 userEntity1) {
                if(!set.contains(userEntity1)){
                    set.add(userEntity1);
                    return true;
                }
                return false;
            }
        }).collect(Collectors.toList());
        result = result.stream().map(o->{
            UserEntity1 user = new UserEntity1();
            for(String u : map.keySet()) {
                user.setUsername(o.getUsername());
                user.setId(map.get(u));
            }
            return user;
        }).collect(Collectors.toList());

        return result;
    }

    @Test
    public void test6(){
        System.out.println(encryptId(""));
//        System.out.println(deSensitive("13123232323", 3,4));
    }

    public static String deSensitive(String personId, int pre, int post) {
        if (StrUtil.isBlank(personId)) {
            return personId;
        }
        StringBuilder sb = new StringBuilder();
        if (personId.length() <= pre + post) {
            for (char c : personId.toCharArray()) {
                sb.append("*");
            }
        } else {
            int i = 0;
            for (; i < personId.length() - post; i++) {
                if (i < pre) {
                    sb.append(personId.charAt(i));
                } else {
                    sb.append("*");
                }
            }
            for (; i < personId.length(); i++) {
                sb.append(personId.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String encryptId(String id){
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        return aes.encryptHex(id);
    }
}
