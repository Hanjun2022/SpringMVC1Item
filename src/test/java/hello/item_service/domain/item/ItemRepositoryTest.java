package hello.item_service.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ItemRepositoryTest {

    ItemRepository itemRepository=new ItemRepository();

    @AfterEach
    //테스트가 끝날때마다 지워줌
    void afterEach(){
        itemRepository.clearStore();
    }
    @Test
    void save(){
        //given
        Item item=new Item("itemA",1000,10);
        //when
        Item saveItem=itemRepository.save(item);
        //then
        Item finditem=itemRepository.findById(item.getId());
        //아이디를 찾고 있는 테스트 alt+엔터
       assertThat(finditem).isEqualTo(saveItem);
       //
    }
    @Test
    void findAll(){
        //given
        Item item1=new Item("item1",10000,10);
        Item item2=new Item("item2",20000,20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> result=itemRepository.findAll();
        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(item1,item2);
    }
    @Test
    void update(){
        //given
        Item item=new Item("item1",10000,10);
        Item savedItem=itemRepository.save(item);
        //저장 한 것을 가져옴
        Long itemId=savedItem.getId();
        //when
        Item updateParam=new Item("item2",20000,30);
        itemRepository.update(itemId,updateParam);

        //then
        Item findItem=itemRepository.findById(itemId);

        Assertions.assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());

        Assertions.assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        Assertions.assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

}
