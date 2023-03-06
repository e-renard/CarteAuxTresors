package com.carbon.lacarteauxtresors;

import com.carbon.lacarteauxtresors.treasuremap.ExploreTreasureMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LaCarteAuTresorApplication implements CommandLineRunner {

	@Autowired
	ExploreTreasureMap treasureMap;
	public static void main(String[] args) {
		SpringApplication.run(LaCarteAuTresorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		treasureMap.createAndSimulateAdventure();
	}
}
