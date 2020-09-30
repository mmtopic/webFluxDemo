package com.example.demo.controller;

import com.example.demo.bean.Symbol;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class DataController {
    public static List dataSource = new ArrayList();
    @RequestMapping(value = "/sse",produces = "text/event-stream;charset=UTF-8")
    public Object streamData(){
// public Flux<Symbol> streamData(){
        Random r = new Random();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // ignore
        }
        return "data:Testing 1,2,3" + r.nextInt() +"\n\n";
    }
}
