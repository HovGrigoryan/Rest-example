package am.itspace.restexample.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ToDo {

    private int id;
    private int userID;
    private int title;
    private boolean completed;
}
