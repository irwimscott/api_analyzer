package com.example.apirules.service;

import com.example.apirules.model.Rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class CodeAnalyzer {

    private static final Logger logger = LoggerFactory.getLogger(CodeAnalyzer.class);

    public String analyze(File projectDir, List<Rule> rules) {
        if (!projectDir.exists() || !projectDir.isDirectory()) {
            logger.error("Diretório inválido: {}", projectDir.getAbsolutePath());
            throw new IllegalArgumentException("Caminho do projeto não é válido.");
        }

        logger.info("Iniciando análise no diretório: {}", projectDir.getAbsolutePath());
        File[] files = projectDir.listFiles();
        if (files == null || files.length == 0) {
            logger.warn("Nenhum arquivo encontrado no diretório: {}", projectDir.getAbsolutePath());
            return "RUIM";
        }

        for (File file : files) {
            logger.info("Verificando arquivo: {}", file.getName());
            // Aqui você pode adicionar a lógica de aplicação das regras no arquivo
        }

        logger.info("Análise do diretório concluída.");
        return "BOA"; // Retorne o resultado real da análise com base nas regras
    }
}