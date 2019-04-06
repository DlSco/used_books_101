package com.usedBooks.redis;

public class UserKey extends BasePrefix {

    public UserKey(String prefix) {
        super(prefix);
    }

    public static final int TOKEN_EXPIRE = 3600*24 * 2;
    /**
     *
     * @param expireSeconds  有效时间
     * @param prefix  key前缀
     */
    public UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static UserKey getByUserName = new UserKey(TOKEN_EXPIRE, "username");
    public static UserKey token = new UserKey(TOKEN_EXPIRE, "tk");
    public static UserKey getByPhone = new UserKey(TOKEN_EXPIRE,"phone");
}
