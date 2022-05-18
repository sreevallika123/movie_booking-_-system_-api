package com.multiplex.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multiplex.dtos.MovieDTO;
import com.multiplex.models.Movie;
import com.multiplex.repos.MovieRepository;
import com.multiplex.utils.StorageService;

@Service
public class MovieService {

	@Autowired private MovieRepository repo;
	@Autowired private StorageService storage;
	
	public void save(MovieDTO dto) {
		Movie movie=new Movie();
		movie.setMovieName(dto.getMovieName());
		String poster=storage.store(dto.getPhoto());
		movie.setPoster(poster);
		repo.save(movie);
	}
	
	public List<Movie> listall(){
		return repo.findAll();
	}
	
	public Movie findById(int id) {
		return repo.getById(id);
	}
	
	public void deleteMovie(int id) {
		repo.delete(repo.getById(id));
	}
}
