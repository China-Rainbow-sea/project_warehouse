package com.rainbowsea.util;


/**
 * 创建一个 Result 类
 * 用于接受和转换前端提交的 json ，交互的内容、
 * 1. 用于返回结果， 利于 json 格式 *
 * 2. 这个工具类， 在网上也可找到
 * 说明: 这里我们不使用 lombok 插件自动生成是，因为我们这里需要自行构建一些
 * 特殊的方法，来进行一个前后端信息的交互操作。
 */
public class Result<T> {

    private String code;  // 状态码
    private String msg; //  对状态码说明
    private T data;  // 返回时，携带的数据，为了扩展好，老师使用泛型


    // 无参构造器
    public Result() {
    }



    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    // 带参构造器--指定返回的 data
    public Result(T data) {
        this.data = data;
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 表示提交成功，显示的提示信息
     * 这个方法不需要我们传入参数，
     * 编写方法-返回需要的 Result对象-表示成功的 Result
     * @return
     */
    public static Result success() {
        Result result = new Result();
        result.setCode("200");
        result.setMsg("success");
        return result;
    }

    /**
     * 带参数的提交成功的
     * 特别注意：在 static 静态方法中是不可以使用泛型的，因为static 是比 泛型的先构造的，
     * 而 T 泛型是在实例化对象的适合，才创建的。
     * 所以想要使用在 static 静态方法当中使用 泛型的话，可以在 static<T> 当中加入 泛型即可,如下:
     * public static <T> Result<T> success(T data)
     * 编写方法-返回需要的 Result 对象-表示成功的 Result，同时可以携带数据
     * 如果需要在static 方法使用泛型，需要在 static <T>
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>(data);
        result.setCode("200");
        result.setMsg("success");
        return result;


    }


    /**
     * 提交错误的提示信息，带有参数的
     *
     * @param msg
     * @param code
     * @return
     */
    public static Result error(String msg, String code) {
        Result result = new Result();
        result.setMsg(msg);
        result.setData(code);

        return result;

    }


    /**
     * 提交失败的，错误信息
     * 编写方法：返回需要的 Result对象-表示成功的Result ，同时携带数据
     *
     * @param msg
     * @param code
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String msg, String code, T data) {
        Result<T> result = new Result<>(data);
        result.setMsg(msg);
        result.setCode(code);

        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
