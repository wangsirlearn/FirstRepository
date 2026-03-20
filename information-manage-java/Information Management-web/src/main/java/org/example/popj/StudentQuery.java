package org.example.popj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentQuery {
    private String name;
    private Integer degree;
    private Integer classId;
    private Integer page=1;
    private Integer pageSize=10;
}
