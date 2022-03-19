package com.haa.defecttracker.service;

import java.util.List;
import java.util.Optional;

import com.haa.defecttracker.entity.DefectList;

public interface DefectListService {

    List<DefectList> getDefectList();

    void saveDefect(DefectList defect);

    Optional<DefectList> fetchDefect(Long id);

    void removeDefect(Long id);

}
