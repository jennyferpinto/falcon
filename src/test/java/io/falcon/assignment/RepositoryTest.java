package io.falcon.assignment;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PayloadRepository repository;

	@Test
	public void testDatabase() throws Exception {
		
		Payload p = new Payload(); 
		p.setContent("aa");
		p.setPalindromeLength(p.getContent());
		
		this.entityManager.persist(p);
	
		Payload payload = this.repository.findByPayloadId(new Long(1));
		assertThat(payload.getContent()).isEqualTo("aa");
		assertThat(payload.getPalindromeLength()).isEqualTo(2);
	}

}