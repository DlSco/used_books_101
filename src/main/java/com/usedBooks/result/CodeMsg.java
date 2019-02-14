package com.usedBooks.result;


//代码匹配异常类

public class CodeMsg {
    private int code;
    private String msg;

    //通用的错误码
    public static CodeMsg SUCCESS = new CodeMsg(1, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    public static CodeMsg REQUEST_ILLEGAL = new CodeMsg(500102, "请求非法");
    public static CodeMsg ACCESS_LIMIT_REACHED= new CodeMsg(500104, "访问太频繁！");
    //登录模块 5002XX
    public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "Session不存在或者已经失效");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "手机号不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500213, "手机号格式错误");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500214, "手机号不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "密码错误");
    public static CodeMsg ADMIN_NOT_EXIST = new CodeMsg(500216, "用户名不存在");
    public static CodeMsg ADMIN_NOT_LOGIN = new CodeMsg(500217, "请登录再访问");
    public static CodeMsg USER_COLD_ERROR = new CodeMsg(500218, "您的用户已被冻结，请联系客服！");
    public static CodeMsg SMS_VERIFICATION_CODE= new CodeMsg(500219, "系统异常，请重新获取验证码");
    public static CodeMsg SMS_FREQUENTLY_CODE= new CodeMsg(500220, "登录过于频繁，请5分钟后再登录");
    public static CodeMsg USER_FREQUENTLY_LOGIN= new CodeMsg(500221, "登录过于频繁，请1分钟后再登录");
    public static CodeMsg USER_CODE_ERROR = new CodeMsg(500222, "验证码错误");
    public static CodeMsg USER_NOT_GETCODE = new CodeMsg(500222, "请获取验证码");
    public static CodeMsg MOBILE_EXITS_REGISTER = new CodeMsg(500223, "手机号已注册，请直接登录！");
    public static CodeMsg USER_FREQUENTLY_REGISTERCODE= new CodeMsg(500224, "获取验证码过于频繁，请5分钟后再获取验证码！");
    public static CodeMsg USER_FREQUENTLY_REGSTER= new CodeMsg(500225, "注册过于频繁，请1分钟后再注册");
    public static CodeMsg USER_FREQUENTLY_PASSWORD= new CodeMsg(500226, "修改密码过于频繁，请1分钟后再修改密码");
    public static CodeMsg USER_RESETPASSWORDCODE_ERROR = new CodeMsg(500227, "系统异常，请重新修改密码！");



    //商品模块 5003XX


    //订单模块 5004XX
    public static CodeMsg ORDER_NOT_EXIST = new CodeMsg(500400, "订单不存在");

    //秒杀模块 5005XX
    public static CodeMsg MIAO_SHA_OVER = new CodeMsg(500500, "商品已经秒杀完毕");
    public static CodeMsg REPEATE_MIAOSHA = new CodeMsg(500501, "不能重复秒杀");
    public static CodeMsg MIAOSHA_FAIL = new CodeMsg(500502, "秒杀失败");
    public static CodeMsg MIAOSHA_END = new CodeMsg(500503, "商品活动结束！");
    public static CodeMsg MIAOSHA_NULLBEGIN = new CodeMsg(500504, "商品活动尚未开始！");
    public static CodeMsg MIAOSHA_NULLGOOD = new CodeMsg(500505, "商品库存不足！");
    public static CodeMsg MIAOSHA_OUTNUMBER = new CodeMsg(500506, "您购买的商品数量已超过规定额度！");
    public static CodeMsg MIAOSHA_READNULL = new CodeMsg(500507, "您购买的商品有未确认的订单，请支付后再购买！");
    public static CodeMsg MIAOSHA_ORDERNULL = new CodeMsg(500507, "您购买的商品有未支付的订单，请支付后再购买！");
    public static CodeMsg MIAOSHA_WAIT = new CodeMsg(500508, "您有订单正在排队中！");


    private CodeMsg( ) {
    }


    public CodeMsg( int code,String msg ) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }


}
