package com.app.main.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.main.model.Poll;

@Repository
public interface PollReopsitary extends JpaRepository<Poll, Long> {

}
