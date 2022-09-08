package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Team {
    private int id;
    private String name;
    private double balance_amount;
    private int captain_id;
    private int player_count;
    private double base_price;
    private int min_player_count;

}
