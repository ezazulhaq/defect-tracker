package com.haa.defecttracker.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.haa.defecttracker.entity.DefectList;
import com.haa.defecttracker.service.DefectListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DefectController {

    @Autowired
    private DefectListService defectListService;

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        DefectList theDefect = new DefectList();

        theModel.addAttribute("defect", theDefect);

        List<String> pList = new ArrayList<>();
        theModel.addAttribute("projectList", projectList(pList));

        List<String> aList = new ArrayList<>();
        theModel.addAttribute("assignedToList", assignedToList(aList));

        List<String> sList = new ArrayList<>();
        theModel.addAttribute("statusList", statusList(sList));

        return "defect-form";
    }

    @PostMapping("/saveDefect")
    public String saveDefect(@ModelAttribute("defect") @Valid DefectList defect, Errors errors, Model theModel) {

        if (null != errors && errors.getErrorCount() > 0) {

            theModel.addAttribute("defect", defect);

            List<String> pList = new ArrayList<>();
            theModel.addAttribute("projectList", projectList(pList));

            List<String> aList = new ArrayList<>();
            theModel.addAttribute("assignedToList", assignedToList(aList));

            List<String> sList = new ArrayList<>();
            theModel.addAttribute("statusList", statusList(sList));

            return "defect-form";
        }

        defectListService.saveDefect(defect);
        return "redirect:/";
    }

    @GetMapping("/updateDefect/{id}")
    public String showFormForUpdate(@PathVariable("id") Long id, Model theModel) {

        Optional<DefectList> theDefect = defectListService.fetchDefect(id);

        if (theDefect.isPresent())
            theModel.addAttribute("defect", theDefect.get());

        List<String> pList = new ArrayList<>();
        theModel.addAttribute("projectList", projectList(pList));

        List<String> aList = new ArrayList<>();
        theModel.addAttribute("assignedToList", assignedToList(aList));

        List<String> sList = new ArrayList<>();
        theModel.addAttribute("statusList", statusList(sList));

        return "defect-form";
    }

    @GetMapping("/removeDefect/{id}")
    public String deleteCustomer(@PathVariable("id") Long id, Model theModel) {
        defectListService.removeDefect(id);
        return "redirect:/";
    }

    public List<String> projectList(List<String> list) {

        list.add("MTB");
        list.add("JBL");
        list.add("IDFC");
        Collections.sort(list);

        return list;
    }

    public List<String> assignedToList(List<String> list) {

        list.add("haq.abdul");
        list.add("ali.khan");
        list.add("heena.md");
        Collections.sort(list);

        return list;
    }

    public List<String> statusList(List<String> list) {

        list.add("Assigned");
        list.add("Fixed");
        Collections.sort(list);

        return list;
    }
}
