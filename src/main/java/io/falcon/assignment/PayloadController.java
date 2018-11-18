package io.falcon.assignment;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
public class PayloadController {

    private static final String template = "Hello, %s!";
    
    // @Autowired
    // private PayloadService payloadService;

    @Autowired
    private PayloadRepository payloadRepository;

    @GetMapping("/payload")
    public List<Payload> getPayload(Payload payload) {
        return payloadRepository.findAll();
    }

    @GetMapping("/orderedpayload")
    public Page<Payload> getQuestions(Pageable pageable) {
        return payloadRepository.findAll(pageable);
    }

    @PostMapping("/payload")
    @ResponseBody
    public String createPayload(@RequestBody Payload payload) {
        payload.setPalindromeLength(payload.getContent());
        payloadRepository.save(payload);
        return payload.toString();
    }

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return String.format(template, name);
    }
	
	// @GetMapping("/payload/{id}")
    @RequestMapping(value ="/payload/{payloadId}", method=RequestMethod.GET)
    public Payload getPayloadById(@PathVariable("payloadId") @Valid Long payloadId) {
        return payloadRepository.findByPayloadId(payloadId);
    }
}