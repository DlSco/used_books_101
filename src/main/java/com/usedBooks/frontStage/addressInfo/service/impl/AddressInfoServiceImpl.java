package com.usedBooks.frontStage.addressInfo.service.impl;


import com.usedBooks.frontStage.addressInfo.mapper.AddressInfoMapper;
import com.usedBooks.frontStage.addressInfo.pojo.AddressInfo;
import com.usedBooks.frontStage.addressInfo.service.AddressInfoService;

import com.usedBooks.frontStage.addressInfo.vo.AddressVo;
import com.usedBooks.util.DicConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AddressInfoServiceImpl implements AddressInfoService {


    @Autowired
    private AddressInfoMapper addressInfoMapper;

    @Autowired
    private DicConstants dicConstants;

    @Override
    public int saveAddressInfo(AddressInfo addressInfo) {
        return addressInfoMapper.insert(addressInfo);
    }

    @Override
    public int updateAddress(AddressInfo addressInfo) {
        return addressInfoMapper.updateByPrimaryKey(addressInfo);
    }

    @Override
    public int deleteAddress(Integer id) {
        return addressInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<AddressVo> getAddressByUser(AddressInfo addressInfo) {

        List<AddressInfo> addressInfos = addressInfoMapper.select(addressInfo);
        List<AddressVo> voList = new ArrayList<>();
        System.out.println(addressInfos.size()+"id:"+addressInfos.get(0).getId());
        for (AddressInfo address  :  addressInfos) {
            AddressVo vo = new AddressVo();
            vo.setProvinceName(dicConstants.getItemName("province",address.getProvince()+""));
            vo.setProvince(address.getProvince());
            vo.setAddressDetail(address.getAddressDetail());
            vo.setId(address.getId());
            vo.setReceiver(address.getReceiver());
            vo.setReceivingPhone(address.getReceivingPhone());
            vo.setUserId(address.getUserId());
            voList.add(vo);
        }
        return voList;
    }
}
