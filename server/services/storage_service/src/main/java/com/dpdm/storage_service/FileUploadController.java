package com.dpdm.storage_service;

import java.io.IOException;
import java.net.URL;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class FileUploadController {

	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	private class FileResponse{

		String fileName;
		String author;
		URL downloadLink;

		public FileResponse(String fileName, String author, URL downloadLink) {
			this.fileName = fileName;
			this.author = author;
			this.downloadLink = downloadLink;
		}
		public FileResponse(){}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public URL getDownloadLink() {
			return downloadLink;
		}
		public void setDownloadLink(URL downloadLink) {
			this.downloadLink = downloadLink;
		}

		

	}

	@GetMapping("/files")
	public ResponseEntity<FileResponse[]> listUploadedFiles() throws IOException {


		FileResponse files[] = storageService.loadAll().map(file -> {
			try {
				return new FileResponse(file.getFileBlobId(),file.getUID(), storageService.loadAsResource(file.getFileBlobId()).getURL());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}).toArray(FileResponse[]::new);

		return new ResponseEntity<FileResponse[]>(files,HttpStatus.OK);
	}

	// @GetMapping("/files/{filename:.+}")
	// @ResponseBody
	// public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

	// 	Resource file = storageService.loadAsResource(filename);
	// 	return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
	// 			"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	// }

	@PostMapping("/file")
	public ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile file,@RequestHeader(value="userToken", required=true) String userToken) {

		
		storageService.store(file,null);
		
		return ResponseEntity.ok().body("success");
	}

	// @ExceptionHandler(StorageFileNotFoundException.class)
	// public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
	// 	return ResponseEntity.notFound().build();
	// }

}