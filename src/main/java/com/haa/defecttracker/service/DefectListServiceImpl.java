package com.haa.defecttracker.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.haa.defecttracker.doa.DefectListDAO;
import com.haa.defecttracker.entity.DefectList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefectListServiceImpl implements DefectListService {

    @Autowired
    private DefectListDAO defectListDAO;

    @Override
    @Transactional
    public List<DefectList> getDefectList() {
        return defectListDAO.getDefectList();
    }

    @Override
    @Transactional
    public void saveDefect(DefectList defect) {
        defectListDAO.saveDefect(defect);
    }

    @Override
    @Transactional
    public Optional<DefectList> fetchDefect(Long id) {
        return defectListDAO.fetchDefect(id);
    }

    @Override
    @Transactional
    public void removeDefect(Long id) {
        defectListDAO.removeDefect(id);
    }

    @Override
    public List<DefectList> getDefectList(Long defectId) {
        return defectListDAO.getDefectList(defectId);
    }

}
