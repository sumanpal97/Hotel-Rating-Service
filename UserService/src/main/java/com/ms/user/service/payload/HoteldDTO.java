package com.ms.user.service.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoteldDTO {
    private String hotelId;

    private String name;

    private String location;

    private String about;
}
