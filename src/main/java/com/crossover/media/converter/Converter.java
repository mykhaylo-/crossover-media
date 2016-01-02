package com.crossover.media.converter;

public interface Converter<S,T> {
	
	T convert(S source);
}
