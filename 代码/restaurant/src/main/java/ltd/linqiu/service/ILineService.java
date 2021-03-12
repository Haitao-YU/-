package ltd.linqiu.service;

import ltd.linqiu.entity.Line;

import java.util.List;

public interface ILineService {

    List<Line> getAll();

    Integer getSerialNumberByPhone(String phone);

    Line enqueue(Line in);

    Line cancelEnqueue(Line in);

    Line dequeue();
}
