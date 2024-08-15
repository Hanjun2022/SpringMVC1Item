package hello.item_service.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
//Repository를 쓰면 Component에 대상이 된다 .
    // 동시접근 막고 싶으면 ConcurrentHashMap
//    private static final Map<Long,Item>store=new HashMap<>();
//    private static long sequence=0L;
//
//    public Item save(Item item){
//        item.setId(++sequence);
//        //id를 넣어주고
//        store.put(item.getId(),item);
//        //item을 넣어준다.
//        return item;
//    }
//    public Item findById(Long id){
//        return store.get(id);
//    }
//    public List<Item>findAll(){
//        return new ArrayList<>(store.values());
//    }
//
//    public void update(Long id,Item updateParam){
//        Item findItem=findById(id);
//        //업데이트 대상 item 을 찾아준다.
//        findItem.setItemName(updateParam.getItemName());
//
//        findItem.setPrice(updateParam.getPrice());
//        findItem.setQuantity(updateParam.getQuantity());
//        //DTO를 이용할 수 있다.
//    }
//    public void clearStore(){
//        store.clear();
//    }

    private final static Map<Long,Item>store=new HashMap<>();
    private  static Long sequence=0L;

    public Item save(Item item){
        item.setId(sequence++);
        store.put(item.getId(),item);
        return item;
    }
    public Item findById(Long id){
        return store.get(id);
    }
    public List<Item>findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long id,Item updateParam){
        Item finditem=store.get(id);
        finditem.setPrice(updateParam.getPrice());
        finditem.setItemName(updateParam.getItemName());
      finditem.setQuantity(updateParam.getQuantity());
    }
    public void clear(){ store.clear();
    }

}
