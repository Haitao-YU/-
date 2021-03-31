package ltd.linqiu.service.impl;

import ltd.linqiu.entity.Line;
import ltd.linqiu.mapper.LineMapper;
import ltd.linqiu.mapper.TableMapper;
import ltd.linqiu.service.ILineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineService implements ILineService {
    @Autowired
    private LineMapper lineMapper;
    @Autowired
    private TableMapper tableMapper;

    @Override
    public List<Integer> getMealsNumberOption() {
        List<Integer> ret = tableMapper.selectDistinctSeats();
        if (ret == null || ret.size() == 0) {
            return null;
        } else {
            return ret;
        }
    }

    /**
     * 查看整个队列
     */
    @Override
    public List<Line> getAll() {
        return lineMapper.selectAll();
    }

    /**
     * 查看单个类型队列
     */
    @Override
    public List<Line> getListByMealsNumber(Integer mealsNumb) {
        return lineMapper.selectByMealsNumber(mealsNumb);
    }


    /**
     * 根据手机号获得自身位置
     */
    @Override
    public Line getByPhone(String phone) {
        return lineMapper.selectByPhone(phone);
    }

    /**
     * 入队
     */
    @Override
    public Line enqueue(Line in) {
        // 检查是否已经存在
        if (lineMapper.selectByPhone(in.getPhone()) == null) {
            // 获得自身顺序
            in.setSerialNumber(lineMapper.sizeByMealsNumber(in.getMealsNumber()) + 1);
            // 入队
            if (lineMapper.insert(in) == 1) {
                return in;
            } else {
                throw new RuntimeException("入队失败");
            }
        } else {
            return null;
        }
    }

    /**
     * 取消排队
     */
    @Override
    public Line cancelEnqueue(Line out) {
        // 检查是否存在
        Line old = lineMapper.selectByPhone(out.getPhone());
        if (old != null) {
            // 删除自身
            lineMapper.deleteByPhone(old);
            // 更新自身后面的队列元素
            List<Line> backLineList = lineMapper.selectBiggerThanSerialNumber(old);
            for (Line line : backLineList) {
                line.setSerialNumber(line.getSerialNumber() - 1);
                lineMapper.updateSerialNumberByPhone(line);
            }
        }
        return old;
    }


    /**
     * 出队
     */
    @Override
    public Line dequeue(Integer mealsNumber) {
        Line out = null;
        List<Line> lineList = lineMapper.selectByMealsNumber(mealsNumber);
        if (lineList.size() > 0) {
            // 获取要出队的元素
            out = lineList.get(0);
            out.setSerialNumber(0);
            // 删除出队元素
            if (lineMapper.deleteByPhone(out) != 1) {
                throw new RuntimeException("删除失败");
            }
            // 更新表中其他元素的序号，必须从小到大顺序将序号-1,否则违反unique约束
            for (int i = 1; i < lineList.size(); i++) {
                Line line = lineList.get(i);
                line.setSerialNumber(line.getSerialNumber() - 1);
                if (lineMapper.updateSerialNumberByPhone(line) != 1) {
                    throw new RuntimeException("更新失败");
                }
            }
        }
        return out;
    }

}
