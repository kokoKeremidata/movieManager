import java.util.*;
public class Main{
    public static void main(String[] args) {
        Set<Movie> movies = new LinkedHashSet<>();
        movies.add(new Movie("Cars","Animation"));
        movies.add(new Movie("Toy Story", "Animation"));
        movies.add(new Movie("The Incredibles", "Action"));

        new form(movies);
    }
}
class Movie{
    private String title;
    private String genre;
    public Movie(String title, String genre){
        this.title = title;
        this.genre = genre;
    }
    public String getTitle(){
        return title;
    }
    public String getGenre(){
        return genre;
    }
}