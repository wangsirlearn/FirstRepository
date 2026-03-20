package org.example.popj;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpExpr {
    private int id;
    private int empId;
    private LocalDate begin;
    private LocalDate end;
    private String company;
    private String job;
}
