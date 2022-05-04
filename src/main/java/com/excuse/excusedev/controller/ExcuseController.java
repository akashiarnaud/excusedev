package com.excuse.excusedev.controller;
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
    public Iterable<String> list() {
        return excuseService.list();
    }

    @PostMapping("/add")
    public void add(@RequestBody String tag, @RequestBody String excuse) {
        excuseService.add(tag, excuse);
    }
}
