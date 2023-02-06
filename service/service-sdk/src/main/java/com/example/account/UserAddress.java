package com.example.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserAddress {
    private String nickName;
    private String phone;
    private String province;
    private String city;
    private String address;
}
