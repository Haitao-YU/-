package ltd.linqiu.service;

import ltd.linqiu.entity.Line;
import ltd.linqiu.front.LineInfo;

import java.util.List;

public interface ILineService {
    List<Integer> getMealsNumberOption();

    List<Line> getAll();

    List<Line> getListByMealsNumber(Integer mealsNumber);

    Line getByPhone(String phone);

    Line enqueue(Line in);

    Line cancelEnqueue(Line in);

    Line dequeue(Integer mealsNumber);

    LineInfo getInfosByMealsNumberPhone(Integer mealsNumber, String phone);
}
