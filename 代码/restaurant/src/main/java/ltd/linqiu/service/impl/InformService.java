package ltd.linqiu.service.impl;

import ltd.linqiu.entity.Inform;
import ltd.linqiu.mapper.InformMapper;
import ltd.linqiu.service.IInformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InformService implements IInformService {
    @Autowired
    private InformMapper informMapper;

    @Override
    public List<Inform> getAll() {
        return informMapper.selectAll();
    }

    @Override
    public Integer check(String tableId) {
        return informMapper.deleteByTableId(tableId);
    }
}
