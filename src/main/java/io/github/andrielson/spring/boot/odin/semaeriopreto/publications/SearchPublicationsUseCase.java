package io.github.andrielson.spring.boot.odin.semaeriopreto.publications;

import io.github.andrielson.spring.boot.odin.semaeriopreto.subscribers.SubscribersRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class SearchPublicationsUseCase {

    private final SubscribersRepositoryAdapter subscribersRepository;

    public Stream<Publication> searchByDate(LocalDate date) {
        final String datePattern = "dd/MM/yyyy";
        final String keyword = "semae";
        final String linkPrefix = "https://www.riopreto.sp.gov.br/DiarioOficial/";
        final Map<String, String> formParams = new HashMap<>(3);
        formParams.put("Diario!listar.action", "Buscar");
        formParams.put("diario.dataPublicacao", date.format(DateTimeFormatter.ofPattern(datePattern)));
        formParams.put("diario.palavraChave", keyword);

        try {
            return Jsoup.connect(linkPrefix + "Diario!listar.action")
                    .data(formParams)
                    .post()
                    .body()
                    .getElementsByAttributeValueStarting("href", "Diario!arquivo.action?diario.codPublicacao=")
                    .stream()
                    .map(element -> new Publication(element.attr("href").split("=")[1], linkPrefix + element.attr("href")));
        } catch (IOException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }
}
