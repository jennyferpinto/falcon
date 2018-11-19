package io.falcon.assignment;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class PayloadController {

    private static final String template = "Hello, %s!";

    @Autowired
    private PayloadRepository payloadRepository;

    @GetMapping("/payload")
    @ResponseBody
    public ResponseEntity<List<Payload>> getPayload(Payload payload) {
        List<Payload> payloads = payloadRepository.findAll();
        return new ResponseEntity<List<Payload>>(payloads, HttpStatus.OK);
    }

    @RequestMapping(value = "/payload", method = RequestMethod.POST)
    public ResponseEntity<Payload> newPayload(@Valid @RequestBody Payload payload ) {
        payload.setPalindromeLength(payload.getContent());
        payloadRepository.save(payload);
        return new ResponseEntity<Payload>(payload, HttpStatus.OK);
    }
	
    @RequestMapping(value ="/payload/{payloadId}", method=RequestMethod.GET)
    public ResponseEntity<Payload> getPayloadById(@PathVariable("payloadId") @Valid Long payloadId) {
        return new ResponseEntity<Payload>(payloadRepository.findByPayloadId(payloadId), HttpStatus.OK);
    }
}