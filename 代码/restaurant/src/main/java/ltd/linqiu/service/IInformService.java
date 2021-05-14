package ltd.linqiu.service;

import ltd.linqiu.entity.Inform;

import java.util.List;

public interface IInformService {
    List<Inform> getAll();

    Integer check(String tableId);
}
