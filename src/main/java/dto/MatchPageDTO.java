package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatchPageDTO {

    private Long pageNumber;

    private Long lastPageNumber;

    private String filterByName;

}
