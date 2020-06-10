package org.example.annotation;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController //自动进行监控
@RequestMapping("/metricstest")
public class MetricsController {
    @Autowired
    private UserService userService;

    @GetMapping("transaction")
    public int transaction(@RequestParam("name") String name) {
        try {
            userService.createUser(name);
        } catch (Exception ex) {
            log.error("create user failed because {}", ex.getMessage());
        }
        return 1;
    }
}
