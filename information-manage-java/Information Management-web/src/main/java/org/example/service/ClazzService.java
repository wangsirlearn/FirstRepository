package org.example.service;

import org.example.popj.Clazz;
import org.example.popj.ClazzPageResult;
import org.example.popj.ClazzQuery;

import java.util.List;

public interface ClazzService {
    ClazzPageResult findPage(ClazzQuery clazzQuery);

    void deleteClazz(int id);

    void addClazz(Clazz clazz);

    Clazz findById(Integer id);

    void updateClazz(Clazz clazz);

    List<Clazz> findAll();
}
