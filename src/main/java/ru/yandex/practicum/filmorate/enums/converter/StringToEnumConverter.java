package ru.yandex.practicum.filmorate.enums.converter;

import org.springframework.core.convert.converter.Converter;
import ru.yandex.practicum.filmorate.enums.Search;

import static ru.yandex.practicum.filmorate.enums.Search.getEnumValueByString;

public class StringToEnumConverter implements Converter<String, Search> {
	@Override
	public Search convert(String source) {
		return getEnumValueByString(source);
	}
}
