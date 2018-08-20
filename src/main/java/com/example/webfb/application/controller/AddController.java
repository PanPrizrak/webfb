package com.example.webfb.application.controller;

import com.example.webfb.application.entity.User;
import com.example.webfb.application.entity.Worker;
import com.example.webfb.application.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class AddController {

    @Autowired
    private WorkerRepository workerRepository;

    @Value("${upload.path}")
    private String uploadPath;

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
            @RequestParam("file") MultipartFile file,
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            @RequestParam String email,
            Map<String, Object> model,
            @RequestParam(required=false, defaultValue = "") String filterName)
            throws IOException {


        String resultFilename = null;

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if( !uploadDir.isDirectory()){
                if(!uploadDir.mkdirs()) {
                    // JOptionPane.showMessageDialog(null,"OOOOO");
                    System.out.println("!!!!!!!!!!!!!!!!" + uploadDir.getAbsolutePath() + "!!!!!!!!!!!!!!!!!" + uploadDir.isDirectory());
                }

            }
            System.out.println("!!!!!!!!!!!!!!!!" + uploadDir.getAbsolutePath() + "!!!!!!!!!!!!!!!!!" + uploadDir.isDirectory());
            String uuidfile = UUID.randomUUID().toString();
            resultFilename  = uuidfile + "." + file.getOriginalFilename();


            file.transferTo(new File(uploadPath + "/" + resultFilename));


        }

        Worker worker = new Worker(name, email, user, resultFilename);


        workerRepository.save(worker);

        Iterable<Worker> workers = workerRepository.findAll();

        model.put("workers", workers);
        model.put("error"," ");
        model.put("filterName",filterName);

        return "add";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("idworker") Long idworker,Map<String, Object> model,
                         @RequestParam(required=false, defaultValue = "") String filterName){

        workerRepository.deleteById(idworker);
        Iterable<Worker> workers = workerRepository.findAll();

        model.put("workers", workers);
        model.put("error"," ");
        model.put("filterName",filterName);

        return "add";
    }

}
