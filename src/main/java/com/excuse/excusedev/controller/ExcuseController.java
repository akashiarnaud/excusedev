package com.excuse.excusedev.controller;
import com.excuse.excusedev.domain.Excuse;
import com.excuse.excusedev.service.ExcuseService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/excuses")
public class ExcuseController {

    private ExcuseService excuseService;

    public ExcuseController(ExcuseService excuseService) {
        this.excuseService = excuseService;
    }

    @GetMapping("/list")
    public Iterable<Excuse> list() {
        if(excuseService != null){
            return excuseService.list();
        }
        return null;
    }

    @PostMapping("/add")
    public void add(@RequestBody Excuse excuse) {
        excuseService.add(excuse);
    }
}
