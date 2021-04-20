package ltd.linqiu.service;

import ltd.linqiu.entity.Table;

import java.util.List;

public interface ITableService {
    List<Table> getAll();

    Table getById(String id);

    Integer add(Table table);

    Integer switchState(String id, Integer state);

    Integer modifyById(Table table);

    Integer deleteById(Table table);

    boolean enableAll();

    List<Integer> stateCount();

    List<String> getAllPosition();

    List<Integer> getAllSeats();
}
