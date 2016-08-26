
package com.qtz_ht_timer.util;  
/** 
 * ClassName:TimeConfig <br/> 
 * Function: TODO (). <br/> 
 * Reason:   TODO (). <br/> 
 * Date:     2016年6月3日 下午7:13:08 <br/> 
 * @author   yxd 
 * @version   
 * @see       
 */
public class TimeConfig {
	
    public static final int TASK_TYPE_OF_HTSTUFF = 1;//商家流水线
    public static final int TASK_TYPE_OF_ADMIN = 2 ;//平台流水线
    
    public static final String exeDataDtr= "00:00";//执行任务时间 
    
    /**
     * 对账状态:0完成,1冻结中
     */
    public static final int  RECONCILIACTION_STATE_FINISHED = 0 ;//:0完成
    
    public static final int  RECONCILIACTION_STATE_FORZEN = 1 ;//1冻结中
    
    /**
     * 商家货款解冻流水bean 名称
     */
    public static final String SUPP_THAW_FORZEN_RECONCILIACTION = "merchantLoanThawImpl";
    /**
     * 平台货款解冻流水bean 名称
     */
    public static final String PLATFORM_THAW_FORZEN_RECONCILIACTION = "paymentPlatformThawImpl";
    
    /**
     * 开始bean名称
     */
    public static final String MAIN_START_RECONCILIACTION = "mainAutoHandleImpl";
    
    
    /**
     * 主任务启动的编号
     */
    public static final long 	MAIN_START_NO = 10000L;
    
    /**
     * 测试开始时间
     */
    public static final long EXE_TIME_CONFIG_STR = 1000*60;
    
    /**
     * 周期性的测试时间
     */
    public static final String EXE_TIME_CONFIG_TEST = "0 0/2 * * * ?";
    
    
    
    /**
     * 判断是否是第一次启动
     */
    public static volatile boolean isFirstStart = true;
    /**
     * 0 0 12 * * ?
     */
    public static String PRIFIX_EXE_TIME_STR = "0 0 ";//前缀
    public static String SUFFIX_EXE_TIME_STR = " * * ?";//后缀
}
  