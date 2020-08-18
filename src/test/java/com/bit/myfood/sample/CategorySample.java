package com.bit.myfood.sample;

import com.bit.myfood.ApplicationTests;
import com.bit.myfood.model.entity.Category;
import com.bit.myfood.model.enumclass.CategoryType;
import com.bit.myfood.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class CategorySample extends ApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void createSample(){
        List<String> category = Arrays.asList("COMPUTER","CLOTHING","MULTI_SHOP","INTERIOR","FOOD","SPORTS","SHOPPING_MALL","DUTY_FREE","BEAUTY");
        List<String> title = Arrays.asList("컴퓨터-전자제품","의류","멀티샵","인테리어","음식","스포츠","쇼핑몰","면세점","화장");

        for(int i = 0; i < category.size(); i++){
            CategoryType c = CategoryType.valueOf(category.get(i));
            String t = title.get(i);
            Category create = Category.builder()
                    .type(c)
                    .title(t)
                    .build();

            System.out.println(create);
            categoryRepository.save(create);
        }
    }
}