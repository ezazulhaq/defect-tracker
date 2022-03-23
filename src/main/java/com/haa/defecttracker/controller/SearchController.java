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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    private DefectListService defectListService;

    @GetMapping(value = "/searchId")
    public String getBySearchId(@RequestParam(value = "search") String search, Model theModel) {

        if (search.equalsIgnoreCase(""))
            search = "0";

        List<DefectList> dList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean authorized = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"));

        if (authorized)
            dList = defectListService.getDefectList(Long.valueOf(search));
        else
            dList = defectListService.getDefectList(Long.valueOf(search))
                    .stream()
                    .filter(i -> i.getAssignedTo().equalsIgnoreCase(auth.getName()))
                    .collect(Collectors.toList());

        theModel.addAttribute("defectList", dList);

        return "home";
    }

    @GetMapping(value = "/sortByDefectId")
    public String sortByDefectId(Model theModel) {

        List<DefectList> dList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean authorized = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"));

        if (authorized)
            dList = defectListService.getDefectList()
                    .stream()
                    .sorted((i, j) -> i.getBugId().compareTo(j.getBugId()))
                    .collect(Collectors.toList());
        else
            dList = defectListService.getDefectList()
                    .stream()
                    .filter(i -> i.getAssignedTo().equalsIgnoreCase(auth.getName()))
                    .sorted((i, j) -> i.getBugId().compareTo(j.getBugId()))
                    .collect(Collectors.toList());

        theModel.addAttribute("defectList", dList);

        return "home";
    }

    @GetMapping(value = "/sortByAssignedTo")
    public String sortByAssignedTo(Model theModel) {

        List<DefectList> dList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean authorized = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"));

        if (authorized)
            dList = defectListService.getDefectList()
                    .stream()
                    .sorted((i, j) -> i.getAssignedTo().compareTo(j.getAssignedTo()))
                    .collect(Collectors.toList());
        else
            dList = defectListService.getDefectList()
                    .stream()
                    .filter(i -> i.getAssignedTo().equalsIgnoreCase(auth.getName()))
                    .sorted((i, j) -> i.getAssignedTo().compareTo(j.getAssignedTo()))
                    .collect(Collectors.toList());

        theModel.addAttribute("defectList", dList);

        return "home";
    }

    @GetMapping(value = "/sortByProject")
    public String sortByProject(Model theModel) {

        List<DefectList> dList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean authorized = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"));

        if (authorized)
            dList = defectListService.getDefectList()
                    .stream()
                    .sorted((i, j) -> i.getProject().compareTo(j.getProject()))
                    .collect(Collectors.toList());
        else
            dList = defectListService.getDefectList()
                    .stream()
                    .filter(i -> i.getAssignedTo().equalsIgnoreCase(auth.getName()))
                    .sorted((i, j) -> i.getProject().compareTo(j.getProject()))
                    .collect(Collectors.toList());

        theModel.addAttribute("defectList", dList);

        return "home";
    }

    @GetMapping(value = "/sortByStatus")
    public String sortByStatus(Model theModel) {

        List<DefectList> dList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean authorized = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"));

        if (authorized)
            dList = defectListService.getDefectList()
                    .stream()
                    .sorted((i, j) -> i.getStatus().compareTo(j.getStatus()))
                    .collect(Collectors.toList());
        else
            dList = defectListService.getDefectList()
                    .stream()
                    .filter(i -> i.getAssignedTo().equalsIgnoreCase(auth.getName()))
                    .sorted((i, j) -> i.getStatus().compareTo(j.getStatus()))
                    .collect(Collectors.toList());

        theModel.addAttribute("defectList", dList);

        return "home";
    }

}
