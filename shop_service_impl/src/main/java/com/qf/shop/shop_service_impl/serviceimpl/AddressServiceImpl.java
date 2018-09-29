package com.qf.shop.shop_service_impl.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.entity.Address;
import com.qf.service.IAddressService;
import com.qf.shop.dao.IAddressDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private IAddressDao addressDao;

    @Override
    public List<Address> queryByUid(Integer uid) {
        return addressDao.queryByUid(uid);
    }

    @Override
    public Address addAddress(Address address) {
        addressDao.addAddress(address);
        return address;
    }
}
