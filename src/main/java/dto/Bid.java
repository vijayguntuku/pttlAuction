package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class Bid {
    private int id;
    private String start_time;
    private String end_time;
    private int player_id;
    private int team_id;
    private boolean isSold;
    private double amount;

}
