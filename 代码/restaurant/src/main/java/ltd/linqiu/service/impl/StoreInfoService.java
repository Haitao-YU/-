package ltd.linqiu.service.impl;

import ltd.linqiu.entity.StoreInfo;
import ltd.linqiu.mapper.StoreInfoMapper;
import ltd.linqiu.service.IStoreInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreInfoService implements IStoreInfoService {

    @Autowired
    private StoreInfoMapper storeInfoMapper;


    @Override
    public List<StoreInfo> getAll() {
        return storeInfoMapper.selectAll();
    }


    @Override
    public StoreInfo getById(Integer id) {
        return storeInfoMapper.selectById(id);
    }

    @Override
    public StoreInfo getByName(String name) {
        List<StoreInfo> storeInfoList = storeInfoMapper.selectByName(name);
        if (storeInfoList.size() == 1) {
            return storeInfoList.get(0);
        }
        return null;
    }

    @Override
    public Integer add(StoreInfo storeInfo) {
        return storeInfoMapper.insert(storeInfo);
    }

    @Override
    public Integer modify(StoreInfo storeInfo) {
        return storeInfoMapper.update(storeInfo);
    }
}
