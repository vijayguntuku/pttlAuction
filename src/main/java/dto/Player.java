package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Player {
    private int id;
    private String name;
    private String email;
    private String phone_no;
    private double auction_price;
    private int team_id;
    private double base_price;
    private boolean isSold;
    private boolean isCaptain;
    private String image;
    private String teamName;


}
