package com.tommy.ypi.controller;

import com.tommy.ypi.exception.ResourceNotFoundException;
import com.tommy.ypi.model.ApiContents;
import com.tommy.ypi.repository.ApiContentsRepository;
import com.tommy.ypi.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ApiContentsController {
	@Autowired
	private ApiContentsRepository apiContentsRepository;

	@Autowired
	private MenuRepository menuRepository;

	@GetMapping("/menus/{menuId}/api_contents")
	public List<ApiContents> getApiContentsByMenuId(@PathVariable Long menuId) {
		return apiContentsRepository.findByMenuId(menuId);
	}

	@PostMapping("/menus/{menuId}/api_contents")
	public ApiContents addApiContents(@PathVariable Long menuId,
	                        @Valid @RequestBody ApiContents apiContents) {
		return menuRepository.findById(menuId)
						.map(menu -> {
							apiContents.setMenu(menu);
							return apiContentsRepository.save(apiContents);
						}).orElseThrow(() -> new ResourceNotFoundException("Menu not found with id " + menuId));
	}

	@PutMapping("/menus/{menuId}/api_contents/{apiContentsId}")
	public ApiContents updateApiContents(@PathVariable Long menuId,
	                           @PathVariable Long apiContentsId,
	                           @Valid @RequestBody ApiContents apiContentsRequest) {
		if(!menuRepository.existsById(menuId)) {
			throw new ResourceNotFoundException("Menu not found with id " + menuId);
		}

		return apiContentsRepository.findById(apiContentsId)
						.map(apiContents -> {
							apiContents.setContent(apiContentsRequest.getContent());
							return apiContentsRepository.save(apiContents);
						}).orElseThrow(() -> new ResourceNotFoundException("ApiContents not found with id " + apiContentsId));
	}

	@DeleteMapping("/menus/{menuId}/api_contents/{apiContentsId}")
	public ResponseEntity<?> deleteApiContents(@PathVariable Long menuId,
	                                      @PathVariable Long apiContentsId) {
		if(!menuRepository.existsById(menuId)) {
			throw new ResourceNotFoundException("Menu not found with id " + menuId);
		}

		return apiContentsRepository.findById(apiContentsId)
						.map(apiContents -> {
							apiContentsRepository.delete(apiContents);
							return ResponseEntity.ok().build();
						}).orElseThrow(() -> new ResourceNotFoundException("ApiContents not found with id " + apiContentsId));

	}
}
