package com.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.WritePolicy;

/**
 * @author:
 * @date: created in 16:05 2020/8/4
 * @version:
 */
public class AerospikeTest {
    private Logger logger = LoggerFactory.getLogger(AerospikeTest.class);

    AerospikeClient aerospikeClient = new AerospikeClient("xxxx", 3000);
    WritePolicy writePolicy = new WritePolicy();
    // 新建一个key，namespace为test，set为mySet，根据文档这个myKey并不是这个key的名字
    Key key = new Key("test", "mySet", "myKey");

    /**
     * As writing value. 向同一个key中写入两个不同的bin，logger输出里面的record 如果bin的名字一样，那么前一个会被后一个所覆盖，所以写入和修改都是同一个方法
     */
    @Test
    public void ASWritingValue() {
        Bin bin = new Bin("myBin", "myValue");
        Bin bin2 = new Bin("myBin2", "myValue2");
        aerospikeClient.put(null, key, bin);
        aerospikeClient.put(null, key, bin2);
        logger.info("" + aerospikeClient.get(null, key));
    }

    /**
     * As del bin. 删除key中的名为“mybin”的bin
     */
    @Test
    public void ASDelBin() {
        Bin bin = Bin.asNull("myBin");
        aerospikeClient.put(null, key, bin);
        logger.info("" + aerospikeClient.get(null, key));
    }

    /**
     * Get record. 根据key和申明的bin的名字来获取指定的record
     */
    @Test
    public void getRecord() {
        // 得到所有record
        Record recordAll = aerospikeClient.get(null, key);
        Record record1 = aerospikeClient.get(null, key, "myBin");
        Record record2 = aerospikeClient.get(null, key, "myBin2");
        Record records = aerospikeClient.get(null, key, "myBin", "myBin2");
        logger.info("" + recordAll);
        logger.info(record1.toString());
        logger.info(record2.toString());
        logger.info(records.toString());
    }

    /**
     * Del records. 删除records，官网的解释是这样的：This example deletes the key mykey from the namespace test in the set myset。
     */
    @Test
    public void delRecords() {
        aerospikeClient.delete(null, key);
    }

    /** 测试写AS */
    @Test
    public void testSave() {
        // policy 是用来定义策略，AS中定义多种策略实现(比如超时，过期机制，事务等)，也可设置为null
        for (int i = 100; i < 110; i++) {
            // Key第一个参数ns,第二个参数set,第三个参数用来唯一标识的PK
            Key key = new Key("testMemory", "demo", "zhangsan" + i);
            // bin可以很多个，多到参考我们受限个数
            Bin nameBin = new Bin("name", "张三" + i);
            Bin passBin = new Bin("pass", "123456");
            // expire这是一个伪的过期时间
            Bin expireBin = new Bin("expire", System.currentTimeMillis() + 1 * 60 * 60 * 1000L);
            // 这才是真的过期策略，小于105的100秒后会消失，可以通过命令查看
            if (i < 105) {
                writePolicy.setTimeout(50);
                writePolicy.expiration = 100; // 重要的事情只说一遍，单位(秒)
            } else {
                writePolicy.expiration = -1; // 不过期
            }
            aerospikeClient.put(writePolicy, key, nameBin, passBin, expireBin);
        }
    }

    /** 测试读AS,而且是批量读 */
    @Test
    public void testQuery() {
        String[] bin_names = {"name", "pass"}; // 查询字段
        Key[] keys = new Key[10]; // 查询key
        int index = 0;
        for (int i = 100; i < 110; i++) {
            keys[index++] = new Key("testMemory", "demo", "zhangsan" + i);
        }
        Record[] recordArr = aerospikeClient.get(null, keys, bin_names); // 批量查询
        // client.get(null, keys); 最后一个参数不传时，将把所有的bins全查出来
        for (Record r : recordArr) {
            if (r != null) {
                System.out.println(">>>name:" + r.getString("name") + "---pass:" + r.getString("pass"));
            }
        }
    }

}