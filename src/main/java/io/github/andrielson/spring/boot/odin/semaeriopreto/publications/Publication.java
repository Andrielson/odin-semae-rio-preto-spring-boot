package io.github.andrielson.spring.boot.odin.semaeriopreto.publications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Publication {
    private String code;
    private String link;
}
