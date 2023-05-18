package com.thanhle.controller;

import com.thanhle.domain.Insurer;
import com.thanhle.repository.InsurerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin(origins = "http://localhost:8282")
@RequestMapping("/api")
public class InsurerController {

    @Autowired
    private InsurerRepository insurerRepository;

    @PostMapping("/insurer")
    public Insurer createInsurer(@RequestBody Insurer insurer) {
        return insurerRepository.save(insurer);
    }
}
