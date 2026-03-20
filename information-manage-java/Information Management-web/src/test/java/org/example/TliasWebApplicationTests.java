package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.mapper.DeptMapper;
import org.example.popj.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@Slf4j
@SpringBootTest
class TliasWebApplicationTests {

    @Autowired
    private DeptMapper deptMapper;
    @Test
    public void findAllTest() {
        UUID name = UUID.randomUUID();
        log.debug(String.valueOf(name));
    }


}
