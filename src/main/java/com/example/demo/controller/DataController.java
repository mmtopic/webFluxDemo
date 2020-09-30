package com.example.demo.controller;

import com.example.demo.bean.Symbol;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class DataController {
    public static Map<String, Symbol> dataSource = new HashMap<>();

    @RequestMapping(value = "/sse", produces = "text/event-stream;charset=UTF-8")
    public Flux<Symbol> streamData() {
        Random random = new Random();
        Symbol symbol = new Symbol();
        symbol.setSymbol("AUSD");
        String ask = String.valueOf(random.doubles());
        symbol.setAsk(random.nextDouble()*1000 %1);
        String bid = String.valueOf(random.doubles());
        symbol.setBid(random.nextDouble() *10000 %1);
        dataSource.put(symbol.getSymbol(), symbol);

        List<Symbol> resList = new ArrayList();
        dataSource.forEach((k, v) -> {
            resList.add(v);
        });
        return Flux.fromIterable(resList);
    }

    @RequestMapping(value = "/sse/time", produces = "text/event-stream;charset=UTF-8")
    public Object streamString() {

        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String res = dtf2.format(time);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // ignore
        }
        return res;
    }
}
