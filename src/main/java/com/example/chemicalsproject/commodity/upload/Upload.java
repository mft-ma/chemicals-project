package com.example.chemicalsproject.commodity.upload;

import com.alibaba.fastjson.JSONObject;
import com.example.chemicalsproject.commodity.service.CommodityService;
import com.example.chemicalsproject.pojo.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@CrossOrigin
public class Upload {
    @Autowired
    CommodityService commodityService;

    /*
     * 图片上传测试
     */
    @RequestMapping("/upload")
    @ResponseBody
    public JSONObject upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id, HttpServletRequest request) throws IOException {
//    ResponseEntity responseEntity=new ResponseEntity()
        System.out.println(id);
        JSONObject json = new JSONObject();
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));//后缀
            String uuidname = UUID.randomUUID().toString();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = simpleDateFormat.format(new Date());
            String imgName = dateStr + uuidname + suffix;
//            this.uploadStatic(file,imgName);
//            file.transferTo(new File("D:/environment/apache-tomcat-8.0.35/webapps/down/"+imgName));
            file.transferTo(new File("D:/app/idea/element-template/public/image/" + imgName));
            json.put("code", 0);
            json.put("msg", "success");
            json.put("src", dateStr + uuidname + suffix);
            Commodity commodity = new Commodity();
            commodity.setSid(id);
            commodity.setImgStatus("1");
            commodity.setImgPath(dateStr + uuidname + suffix);
            commodityService.updateById(commodity);
        }
        return json;
    }
}
