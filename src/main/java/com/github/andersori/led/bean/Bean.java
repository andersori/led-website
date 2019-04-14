package com.github.andersori.led.bean;

public interface Bean<T> {
	
	public void toBean(T entity);
	
	public T toEntity();
}
