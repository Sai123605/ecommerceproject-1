package com.project.serice;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Category;
import com.project.repo.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository crepo;
	public Category saveCategory(Category c) {
	    return crepo.save(c); // return the saved entity
	}

	/*
	 * public void saveCategory(Category c) { crepo.save(c); }
	 */
	public List<Category> getAll() {
		return crepo.findAll();
	}
	
	public void deletebyId(int id) {
		crepo.deleteById(id);
	}
	
	public Optional<Category> fetchbyId(int id) {
		return crepo.findById(id);
	}
	


}

