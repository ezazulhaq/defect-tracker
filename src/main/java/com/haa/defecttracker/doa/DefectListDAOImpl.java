package com.haa.defecttracker.doa;

import java.util.List;
import java.util.Optional;

import com.haa.defecttracker.entity.DefectList;
import com.haa.defecttracker.repo.DefectListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DefectListDAOImpl implements DefectListDAO {

    @Autowired
    private DefectListRepository defectListRepository;

    @Override
    public List<DefectList> getDefectList() {
        return defectListRepository.findAll();
    }

    @Override
    public void saveDefect(DefectList defect) {
        defectListRepository.save(defect);
    }

    @Override
    public Optional<DefectList> fetchDefect(Long id) {
        return defectListRepository.findById(id);
    }

    @Override
    public void removeDefect(Long id) {
        defectListRepository.deleteById(id);
    }

}
