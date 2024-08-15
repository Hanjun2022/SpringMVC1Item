package hello.item_service.web.basic;

import hello.item_service.domain.item.Item;
import hello.item_service.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
//
public class BasicItemController {
    private final ItemRepository itemRepository;

    //생성자가 하나이면 @Autowired를 생략할 수 있다.
    @GetMapping
    public String items(Model model){
        List<Item>items=itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }
    // RestCont X 아니고 ResponseBody가 없어서 화면뷰이름을 반환 (동적으로 이동)

    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA",10000,10));
        itemRepository.save(new Item("itemB",20000,20));
    }
    //Test용으로 만듬

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId,Model model){
        Item item=itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }
    // 하나를 찾아서 model에 값을 넣어준다. model에 item으로 넘겨준것을 동적뷰파일에서 item으로 꺼낸다.



    //폼을 보여주는 부분
    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }


    //같은 url인데 매서드로 능력을 구분해준다.  이  url에 같은 방식으로 보낸다.


  //  @PostMapping("/add")
    public String save(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                        Model model){
        Item item=new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        model.addAttribute("item",item);
        //저장된 결과물을 넣는 과정
        return "basic/item";
    }

   // @PostMapping("/add")
    public String AddItemV2(@ModelAttribute("item") Item item,Model model) {
//        Item item=new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);
        //위에꺼 대체 메시지 컨버터 느낌이네

        itemRepository.save(item);
        // model.addAttribute("item",item); 자동추가(model에 ("item")이 이름으로 넣어준다. 생략기능
        return "basic/item";
    }


//    // @PostMapping("/add")
//    public String AddItemV2(@ModelAttribute Item item,Model model) {
////        Item item=new Item();
////        item.setItemName(itemName);
////        item.setPrice(price);
////        item.setQuantity(quantity);
//        //위에꺼 대체 메시지 컨버터 느낌이네
//        //Item->item
//        //HelloData->helloData (이런식으로 첫글자만 바뀜)
//
//        itemRepository.save(item);
//        // model.addAttribute("item",item); 자동추가(model에 ("item")이 이름으로 넣어준다. 생략기능
//        return "basic/item";
//    }------------------------------------------------------------------------------------




    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId,Model model){
        Item item=itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }
    //상품 조회와 비슷하다.
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId,@ModelAttribute Item item){
    itemRepository.update(itemId,item);
       return "redirect:/basic/items/{itemId}";
    }

   // @PostMapping("/add")
    public String AddItemV5(@ModelAttribute("item") Item item,Model model){

        itemRepository.save(item);

        return "redirect:/basic/items/"+item.getId();

    }
    @PostMapping("/add")
    public String AddItemV6(@ModelAttribute("item") Item item, RedirectAttributes redirectAttributes){
        Item savedItem=itemRepository.save(item);
        redirectAttributes.addAttribute("itemId",savedItem.getId());
        //값이 밑에껄로 치환된다.
        redirectAttributes.addAttribute("status",true);
        //status 가 true이면 저장에서 넘어온것이다.
        return "redirect:/basic/items/{itemId}";

    }
}
