package ltd.linqiu.service.impl;

import ltd.linqiu.entity.StoreInfo;
import ltd.linqiu.mapper.StoreInfoMapper;
import ltd.linqiu.service.IStoreInfoService;
import ltd.linqiu.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    public StoreInfo onOff() {
        List<StoreInfo> before = storeInfoMapper.selectByName("状态");
        if (before.size() > 0) {
            StoreInfo state = before.get(0);
            if (state.getValue().equals("营业中")) {
                state.setValue("已打烊");
            } else {
                state.setValue("营业中");
            }
            storeInfoMapper.update(state);
            return state;
        } else {
            return null;
        }

    }

    @Override
    public Integer defaultFoodImage(MultipartFile file) {
        String url = FileUtil.save(file, "storeInfo");
        if (url == null) {
            return 400;
        }
        if (storeInfoMapper.update(new StoreInfo("餐品默认图片", "http://www.linqiu.ltd/file/" + url)) == 0) {
            return 401;
        }
        return 0;
    }

    @Override
    public Integer defaultTableImage(MultipartFile file) {
        String url = FileUtil.save(file, "storeInfo");
        if (url == null) {
            return 400;
        }
        if (storeInfoMapper.update(new StoreInfo("餐桌默认图片", "http://www.linqiu.ltd/file/" + url)) == 0) {
            return 401;
        }
        return 0;
    }
}
