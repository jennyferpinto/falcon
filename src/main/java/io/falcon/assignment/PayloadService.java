package io.falcon.assignment;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PayloadService {
	
	@Autowired
	private PayloadRepository payloadRepository;
	
	@Transactional
	@PostConstruct
	public void init() {
		// payloadRepository.save(new Payload("hello","123"));
		// payloadRepository.save(new Payload("this", "123"));
		// payloadRepository.save(new Payload("works", "123"));
	}
	
	public List<Payload> findAll() {
		return payloadRepository.findAll();
    }
    
    public void addPayload(Payload p){
		payloadRepository.save(p);
    }

}