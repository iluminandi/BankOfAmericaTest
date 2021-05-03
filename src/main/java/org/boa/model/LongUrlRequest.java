package org.boa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LongUrlRequest {

    @NotNull(message = "longUrl field must be provided.")
    private String longUrl;
}
