package hello.item_service.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private Long id;
    private String itemName;
    private Integer price=0;
    private int quantity=0;

    public Item(){
    }
    //

    //DTO
    public Item(String itemName, Integer price, Integer quantity){
        this.itemName=itemName;
        this.price=price;
        this.quantity=quantity;
    }
}
