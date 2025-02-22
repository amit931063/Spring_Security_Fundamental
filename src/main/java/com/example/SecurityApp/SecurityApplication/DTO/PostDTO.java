package com.example.SecurityApp.SecurityApplication.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDTO {
    private  Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private  String  description ;


}
