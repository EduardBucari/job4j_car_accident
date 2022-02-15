package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@Service
public class AccidentRuleService {
    private final AccidentMem mem;

    public AccidentRuleService(AccidentMem mem) {
        this.mem = mem;
    }

    public Collection<Rule> findAllRules() {
        return mem.findAllRules();
    }

    public Rule findRuleById(int id) {
        return mem.findRuleById(id);
    }

    public void addRules(Accident accident, String[] ids) {
        for (String id : ids) {
            accident.addRule(findRuleById(Integer.parseInt(id)));
        }
    }
}
