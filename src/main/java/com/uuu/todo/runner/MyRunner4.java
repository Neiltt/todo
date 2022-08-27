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
public class MyRunner4 implements CommandLineRunner {
    @Autowired
    EventRepository repository;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //設定日期格式
    String dateStr = df.format(new Date()); //字串轉日期

    @Override
    public void run(String... args) throws Exception {
        initDB();
        queryByLike();
    }

    private void queryByLike() {
        List<Event> eventList = repository.findByTaskLike("%C%");
        for (Event b : eventList) {
            log.info("[like]b:{}",b);
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
