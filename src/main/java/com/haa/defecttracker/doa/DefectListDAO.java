package com.haa.defecttracker.doa;

import java.util.List;
import java.util.Optional;

import com.haa.defecttracker.entity.DefectList;

public interface DefectListDAO {

    List<DefectList> getDefectList();

    void saveDefect(DefectList defect);

    Optional<DefectList> fetchDefect(Long id);

    void removeDefect(Long id);

}
