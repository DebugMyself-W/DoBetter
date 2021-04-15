package com.better.modules.base.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonData implements Serializable {
    /**
     * 状态码 0 表示成功，1 表示处理中，-1表示失败
     */
    private Integer code;
    /**
     * 数据
     */
    private Object data;
    /**
     * 描述信息
     */
    private String msg;

    /**
     * 成功，无传入数据
     *
     * @return
     */
    public static JsonData buildSuccess() {
        return new JsonData(0, null, null);
    }

    /**
     * 成功，有传入数据
     *
     * @param data 数据
     * @return
     */
    public static JsonData buildSuccess(Object data) {
        return new JsonData(0, data, null);
    }

    /**
     * 失败，有返回错误信息
     *
     * @param msg 描述信息
     * @return
     */
    public static JsonData buildError(String msg) {
        return new JsonData(-1, null, msg);
    }

    /**
     * 失败，有状态码，描述信息
     *
     * @param code 状态码
     * @param msg  描述信息
     * @return
     */
    public static JsonData buildError(Integer code, String msg) {
        return new JsonData(code, null, msg);
    }

    /**
     * 是否返回成功
     * @param jsonData
     * @return
     */
    public static boolean isSuccess(JsonData jsonData) {
        return jsonData.getCode() == 0;
    }
}
