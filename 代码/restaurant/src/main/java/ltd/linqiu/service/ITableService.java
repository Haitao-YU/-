package ltd.linqiu.service;

import ltd.linqiu.entity.Table;

import java.util.List;

public interface ITableService {
    List<Table> getAll();

    Integer add(Table table);

    Integer switchState(Integer id, Integer state);

    Integer modifyById(Table table);

    Integer deleteById(Table table);

    boolean enableAll();
}
