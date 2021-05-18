package com.dpdm.storage_service;

import java.net.URI;
import java.util.stream.Stream;

import com.google.firebase.auth.FirebaseToken;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void init();

	void store(MultipartFile file,FirebaseToken user);

	Stream<MyFile> loadAll();

	URI load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();

}