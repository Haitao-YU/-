package ltd.linqiu.service.impl;

import ltd.linqiu.entity.Table;
import ltd.linqiu.mapper.StoreInfoMapper;
import ltd.linqiu.mapper.TableMapper;
import ltd.linqiu.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Integer switchState(Integer id, Integer state) {
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
    public Integer deleteById(Table table) {
        Table old = tableMapper.selectById(table.getId());
        if (old == null) {
            return 0;
        } else {
            return tableMapper.deleteById(table);
        }
    }
}
