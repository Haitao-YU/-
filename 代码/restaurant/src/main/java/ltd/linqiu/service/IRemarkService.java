package ltd.linqiu.service;

import ltd.linqiu.entity.Remark;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRemarkService {
    List<Remark> getAll();

    Remark getById(Integer id);

    Remark getByOrderId(Integer orderId);

    Integer add(Remark remark);

    Boolean uploadImage(Integer id, MultipartFile file);

    Integer delete(Remark remark);

    Integer reply(String reply, Integer id);
}
