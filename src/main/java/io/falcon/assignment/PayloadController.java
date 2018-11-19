package io.falcon.assignment;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class PayloadController {

    private static final String template = "Hello, %s!";

    @Autowired
    private PayloadRepository payloadRepository;

    @GetMapping("/payload")
    public List<Payload> getPayload(Payload payload) {
        return payloadRepository.findAll();
    }

    // @PostMapping("/payload")
    // @ResponseBody
    // public String createPayload(@RequestBody Payload payload) {
    //     payload.setPalindromeLength(payload.getContent());
    //     payloadRepository.save(payload);
    //     return payload.toString();
    // }

    @RequestMapping(value = "/payload", method = RequestMethod.POST)
    public ResponseEntity<String> newPayload(@Valid @RequestBody Payload payload ) {
        payload.setPalindromeLength(payload.getContent());
        payloadRepository.save(payload);
        return new ResponseEntity<>(payload.toString(),HttpStatus.OK);
    }


    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return String.format(template, name);
    }
	
    @RequestMapping(value ="/payload/{payloadId}", method=RequestMethod.GET)
    public Payload getPayloadById(@PathVariable("payloadId") @Valid Long payloadId) {
        return payloadRepository.findByPayloadId(payloadId);
    }
}