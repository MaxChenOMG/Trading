package test.study.runleocat.malluser.malluser.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import test.study.runleocat.malluser.malluser.controller.po.MallUserParam;
import test.study.runleocat.malluser.malluser.controller.vo.MallGood;
import test.study.runleocat.malluser.malluser.dao.entity.MallUser;
import test.study.runleocat.malluser.malluser.service.MallUserService;
import test.study.runleocat.malluser.utils.Result;
import test.study.runleocat.malluser.utils.ServiceResult;
import test.study.runleocat.malluser.utils.VerifyCodeUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/malluser-manage-api/v1")
public class MallUserController {
    //生成验证码图片
    @GetMapping("/verifyImg")
    public void generateVerifyImg(HttpSession session,
                                  HttpServletResponse response)
            throws IOException {
        //生成随机验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //将验证码存入session，便于与用户的输入进行比对
        session.setAttribute("code",code);
        //设置响应的类型
        response.setContentType("image/png");
        //将验证码写入到图片中，并通过response响应图片
        ServletOutputStream os = response.getOutputStream();
        VerifyCodeUtils.outputImage(180,50,os,code);
    }

    //校验验证码
    @PostMapping("matchVerifyCode")
    @ResponseBody
    public Result matchVerifyCode(@RequestParam String verifyCode, HttpSession session ){
        String code = session.getAttribute("code").toString();
        System.out.println("code = " + code);
        System.out.println("verifyCode = " + verifyCode);
        if (code.equalsIgnoreCase(verifyCode)) {
            return Result.success();
        }else{
            return Result.error("200","验证码输入错误，请重新输入！");
        }
    }
    @Resource
    private MallUserService mallUserService;

    @PutMapping(value = "/userLogin")
//    @RequestMapping(value = "/userLogin", method = RequestMethod.PUT)
    @ResponseBody
    public Result userLogin(@RequestBody @Validated MallUserParam mallUserParam){
        String mallUserName = mallUserParam.getMallUserName();
        String mallUserPassword = mallUserParam.getMallUserPassword();
        ServiceResult sr = mallUserService.doLogin(mallUserName,mallUserPassword);
        if("0".equals(sr.getCode())){
            return Result.success(sr.getData());
        }else {
            return Result.error(sr.getCode(),sr.getMsg());
        }
    }
    @PutMapping(value = "/userRegister")
    @ResponseBody
    public Result userRegister(@RequestBody MallUserParam mallUserParam){
        String mallUserName = mallUserParam.getMallUserName();
        String mallUserPassword = mallUserParam.getMallUserPassword();
        String mallUserRealName = mallUserParam.getMallUserRealName();
        String mallUserGender = mallUserParam.getMallUserGender();
        //根据用户名查询user对象
        //mallOrder mallOrderImpl.get
        MallUser userDB = mallUserService.findUserByUserName(mallUserName);
        if (!ObjectUtils.isEmpty(userDB)) {  //用户名已存在
            return Result.error("301","此用户名已存在，请重新输入");
        }else  if (!mallUserService.mallUserRegister(mallUserName,mallUserRealName,mallUserPassword,mallUserGender)) {   //执行添加
            return Result.error("302","注册失败！");
        }
        MallUser returnMallUser = new MallUser();
        returnMallUser.setMallUserName(mallUserName);
        returnMallUser.setMallUserPassword(mallUserPassword);
        returnMallUser.setMallUserRealName(mallUserRealName);
        returnMallUser.setMallUserGender(mallUserGender);

        return Result.success(returnMallUser);

    }

    private RestTemplate restTemplate;
    @Resource
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    @PutMapping(value = "/getMallUserGoodsRemoteByPut")

    @RequestMapping(value = "/getMallUserGoodsRemoteByPut", method = RequestMethod.POST)
    @ResponseBody
    public Result getMallUserGoodsRemoteByPut(@RequestBody MallUserParam mallUserParam){
        System.out.println("getMallUserGoodsRemoteByPut");

        // get goodid of this user from OrderService
        JSONObject postJsonObject1 = new JSONObject();
        String mallUserName = mallUserParam.getMallUserName();
        postJsonObject1.put("mallUserName",mallUserName);
        ResponseEntity<Object> objectResponseEntity1 = restTemplate.postForEntity("http://localhost:8071/mallorder-manage-api/v1/getMallGoodIdsByUserNamePost", postJsonObject1, Object.class);
        MediaType contentType1 = objectResponseEntity1.getHeaders().getContentType();
        System.out.println(contentType1);
        System.out.println("return message is:" + objectResponseEntity1.getBody());

        Object restMallUserGoodObj1 = objectResponseEntity1.getBody();
        JSONObject restMallUserJsonObj1 = JSONObject.fromObject(restMallUserGoodObj1);

//        Map<String,Class> childClass = new HashMap<String,Class>();
//        childClass.put("data", MallGood3.class);



        JsonConfig config1 = new JsonConfig();
        config1.setRootClass(Long.class);
//        config.setClassMap(childClass);
        List<Long> mallGoodIdList= (List<Long>) JSONArray.toCollection((JSONArray) restMallUserJsonObj1.get("data"),config1);

        // get mall good information
//        Long[] mallGoodIds = new Long[mallGoodIdList.size()];
//        mallGoodIdList.toArray(mallGoodIds);
        JSONObject postJsonObject2 = new JSONObject();
//        postJsonObject2.put("mallGoodsList",mallGoodIds);
        postJsonObject2.put("mallGoodsList",mallGoodIdList);
        ResponseEntity<Object> objectResponseEntity2 = restTemplate.postForEntity("http://localhost:8072/mallgoods-manage-api/v1/getMallGoodsByPost", postJsonObject2, Object.class);
        MediaType contentType2 = objectResponseEntity2.getHeaders().getContentType();
        System.out.println(contentType2);
        System.out.println("return message is:" + objectResponseEntity2.getBody());

        Object restMallUserGoodObj = objectResponseEntity2.getBody();
        JSONObject restMallUserJsonObj2 = JSONObject.fromObject(restMallUserGoodObj);

//        Map<String,Class> childClass = new HashMap<String,Class>();
//        childClass.put("data", MallGood3.class);



        JsonConfig config2 = new JsonConfig();
        config2.setRootClass(MallGood.class);
//        config.setClassMap(childClass);
        List<MallGood> mallGood3s= (List<MallGood>) JSONArray.toCollection((JSONArray) restMallUserJsonObj2.get("data"),config2);
        //build package

        return Result.success(mallGood3s);
    }
}
