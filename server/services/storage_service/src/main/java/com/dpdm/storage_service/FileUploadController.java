package com.dpdm.storage_service;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.dpdm.storageapi.controllers.FilesApiController;
import com.dpdm.storageapi.model.FileResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@CrossOrigin
@RestController
public class FileUploadController extends FilesApiController{

	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService,NativeWebRequest request) {
		super(request);
		this.storageService = storageService;
	}

	@Override
	@GetMapping("/files")
	public ResponseEntity<List<FileResponse>> filesGet() {


		List<FileResponse> files = storageService.loadAll().map(file -> {
			try {
				FileResponse fileResponse = new FileResponse();
				fileResponse.setFileName(file.getFileBlobId());
				fileResponse.setOwner(file.getUID());
				fileResponse.setDownloadLink( storageService.loadAsResource(file.getFileBlobId()).getURL().toString());
				return fileResponse;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());

		return new ResponseEntity<List<FileResponse>>(files,HttpStatus.OK);
	}

	// @GetMapping("/files/{filename:.+}")
	// @ResponseBody
	// public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

	// 	Resource file = storageService.loadAsResource(filename);
	// 	return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
	// 			"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	// }

	@Override
	@PostMapping("/files")
	public ResponseEntity<Void> filesPost(@RequestHeader(value="userToken", required=true) String userToken,@Valid @RequestPart(value = "filename", required = false) MultipartFile filename) {

		
		storageService.store(filename,null);
		
		return ResponseEntity.ok().build();
	}

	// @ExceptionHandler(StorageFileNotFoundException.class)
	// public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
	// 	return ResponseEntity.notFound().build();
	// }

}