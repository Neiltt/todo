package com.uuu.todo.repository;

import com.uuu.todo.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface EventRepository extends CrudRepository <Event,Long> {

    public List<Event> findByTask(String task);
    Page<Event> findAll(Pageable pageable);


    public List<Event> findByTaskAndNote(String task, String detail);
    public List<Event> findByTaskOrNote(String task, String detail);
    public List<Event> findByNoteLike(String note);
    @Query("SELECT b FROM Event b ORDER BY b.task, b.date")
    public List<Event> findAllOrderByProduct();

    List<Event> findByTaskLike(String s);
}
