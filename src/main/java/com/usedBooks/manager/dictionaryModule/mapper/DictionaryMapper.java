 /*
 * @Project Name:TODO 
 * @File Name:DictionaryMapper
 * @Package Name:com.usedBooks.common
 * @Date:2019年03月07日 21:05
 * @Creator:Vakoe
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.usedBooks.manager.dictionaryModule.mapper;

import com.usedBooks.manager.dictionaryModule.pojo.Dictionary;
import com.usedBooks.mybatis.common.BaseMapper;

import java.util.List;
import java.util.Map;

 public interface DictionaryMapper extends BaseMapper<Dictionary> {

     List<Dictionary> toList(Map<String, Object> map);
 }