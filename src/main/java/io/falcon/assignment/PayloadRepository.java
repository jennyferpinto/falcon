package io.falcon.assignment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PayloadRepository extends JpaRepository<Payload, Long>{

	Payload findByPayloadId(Long payloadId);

}