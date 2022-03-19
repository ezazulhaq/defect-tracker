package com.haa.defecttracker.repo;

import com.haa.defecttracker.entity.DefectList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefectListRepository extends JpaRepository<DefectList, Long> {

}
