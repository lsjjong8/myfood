package com.bit.myfood.sample;

import com.bit.myfood.ApplicationTests;
import com.bit.myfood.model.entity.Category;
import com.bit.myfood.model.entity.Item;
import com.bit.myfood.model.entity.Partner;
import com.bit.myfood.model.enumclass.CategoryType;
import com.bit.myfood.model.enumclass.ItemStatus;
import com.bit.myfood.repository.CategoryRepository;
import com.bit.myfood.repository.ItemRepository;
import com.bit.myfood.repository.PartnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Slf4j
public class ItemSample extends ApplicationTests {

    private Random random = new Random();

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void createAll(){
        createHomeAppliance();
        createClothing();
        createMultiShop();
        createInterior();
        createFood();
        createSports();
        createShoppingMall();
        createDutyFree();
        createBeauty();
    }


    @Test
    public void createHomeAppliance(){
        CategoryType type = CategoryType.COMPUTER;
        Category category = categoryRepository.findByType(type).get();
        List<Partner> partnerList = partnerRepository.findByCategory(category);

        for(Partner p : partnerList){

            for(int i = 1 ; i < 6; i ++){
                int div = (random.nextInt(10)+1) % 2;
                ItemStatus status = (div == 0 ? ItemStatus.REGISTERED : ItemStatus.UNREGISTERED);

                Item item = Item.builder()
                        .partner(p)
                        .status(status)
                        .name(category.getTitle()+i+"호")
                        .title(p.getName()+"의 가전제품"+i+"호")
                        .price(BigDecimal.valueOf((long)random.nextInt(999999)+1))
                        .content(p.getName()+"의 가전제품"+i+"호"+"의 가전제품 입니다. 2019년도 신제품 입니다")
                        .brandName(p.getName())
                        .registeredAt(getRandomDate())
                        .unregisteredAt(status.equals(ItemStatus.UNREGISTERED) ? getRandomDate() : null )
                        .build();

                itemRepository.save(item);
            }
        }
    }

    @Test
    public void createClothing(){

        CategoryType type = CategoryType.CLOTHING;
        Category category = categoryRepository.findByType(type).get();
        List<Partner> partnerList = partnerRepository.findByCategory(category);

        for(Partner p : partnerList){

            for(int i = 1 ; i < 6; i ++){
                int div = (random.nextInt(10)+1) % 2;
                ItemStatus status = (div == 0 ? ItemStatus.REGISTERED : ItemStatus.UNREGISTERED);

                Item item = Item.builder()
                        .partner(p)
                        .status(status)
                        .name(category.getTitle()+i+"호")
                        .title(p.getName()+"의 의류제품"+i+"호")
                        .price(BigDecimal.valueOf((long)random.nextInt(999999)+1))
                        .content(p.getName()+"의 의류제품"+i+"호"+"의 겨울 상품 입니다. 2029년도 신제품 입니다")
                        .brandName(p.getName())
                        .registeredAt(getRandomDate())
                        .unregisteredAt(status.equals(ItemStatus.UNREGISTERED) ? getRandomDate() : null )
                        .build();
                itemRepository.save(item);
            }
        }
    }


    @Test
    public void createMultiShop(){

        CategoryType type = CategoryType.MULTI_SHOP;
        Category category = categoryRepository.findByType(type).get();
        List<Partner> partnerList = partnerRepository.findByCategory(category);

        for(Partner p : partnerList){

            for(int i = 1 ; i < 6; i ++){
                int div = (random.nextInt(10)+1) % 2;
                ItemStatus status = (div == 0 ? ItemStatus.REGISTERED : ItemStatus.UNREGISTERED);

                Item item = Item.builder()
                        .partner(p)
                        .status(status)
                        .name(category.getTitle()+i+"호")
                        .title(p.getName()+"의 XX 제품"+i+"호")
                        .price(BigDecimal.valueOf((long)random.nextInt(999999)+1))
                        .content(p.getName()+"의 XX 제품"+i+"호"+"의 여러가지 상품 입니다. 2021년도 신제품 입니다")
                        .brandName(p.getName())
                        .registeredAt(getRandomDate())
                        .unregisteredAt(status.equals(ItemStatus.UNREGISTERED) ? getRandomDate() : null )
                        .build();
                itemRepository.save(item);
            }
        }
    }

    @Test
    public void createInterior(){

        CategoryType type = CategoryType.INTERIOR;
        Category category = categoryRepository.findByType(type).get();
        List<Partner> partnerList = partnerRepository.findByCategory(category);

        for(Partner p : partnerList){

            for(int i = 1 ; i < 6; i ++){
                int div = (random.nextInt(10)+1) % 2;
                ItemStatus status = (div == 0 ? ItemStatus.REGISTERED : ItemStatus.UNREGISTERED);

                Item item = Item.builder()
                        .partner(p)
                        .status(status)
                        .name(category.getTitle()+i+"호")
                        .title(p.getName()+"의 가구"+i+"호")
                        .price(BigDecimal.valueOf((long)random.nextInt(999999)+1))
                        .content(p.getName()+"의 가구"+i+"호"+"의 원룸에 들어가는. 2023년도 신제품 입니다")
                        .brandName(p.getName())
                        .registeredAt(getRandomDate())
                        .unregisteredAt(status.equals(ItemStatus.UNREGISTERED) ? getRandomDate() : null )
                        .build();
                itemRepository.save(item);
            }
        }
    }

    @Test
    public void createFood(){

        CategoryType type = CategoryType.FOOD;
        Category category = categoryRepository.findByType(type).get();
        List<Partner> partnerList = partnerRepository.findByCategory(category);

        for(Partner p : partnerList){

            for(int i = 1 ; i < 6; i ++){
                int div = (random.nextInt(10)+1) % 2;
                ItemStatus status = (div == 0 ? ItemStatus.REGISTERED : ItemStatus.UNREGISTERED);

                Item item = Item.builder()
                        .partner(p)
                        .status(status)
                        .name(category.getTitle()+i+"호")
                        .title(p.getName()+"의 제빵"+i+"호")
                        .price(BigDecimal.valueOf((long)random.nextInt(999999)+1))
                        .content(p.getName()+"의 제빵"+i+"호"+"의 생일 케이크 입니다")
                        .brandName(p.getName())
                        .registeredAt(getRandomDate())
                        .unregisteredAt(status.equals(ItemStatus.UNREGISTERED) ? getRandomDate() : null )
                        .build();
                itemRepository.save(item);
            }
        }
    }

    @Test
    public void createSports(){

        CategoryType type = CategoryType.SPORTS;
        Category category = categoryRepository.findByType(type).get();
        List<Partner> partnerList = partnerRepository.findByCategory(category);

        for(Partner p : partnerList){

            for(int i = 1 ; i < 6; i ++){
                int div = (random.nextInt(10)+1) % 2;
                ItemStatus status = (div == 0 ? ItemStatus.REGISTERED : ItemStatus.UNREGISTERED);

                Item item = Item.builder()
                        .partner(p)
                        .status(status)
                        .name(category.getTitle()+i+"호")
                        .title(p.getName()+"의 겨울 스포츠"+i+"호")
                        .price(BigDecimal.valueOf((long)random.nextInt(999999)+1))
                        .content(p.getName()+"의 겨울 스포츠"+i+"호"+"의 스키 상품 입니다")
                        .brandName(p.getName())
                        .registeredAt(getRandomDate())
                        .unregisteredAt(status.equals(ItemStatus.UNREGISTERED) ? getRandomDate() : null )
                        .build();
                itemRepository.save(item);
            }
        }
    }

    @Test
    public void createShoppingMall(){

        CategoryType type = CategoryType.SHOPPING_MALL;
        Category category = categoryRepository.findByType(type).get();
        List<Partner> partnerList = partnerRepository.findByCategory(category);

        for(Partner p : partnerList){

            for(int i = 1 ; i < 6; i ++){
                int div = (random.nextInt(10)+1) % 2;
                ItemStatus status = (div == 0 ? ItemStatus.REGISTERED : ItemStatus.UNREGISTERED);

                Item item = Item.builder()
                        .partner(p)
                        .status(status)
                        .name(category.getTitle()+i+"호")
                        .title(p.getName()+"의 OO 상품"+i+"호")
                        .price(BigDecimal.valueOf((long)random.nextInt(999999)+1))
                        .content(p.getName()+"의 OO상품"+i+"호"+"의 EE 상품 입니다")
                        .brandName(p.getName())
                        .registeredAt(getRandomDate())
                        .unregisteredAt(status.equals(ItemStatus.UNREGISTERED) ? getRandomDate() : null )
                        .build();
                itemRepository.save(item);
            }
        }
    }


    @Test
    public void createDutyFree(){

        CategoryType type = CategoryType.DUTY_FREE;
        Category category = categoryRepository.findByType(type).get();
        List<Partner> partnerList = partnerRepository.findByCategory(category);

        for(Partner p : partnerList){

            for(int i = 1 ; i < 6; i ++){
                int div = (random.nextInt(10)+1) % 2;
                ItemStatus status = (div == 0 ? ItemStatus.REGISTERED : ItemStatus.UNREGISTERED);

                Item item = Item.builder()
                        .partner(p)
                        .status(status)
                        .name(category.getTitle()+i+"호")
                        .title(p.getName()+"의 면세상품"+i+"호")
                        .price(BigDecimal.valueOf((long)random.nextInt(999999)+1))
                        .content(p.getName()+"의 면세상품"+i+"호"+"의 면세 상품 입니다")
                        .brandName(p.getName())
                        .registeredAt(getRandomDate())
                        .unregisteredAt(status.equals(ItemStatus.UNREGISTERED) ? getRandomDate() : null )
                        .build();
                itemRepository.save(item);
            }
        }
    }


    @Test
    public void createBeauty(){

        CategoryType type = CategoryType.BEAUTY;
        Category category = categoryRepository.findByType(type).get();
        List<Partner> partnerList = partnerRepository.findByCategory(category);

        for(Partner p : partnerList){

            for(int i = 1 ; i < 6; i ++){
                int div = (random.nextInt(10)+1) % 2;
                ItemStatus status = (div == 0 ? ItemStatus.REGISTERED : ItemStatus.UNREGISTERED);

                Item item = Item.builder()
                        .partner(p)
                        .status(status)
                        .name(category.getTitle()+i+"호")
                        .title(p.getName()+"의 OO 화장품"+i+"호")
                        .price(BigDecimal.valueOf((long)random.nextInt(99999)+1))
                        .content(p.getName()+"의 OO 화장품"+i+"호"+"의 상품 입니다")
                        .brandName(p.getName())
                        .registeredAt(getRandomDate())
                        .unregisteredAt(status.equals(ItemStatus.UNREGISTERED) ? getRandomDate() : null )
                        .build();
                itemRepository.save(item);
            }
        }
    }

    private LocalDateTime getRandomDate(){
        return LocalDateTime.of(2019,getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber());
    }

    private int getRandomNumber(){
        return random.nextInt(11)+1;
    }
}
