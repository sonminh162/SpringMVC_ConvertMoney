package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MoneyConvertController {
    @GetMapping("/")
    public String convert(){
        return "index";
    }

    @PostMapping("/")
    public String convert(@RequestBody String request, Model model){
        String[] array = request.split("&");
        String[] arrayType = array[0].split("=");
        String[] arrayMoney = array[1].split("=");

        float money;
        String type = arrayType[1];
        model.addAttribute("type",type);
        float result = 0;
        try{
            money = Float.parseFloat(arrayMoney[1]);
            switch (type) {
                case "usd":{
                    result=money/22000;
                    break;
                }
                case "vnd":{
                    result=money*22000;
                    break;
                }
                default:
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        model.addAttribute("result",result);
        return "result";
    }
}
