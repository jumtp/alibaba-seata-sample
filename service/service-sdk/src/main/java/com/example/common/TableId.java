package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableId {

    List<Id> idList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Id {
        private Long id;

    }
}
