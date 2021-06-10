package de.fiducia.simple.first;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import de.fiducia.simple.client.Client;





@Component
@Slf4j
public class FirstRunner implements CommandLineRunner {
	
	private final Client client;
	
	
	
    public FirstRunner(final Client client) {
		this.client = client;
	}



	@Override
    public void run(String... args) throws Exception {
        log.info("Hallo");
        client.run();
    }
}

