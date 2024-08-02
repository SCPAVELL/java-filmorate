package ru.yandex.practicum.filmorate.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;
import java.util.Optional;

@Repository
public class FilmRepository extends BaseRepository<Film> {
	private static final String FIND_ALL_QUERY = "SELECT f.*, gnr.*, dtr.*, l.USER_ID, m.NAME AS MPA_NAME FROM FILMS f "
			+ "LEFT JOIN MPA m ON f.MPA_ID = m.MPA_ID "
			+ "LEFT JOIN (SELECT fg.FILM_ID, fg.GENRE_ID, g.NAME AS GENRE_NAME "
			+ "FROM FILM_GENRES fg INNER JOIN GENRES g ON fg.GENRE_ID = g.GENRE_ID) gnr "
			+ "ON f.FILM_ID = gnr.FILM_ID " + "LEFT JOIN (SELECT fd.FILM_ID, fd.DIRECTOR_ID, d.NAME AS DIRECTOR_NAME "
			+ "FROM FILM_DIRECTORS fd INNER JOIN DIRECTORS d ON fd.DIRECTOR_ID = d.DIRECTOR_ID) dtr "
			+ "ON f.FILM_ID = dtr.FILM_ID " + "LEFT JOIN LIKES l ON l.FILM_ID = f.FILM_ID";

	private static final String FIND_QUERY = "SELECT f.*, gnr.*, dtr.*, l.USER_ID, m.NAME AS MPA_NAME FROM FILMS f "
			+ "LEFT JOIN MPA m ON f.MPA_ID = m.MPA_ID "
			+ "LEFT JOIN (SELECT fg.FILM_ID, fg.GENRE_ID, g.NAME AS GENRE_NAME "
			+ "FROM FILM_GENRES fg INNER JOIN GENRES g ON fg.GENRE_ID = g.GENRE_ID) gnr "
			+ "ON f.FILM_ID = gnr.FILM_ID " + "LEFT JOIN (SELECT fd.FILM_ID, fd.DIRECTOR_ID, d.NAME AS DIRECTOR_NAME "
			+ "FROM FILM_DIRECTORS fd INNER JOIN DIRECTORS d ON fd.DIRECTOR_ID = d.DIRECTOR_ID) dtr "
			+ "ON f.FILM_ID = dtr.FILM_ID " + "LEFT JOIN LIKES l ON l.FILM_ID = f.FILM_ID " + "WHERE f.FILM_ID = ?";

	private static final String INSERT_QUERY = "INSERT INTO FILMS(NAME, DESCRIPTION, RELEASE_DATE, DURATION, MPA_ID)"
			+ "VALUES (?, ?, ?, ?, ?)";

	private static final String UPDATE_QUERY = "UPDATE FILMS SET DESCRIPTION = ?, NAME = ?, RELEASE_DATE = ?, DURATION = ?, MPA_ID = ? WHERE FILM_ID = ?";

	private static final String DELETE_QUERY = "DELETE FROM FILMS WHERE FILM_ID = ?";

	private static final String INSERT_LIKE_QUERY = "INSERT INTO LIKES(FILM_ID, USER_ID)" + "VALUES (?, ?)";

	private static final String DELETE_LIKE_QUERY = "DELETE FROM LIKES WHERE FILM_ID = ? AND USER_ID =?";

	private static final String INSERT_GENRE_QUERY = "INSERT INTO FILM_GENRES(FILM_ID, GENRE_ID) VALUES(?, ?)";

	private static final String DELETE_GENRE_QUERY = "DELETE FROM FILM_GENRES WHERE FILM_ID = ?";

	private static final String INSERT_DIRECTOR_QUERY = "INSERT INTO FILM_DIRECTORS(FILM_ID, DIRECTOR_ID) VALUES(?, ?)";

	private static final String DELETE_DIRECTOR_QUERY = "DELETE FROM FILM_DIRECTORS WHERE FILM_ID = ?";

	private static final String FILMS_RECOMMENDED_QUERY = "SELECT f.*, gnr.*, l.USER_ID, m.NAME AS MPA_NAME, dtr.*"
			+ "FROM likes l " + "JOIN films f ON f.film_id = l.film_id "
			+ "LEFT JOIN (SELECT fd.FILM_ID, fd.DIRECTOR_ID, d.NAME AS DIRECTOR_NAME " + "FROM FILM_DIRECTORS fd "
			+ "INNER JOIN DIRECTORS d ON fd.DIRECTOR_ID = d.DIRECTOR_ID) dtr " + "ON f.FILM_ID = dtr.FILM_ID "
			+ "LEFT JOIN MPA m ON f.MPA_ID = m.MPA_ID "
			+ "LEFT JOIN (SELECT fg.FILM_ID, fg.GENRE_ID, g.NAME AS GENRE_NAME "
			+ "FROM FILM_GENRES fg INNER JOIN GENRES g ON fg.GENRE_ID = g.GENRE_ID) gnr "
			+ "ON f.FILM_ID = gnr.FILM_ID "
			+ "WHERE l.user_id = (SELECT l2.user_id FROM likes l1 JOIN likes l2 ON l1.film_id = l2.film_id "
			+ "AND l1.user_id != l2.user_id WHERE l1.user_id = ? GROUP BY l2.user_id "
			+ "ORDER BY COUNT(*) DESC LIMIT 1) " + "AND l.film_id NOT IN (SELECT film_id FROM likes WHERE user_id = ?)";

	private String getQueryForSearch(List<String> by) {
		String partOfNameQuery = "LOWER(f.NAME) LIKE LOWER(CONCAT('%', ?, '%'))";
		String partOfDirectorQuery = "LOWER(dtr.DIRECTOR_NAME) LIKE LOWER(CONCAT('%', ?, '%'))";
		StringBuilder resultBy = new StringBuilder();
		by.forEach(i -> {
			if (i.equals("title")) {
				if (!resultBy.isEmpty()) {
					resultBy.append(" OR ");
				}
				resultBy.append(partOfNameQuery);
			}
			if (i.equals("director")) {
				if (!resultBy.isEmpty()) {
					resultBy.append(" OR ");
				}
				resultBy.append(partOfDirectorQuery);
			}
		});
		return FIND_ALL_QUERY + " WHERE " + resultBy;
	}

	public FilmRepository(JdbcTemplate jdbc, ResultSetExtractor<List<Film>> extractor) {
		super(jdbc, extractor);
	}

	public List<Film> getRecommendedFilms(Long userId) {
		return findMany(FILMS_RECOMMENDED_QUERY, userId, userId);
	}

	public List<Film> getFilms() {
		return findMany(FIND_ALL_QUERY);
	}

	public Optional<Film> getFilm(Long id) {
		return findOne(FIND_QUERY, id);
	}

	public Film addFilm(Film film) {
		long id = insert(INSERT_QUERY, film.getName(), film.getDescription(), film.getReleaseDate(), film.getDuration(),
				film.getMpa() == null ? null : film.getMpa().getId());
		film.setId(id);
		return film;
	}

	public Film changeFilm(Film newFilm) {
		update(UPDATE_QUERY, newFilm.getDescription(), newFilm.getName(), newFilm.getReleaseDate(),
				newFilm.getDuration(), newFilm.getMpa().getId(), newFilm.getId());
		return newFilm;
	}

	public void deleteFilm(Long id) {
		delete(DELETE_QUERY, id);
	}

	public void deleteGenres(Long filmId) {
		delete(DELETE_GENRE_QUERY, filmId);
	}

	public void deleteDirectors(Long filmId) {
		delete(DELETE_DIRECTOR_QUERY, filmId);
	}

	public void addLike(Long filmId, Long userId) {
		simpleInsert(INSERT_LIKE_QUERY, filmId, userId);
	}

	public void deleteLike(Long filmId, Long userId) {
		delete(DELETE_LIKE_QUERY, filmId, userId);
	}

	public void addGenre(Long filmId, Integer genreId) {
		simpleInsert(INSERT_GENRE_QUERY, filmId, genreId);
	}

	public void addDirector(Long filmId, long directorId) {
		simpleInsert(INSERT_DIRECTOR_QUERY, filmId, directorId);
	}

	public List<Film> search(String query, List<String> searchBy) {
		Object[] params = searchBy.stream().map(i -> query).toArray(String[]::new);
		return findMany(getQueryForSearch(searchBy), params);
	}
}