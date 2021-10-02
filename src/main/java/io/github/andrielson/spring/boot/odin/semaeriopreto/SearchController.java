package io.github.andrielson.spring.boot.odin.semaeriopreto;

import io.github.andrielson.spring.boot.odin.semaeriopreto.publications.Publication;
import io.github.andrielson.spring.boot.odin.semaeriopreto.publications.SearchPublicationsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("/search")
@RequiredArgsConstructor
@RestController
public class SearchController {

    private final SearchPublicationsUseCase useCase;

    @GetMapping("/{date}")
    public ResponseEntity<Set<Publication>> searchByDate(@PathVariable String date) {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeException exception) {
            return ResponseEntity.badRequest().build();
        }
        Set<Publication> publications = useCase.searchByDate(localDate)
                .collect(Collectors.toSet());
        return publications.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(publications);
    }
}
