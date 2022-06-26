package com.score;

import static org.mockito.Mockito.reset;

import org.junit.jupiter.api.Test;
import com.score.Jeux;

import org.springframework.boot.test.context.SpringBootTest;
import com.score.InterfaceScore;
@SpringBootTest
class ScoreTennisApplicationTests {

	@Test
	void contextLoads() {
		Jeux gt = new Jeux("Talla", "Piere");
		gt.reset();
		gt.play(1);
		gt.showScores();
		
	}
	
	
	

}
