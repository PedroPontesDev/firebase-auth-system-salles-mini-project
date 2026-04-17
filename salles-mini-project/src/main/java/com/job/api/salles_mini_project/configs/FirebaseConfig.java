package com.job.api.salles_mini_project.configs;

import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.context.annotation.Configuration;

import com.google.api.client.util.Value;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {

	@Value("${firebase.credentials.path}")
	private String firebaseCredentialsPath;

	@PostConstruct
	public void init() {
		try (InputStream serviceAccount = new FileInputStream(firebaseCredentialsPath)) {

			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

			if (FirebaseApp.getApps().isEmpty()) {
				FirebaseApp.initializeApp();
			}

		} catch (Exception e) {
			throw new RuntimeException("Erro ao inicializar Firebase Admin SDK", e);
		}
	}

}
