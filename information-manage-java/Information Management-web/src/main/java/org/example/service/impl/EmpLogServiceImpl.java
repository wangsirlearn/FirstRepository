package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.mapper.EmpLogMapper;
import org.example.popj.EmpLog;
import org.example.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class EmpLogServiceImpl implements EmpLogService {



    @Autowired
    private EmpLogMapper empLogMapper;
    /**
     * 保存员工日志信息
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void save(EmpLog empLog) {
        empLogMapper.save(empLog);
    }
}
