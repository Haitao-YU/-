package ltd.linqiu.service.impl;

import ltd.linqiu.entity.Table;
import ltd.linqiu.mapper.StoreInfoMapper;
import ltd.linqiu.mapper.TableMapper;
import ltd.linqiu.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableService implements ITableService {
    @Autowired
    private TableMapper tableMapper;
    @Autowired
    private StoreInfoMapper storeInfoMapper;

    @Override
    public List<Table> getAll() {
        return tableMapper.selectAll();
    }


    @Override
    public Table getById(String id) {
        return tableMapper.selectById(id);
    }

    @Override
    public Integer add(Table table) {
        Table old = tableMapper.selectById(table.getId());
        if (old != null) {
            return 0;
        } else {
            table.setState(0);
            if (table.getImage() == null || table.getImage().length() == 0) {
                table.setImage(storeInfoMapper.selectByName("餐桌默认图片").get(0).getValue());
            }
            return tableMapper.insert(table);
        }
    }

    @Override
    public Integer modifyById(Table table) {
        Table old = tableMapper.selectById(table.getId());
        if (old == null) {
            return 0;
        } else {
            if (table.getImage() == null || table.getImage().length() == 0) {
                table.setImage(storeInfoMapper.selectByName("餐桌默认图片").get(0).getValue());
            }
            return tableMapper.updateById(table);
        }
    }

    @Override
    public Integer switchState(String id, Integer state) {
        Table old = tableMapper.selectById(id);

        if (old == null) {
            return 0;
        } else {
            return tableMapper.updateStateById(id, state);
        }
    }

    @Override
    public boolean enableAll() {
        try {
            tableMapper.updateAllStateByState(2, 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Integer> stateCount() {
        List<Integer> ret = new ArrayList<>();
        Integer countFree = tableMapper.countByState(0);
        Integer countBusy = tableMapper.countByState(1);
        Integer countDisable = tableMapper.countByState(2);
        ret.add(countFree);
        ret.add(countBusy);
        ret.add(countFree + countBusy + countDisable);
        ret.add(countFree + countBusy);
        ret.add(countDisable);
        return ret;
    }

    @Override
    public Integer deleteById(Table table) {
        Table old = tableMapper.selectById(table.getId());
        if (old == null) {
            return 0;
        } else {
            return tableMapper.deleteById(table);
        }
    }

    @Override
    public List<String> getAllPosition() {
        List<String> ret = tableMapper.selectDistinctPosition();
        if (ret.size() > 0) {
            return ret;
        } else {
            return null;
        }
    }

    @Override
    public List<Integer> getAllSeats() {
        List<Integer> ret = tableMapper.selectDistinctSeats();
        if (ret.size() > 0) {
            return ret;
        } else {
            return null;
        }
    }
}
