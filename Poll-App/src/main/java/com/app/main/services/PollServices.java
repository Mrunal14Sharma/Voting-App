package com.app.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.main.model.OptionVote;
import com.app.main.model.Poll;
import com.app.main.repositary.PollReopsitary;

import jakarta.transaction.Transactional;

@Service
public class PollServices {
	
	private final PollReopsitary pollrepo;

	public PollServices(PollReopsitary pollrepo) {
		this.pollrepo = pollrepo;
	}
	
	@Transactional
	public Poll createPoll(Poll poll) {
		
		return pollrepo.save(poll);
	}

	public List<Poll> getAllPolls() {
		return pollrepo.findAll();
	}

	public Optional<Poll> getPollById(Long id) {
		return pollrepo.findById(id);
	}
	
//	import org.springframework.transaction.annotation.Transactional;

	@Transactional
	public void vote(Long pollId, int optionIndex) {
	    Poll poll = pollrepo.findById(pollId).orElseThrow(() -> new RuntimeException("Poll not Found!!"));

	    List<OptionVote> options = poll.getOptions();
	    if (optionIndex < 0 || optionIndex >= options.size()) {
	        throw new IllegalArgumentException("Invalid Option Index!!");
	    }

	    OptionVote selectedOption = options.get(optionIndex);
	    selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);

	    pollrepo.save(poll);
	}


//	public void vote(Long pollId, int optionIndex) {
//		Poll poll = pollrepo.findById(pollId).orElseThrow(()-> new RuntimeException("Poll not Found!!"));
//		
//		List<OptionVote> options = poll.getOptions();
//		
//		if(optionIndex < 0 || optionIndex >= options.size()) {
//			throw new IllegalArgumentException("Invalid Option Index!!");
//		}
//		
//		OptionVote selectedOption = options.get(optionIndex);
//		
//		selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);
//		
//		pollrepo.save(poll);
//	}

}
