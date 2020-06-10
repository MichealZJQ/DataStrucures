package org.example.annotation;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService {

    @Transactional
    @Metrics //启用方法监控
    public void createUser(String uname) {
        log.info("创建用户");
        if ("test".equals(uname)) throw new RuntimeException("invalid username!");
    }
}