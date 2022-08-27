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
import java.util.Optional;

@Slf4j
@Component
public class MyRunner1 implements CommandLineRunner {
    @Autowired
    EventRepository repository;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //設定日期格式
    String dateStr = df.format(new Date()); //字串轉日期

    @Override
    public void run(String... args) throws Exception {
        log.info("使用JPA連線到資料庫");
        initDB();
        dumpDB();
        modifyrecord();
        validateDB();
        getModifiedDB();
        deleteAll();
        initDB();
        deleteOnlySelected();
    }

    private void deleteOnlySelected() {
        log.info("只刪除某些資料");
        List<Event> coffees = repository.findByTask("C");
        for (Event b : coffees) {
            repository.delete(b);
        }
        log.info("重新傾印所有的資料, 資料共有{}筆", repository.count());
    }

    private void deleteAll() {
        log.info("刪除所有的資料");
        repository.deleteAll();
        log.info("重新傾印所有的資料, 資料共有{}筆", repository.count());
    }

    private void getModifiedDB() {
        Event b = repository.findById(1L).get();
        log.info("剛被修改過是:{}", b);
    }

    private void validateDB() {
        log.info("重新傾印所有的資料, 資料共有{}筆", repository.count());
        for (Event b : repository.findAll()) {
            log.info("事件id:{},task:{}, note:{}", b.getId(), b.getTask(), b.getNote());
        }

    }

    private void modifyrecord() {
        Optional<Event> eventOptional = repository.findById(1L);
        Event eventToUpdate = eventOptional.get();
        eventToUpdate.setTask("U");
        repository.save(eventToUpdate);
    }

    private void dumpDB() {
        log.info("傾印所有的資料, 資料共有{}筆", repository.count());
        for (Event b : repository.findAll()) {
            log.info("事件id:{},task:{}, note:{}", b.getId(), b.getTask(), b.getNote());
        }
    }

    private void initDB() {
        log.info("清空資料並且放幾筆資料進去");
        repository.save(new Event("2022-08-16 07:00", "wake up","X"));
        repository.save(new Event("2022-08-16 09:00", "working","X"));
        repository.save(new Event("2022-08-16 12:00", "lunch","X"));
        repository.save(new Event("2022-08-16 17:00", "work off","X"));
        repository.save(new Event("2022-08-16 20:00", "learn","X"));
        repository.save(new Event("2022-08-16 21:00", "reading","C"));
        repository.save(new Event(dateStr, "coding","D"));
    }
}