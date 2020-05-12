package org.jahiduls.sudokuservice.service.strategy;

import org.jahiduls.sudokuservice.service.SolverStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StrategyConfig {

    @Bean
    public SolverStrategy bruteForceStrategy() {
        return new BruteForceStrategy();
    }

}
