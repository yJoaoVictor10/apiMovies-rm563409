package br.com.fiap.movies.serices;

import br.com.fiap.movies.models.Movie;
import br.com.fiap.movies.repositories.MovieRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    public List<Movie> getMovies(){
        return repository.findAll();
    }

    public Movie addMovie(Movie movie){
        return repository.save(movie);

    }

    // optional - projeto do null pointer exception
    public Optional<Movie> getMovieById(Long id) { // Optional para evitar Null Pointer Exception. Representa um valor que pode existir ou não
        return repository.findById(id);
       /*return repository.stream()
                .filter(movie -> movie.getId().equals(id) )
                .findFirst(); // retornar o primeiro elemento de um stream que satisfaça a condição aplicada anteriormente*/
    }


    public void deleteMovie(Long id) {
        var optionalMovie = getMovieById(id);
        if(optionalMovie.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado");
        }
        repository.deleteById(id);
    }

    public Movie updateMovie(Long id, Movie newMovie) {
        var optionalMovie = getMovieById(id);
        if(optionalMovie.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado");
        }
        var movie = optionalMovie.get();
        //BeanUtils.copyProperties(newMovie, movie, "id"); //Útil para copiar um objeto e jogar em outro, o terceiro parâmetro (opcional) serve para ignorar uma propriedade
        newMovie.setId(id);
        return repository.save(newMovie);
    }
}
