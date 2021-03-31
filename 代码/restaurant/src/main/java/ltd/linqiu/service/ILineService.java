package ltd.linqiu.service;

import ltd.linqiu.entity.Line;

import java.util.List;

public interface ILineService {
    List<Integer> getMealsNumberOption();

    List<Line> getAll();

    List<Line> getListByMealsNumber(Integer mealsNumb);

    Line getByPhone(String phone);

    Line enqueue(Line in);

    Line cancelEnqueue(Line in);

    Line dequeue(Integer mealsNumber);


}
