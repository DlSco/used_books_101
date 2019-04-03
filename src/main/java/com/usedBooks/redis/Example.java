package com.usedBooks.redis;

public class Example extends BasePrefix{

	private Example(String prefix) {
		super(prefix);
	}

	/**
	 *
	 * @param expireSeconds  有效时间
	 * @param prefix  key前缀
	 */
	private Example(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}
	public static Example getById = new Example(30, "id");
	public static Example getByName = new Example(30, "name");
}
