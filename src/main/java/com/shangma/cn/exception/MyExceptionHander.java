package com.shangma.cn.exception;

import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.http.EnumStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/22 10:47
 * 文件说明：
 */
@RestControllerAdvice
public class MyExceptionHander {

    /**
     * 专门处理表单校验不成功的情况
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AxiosResult<Map<String, String>> handler(MethodArgumentNotValidException e) {
        Map<String, String> map = new HashMap<String, String>();
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasFieldErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (int i = 0; i < fieldErrors.size(); i++) {
                map.put(fieldErrors.get(i).getField(), fieldErrors.get(i).getDefaultMessage());
            }
        }
        return AxiosResult.error(EnumStatus.FORM_VALICATOR_ERROR, map);
    }

    /**
     * 专门处理表单校验不成功的情况
     */
    @ExceptionHandler(PermException.class)
    public AxiosResult<Void> handler(PermException e) {
        return AxiosResult.error(e.getEnumStatus());
    }
}
