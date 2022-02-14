package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;

@Controller
public class AccidentControl {

    private final AccidentService accidents;
    private final AccidentTypeService typeService;

    public AccidentControl(AccidentService accidents, AccidentTypeService typeService) {
        this.accidents = accidents;
        this.typeService = typeService;
    }

    @GetMapping(path = "/create")
    public String create(Model model) {
        model.addAttribute("types", typeService.accidentTypes());
        return "/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        typeService.setType(accident);
        accidents.create(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        Accident accident = accidents.findById(id);
        model.addAttribute("accident", accident);
        model.addAttribute("types", typeService.accidentTypes());
        return "/update";
    }
}
