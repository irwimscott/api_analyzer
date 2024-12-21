package com.example.apirules.service;

import com.example.apirules.model.Rule;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class RuleLoaderService {

    private static final Logger logger = LoggerFactory.getLogger(RuleLoaderService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Rule> loadRules(String rulesPath) throws IOException {
        logger.info("Carregando regras do arquivo: {}", rulesPath);
        File file = new File(rulesPath);

        if (!file.exists()) {
            logger.error("Arquivo de regras não encontrado: {}", rulesPath);
            throw new IllegalArgumentException("Arquivo de regras não encontrado.");
        }

        List<Rule> rules;
        JsonNode rootNode = objectMapper.readTree(file);

        if (rootNode.has("rules")) {
            logger.info("Arquivo contém propriedade 'rules'. Carregando...");
            rules = objectMapper.convertValue(
                rootNode.get("rules"),
                new TypeReference<List<Rule>>() {}
            );
        } else {
            logger.info("Arquivo contém uma lista direta de regras. Carregando...");
            rules = objectMapper.readValue(file, new TypeReference<List<Rule>>() {});
        }

        logger.info("Total de regras carregadas: {}", rules.size());
        return rules;
    }
}