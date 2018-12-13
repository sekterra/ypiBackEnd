package com.tommy.ypi.controller;

import com.tommy.ypi.exception.ResourceNotFoundException;
import com.tommy.ypi.model.Menu;
import com.tommy.ypi.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MenuController {
	@Autowired
	private MenuRepository menuRepository;

	@GetMapping("/menus")
	public Page<Menu> getMenus(Pageable pageable) {
		return menuRepository.findAll(pageable);
	}

	@PostMapping("/menus")
	public Menu createMenu(@Valid @RequestBody Menu menu) {
		return menuRepository.save(menu);
	}

	@PutMapping("/menus/{menuId}")
	public Menu updateMenu(@PathVariable Long menuId,
	                       @Valid @RequestBody Menu menuRequest) {
		return menuRepository.findById(menuId)
						.map(menu -> {
							menu.setName(menuRequest.getName());
							menu.setPath(menuRequest.getPath());
							menu.setOrderNo(menuRequest.getOrderNo());
							return menuRepository.save(menu);
						}).orElseThrow(() -> new ResourceNotFoundException("Menu not found with id " + menuId));
	}


	@DeleteMapping("/menus/{menuId}")
	public ResponseEntity<?> deleteMenu(@PathVariable Long menuId) {
		return menuRepository.findById(menuId)
						.map(menu -> {
							menuRepository.delete(menu);
							return ResponseEntity.ok().build();
						}).orElseThrow(() -> new ResourceNotFoundException("Menu not found with id " + menuId));
	}
}
