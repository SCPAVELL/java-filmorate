package ru.yandex.practicum.filmorate.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Search {
	title("title"), director("director"), titleAndDirector("title,director"), directorAndTitle("director,title");

	private static Map<String, Search> elements;

	static {
		elements = Arrays.stream(values()).collect(Collectors.toMap(e -> e.by, e -> e));
	}
	private String by;

	Search(String by) {
		this.by = by;
	}

	public static Search getEnumValueByString(String key) {
		return elements.get(key);
	}

	public String toString() {
		return this.by;
	}
}