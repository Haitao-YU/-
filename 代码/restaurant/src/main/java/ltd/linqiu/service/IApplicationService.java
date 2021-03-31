package ltd.linqiu.service;

import ltd.linqiu.entity.Application;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IApplicationService {
    List<Application> getAll();

    Application getById(Integer id);

    List<Application> getByStuId(String stuId);

    Integer add(Application application);

    Integer modifyState(Application application);

    Integer delete(Application application);

    Boolean evidentMaterial(Integer id, MultipartFile file);
}
