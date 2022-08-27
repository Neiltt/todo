package com.uuu.todo.runner;

import com.uuu.todo.model.Event;
import com.uuu.todo.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class MyRunner2 implements CommandLineRunner {
    @Autowired
    EventRepository repository;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //設定日期格式
    String dateStr = df.format(new Date()); //字串轉日期

    @Override
    public void run(String... args) throws Exception {
        initDB();
        findByPage();
    }

    private void findByPage() {
        PageRequest req1 = PageRequest.of(0, 3);
        Page<Event> firstPage = repository.findAll(req1);
        log.info("第一批次的內容:");
        for (Event b: firstPage.getContent()){
            log.info("資料是:{}",b);
        }
        Page<Event> secondPage = repository.findAll(req1.next());
        log.info("第二批次的內容:");
        for (Event b: secondPage.getContent()){
            log.info("資料是:{}",b);
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