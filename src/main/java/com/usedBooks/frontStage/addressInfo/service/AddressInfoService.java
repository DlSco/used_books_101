package com.usedBooks.frontStage.addressInfo.service;

import com.usedBooks.frontStage.addressInfo.pojo.AddressInfo;
import com.usedBooks.frontStage.addressInfo.vo.AddressVo;
import java.util.List;

public interface AddressInfoService {

  int saveAddressInfo(AddressInfo addressInfo);

  int updateAddress(AddressInfo addressInfo);

  int deleteAddress(Integer id);

  List<AddressVo> getAddressByUser(AddressInfo addressInfo);

}
