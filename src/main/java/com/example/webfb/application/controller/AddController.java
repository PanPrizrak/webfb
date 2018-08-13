package com.example.webfb.application.controller;

import com.example.webfb.application.entity.User;
import com.example.webfb.application.entity.Worker;
import com.example.webfb.application.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class AddController {

    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping("/add")
    public String add(@RequestParam(required=false, defaultValue = "") String filterName, Model model) {//@RequestParam(name="name", required=false, defaultValue="World") String name, Model model
        Iterable<Worker> workers = workerRepository.findAll();

        Iterable<Worker> bufWorkers;
        String error = " ";
        // JOptionPane.showMessageDialog(null, filterName);

        if (filterName != null && !filterName.isEmpty() ) {
            bufWorkers = workerRepository.findByName(filterName);
            if(bufWorkers.iterator().hasNext()){
                workers = bufWorkers;
                error = " ";
            }else{
                workers = workerRepository.findAll();
                error = "Данное имя не найдено";
            }

        } else {
            workers = workerRepository.findAll();
            error = " ";
        }

        model.addAttribute("workers", workers);
        model.addAttribute("error",error);
        model.addAttribute("filterName",filterName);
        return "add";
    }

    @PostMapping("/add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            @RequestParam String email,
            Map<String, Object> model) {
        Worker worker = new Worker(name, email, user);

        workerRepository.save(worker);

        Iterable<Worker> workers = workerRepository.findAll();

        model.put("workers", workers);
        model.put("error"," ");

        return "add";
    }

}
