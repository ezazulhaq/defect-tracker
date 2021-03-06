package com.haa.defecttracker.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.haa.defecttracker.entity.DefectList;
import com.haa.defecttracker.service.DefectListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @Autowired
    private DefectListService defectListService;

    @GetMapping(value = "/")
    public String getDefectList(Model theModel) {

        List<DefectList> dList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean authorized = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"));

        if (authorized)
            dList = defectListService.getDefectList();
        else
            dList = defectListService.getDefectList()
                    .stream()
                    .filter(i -> i.getAssignedTo().equalsIgnoreCase(auth.getName()))
                    .collect(Collectors.toList());

        theModel.addAttribute("defectList", dList);

        return "home";
    }

}
