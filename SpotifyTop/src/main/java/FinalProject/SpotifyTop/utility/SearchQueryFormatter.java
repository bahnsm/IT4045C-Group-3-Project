package finalproject.spotifytop.utility;

public class SearchQueryFormatter {

	public static String format(final String searchQuery) {
		return (searchQuery.trim()).replace(" ", "+");
	}
}
