package com.example.RideSharingApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RiderDto {
    private UserDto userDto;
    private double rating;
}
