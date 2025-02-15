package com.app.main.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.main.model.Poll;
import com.app.main.request.Vote;
import com.app.main.services.PollServices;

@RestController
@RequestMapping("/api/polls")
@CrossOrigin(origins = "http://localhost:4200/")
public class PollController {
	private final PollServices pollservice;

	public PollController(PollServices pollservice) {
		this.pollservice = pollservice;
	}
	
	@PostMapping
	public Poll createPoll(@RequestBody Poll poll) {
		System.out.println(" Received poll: " + poll);
	    
	    if (poll.getOptions() == null || poll.getOptions().isEmpty()) {
	        System.out.println(" Error: Options list is empty!");
//	        return ResponseEntity.badRequest().build();
	    }
		return pollservice.createPoll(poll);
	}
	
	@GetMapping
	public List<Poll> getAllPolls(){
		return pollservice.getAllPolls();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Poll> getPoll(@PathVariable Long id){
		return pollservice.getPollById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/vote")
	public void vote(@RequestBody Vote vote) {
		pollservice.vote(vote.getPollId(),vote.getOptionIndex());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
