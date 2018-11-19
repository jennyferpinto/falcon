package io.falcon.assignment;
import org.springframework.stereotype.Service;

@Service
public class PayloadService {
    private PayloadRepository payloadRepository;

    public PayloadService(PayloadRepository payloadRepository){
        this.payloadRepository = payloadRepository;
    }

    public Iterable<Payload> list() {
        return payloadRepository.findAll();
    }
}