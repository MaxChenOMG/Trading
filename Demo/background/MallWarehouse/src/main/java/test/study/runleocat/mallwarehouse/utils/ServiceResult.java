package test.study.runleocat.mallwarehouse.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//响应结果封装类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResult<T> {
    private String code;
    private String msg;
    private T data;

    public ServiceResult(T data) {
        this.data = data;
    }

    public static ServiceResult success() {
        ServiceResult result = new ServiceResult<>();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    //响应成功时：状态码和提示信息确定，其中的数据不确定
    public static <T> ServiceResult<T> success(T data) {
        ServiceResult<T> result = new ServiceResult<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    //响应失败时：状态码和提示信息都不确定，其中无数据
    public static ServiceResult error(String code, String msg) {
        ServiceResult result = new ServiceResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
