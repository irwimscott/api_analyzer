package com.example.apirules.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignora campos não mapeados no JSON
public class Rule {

    @JsonProperty("id") // Mapeia o campo "id" do JSON
    private String id;

    @JsonProperty("name") // Mapeia o campo "name" do JSON
    private String name;

    @JsonProperty("description") // Mapeia o campo "description" do JSON
    private String description;

    @JsonProperty("severity") // Mapeia o campo "severity" do JSON
    private String severity;

    @JsonProperty("pattern") // Mapeia o campo "pattern" do JSON
    private String pattern;

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
