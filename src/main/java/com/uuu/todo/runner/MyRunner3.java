package com.uuu.todo.runner;

import com.uuu.todo.model.Event;
import com.uuu.todo.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class MyRunner3 implements CommandLineRunner {
    @Autowired
    EventRepository repository;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //設定日期格式
    String dateStr = df.format(new Date()); //字串轉日期

    @Override
    public void run(String... args) throws Exception {
        log.info("填充資料庫");
        initDB();
        customSearch1();
        customSearch2();
    }

    private void customSearch2() {
        log.info("使用or於兩條件");
        List<Event> events = repository.findByTaskOrNote("X","coding");
        for(Event b: events) {
            log.info("[or]的場合:{}",b);
        }

    }

    private void customSearch1() {
        log.info("使用and於兩條件");
        List<Event> events = repository.findByTaskAndNote("D","coding");
        for(Event b: events) {
            log.info("[and]的場合:{}",b);
        }
    }

    private void initDB() {
        log.info("加入更多資料");
        repository.save(new Event("2022-08-16 07:00", "wake up","X"));
        repository.save(new Event("2022-08-16 09:00", "working","X"));
        repository.save(new Event("2022-08-16 12:00", "lunch","X"));
        repository.save(new Event("2022-08-16 17:00", "work off","X"));
        repository.save(new Event("2022-08-16 20:00", "learn","X"));
        repository.save(new Event("2022-08-16 21:00", "reading","C"));
        repository.save(new Event(dateStr, "coding","D"));

    }
}