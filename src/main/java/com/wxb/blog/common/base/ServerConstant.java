package com.wxb.blog.common.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerConstant {
    /**
     * 接口返回错误码
     *
     * @author 陈如松
     *
     */
    public static class Error {

        public static final int SUCCESS = 0;

        /**
         * 服务内部异常. 用于soa内部出现的各种异常
         */
        public static final int SYSTEM_INNER_ERROR = 1001;
        /**
         * 调用服务化接口错误. 调用其他soa或thrift接口出错
         */
        public static final int CALL_SERVER_INTERFACE_FAIL = 2001;
        /**
         * 调用三方接口出错 调用其他公司或平台接口出错
         */
        public static final int CALL_OPEN_INTERFACE_FAIL = 3001;
        /**
         * 请求参数不合法. 其他系统调用mobile_server_api参数不合法
         */
        public static final int PARAMS_INAVAILABLE = 4001;
        /**
         * 没有可用的数据 调用接口的参数找不到对应的合法数据，例如:通过用户id找不到用户信息
         */
        public static final int NO_AVALIABLE_DATA = 5001;
        /**
         * 没有权限
         */
        public static final int PERMISSION_DENY = 6001;

    }

    /**
     * 应用类型，所有的接口都使用该类型. 不同应用涉及的表中应用类型的取值不同，请在自己的接口中进行转换
     *
     * @author 陈如松
     *
     */
    public static class AppType {

        public static final int OTHER = 0;
        /**
         * 买家版
         */
        public static final int BUYER = 0B1;
        /**
         * 钉耙
         */
        public static final int SELLER = 0B10;
        /**
         * 触屏版
         */
        public static final int WAP = 0B100;
        /**
         * 微信
         */
        public static final int WECHAT = 0B1000;

        public static final int ALL = BUYER | SELLER | WAP | WECHAT;
    }

    /**
     * 系统类型
     *
     * @author guopeng
     *
     */
    public static class SystemType {

        public static final int ALL = 0;
        /**
         * 买家版
         */
        public static final int BUYER = 1;
        /**
         * 钉耙
         */
        public static final int SELLER = 2;
        /**
         * 触屏版
         */
        public static final int WAP = 3;
        /**
         * 微信
         */
        public static final int WECHAT = 4;

        /**
         * 新boss后台
         */
        public static final int BOSS = 5;

        /**
         * im后台
         */
        public static final int IM = 6;

        /**
         * 推送后台
         */
        public static final int PUSH = 7;
        /**
         * server
         */
        public static final int SOA_SERVER = 8;

        /**
         * 活动后台
         */
        public static final int OPERATIONS = 9;

        /**
         * 优惠劵后台
         */
        public static final int PROMO = 10;

        /**
         * 优惠劵SOA
         */
        public static final int SOA_PROMO = 11;

        /**
         * 动态圈子SOA
         */
        public static final int SOA_COMMUNITY = 12;

        public static final List<Integer> SYSTEMTYPE = new ArrayList<Integer>(
                Arrays.asList(SystemType.ALL, SystemType.BOSS, SystemType.IM, SystemType.PUSH,
                        SystemType.BUYER, SystemType.OPERATIONS,
                        SystemType.PROMO, SystemType.SELLER,
                        SystemType.SOA_COMMUNITY, SystemType.SOA_PROMO,
                        SystemType.SOA_SERVER, SystemType.WAP,
                        SystemType.WECHAT));
    }

    /**
     * 终端类型
     *
     * @author 陈如松
     *
     */
    public static class TerminalType {

        public static final int ALL = 0;

        public static final int ANDROID = 1;

        public static final int IOS = 2;

        public static final int WIN_PHONE = 3;

        public static final int WIN_PAD = 4;

        public static final int WECHAT = 5;

        public static final int WEB = 6;

        public static final int OTHER = 7;
    }

    /**
     * 推送
     *
     * @author 陈如松
     * 过期，使用MuptyPushType
     */
    @Deprecated
    public static class PushType {
        /**
         * ios apns推送
         */
        public static final String APNS = "apns";
        /**
         * Android 百度推送
         */
        public static final String BAIDU_OLD = "baidu_old";

    }

    /**
     * 推送类型,
     * 不固定推送类型。与数据库wl_account_mobile中推送类型一致
     * @author 陈如松
     * @author crs
     *
     */
    public static class MuptyPushType {

        public static final int IOS_APNS = 1;

        public static final int ANDROID_HUAWEI = 2;

        public static final int ANDROID_XIAOMI = 3;

        public static final int ANDROID_OLD_BAIDU = 4;

    }

    /**
     * 类目常量
     *
     * @author 陈如松
     *
     */
    public static class CategoryType {
        /**
         * 兴趣类目
         */
        public static final int INTEREST = 3;
        /**
         * 买家版显示的导购类目
         */
        public static final int GUID_BUYER = 4;
        /**
         * 触屏版显示的导购类目
         */
        public static final int GUID_WAP = 5;
        /**
         * 买家版发布类目
         */
        public static final int PUB_BUYER = 6;
        /**
         * 触屏版发布类目
         */
        public static final int PUB_WAP = 7;
    }

    /**
     * 后台系统类型
     *
     * @author 陈如松
     *
     */
    public static class PermissionSystemType {
        /**
         * boss系统
         */
        public static final int BOSS = 1;

    }
}
