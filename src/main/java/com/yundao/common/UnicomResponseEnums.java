package com.yundao.common;

public enum  UnicomResponseEnums {
    SYSTEM_ERROR("-001","系统异常"),
    BAD_REQUEST("-002","错误的请求参数"),
    NOT_FOUND("-003","找不到请求路径！"),
    CONNECTION_ERROR("-004","网络连接请求失败！"),
    METHOD_NOT_ALLOWED("-005","不合法的请求方式"),
    DATABASE_ERROR("-006","数据库异常"),
    BOUND_STATEMENT_NOT_FOUNT("-007","找不到方法！"),
    DELETE_FAIL("-008", "删除失败"),
    UPDATE_FAIL("-009", "更新失败"),
    FAIL_REGISTER("001","注册失败"),
    NO_USER_EXIST("002","用户名不存在"),
    INVALID_PASSWORD("003","密码错误"),
    NO_PERMISSION("004","非法请求！"),
    SUCCESS_OPTION("005","操作成功！"),
    REPEAT_JOINCLASS("007","重复加入班级"),
    FAIL_GETDATA("008","获取信息失败"),
    BAD_REQUEST_TYPE("009","错误的请求类型"),
    INVALID_MOBILE("010","无效的手机号码"),
    INVALID_EMAIL("011","无效的邮箱"),
    INVALID_NAME("012","无效的用户名"),
    REPEAT_NAME("013","已存在此用户名"),
    REPEAT_MOBILE("014","已存在此手机号"),
    REPEAT_EMAIL("015","已存在此邮箱地址"),
    NO_RECORD("016","没有查到相关记录"),
    LOGIN_SUCCESS("017","登录成功"),
    LOGOUT_SUCCESS("018","已退出登录"),
    SENDEMAIL_SUCCESS("019","邮件已发送，请注意查收"),
    EDITPWD_SUCCESS("020","修改密码成功"),
    No_FileSELECT("021","未选择文件"),
    FILEUPLOAD_SUCCESS("022","上传成功"),
    NOLOGIN("023","未登录"),
    INSERT_FAIL("024","添加数据失败"),
    ERROR_IDCODE("025","验证码不正确"),
    INVALID_CLASS("026","无效的班级名称"),
    INVALID_COURSE("027","无效的课程名称"),
    INVALID_TIME("028","无效的课时"),
    INVALID_COLLEGE("029","无效的学院名称"),
    INVALID_TERM("030","无效的学期名称");

    private String code;
    private String msg;
    private UnicomResponseEnums(String code, String msg) {

        this.code = code;
        this.msg = msg;
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
}
