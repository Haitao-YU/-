package ltd.linqiu.service.impl;

import ltd.linqiu.entity.Line;
import ltd.linqiu.front.LineInfo;
import ltd.linqiu.mapper.LineMapper;
import ltd.linqiu.mapper.TableMapper;
import ltd.linqiu.service.ILineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LineService implements ILineService {
    private static volatile boolean lock = false;


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

    @Override
    public List<Line> getAll() {
        return lineMapper.selectAll();
    }


    @Override
    public List<Line> getListByMealsNumber(Integer mealsNumber) {
        return lineMapper.selectByMealsNumber(mealsNumber);
    }

    @Override
    public Line getByPhone(String phone) {
        return lineMapper.selectByPhone(phone);
    }

    @Override
    public Line enqueue(Line in) {
        while (lock) {
            Thread.yield();
        }
        lock = true;
        // 检查是否已经存在
        if (lineMapper.selectByPhone(in.getPhone()) == null) {
            // 获得自身顺序
            in.setSerialNumber(lineMapper.sizeByMealsNumber(in.getMealsNumber()) + 1);
            // 入队
            if (lineMapper.insert(in) == 1) {
                lock = false;
                return in;
            } else {
                lock = false;
                throw new RuntimeException("入队失败");
            }
        } else {
            lock = false;
            return null;
        }
    }

    @Override
    public Line cancelEnqueue(Line out) {
        while (lock) {
            Thread.yield();
        }
        lock = true;
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
        lock = false;
        return old;
    }

    @Override
    public Line dequeue(Integer mealsNumber) {
        while (lock) {
            Thread.yield();
        }
        lock = true;
        Line out = null;
        List<Line> lineList = lineMapper.selectByMealsNumber(mealsNumber);
        if (lineList.size() > 0) {
            // 获取要出队的元素
            out = lineList.get(0);
            out.setSerialNumber(0);
            // 删除出队元素
            if (lineMapper.deleteByPhone(out) != 1) {
                lock = false;
                throw new RuntimeException("删除失败");
            }
            // 更新表中其他元素的序号，必须从小到大顺序将序号-1,否则违反unique约束
            for (int i = 1; i < lineList.size(); i++) {
                Line line = lineList.get(i);
                line.setSerialNumber(line.getSerialNumber() - 1);
                if (lineMapper.updateSerialNumberByPhone(line) != 1) {
                    lock = false;
                    throw new RuntimeException("更新失败");
                }
            }
        }
        lock = false;
        return out;
    }

    @Override
    public LineInfo getInfosByMealsNumberPhone(Integer mealsNumber, String phone) {
        LineInfo ret = new LineInfo();
        ret.setNOfFreeTables(tableMapper.countByStateSeats(0, mealsNumber));
        ret.setNOfLine(lineMapper.sizeByMealsNumber(mealsNumber));
        Line old = lineMapper.selectByPhone(phone);
        if (old != null && old.getMealsNumber().equals(mealsNumber)) {
            ret.setMyPosition(old.getSerialNumber());
        }
        return ret;
    }
}
