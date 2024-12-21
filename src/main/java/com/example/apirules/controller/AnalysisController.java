package com.example.apirules.controller;

import com.example.apirules.model.Rule;
import com.example.apirules.service.RuleLoaderService;
import com.example.apirules.service.CodeAnalyzer;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AnalysisController.class);

    @Autowired
    private RuleLoaderService ruleLoaderService;

    @Autowired
    private CodeAnalyzer codeAnalyzer;

    @PostMapping
    public ResponseEntity<String> analyze(
        @RequestParam("projectPath") String projectPath,
        @RequestParam("rulesPath") String rulesPath) {
        try {
            logger.info("Iniciando análise do projeto.");
            logger.info("Caminho do projeto: {}", projectPath);
            logger.info("Caminho do arquivo de regras: {}", rulesPath);

            List<Rule> rules = ruleLoaderService.loadRules(rulesPath);
            String result = codeAnalyzer.analyze(new File(projectPath), rules);

            logger.info("Análise concluída com resultado: {}", result);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Erro na análise: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("Erro na análise: " + e.getMessage());
        }
    }
}