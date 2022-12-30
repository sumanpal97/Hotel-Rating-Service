package com.ms.user.service.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String userId;

    private String name;

    private String email;

    private String about;

    private List<RatingDTO> ratings = new ArrayList<>();
}
