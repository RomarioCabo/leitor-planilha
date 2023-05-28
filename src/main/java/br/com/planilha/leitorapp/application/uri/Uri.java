package br.com.planilha.leitorapp.application.uri;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public abstract class Uri {

    public URI generate(String fromPath, Integer page, Integer elementsPerPage) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(fromPath);

        if (page != null) {
            builder.queryParam("page", page);
        }

        if (elementsPerPage != null) {
            builder.queryParam("elementsPerPage", elementsPerPage);
        }

        return builder.build().toUri();
    }
}