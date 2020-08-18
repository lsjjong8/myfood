package com.bit.myfood.model.network.request;

import com.bit.myfood.model.enumclass.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryApiRequest {

    private Long id;

    private CategoryType type;

    private String title;

}
