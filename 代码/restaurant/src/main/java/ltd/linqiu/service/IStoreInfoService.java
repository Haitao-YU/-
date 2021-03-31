package ltd.linqiu.service;

import ltd.linqiu.entity.StoreInfo;

import java.util.List;

public interface IStoreInfoService {

    List<StoreInfo> getAll();

    StoreInfo getById(Integer id);

    StoreInfo getByName(String name);

    StoreInfo onOff();

    Integer add(StoreInfo storeInfo);

    Integer modify(StoreInfo storeInfo);


}
