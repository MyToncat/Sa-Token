/*
 * Copyright 2020-2099 sa-token.cc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.dev33.satoken.sso.config;


import cn.dev33.satoken.sso.error.SaSsoErrorCode;
import cn.dev33.satoken.sso.exception.SaSsoException;
import cn.dev33.satoken.sso.function.DoLoginHandleFunction;
import cn.dev33.satoken.sso.function.NotLoginViewFunction;
import cn.dev33.satoken.sso.function.SendHttpFunction;
import cn.dev33.satoken.util.SaFoxUtil;
import cn.dev33.satoken.util.SaResult;

import java.io.Serializable;

/**
 * Sa-Token SSO 单点登录模块 配置类 （Server端）
 *
 * @author click33
 * @since 1.38.0
 */
public class SaSsoServerConfig implements Serializable {

    private static final long serialVersionUID = -6541180061782004705L;


    // ----------------- Server端相关配置

    /**
     * 指定当前系统集成 SSO 时使用的模式（约定型配置项，不对代码逻辑产生任何影响）
     */
    public String mode = "";

    /**
     * Ticket有效期 (单位: 秒)
     */
    public long ticketTimeout = 60 * 5;

    /**
     * 所有允许的授权回调地址，多个用逗号隔开 (不在此列表中的URL将禁止下放ticket)
     */
    public String allowUrl = "*";

    /**
     * 是否打开单点注销功能
     */
    public Boolean isSlo = true;

    /**
     * 是否打开模式三（此值为 true 时将使用 http 请求：校验ticket值、单点注销、获取userinfo）
     */
    public Boolean isHttp = false;

    /**
     * 是否校验参数签名（方便本地调试用的一个配置项，生产环境请务必为true）
     */
    public Boolean isCheckSign = true;


    // get set

    /**
     * 获取 指定当前系统集成 SSO 时使用的模式（约定型配置项，不对代码逻辑产生任何影响）
     *
     * @return /
     */
    public String getMode() {
        return this.mode;
    }

    /**
     * 设置 指定当前系统集成 SSO 时使用的模式（约定型配置项，不对代码逻辑产生任何影响）
     *
     * @param mode /
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return Ticket有效期 (单位: 秒)
     */
    public long getTicketTimeout() {
        return ticketTimeout;
    }

    /**
     * @param ticketTimeout Ticket有效期 (单位: 秒)
     * @return 对象自身
     */
    public SaSsoServerConfig setTicketTimeout(long ticketTimeout) {
        this.ticketTimeout = ticketTimeout;
        return this;
    }

    /**
     * @return 所有允许的授权回调地址，多个用逗号隔开 (不在此列表中的URL将禁止下放ticket)
     */
    public String getAllowUrl() {
        return allowUrl;
    }

    /**
     * @param allowUrl 所有允许的授权回调地址，多个用逗号隔开 (不在此列表中的URL将禁止下放ticket)
     * @return 对象自身
     */
    public SaSsoServerConfig setAllowUrl(String allowUrl) {
        this.allowUrl = allowUrl;
        return this;
    }

    /**
     * @return 是否打开单点注销功能
     */
    public Boolean getIsSlo() {
        return isSlo;
    }

    /**
     * @param isSlo 是否打开单点注销功能
     * @return 对象自身
     */
    public SaSsoServerConfig setIsSlo(Boolean isSlo) {
        this.isSlo = isSlo;
        return this;
    }

    /**
     * @return isHttp 是否打开模式三（此值为 true 时将使用 http 请求：校验ticket值、单点注销、获取userinfo）
     */
    public Boolean getIsHttp() {
        return isHttp;
    }

    /**
     * @param isHttp 是否打开模式三（此值为 true 时将使用 http 请求：校验ticket值、单点注销、获取userinfo）
     * @return 对象自身
     */
    public SaSsoServerConfig setIsHttp(Boolean isHttp) {
        this.isHttp = isHttp;
        return this;
    }

    /**
     * 获取 是否校验参数签名（方便本地调试用的一个配置项，生产环境请务必为true）
     *
     * @return isCheckSign 是否校验参数签名（方便本地调试用的一个配置项，生产环境请务必为true）
     */
    public Boolean getIsCheckSign() {
        return this.isCheckSign;
    }

    /**
     * 设置 是否校验参数签名（方便本地调试用的一个配置项，生产环境请务必为true）
     *
     * @param isCheckSign 是否校验参数签名（方便本地调试用的一个配置项，生产环境请务必为true）
     */
    public SaSsoServerConfig setIsCheckSign(Boolean isCheckSign) {
        this.isCheckSign = isCheckSign;
        return this;
    }

    /**
     * 以数组形式写入允许的授权回调地址
     * @param url 所有集合
     * @return 对象自身
     */
    public SaSsoServerConfig setAllow(String ...url) {
        this.allowUrl = SaFoxUtil.arrayJoin(url);
        return this;
    }

    @Override
    public String toString() {
        return "SaSsoServerConfig ["
                + "mode=" + mode
                + ", ticketTimeout=" + ticketTimeout
                + ", allowUrl=" + allowUrl
                + ", isSlo=" + isSlo
                + ", isHttp=" + isHttp
                + ", isCheckSign=" + isCheckSign
                + "]";
    }


    // -------------------- 所有回调函数 --------------------


    /**
     * SSO-Server端：未登录时返回的View
     */
    public NotLoginViewFunction notLoginView = () -> {
        return "当前会话在SSO-Server认证中心尚未登录（当前未配置登录视图）";
    };

    /**
     * SSO-Server端：登录函数
     */
    public DoLoginHandleFunction doLoginHandle = (name, pwd) -> {
        return SaResult.error();
    };

    /**
     * SSO-Server端：发送Http请求的处理函数
     */
    public SendHttpFunction sendHttp = url -> {
        throw new SaSsoException("请配置 Http 请求处理器").setCode(SaSsoErrorCode.CODE_30010);
    };

}
