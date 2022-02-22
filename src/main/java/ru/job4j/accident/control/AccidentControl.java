package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentRuleService;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {

    private final AccidentService accidents;
    private final AccidentTypeService typeService;
    private final AccidentRuleService ruleService;

    public AccidentControl(AccidentService accidents, AccidentTypeService typeService, AccidentRuleService ruleService) {

        this.accidents = accidents;
        this.typeService = typeService;
        this.ruleService = ruleService;
    }

    @GetMapping(path = "/create")
    public String create(Model model) {
        model.addAttribute("types", typeService.accidentTypes());
        model.addAttribute("rules", ruleService.findAllRules());
        return "/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        typeService.setType(accident);
        ruleService.addRules(accident, ids);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidents.findById(id));
        model.addAttribute("types", typeService.accidentTypes());
        model.addAttribute("rules", ruleService.findAllRules());
        return "/update";
    }
}
