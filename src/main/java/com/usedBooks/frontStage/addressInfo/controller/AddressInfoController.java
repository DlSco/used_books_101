package com.usedBooks.frontStage.addressInfo.controller;


import com.usedBooks.frontStage.addressInfo.pojo.AddressInfo;
import com.usedBooks.frontStage.addressInfo.service.AddressInfoService;
import com.usedBooks.result.CodeMsg;
import com.usedBooks.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
@RequestMapping("/address")
public class AddressInfoController{

    @Autowired
    private AddressInfoService addressInfoService;

    /**
     *
     * @param addressInfo   实体
     * @return       结果集
     */
    @RequestMapping(value="/addAddress",method = RequestMethod.POST)
    public Result addAddress(AddressInfo addressInfo){
        if(addressInfoService.saveAddressInfo(addressInfo)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"添加收货地址失败"));
    }

    @RequestMapping(value = "/delete/{id}")
    public Result deleteAddress(@PathVariable Integer id){
        if(addressInfoService.deleteAddress(id)>0){
            return Result.success(null);
        }
        return Result.error(new CodeMsg(0,"删除失败"));
    }
    @RequestMapping(value = "/update")
    public Result updateAddress(AddressInfo addressInfo){
        if(addressInfoService.updateAddress(addressInfo)>0){
            return Result.success("");
        }
        return Result.error(new CodeMsg(0,"修改失败"));
    }

    @RequestMapping(value = "/getAddress")
    public Result getAddress(AddressInfo addressInfo){

        return Result.success(addressInfoService.getAddressByUser(addressInfo));
    }
}