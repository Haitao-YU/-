package ltd.linqiu.service.impl;

import com.alibaba.fastjson.JSON;
import ltd.linqiu.entity.Application;
import ltd.linqiu.mapper.ApplicationMapper;
import ltd.linqiu.service.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ApplicationService implements IApplicationService {
    @Autowired
    private ApplicationMapper applicationMapper;


    @Override
    public List<Application> getAll() {
        return applicationMapper.selectAll();
    }

    @Override
    public Application getById(Integer id) {
        return applicationMapper.selectById(id);
    }

    @Override
    public List<Application> getByStuId(String stuId) {
        return applicationMapper.selectByStuId(stuId);
    }

    @Override
    @Transactional
    public Integer add(Application application) {
        if (applicationMapper.insert(application) > 0) {
            return applicationMapper.getMaxId();
        }
        return 0;
    }

    @Override
    public Integer delete(Application application) {
        return applicationMapper.delete(application);
    }

    @Override
    public Integer modifyState(Application application) {
        return applicationMapper.updateStateById(application);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Boolean evidentMaterial(Integer id, MultipartFile file) {
        //上传文件路径
        String path = new ApplicationHome(getClass()).getSource().getParent() + "\\upload\\" + new SimpleDateFormat(
                "yyyy-MM-dd").format(new Date());
        //上传文件名
        String filename = file.getOriginalFilename();
        if (filename == null) {
           return false;
        }
        File filePath = new File(path, filename);
        //判断路径是否存在，如果不存在就创建一个
        if (!filePath.getParentFile().exists()) {
            if (!filePath.getParentFile().mkdirs()) {
                return false;
            }
        }
        String fullPath = path + File.separator + filename;
        synchronized (this) {
            Application application = applicationMapper.selectById(id);
            String stringImages = application.getEvidentMaterials();
            Set<String> setImages;
            if (stringImages == null || stringImages.equals("")) {
                setImages = new HashSet<>();
            } else {
                setImages = JSON.parseObject(stringImages, HashSet.class);
            }
            setImages.add(fullPath);
            System.out.println(setImages);
            stringImages = JSON.toJSONString(setImages);
            System.out.println("size " + setImages.size() + ": " + stringImages);
            applicationMapper.updateEvidentMaterialsById(id, stringImages);
        }
        //将上传文件保存到目标文件当中
        try {
            file.transferTo(new File(fullPath));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
