package org.example.popj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// 实体类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private int id;
    private String username;
    private String password;
    private String name;
    private int gender;
    private String phone;
    private int job;
    private int salary;
    private String image;
    private LocalDate entryDate;
    private int deptId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private String deptName;
    // 员工对应的expr
    private List<EmpExpr> empExprs;

}
