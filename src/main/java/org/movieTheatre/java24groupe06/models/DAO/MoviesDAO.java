package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.models.Movie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoviesDAO extends AbstractDAO{

    public List<Movie.MovieBuilder> getDB() throws SQLException {
        List<DTO> DTOList =getListDTOMovie();
        List<Movie.MovieBuilder> movieBuilderList = new ArrayList<>();

        for (DTO dto :DTOList){
             movieBuilderList.add(initializeMovieBuilder(dto));
        }
        return movieBuilderList;
    }

    public List<DTO> getListDTOMovie() throws SQLException {;
        String query =String.format("SELECT * FROM Movies WHERE isShowing = true");
        return getListResult(query, rs -> new DTO(rs.getString("title"),
                                    rs.getInt("duration"),
                                    rs.getInt("movieID"),
                                    rs.getString("synopsis"),
                                    rs.getString("ReleaseDate"),
                                    rs.getString("Producer"),
                                    rs.getString("pathImg"),
                                    rs.getBoolean("isShowing"))
            );
    }

    private Movie.MovieBuilder initializeMovieBuilder(DTO dto) throws SQLException {
        Movie.MovieBuilder movieBuilder = new Movie.MovieBuilder()
                .setTitle(dto.getTitle())
                .setDuration(dto.getDuration())
                .setSynopsis(dto.getSynopsis())
                .setIsShowing(dto.isShowing())
                .setReleaseDate(dto.getReleaseDate())
                .setPathImg(dto.getPathImg())
                .setProducer(dto.getProducer())
                .setID(dto.getID());
        return movieBuilder;
    }
}
