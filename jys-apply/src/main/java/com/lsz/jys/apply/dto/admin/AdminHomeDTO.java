package com.lsz.jys.apply.dto.admin;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminHomeDTO {

    private String name;
    private String userId;
    private Boolean admin ;
    private Integer role;
    private String token;
}
