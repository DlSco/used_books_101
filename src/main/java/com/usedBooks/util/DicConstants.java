package com.usedBooks.util;

import com.usedBooks.jpa.dao.DictionaryDao;
import com.usedBooks.jpa.dao.DictionaryItemDao;
import com.usedBooks.manager.dictionaryModule.pojo.Dictionary;
import com.usedBooks.manager.dictionaryModule.pojo.DictionaryItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class DicConstants {

    private final static Logger logger = LoggerFactory.getLogger(DicConstants.class);

    @Autowired
    private DictionaryDao dictionaryDao;
    @Autowired
    private DictionaryItemDao dictionaryItemDao;

    /** 字典数据映射表 */
    @SuppressWarnings("rawtypes")
    private Map<String, Map> dicMap = new HashMap<String, Map>();

    /**
     * 私有构造器
     */
    private DicConstants() {
    }

    /**
     * 初始化字典
     */
    @PostConstruct
    public void init() throws Exception {
        logger.info("--------init");
        DictionaryDao dictionaryDao = this.dictionaryDao;
        DictionaryItemDao dictionaryItemDao = this.dictionaryItemDao;
        List<Dictionary> list = dictionaryDao.findAll();
        for(Dictionary erpDictionary:list){
            //行业代码
            Map<String,Object> map = new HashMap<>();
            List<DictionaryItem> itemList = dictionaryItemDao.findAllByDictId(erpDictionary.getId());
            for(DictionaryItem erpDictionaryItem : itemList){
                map.put(erpDictionaryItem.getItemValue(),erpDictionaryItem.getItemName());
            }
            this.putDic(erpDictionary.getDictCode(), map);
        }

        logger.info("初始化字典map完成");
        logger.info("字典map:"+this.getDicMap());
    }

    /**
     * 根据字典编号得到字典内容映射表。
     *
     * @param dictCode 字典编号
     * @return 字典内容映射表
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getDic(String dictCode) {
        if (dicMap.containsKey(dictCode) == false) {
            return new HashMap();
        }
        Map map = new LinkedHashMap();
        map.putAll(dicMap.get(dictCode));
        return map;
    }

    /**
     * 获取hashmap里的字典项目名
     * @param dictCode    字典代码
     * @param itemValue   字典项目值
     * @return
     */
    public String getItemName(String dictCode,String itemValue) {
        if (dicMap.containsKey(dictCode) == false) {
            return null;
        }
        HashMap<String,Object> map = (HashMap<String, Object>) dicMap.get(dictCode);
        return (String)map.get(itemValue);
    }
    /**
     * 将加载的字典放入该工具类中。
     *
     * @param dictCode 字典编号
     * @param dataMap 字典数据列表
     */
    @SuppressWarnings("rawtypes")
    private synchronized void putDic(String dictCode, Map dataMap) {
        if (dicMap.containsKey(dictCode)) {
            dicMap.remove(dictCode);
        }
        dicMap.put(dictCode, dataMap);
    }

    public Map<String, Map> getDicMap() {
        return dicMap;
    }

    public void setDicMap(Map<String, Map> dicMap) {
        this.dicMap = dicMap;
    }
}
