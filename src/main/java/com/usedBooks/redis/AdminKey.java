package com.usedBooks.redis;

public class AdminKey extends BasePrefix {
    public AdminKey(String prefix) {
        super(prefix);
    }

    public static final int TOKEN_EXPIRE = 3600*24 * 2;
    /**
     *
     * @param expireSeconds  有效时间
     * @param prefix  key前缀
     */
    public AdminKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static AdminKey getByUserName = new AdminKey(TOKEN_EXPIRE, "username");
    public static AdminKey token = new AdminKey(TOKEN_EXPIRE, "tk");
}
