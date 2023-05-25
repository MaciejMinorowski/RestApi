package pl.masiek.restapi.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PostDto {

    private long id;
    private String title;
    private String content;
    private LocalDateTime created;


}
