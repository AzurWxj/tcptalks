package dao;

import java.util.HashMap;
import java.util.Map;

public class StatusMapper {
    public static final int
            SUCCESS=0,
            UNKNOWN_STATUS=-1,
            DUPLICATE_KEY=1062,
            CORRUPTED_RECORD=-2;

    private static Map<Integer,String> errorCodeMapper=new HashMap<>();
    static {
        errorCodeMapper.put(1062,"主键重复");
        errorCodeMapper.put(SUCCESS,"数据库操作成功");
        errorCodeMapper.put(CORRUPTED_RECORD,"记录字段不完整或非法，无法进行相关数据库操作");
    }
    public static String interpretJDBCStatusCode(int code){
        String reason=errorCodeMapper.get(code);
        if(reason==null){
            reason="未知的数据库错误";
        }
        return reason;
    }
}
