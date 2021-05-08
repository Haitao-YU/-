package ltd.linqiu.service.impl;

import com.alibaba.fastjson.JSON;
import ltd.linqiu.entity.Remark;
import ltd.linqiu.mapper.OrderMapper;
import ltd.linqiu.mapper.RemarkMapper;
import ltd.linqiu.service.IRemarkService;
import ltd.linqiu.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class RemarkService implements IRemarkService {
    @Autowired
    private RemarkMapper remarkMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Remark> getAll() {
        return remarkMapper.selectAll();
    }

    @Override
    public Remark getById(Integer id) {
        return remarkMapper.selectById(id);
    }

    @Override
    public Remark getByOrderId(Integer orderId) {
        return remarkMapper.selectByOrderId(orderId);
    }

    @Override
    @Transactional
    public Integer add(Remark remark) {
        if (remarkMapper.insert(remark) == 1) {
            // 更新订单状态
            orderMapper.updateStateById(2, remark.getOrderId());
            return 1;
        }
        return 0;
    }


    @Override
    public synchronized Boolean uploadImage(Integer id, MultipartFile file) {
        String url = FileUtil.save(file, "imagesOfRemarks");
        if (url == null) {
            return false;
        }
        Remark old = remarkMapper.selectById(id);
        String oldImagesString = old.getImages();
        List<String> imagesList = null;
        if (oldImagesString == null || oldImagesString.length() <= 0) {
            imagesList = new ArrayList<>();
        } else {
            imagesList = JSON.parseArray(oldImagesString, String.class);
        }
        imagesList.add("http://www.linqiu.ltd/file/" + url);
        String newImagesString = JSON.toJSONString(imagesList);
        return remarkMapper.updateImagesById(newImagesString, id) == 1;
    }

    @Override
    @Transactional
    public Integer delete(Remark remark) {
        return remarkMapper.delete(remark);
    }


    @Override
    @Transactional
    public Integer reply(String reply, Integer id) {
        return remarkMapper.updateReplyById(reply, id);
    }
}
